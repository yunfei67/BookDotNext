package com.application.bookdotnext.model;

import com.application.bookdotnext.dal.BookInfoDao;
import java.util.Date;
import javax.print.attribute.standard.MediaSize.Engineering;

public class BookInfo {

  private int BookId;
  private String BookTitle;
  private int publishedDate;
  private String description;
  private String infoLink;
  private String categories;
  private String publisherName;
  private String authorName;
  private String ImageLink;

//  public enum Categories {
//    Fiction, ComicsGraphicNovels, AfricanAmericans, AmericanLiterature, Architecture,
//    BiographyAutobiography, Religion, AeronauticsAmerican , Art,
//    SocialScience, JuvenileNonfiction, Actors, AntiquesCollectibles,
//    TechnologyEngineering, AdventureStories, BusinessEconomics;
//  }


  public BookInfo(int bookId, String bookTitle, int publishedDate, String description, String infoLink, String categories, String publisherName, String authorName, String imageLink) {
    BookId = bookId;
    BookTitle = bookTitle;
    this.publishedDate = publishedDate;
    this.description = description;
    this.infoLink = infoLink;
    this.categories = categories;
    this.publisherName = publisherName;
    this.authorName = authorName;
    ImageLink = imageLink;
  }

  public BookInfo(String bookTitle, int publishedDate, String description, String infoLink, String categories, String publisherName, String authorName, String imageLink) {
    BookTitle = bookTitle;
    this.publishedDate = publishedDate;
    this.description = description;
    this.infoLink = infoLink;
    this.categories = categories;
    this.publisherName = publisherName;
    this.authorName = authorName;
    ImageLink = imageLink;
  }

  public int getBookId() {
    return BookId;
  }

  public void setBookId(int bookId) {
    BookId = bookId;
  }

  public String getBookTitle() {
    return BookTitle;
  }

  public void setBookTitle(String bookTitle) {
    BookTitle = bookTitle;
  }

  public int getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(int publishedDate) {
    this.publishedDate = publishedDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getInfoLink() {
    return infoLink;
  }

  public void setInfoLink(String infoLink) {
    this.infoLink = infoLink;
  }

  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
  }

  public String getPublisherName() {
    return publisherName;
  }

  public void setPublisherName(String publisherName) {
    this.publisherName = publisherName;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public String getImageLink() {
    return ImageLink;
  }

  public void setImageLink(String imageLink) {
    ImageLink = imageLink;
  }
}
