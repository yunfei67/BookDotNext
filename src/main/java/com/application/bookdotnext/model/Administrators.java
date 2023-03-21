package com.application.bookdotnext.model;
import java.sql.Timestamp;

public class Administrators extends Persons {
	protected Timestamp lastLogin;

	public Administrators(int userId, String userName, String firstName, String lastName, String password,
			boolean permission, Timestamp lastLogin) {
		super(userId, userName, firstName, lastName, password, permission);
		this.lastLogin = lastLogin;
	}
	public Administrators( String userName, String firstName, String lastName, String password,
			boolean permission, Timestamp lastLogin) {
		super( userName, firstName, lastName, password, permission);
		this.lastLogin = lastLogin;
	}
	
	public Administrators(String userName, String firstName, String lastName, Timestamp lastLogin) {
		super( userName, firstName, lastName);
		this.lastLogin = lastLogin;
	}
	public Administrators(String userName) {
		super( userName);
	}

	public Administrators(int userId) {
		super(userId);
	
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	
}