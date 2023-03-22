

package com.application.bookdotnext.model;

import java.util.Date;

public class BookReview {
	protected int reviewId;
	protected double reviewScore;
	protected String content;
	protected Date created;
	protected Users user;
	protected BookInfo bookInfo;
	

	
	public BookReview(int reviewId, double reviewScore, String content, Date created, Users user, BookInfo bookInfo) {
		super();
		this.reviewId = reviewId;
		this.reviewScore = reviewScore;
		this.content = content;
		this.created = created;
		this.user = user;
		this.bookInfo = bookInfo;
	}


	public BookReview(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public BookReview(double reviewScore, String content, Date created, Users user, BookInfo bookInfo) {
		super();
		this.reviewScore = reviewScore;
		this.content = content;
		this.created = created;
		this.user = user;
		this.bookInfo = bookInfo;
	}


	
	public BookReview(int reviewId, double reviewScore, String content, Date created) {
		super();
		this.reviewId = reviewId;
		this.reviewScore = reviewScore;
		this.content = content;
		this.created = created;
	}


	public int getReviewId() {
		return reviewId;
	}


	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}


	public double getReviewScore() {
		return reviewScore;
	}


	public void setReviewScore(double reviewScore) {
		this.reviewScore = reviewScore;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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


	@Override
	public String toString() {
		return "BookReview{" +
				"reviewId=" + reviewId +
				", reviewScore=" + reviewScore +
				", content='" + content + '\'' +
				", userId=" + user.userId +
				", bookTitle=" + bookInfo.getBookTitle() +
				'}';
	}
}
