package com.application.bookdotnext.model;
import java.util.Date;

public class Administrators extends Persons {
	protected Date lastLogin;

	public Administrators(int userId, String userName, String firstName, String lastName, String password,
			boolean permission, Date lastLogin) {
		super(userId, userName, firstName, lastName, password, permission);
		this.lastLogin = lastLogin;
	}
	
	public Administrators(String userName, String firstName, String lastName, Date lastLogin) {
		super( userName, firstName, lastName);
		this.lastLogin = lastLogin;
	}

	public Administrators(int userId) {
		super(userId);
	
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	
}