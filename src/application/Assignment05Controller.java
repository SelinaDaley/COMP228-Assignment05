package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//import com.companyname.jdbc.InputHelper;
//import com.companyname.jdbc.MovieListController;
import com.companyname.jdbc.beans.Game;
//import com.companyname.jdbc.beans.Movie;


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
	ComboBox comboBox;// = new ComboBox();
	
	public void selectButtonHandler(ActionEvent event) throws SQLException {
		
		table = tableComboBox.getValue().toString();
		tableNameLabel.setText(table + " table selected");
		
		tableData.getChildren().clear();
		tableData2.getChildren().clear();
		displayButton.setDisable(false);
		addButton.setDisable(false);
		updateButton.setDisable(false);
		removeButton.setDisable(false);
	}
	
	public void displayButtonHandler(ActionEvent event) throws SQLException {
		
		tableDataLabel.setText(table + " Table Data ");
		
		if(table.contentEquals("game"))
		{
			tableData.getChildren().clear();
			tableData2.getChildren().clear();
			GameListController.displayAllRows(tableData, table);
		}		
	}
	
	public void addButtonHandler(ActionEvent event) {
		
		tableData2.getChildren().clear();
		messageText.setText("");
		tableActionLabel.setText("Add a row to the " + table + " table ");	
		action = "add";
		
		// Title
		tableData2.add(new Label("Game Title: "), 0, 0);
		tableData2.add(textField, 1, 0);		
				
		Button button = new Button("Submit");
		tableData2.add(button, 1, 2);
		button.setOnAction(this::submitButtonHandler);		
	}
	
	public void updateButtonHandler(ActionEvent event) {
		
		tableData2.getChildren().clear();
		messageText.setText("");
		tableActionLabel.setText("Update a row in the " + table + " table ");
		action = "update";
		
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
	
	public void removeButtonHandler(ActionEvent event) {
		
		tableData2.getChildren().clear();
		messageText.setText("");
		tableActionLabel.setText("Remove a row in the " + table + " table ");
		action = "remove";
		
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
}
