package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;

import application.DBConfig;

import com.companyname.jdbc.beans.Player_And_Game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PlayerAndGameListController {

	@FXML private Text messageText;
	@FXML private TextField nameTextField;
	
	// Buttons
	@FXML private Button displayButton;
	@FXML private Button addButton;
	@FXML private Button updateButton;
	@FXML private Button removeButton;
	
	// READ ALL DATA
	public static void displayAllRows(GridPane tableData, String table) throws SQLException
	{
		String SQLQuery = "SELECT * FROM " + table;
		
		try(
				Connection connection = DBConfig.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
				ResultSet resultSet = statement.executeQuery(SQLQuery);)
		{
			while(resultSet.next())
			{				
				int id = resultSet.getInt("player_game_id");
				int gameId = resultSet.getInt("game_id");
				int playerId = resultSet.getInt("player_id");
				Date date = resultSet.getDate("date");
				DateFormat dateFormat = DateFormat.getDateInstance();
				String formattedDate = dateFormat.format(date);
				int score = resultSet.getInt("score");
				
				tableData.add(new Label(id + " " + gameId + " " + playerId + " " + formattedDate + " " + score), 0, id-1);
				
			}
		}
		catch(SQLException exception)
		{
			DBConfig.displayException(exception);				
		}
	}
	
	public static void displayAllRows2(GridPane tableData, String table) throws SQLException
	{
		String SQLQuery = "SELECT pg.player_game_id, g.game_title, p.first_name, pg.date, pg.score "+
				"FROM player_and_game As pg "+
				"LEFT JOIN game AS g ON g.game_id = pg.game_id "+
				"LEFT JOIN player AS p ON p.player_id = pg.player_id";
		
		try(
				Connection connection = DBConfig.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
				ResultSet resultSet = statement.executeQuery(SQLQuery);)
		{
			while(resultSet.next())
			{				
				int id = resultSet.getInt("pg.player_game_id");
				
				String gameTitle = resultSet.getString("game_title");
				String firstName = resultSet.getString("first_name");
				
				Date date = resultSet.getDate("date");
				DateFormat dateFormat = DateFormat.getDateInstance();
				String formattedDate = dateFormat.format(date);
				int score = resultSet.getInt("score");
				
				tableData.add( new Label(firstName + " played " + gameTitle + " on " + formattedDate + " and has a score of " + score), 0 , id-1);				
			}
		}
		catch(SQLException exception)
		{
			DBConfig.displayException(exception);				
		}
	}
	
}
