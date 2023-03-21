package com.application.bookdotnext.model;
import java.util.Date;

public class Users extends Persons {
	protected Date dob;

	public Users(int userId, String userName, String firstName, String lastName, String password, boolean permission, Date dob) {
		super(userId, userName, firstName, lastName, password, permission);
		this.dob = dob;
		// TODO Auto-generated constructor stub
	}
	public Users(int userId, String userName, String firstName, String lastName, String password) {
		super(userId, userName, firstName, lastName, password);
		
		// TODO Auto-generated constructor stub
	}
	
	public Users( String userName, String firstName, String lastName, String password) {
		super( userName, firstName, lastName, password);
		

	}
	public Users(String userName, String firstName, String lastName,Date dob) {
		super(userName, firstName, lastName);
		this.dob = dob;
		// TODO Auto-generated constructor stub
	}
	public Users(String userName, String firstName, String lastName,Date dob,String password) {
		super(userName, firstName, lastName,password);
		this.dob = dob;
		// TODO Auto-generated constructor stub
	}


	public Users(int userId) {
		super(userId);
		
		// TODO Auto-generated constructor stub
	}
	public Users(String userName) {
		super(userName);

		// TODO Auto-generated constructor stub
	}

	public Users(int userId, String userName, String firstName, String lastName, String password, Date dob) {
		super(userId, userName, firstName, lastName, password);
		this.dob = dob;
	}


	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
	
	
	
}