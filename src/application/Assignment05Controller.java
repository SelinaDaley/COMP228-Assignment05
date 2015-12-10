package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class Assignment05Controller {

	@FXML private Text messageText;
	@FXML private TextField nameTextField;
	@FXML private GridPane tableData;// = new GridPane();
	@FXML private ScrollPane tableData2;
	@FXML private ComboBox tableComboBox;
	
	// Buttons
	@FXML private Button displayButton;
	@FXML private Button addButton;
	@FXML private Button updateButton;
	@FXML private Button removeButton;

	String table;
	
	public void displayButtonHandler(ActionEvent event) throws SQLException {
		
		table = tableComboBox.getValue().toString();
		
		if(table.contentEquals("game"))
		{
			GameListController.displayAllRows(tableData, table);
		}
		else if(table.contentEquals("player and game"))
		{
			
		}
		else if(table.contentEquals("player"))
		{
			//PlayerListController.displayAllRows(tableData, table);
		}
		else
		{
			
		}
		
	}
	
	public void addButtonHandler(ActionEvent event) {
		messageText.setText("Add button pressed");
	}
	
	public void updateButtonHandler(ActionEvent event) {
		messageText.setText("Update button pressed");
	}
	
	public void removeButtonHandler(ActionEvent event) {
		messageText.setText("Remove button pressed");
	}
	
}
