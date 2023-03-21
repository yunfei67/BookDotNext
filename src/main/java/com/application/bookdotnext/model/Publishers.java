package com.application.bookdotnext.model;

public class Publishers {
	
	protected String publisherName;
	protected String countryCode;
	

	public Publishers(String publisherName, String countryCode) {
		this.publisherName = publisherName;
		this.countryCode = countryCode;
	}


	public String getPublisherName() {
		return publisherName;
	}


	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
