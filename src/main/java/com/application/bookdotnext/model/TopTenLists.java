package com.application.bookdotnext.model;
import java.util.Date;


public class TopTenLists {
	protected int topTenListId;
	protected Users user;
	protected BookInfo bookInfo;
	protected Date created;


	public TopTenLists(int topTenListId, Users user, BookInfo bookInfo, Date created) {
		this.topTenListId = topTenListId;
		this.user = user;
		this.bookInfo = bookInfo;
		this.created = created;
	}


	public TopTenLists(Users user, BookInfo bookInfo, Date created) {
		this.user = user;
		this.bookInfo = bookInfo;
		this.created = created;
	}


	public TopTenLists(int topTenListId) {
		this.topTenListId = topTenListId;
	}


	public int getTopTenListId() {
		return topTenListId;
	}


	public void setTopTenListId(int topTenListId) {
		this.topTenListId = topTenListId;
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
