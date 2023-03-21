package com.application.bookdotnext.model;

public class Author {
	
	protected String authorName;
	protected String countryCode;
	
	public Author(String authorName, String countryCode) {
		this.authorName = authorName;
		this.countryCode = countryCode;
	}

	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
}
