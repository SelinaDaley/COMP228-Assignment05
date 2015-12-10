package com.companyname.jdbc.beans;

import java.sql.Date;

public class Game 
{
	// PRIVATE INSTANCE VARIABLES
	private int _id;
	private String _title;
	
	// PUBLIC PROPERTIES GETTERS
	public int getId() {
		return this._id;
	}
	public String getName() {
		return this._title;
	}
		
	// PUBLIC PROPERTIES SETTERS
	public void setId(int id) {
		this._id = id;
	}
	public void setName(String title) {
		this._title = title;
	}
	
}
