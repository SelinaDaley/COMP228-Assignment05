package com.companyname.jdbc.beans;

public class Player {

	// PRIVATE INSTANCE VARIABLES
	private int _id;
	private String _firstName;
	private String _lastName;
	private String _address;
	private String _postalCode;
	private String _province;
	private String _phoneNumber;
	
	// PUBLIC PROPERTIES GETTERS
	public int getId() {
		return this._id;
	}
	public String getFirstName() {
		return this._firstName;
	}
	public String getLastName() {
		return this._lastName;
	}
	public String getAddress() {
		return this._address;
	}
	public String getPostalCode() {
		return this._postalCode;
	}
	public String getProvince() {
		return this._province;
	}
	public String getPhoneNumber() {
		return this._phoneNumber;
	}
			
	// PUBLIC PROPERTIES SETTERS
	public void setId(int id) {
		this._id = id;
	}
	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}
	public void setLastName(String lastName) {
		this._lastName = lastName;
	}
	public void setAddress(String address) {
		this._address = address;
	}
	public void setPostalCode(String postalCode) {
		this._postalCode = postalCode;
	}
	public void setProvince(String province) {
		this._province = province;
	}
	public void setPhoneNumber(String phoneNumber) {
		this._phoneNumber = phoneNumber;
	}	
}
