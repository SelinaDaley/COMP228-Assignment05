package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DBConfig;

import com.companyname.jdbc.beans.Game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	
	// GET ROW COUNT
	public static ObservableList<String> rowCount(String table) throws SQLException
	{
		String SQLQuery = "SELECT * FROM " + table;
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try(
				Connection connection = DBConfig.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
				ResultSet resultSet = statement.executeQuery(SQLQuery);)
		{
			while(resultSet.next())
			{				
				int id = resultSet.getInt("game_id");				
				list.add(""+id);	
			}
			return list;
		}
		catch(SQLException exception)
		{
			DBConfig.displayException(exception);				
		}
		
		return list;
	}
	
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
	
	// READ FROM ONE ROW
	public static Game getRow(int id, String table) throws SQLException
	{
		String SQLQuery = "SELECT * FROM " + table + " WHERE game_id = ?";
		ResultSet resultSet = null;
				
		try(Connection connection = DBConfig.getConnection();
		PreparedStatement statement = connection.prepareStatement(SQLQuery);)
		{
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
		
			//check to see if we received any data
			if(resultSet.next()) {
				Game game =  new Game();
				game.setId(id);
				game.setTitle(resultSet.getString("game_title"));
				return game;
			} else {
				return null;
			}
		}
		catch(SQLException exception) 
		{
			DBConfig.displayException(exception);
			return null;
		} 
		finally 
		{
			if(resultSet != null) 
			{
				resultSet.close();
			}
		}
	}

	
	// INSERT ONE ROW
	public static boolean insertRow(Game game) throws Exception {
		String SQLQuery = "INSERT into game " +
	                      "(game_title) " +
				          "VALUES (?)";
				
		ResultSet keys = null;
		try(
			Connection connection = DBConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
			){
					
			statement.setString(1, game.getTitle());
			//get the number of return rows
			int affected = statement.executeUpdate();
			if(affected == 1) {
				keys = statement.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				game.setId(newKey);
			} else {
				System.err.println("No Rows Affected");
			}
				
					
		} catch(SQLException exception) {
			DBConfig.displayException(exception);
			return false;
		} finally {
			if(keys != null) {
				keys.close();
			}
		}
			
		return true;
	}
	
	// UPDATE ONE ROW
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
	
	// DELETE ONE ROW
	public static boolean deleteRow(int id) throws Exception
	{
		String SQLQuery = "DELETE from game WHERE game_id = ?";
		
		try(
				Connection connection = DBConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQLQuery);)
		{
			statement.setInt(1, id);
			int affected = statement.executeUpdate();
			
			if(affected == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SQLException exception)
		{
			DBConfig.displayException(exception);
			return false;
		}
	} //End of DELETE
	
}
