package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DBConfig;

import com.companyname.jdbc.beans.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

//Controller class for our Player Model - Java Bean
public class PlayerListController {

	@FXML private Text messageText;
	@FXML private TextField nameTextField;
	
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
				int id = resultSet.getInt("player_id");				
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
				int id = resultSet.getInt("player_id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String address = resultSet.getString("address");
				String postalCode = resultSet.getString("postal_code"); 
				String province = resultSet.getString("province");
				String phoneNumber = resultSet.getString("phone_number");
				
				tableData.add( new Label("Player " + id + ": " + firstName + " " + lastName +", "+ address +"  "+ postalCode +"  "+ province +", ("+ phoneNumber+")"), 0, id-1);
			}
		}
		catch(SQLException exception)
		{
			DBConfig.displayException(exception);				
		}
	}
	
	// READ FROM ONE ROW
	public static Player getRow(int id, String table) throws SQLException
	{
		String SQLQuery = "SELECT * FROM " + table + " WHERE player_id = ?";
		ResultSet resultSet = null;
					
		try(Connection connection = DBConfig.getConnection();
		PreparedStatement statement = connection.prepareStatement(SQLQuery);)
		{
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			//check to see if we received any data
			if(resultSet.next()) {
				Player player =  new Player();
				player.setId(id);
				player.setFirstName(resultSet.getString("first_name"));
				player.setLastName(resultSet.getString("last_name"));
				player.setAddress(resultSet.getString("address"));
				player.setPostalCode(resultSet.getString("postal_code"));
				player.setProvince(resultSet.getString("province"));
				player.setPhoneNumber(resultSet.getString("phone_number"));
				return player;
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
	public static boolean insertRow(Player player) throws Exception {
		String SQLQuery = "INSERT into player " +
	                      "(first_name, last_name, address, postal_code, province, phone_number) " +
				          "VALUES (?,?,?,?,?,?)";
				
		ResultSet keys = null;
		try(
			Connection connection = DBConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
			){
					
			statement.setString(1, player.getFirstName());
			statement.setString(2, player.getLastName());
			statement.setString(3, player.getAddress());
			statement.setString(4, player.getPostalCode());
			statement.setString(5, player.getProvince());
			statement.setString(6, player.getPhoneNumber());
			//get the number of return rows
			int affected = statement.executeUpdate();
			if(affected == 1) {
				keys = statement.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				player.setId(newKey);
			} 
			else 
			{
				System.err.println("No Rows Affected");
			}		
		} 
		catch(SQLException exception) 
		{
			DBConfig.displayException(exception);
			return false;
		} 
		finally 
		{
			if(keys != null)
			{
				keys.close();
			}
		}			
		return true;
	}
	
	// UPDATE ONE ROW
	public static boolean updateRow(Player player) throws Exception{
		
		String SQLQuery = "UPDATE player SET " +
	        "first_name = ?, last_name = ?, address = ?, postal_code = ?, province = ?, phone_number = ?" +
	        "WHERE player_id = ?";
			
		try(
				Connection connection = DBConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQLQuery);)
		{
			statement.setString(1, player.getFirstName());
			statement.setString(2, player.getLastName());
			statement.setString(3, player.getAddress());
			statement.setString(4, player.getPostalCode());
			statement.setString(5, player.getProvince());
			statement.setString(6, player.getPhoneNumber());
			statement.setInt(7, player.getId());
					
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
		String SQLQuery = "DELETE from player WHERE player_id = ?";
		
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
