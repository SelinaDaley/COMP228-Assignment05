package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DBConfig;
import com.companyname.jdbc.beans.Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

//Controller class for our Game Model - Java Bean
public class GameListController {

	@FXML private Text messageText;
	@FXML private TextField nameTextField;
	//@FXML private GridPane tableData;
	
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
				StringBuffer buffer = new StringBuffer();
				buffer.append("Game " + resultSet.getInt("game_id") + ": ");
				buffer.append(resultSet.getString("game_title"));
				
				System.out.println(buffer.toString());
				
				int id = resultSet.getInt("game_id");
				String title = resultSet.getString("game_title");
				
				tableData.add( new Label("Game " + id + ": " + title), 0, id-1);
			}
		}
		catch(SQLException exception)
		{
			DBConfig.displayException(exception);				
		}
	}
	
	// UPDATE ON ROW
	public static boolean updateRow(Game game) throws Exception{
	
		String SQLQuery = "UPDATE game SET " +
	        "game_title = ?" +
	        "WHERE game_id = ?";
			
		try(
				Connection connection = DBConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQLQuery);)
		{
			statement.setString(1, game.getTitle());
			statement.setInt(2, game.getId());
				
			int affected = statement.executeUpdate();
			if(affected == 1) {
				return true;
			} else {
				return false;
			}
		}
		catch(SQLException exception)
		{
			DBConfig.displayException(exception);
			return false;
		}
	}
	
}
