package com.companyname.jdbc.beans;

import java.sql.Date;

public class Player_And_Game {

	// PRIVATE INSTANCE VARIABLES
	private int _id;
	private int _gameId;
	private int _playerId;
	private Date _date;
	private int _score;
	
	// PUBLIC PROPERTIES GETTERS
	public int getId() {
		return this._id;
	}
	public int getGameId() {
		return this._gameId;
	}
	public int getPlayerId() {
		return this._playerId;
	}
	public Date getDate() {
		return this._date;
	}
	public int getScore() {
		return this._score;
	}
	
	// PUBLIC PROPERTIES SETTERS
	public void setId(int id) {
		this._id = id;
	}
	public void setGameId(int gameId) {
		this._gameId = gameId;
	}
	public void setPlayerId(int playerId) {
		this._playerId = playerId;
	}
	public void setDate(Date date) {
		this._date = date;
	}
	public void setScore(int score) {
		this._score = score;
	}
	
}
