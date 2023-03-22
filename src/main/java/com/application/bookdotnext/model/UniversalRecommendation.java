package com.application.bookdotnext.model;

import java.security.Timestamp;

public class UniversalRecommendation {
  protected int universalRecomId;
  protected Timestamp created;
  protected Votes vote;
  protected BookInfo bookInfo;

  public UniversalRecommendation(int universalRecomId, Timestamp created, Votes vote, BookInfo bookInfo) {
    super();
    this.universalRecomId = universalRecomId;
    this.created = created;
    this.vote = vote;
    this.bookInfo = bookInfo;
  }

  public UniversalRecommendation(int universalRecomId) {
    this.universalRecomId = universalRecomId;
  }

  public UniversalRecommendation(Timestamp created, Votes vote, BookInfo bookInfo) {
    this.created = created;
    this.vote = vote;
    this.bookInfo = bookInfo;
  }

  public int getUniversalRecomId() {
    return universalRecomId;
  }

  public void setUniversalReomId(int universalRecomId) {
    this.universalRecomId = universalRecomId;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public Votes getVote() {
    return vote;
  }

  public void setVote(Votes vote) {
    this.vote = vote;
  }

  public BookInfo getBookInfo() {
    return bookInfo;
  }

  public void setBookInfo(BookInfo bookInfo) {
    this.bookInfo = bookInfo;
  }




}

