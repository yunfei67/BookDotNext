package com.application.bookdotnext.model;

import java.sql.Timestamp;

public class SearchHistory {
  protected int searchId;
  protected Integer userId;
  protected int visitedBookId;
  protected Timestamp created;

  public SearchHistory(int searchId) {
    this.searchId = searchId;
  }

  public SearchHistory(Integer userId, int visitedBookId, Timestamp created) {
    this.userId = userId;
    this.visitedBookId = visitedBookId;
    this.created = created;
  }

  public SearchHistory(int searchId, Integer userId, int visitedBookId, Timestamp created) {
    this.searchId = searchId;
    this.userId = userId;
    this.visitedBookId = visitedBookId;
    this.created = created;
  }

  public int getSearchId() {
    return searchId;
  }

  public void setSearchId(int searchId) {
    this.searchId = searchId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public int getVisitedBookId() {
    return visitedBookId;
  }

  public void setVisitedBookId(int visitedBookId) {
    this.visitedBookId = visitedBookId;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }
}
