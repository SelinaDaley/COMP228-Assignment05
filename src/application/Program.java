package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import com.companyname.jdbc.beans.Game;

public class Program {
	public static void main(String[] args) throws SQLException{
		//GameListController.displayAllRows();
		
		// UPDATE *************************************************************************************************
		System.out.println("-------------------------");
		System.out.println("Update a Row of Data");
		System.out.println("-------------------------");
		int gameId = InputHelper.getIntegerInput("Select a row to update: ");
		
		try
		{
			Game updateGame = GameListController.getRow(gameId, "");
			if(updateGame == null)
			{
				System.err.println("Row not found");
				return;
			}
			
			String updateTitle = InputHelper.getStringInput("Enter a new Game Title: ");
			updateGame.setTitle(updateTitle);		
			
			try 
			{
				if(GameListController.updateRow(updateGame))
				{
					System.out.println("Row was successfully updated");
				}
			}
			catch (Exception exception) 
			{
				System.err.println(exception);
			} //End of UPDATE
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		
	}
}
