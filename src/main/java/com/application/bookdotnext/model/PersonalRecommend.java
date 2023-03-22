package com.application.bookdotnext.model;

import java.util.Date;

public class PersonalRecommend {
	protected int recommendId;
	protected Date created;
	protected Users user;
	protected BookInfo bookInfo;
	
	
	public PersonalRecommend(int recommendId, Date created, Users user, BookInfo bookInfo) {
		this.recommendId = recommendId;
		this.created = created;
		this.user = user;
		this.bookInfo = bookInfo;
	}


	public PersonalRecommend(Date created, Users user, BookInfo bookInfo) {
		this.created = created;
		this.user = user;
		this.bookInfo = bookInfo;
	}


	public PersonalRecommend(int recommendId) {
		this.recommendId = recommendId;
	}


	public int getRecommendId() {
		return recommendId;
	}


	public void setRecommendId(int recommendId) {
		this.recommendId = recommendId;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public BookInfo getBookInfo() {
		return bookInfo;
	}


	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	
	
	
	
}
