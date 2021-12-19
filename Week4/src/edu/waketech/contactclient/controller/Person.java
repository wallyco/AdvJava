package edu.waketech.contactclient.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

	private final StringProperty ID;
	private final StringProperty firstName;
	private final StringProperty address;
	private final StringProperty cellPhone;
	private final StringProperty email;

	public Person(String ID, String fName, String address, String cellPhone, String email) {
		this.ID = new SimpleStringProperty(ID);
		this.firstName = new SimpleStringProperty(fName);
		this.address = new SimpleStringProperty(address);
		this.cellPhone = new SimpleStringProperty(cellPhone);
		this.email = new SimpleStringProperty(email);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty firstNameProperty() {
		return this.firstName;
	}

	public String getID() {
		return ID.get();
	}

	public void setID(String ID) {
		this.ID.set(ID);
	}
	
	public StringProperty IDProperty() {
		return ID;
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address.set(address);
	}
	
	public StringProperty addressProperty() {
		return address;
	}

	public String getCellPhone() {
		return cellPhone.get();
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone.set(cellPhone);
	}
	
	public StringProperty cellPhoneProperty() {
		return cellPhone;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);;
	}
	public StringProperty emailProperty() {
		return email;
	}


}
