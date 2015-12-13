package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.companyname.jdbc.beans.Game;
import com.companyname.jdbc.beans.Player_And_Game;
import com.companyname.jdbc.beans.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Assignment05Controller {

	@FXML private Text messageText;
	@FXML private TextField nameTextField;
	@FXML private GridPane tableData;// = new GridPane();
	@FXML private GridPane tableData2;
	@FXML private ComboBox tableComboBox;
	@FXML private AnchorPane mainPage;
	@FXML private ScrollPane container2;
	// Labels
	@FXML private Label tableNameLabel;
	@FXML private Label tableDataLabel;
	@FXML private Label tableActionLabel;
	
	// Buttons
	@FXML private Button selectButton;
	@FXML private Button displayButton;
	@FXML private Button addButton;
	@FXML private Button updateButton;
	@FXML private Button removeButton;
	
	String table = "game";
	String action;
	String title;
	TextField textField = new TextField();
	TextField textField2 = new TextField();
	TextField textField3 = new TextField();
	TextField textField4 = new TextField();
	TextField textField5 = new TextField();
	ComboBox comboBox;
	ComboBox comboBox2;
	public void selectButtonHandler(ActionEvent event) throws SQLException {
		
		table = tableComboBox.getValue().toString();
		tableNameLabel.setText(table + " table selected");
		
		tableData.getChildren().clear();
		tableData2.getChildren().clear();
		displayButton.setDisable(false);
		addButton.setDisable(false);
		updateButton.setDisable(false);
		removeButton.setDisable(false); 
		
		if(table.contentEquals("player_and_game"))
		{
			addButton.setDisable(true);
			updateButton.setDisable(true);
			removeButton.setDisable(true); 
		}
		
	}
	
	public void displayButtonHandler(ActionEvent event) throws SQLException {
		
		tableDataLabel.setText(table + " Table Data ");
		tableData.getChildren().clear();
		tableData2.getChildren().clear();
		
		if(table.contentEquals("game"))
		{			
			GameListController.displayAllRows(tableData, table);
		}		
		else if(table.contentEquals("player"))
		{
			PlayerListController.displayAllRows(tableData, table);
		}	
		else if(table.contentEquals("player_and_game"))
		{
			PlayerAndGameListController.displayAllRows2(tableData, table);
		}
	}
	
	public void addButtonHandler(ActionEvent event) {
		
		tableData2.getChildren().clear();
		messageText.setText("");
		tableActionLabel.setText("Add a row to the " + table + " table ");	
		action = "add";
		
		if(table.contentEquals("game"))
		{
			// Title
			tableData2.add(new Label("Game Title: "), 0, 0);
			tableData2.add(textField, 1, 0);
			
			// Button
			Button button = new Button("Submit");
			tableData2.add(button, 1, 2);
			button.setOnAction(this::submitButtonHandler);
		}
		else if(table.contentEquals("player"))
		{
			// First Name
			tableData2.add(new Label("First Name: "), 0, 0);
			tableData2.add(textField, 1, 0);
			
			// Last Name
			tableData2.add(new Label("Last Name: "), 0, 1);
			tableData2.add(textField2, 1, 1);
			
			// Address
			tableData2.add(new Label("Address: "), 0, 2);
			tableData2.add(textField3, 1, 2);
			
			// Postal Code
			tableData2.add(new Label("Postal Code: "), 0, 3);
			tableData2.add(textField4, 1, 3);
			
			// Province
			tableData2.add(new Label("Province: "), 0, 4);
			comboBox2 = new ComboBox();
			tableData2.add(comboBox2, 1, 4);	
			ObservableList<String> list = FXCollections.observableArrayList();
			list.add("AB");list.add("BC");list.add("MB");list.add("NB");list.add("NL");
			list.add("NT");list.add("NS");list.add("NU");list.add("ON");list.add("PE");
			list.add("QC");list.add("SK");list.add("YT");			
			comboBox2.setItems(list); 
			comboBox2.setValue("ON");
			
			// Phone Number
			tableData2.add(new Label("Phone: "), 0, 5);
			tableData2.add(textField5, 1, 5);
			
			// Button
			Button button = new Button("Submit");
			tableData2.add(button, 1, 6);
			button.setOnAction(this::submitButtonHandler2);
		}				
				
	}
	
	public void updateButtonHandler(ActionEvent event) {
		
		tableData2.getChildren().clear();
		messageText.setText("");
		tableActionLabel.setText("Update a row in the " + table + " table ");
		action = "update";
		
		if(table.contentEquals("game"))
		{
			// ID
			tableData2.add(new Label("Game ID: "), 0, 0);	
			comboBox = new ComboBox();
			tableData2.add(comboBox, 1, 0);		
			
			GameListController control = new GameListController();
		
			try 
			{
				comboBox.setItems(control.rowCount(table));
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
			}
					
			comboBox.setValue("1");
			// Title
			tableData2.add(new Label("Game Title: "), 0, 1);
			tableData2.add(textField, 1, 1);		
					
			Button button = new Button("Submit");
			tableData2.add(button, 1, 3);
			
			button.setOnAction(this::submitButtonHandler);
		}
		else if(table.contentEquals("player"))
		{
			// ID
			tableData2.add(new Label("Player ID: "), 0, 0);	
			comboBox = new ComboBox();
			tableData2.add(comboBox, 1, 0);		
			
			PlayerListController control = new PlayerListController();
		
			try 
			{
				comboBox.setItems(control.rowCount(table));
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
			}				
			comboBox.setValue("1");
			
			// First Name
			tableData2.add(new Label("First Name: "), 0, 1);
			tableData2.add(textField, 1, 1);
						
			// Last Name
			tableData2.add(new Label("Last Name: "), 0, 2);
			tableData2.add(textField2, 1, 2);
			
			// Address
			tableData2.add(new Label("Address: "), 0, 3);
			tableData2.add(textField3, 1, 3);
						
			// Postal Code
			tableData2.add(new Label("Postal Code: "), 0, 4);
			tableData2.add(textField4, 1, 4);
					
			// Province
			tableData2.add(new Label("Province: "), 0, 5);
			comboBox2 = new ComboBox();
			tableData2.add(comboBox2, 1, 5);	
			ObservableList<String> list = FXCollections.observableArrayList();
			list.add("AB");list.add("BC");list.add("MB");list.add("NB");list.add("NL");
			list.add("NT");list.add("NS");list.add("NU");list.add("ON");list.add("PE");
			list.add("QC");list.add("SK");list.add("YT");			
			comboBox2.setItems(list); 
			comboBox2.setValue("ON");
						
			// Phone Number
			tableData2.add(new Label("Phone: "), 0, 6);
			tableData2.add(textField5, 1, 6);
						
			// Button
			Button button = new Button("Submit");
			tableData2.add(button, 1, 7);
			button.setOnAction(this::submitButtonHandler2);
		}
		
	}
	
	public void removeButtonHandler(ActionEvent event) {
		
		tableData2.getChildren().clear();
		messageText.setText("");
		tableActionLabel.setText("Remove a row in the " + table + " table ");
		action = "remove";
		
		if(table.contentEquals("game"))
		{
			// ID
			tableData2.add(new Label("Game ID: "), 0, 0);	
			comboBox = new ComboBox();
			tableData2.add(comboBox, 1, 0);	
			
			GameListController control = new GameListController();
			
			try 
			{
				comboBox.setItems(control.rowCount(table));
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
			}
					
			comboBox.setValue("1");
			
			Button button = new Button("Submit");
			tableData2.add(button, 1, 2);
			button.setOnAction(this::submitButtonHandler);
		}
		else if(table.contentEquals("player"))
		{
			// ID
			tableData2.add(new Label("Player ID: "), 0, 0);	
			comboBox = new ComboBox();
			tableData2.add(comboBox, 1, 0);	
			
			PlayerListController control = new PlayerListController();
			
			try 
			{
				comboBox.setItems(control.rowCount(table));
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
			}
					
			comboBox.setValue("1");
			
			Button button = new Button("Submit");
			tableData2.add(button, 1, 2);
			button.setOnAction(this::submitButtonHandler2);
		}
		
	}
	
	public void submitButtonHandler(ActionEvent event) {
		
		if(textField.getText().length() > 0 && action == "add")
		{
			try 
			{
				Game addGame = new Game();	
				addGame.setTitle(textField.getText());
				
				boolean result = GameListController.insertRow(addGame);
				
				if(result)
				{
					messageText.setText("New row with primary key " + addGame.getId() + " was inserted");
					textField.setText("");
					tableData.getChildren().clear();
					tableData2.getChildren().clear();
				}
				
			} 
			catch (Exception exception) 
			{
				System.err.println("Invalid Input");
			}
		}//add
			
		if(textField.getText().length() > 0 && action == "update")
		{
			try {
				
				int num = Integer.parseInt(comboBox.getValue().toString());
				Game updateGame = GameListController.getRow( num, table);
				
				if(updateGame == null)
				{
					messageText.setText("Row not found");
					return;
				}			
				
				updateGame.setTitle(textField.getText());		
				
				try 
				{
					if(GameListController.updateRow(updateGame))
					{
						messageText.setText("Row was successfully updated");
						textField.setText("");
						tableData.getChildren().clear();
						tableData2.getChildren().clear();
					} 
				}
				catch (Exception exception) 
				{
					System.err.println(exception);
				}				
				
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}		
		}//update
		
		if(action == "remove")
		{
			try 
			{
				int deleteId = Integer.parseInt(comboBox.getValue().toString());
				
				try {
					if(GameListController.deleteRow(deleteId))
					{
						messageText.setText("Row " + deleteId + " was successfully deleted");
						textField.setText("");
						tableData.getChildren().clear();
						tableData2.getChildren().clear();
					}
					else
					{
						messageText.setText("Nothing to delete");
					}
				} 
				catch (Exception exception) 
				{
					System.err.println(exception);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}			
		}//remove
		
	}	
	
	public void submitButtonHandler2(ActionEvent event) {
		
		if(textField.getText().length() > 0 && textField2.getText().length() > 0 && textField3.getText().length() > 0 
				&& textField4.getText().length() > 0 && textField5.getText().length() > 0 && action == "add")
		{
			try 
			{
				Player addPlayer = new Player();	
				addPlayer.setFirstName(textField.getText());		
				addPlayer.setLastName(textField2.getText());
				addPlayer.setAddress(textField3.getText());
				addPlayer.setPostalCode(textField4.getText());
				addPlayer.setProvince(comboBox2.getValue().toString());
				addPlayer.setPhoneNumber(textField5.getText());
				
				boolean result = PlayerListController.insertRow(addPlayer);
				
				if(result)
				{
					messageText.setText("New row with primary key " + addPlayer.getId() + " was inserted");
					textField.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
					textField5.setText("");
					tableData.getChildren().clear();
					tableData2.getChildren().clear();
				}
				
			} 
			catch (Exception exception) 
			{
				System.out.println(exception.getMessage());
				System.err.println("Invalid Input");
			}
		}//add
			
		if(textField.getText().length() > 0 && textField2.getText().length() > 0 && textField3.getText().length() > 0 
				&& textField4.getText().length() > 0 && textField5.getText().length() > 0 && action == "update")
		{
			try {
				
				int num = Integer.parseInt(comboBox.getValue().toString());
				Player updatePlayer = PlayerListController.getRow( num, table);
				
				if(updatePlayer == null)
				{
					messageText.setText("Row not found");
					return;
				}			
				
				updatePlayer.setFirstName(textField.getText());		
				updatePlayer.setLastName(textField2.getText());
				updatePlayer.setAddress(textField3.getText());
				updatePlayer.setPostalCode(textField4.getText());
				updatePlayer.setProvince(comboBox2.getValue().toString());
				updatePlayer.setPhoneNumber(textField5.getText());
				
				try 
				{
					if(PlayerListController.updateRow(updatePlayer))
					{
						messageText.setText("Row was successfully updated");
						textField.setText("");
						textField2.setText("");
						textField3.setText("");
						textField4.setText("");
						textField5.setText("");
						tableData.getChildren().clear();
						tableData2.getChildren().clear();
					} 
				}
				catch (Exception exception) 
				{
					System.err.println(exception);
				}				
				
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}		
		}//update
		
		if(action == "remove")
		{
			try 
			{
				int deleteId = Integer.parseInt(comboBox.getValue().toString());
				
				try {
					if(PlayerListController.deleteRow(deleteId))
					{
						messageText.setText("Row " + deleteId + " was successfully deleted");
						textField.setText("");
						tableData.getChildren().clear();
						tableData2.getChildren().clear();
					}
					else
					{
						messageText.setText("Nothing to delete");
					}
				} 
				catch (Exception exception) 
				{
					System.err.println(exception);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}			
		}//remove
		
	}
}
