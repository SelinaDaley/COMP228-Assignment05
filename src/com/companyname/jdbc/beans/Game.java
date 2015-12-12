package com.companyname.jdbc.beans;

public class Game 
{
	// PRIVATE INSTANCE VARIABLES
	private int _id;
	private String _title;
	
	// PUBLIC PROPERTIES GETTERS
	public int getId() {
		return this._id;
	}
	public String getTitle() {
		return this._title;
	}
		
	// PUBLIC PROPERTIES SETTERS
	public void setId(int id) {
		this._id = id;
	}
	public void setTitle(String title) {
		this._title = title;
	}
	
}
