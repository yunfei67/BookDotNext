package com.application.bookdotnext.model;

import java.sql.Timestamp;
/*
Yang Chen
 */
public class Votes {
  protected int voteId;

  public Votes(Timestamp created, int bookId, int userId) {
    this.created = created;
    this.bookId = bookId;
    this.userId = userId;
  }

  protected Timestamp created;

  protected int bookId;
  protected int userId;

  public Votes(int voteId, Timestamp created, int bookId, int userId) {
    this.voteId = voteId;
    this.created = created;
    this.bookId = bookId;
    this.userId = userId;
  }

  public int getVoteId() {
    return voteId;
  }

  public void setVoteId(int voteId) {
    this.voteId = voteId;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}
