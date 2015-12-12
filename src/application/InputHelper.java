package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javafx.scene.control.TextField;

public class InputHelper {

	// Get String from the console
		public static String getStringInput(String prompt)
		{
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.print(prompt);
			System.out.flush();
			
			try 
			{
				return input.readLine();
			} 
			catch (Exception exception) 
			{
				 return "Error: " + exception.getMessage();
			}
		}
	
	// Get Integer from the console
		public static int getIntegerInput(String prompt) throws NumberFormatException
		{
			String input = getStringInput(prompt);
			return Integer.parseInt(input);
		}
	
	// Get String from the console
		public static String getTextBoxInput(String prompt, TextField textBox)
		{
			//BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			//System.out.print(prompt);
			//System.out.flush();
			
			
			try 
			{
				//return input.readLine();
				return textBox.getText();
			} 
			catch (Exception exception) 
			{
				 return "Error: " + exception.getMessage();
			}
		}
	
}
