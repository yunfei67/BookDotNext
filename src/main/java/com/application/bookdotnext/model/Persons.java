
package com.application.bookdotnext.model;

/**
 * Persons is a simple, plain old java objects (POJO).
 * 
 * Persons/PersonsDao is the superclass for Administrators/AdministratorsDao and
 * us to create records in the Persons MySQL table without having the associated records
 * in the Administrators or BlogUsers MySQL tables. Alternatively, Persons could be an
 * interface or an abstract class, which would force a Persons record to be created only
 * if an Administrators or BlogUsers record is created, too.
 */
public class Persons {
	protected int userId;
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected String password;
	protected boolean permission;
	
	public Persons(int userId, String userName, String firstName, String lastName, String password,
			boolean permission) {
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.permission = permission;
	}
	public Persons( String userName, String firstName, String lastName, String password,
			boolean permission) {

		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.permission = permission;
	}
	public Persons(int userId, String userName, String firstName, String lastName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;

	}
	
	
	public Persons(String userName, String firstName, String lastName, String password) {

		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;

	}
	
	public Persons( String userName, String firstName, String lastName) {

		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	
	public Persons(int userId) {
		this.userId = userId;
	}
	public Persons(String userName) {
		this.userName= userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}
	
	
	
	
}
