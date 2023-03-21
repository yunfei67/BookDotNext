package com.application.bookdotnext.dal;

import com.application.bookdotnext.model.BookInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//test3
public class BookInfoDao {

  protected ConnectionManager connectionManager;

  private static BookInfoDao instance = null;
  protected BookInfoDao() {
    connectionManager = new ConnectionManager();
  }
  public static BookInfoDao getInstance() {
    if(instance == null) {
      instance = new BookInfoDao();
    }
    return instance;
  }

  public BookInfo create(BookInfo bookInfo) throws SQLException {
    String insertBookInfo =
        "INSERT INTO BookInfo(BookTitle,PublishedDate,Description,InfoLink,Categories,PublisherName,AuthorName,ImageLink) " +
            "VALUES(?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      // BookInfo has an auto-generated key. So we want to retrieve that key.
      insertStmt = connection.prepareStatement(insertBookInfo,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, bookInfo.getBookTitle());
      // Note: for the sake of simplicity, just set Picture to null for now.
      insertStmt.setInt(2,  bookInfo.getPublishedDate());
      insertStmt.setString(3, bookInfo.getDescription());
      insertStmt.setString(4, bookInfo.getInfoLink());
      insertStmt.setString(5, bookInfo.getCategories());
      insertStmt.setString(6, bookInfo.getPublisherName());
      insertStmt.setString(7, bookInfo.getAuthorName());
      insertStmt.setString(8, bookInfo.getImageLink());
      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      // For more details, see:
      // http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
      resultKey = insertStmt.getGeneratedKeys();
      int bookId = -1;
      if(resultKey.next()) {
        bookId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      bookInfo.setBookId(bookId);
      return bookInfo;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
      if(resultKey != null) {
        resultKey.close();
      }
    }
  }

  /**
   * Update the description of the BookInfo instance.
   * This runs a UPDATE statement.
   */
  public BookInfo updateDescription(BookInfo bookInfo, String newDescription) throws SQLException {
    String updateBookInfo = "UPDATE BookInfo SET Description=? WHERE BookId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateBookInfo);
      updateStmt.setString(1, newDescription);
      // Sets the Created timestamp to the current time.
      updateStmt.setInt(2, bookInfo.getBookId());
      updateStmt.executeUpdate();

      // Update the bookInfo param before returning to the caller.
      bookInfo.setDescription(newDescription);
      return bookInfo;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  /**
   * Delete the BookInfo instance.
   * This runs a DELETE statement.
   */
  public BookInfo delete(BookInfo bookInfo) throws SQLException {
    // Note: BlogComments has a fk constraint on BookInfo with the reference option
    // ON DELETE CASCADE. So this delete operation will delete all the referencing
    // BlogComments.
    String deleteBookInfo = "DELETE FROM BookInfo WHERE BookId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteBookInfo);
      deleteStmt.setInt(1, bookInfo.getBookId());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the BookInfo instance.
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

  /**
   * Get the BookInfo record by fetching it from your MySQL instance.
   * This runs a SELECT statement and returns a single BookInfo instance.
   * Note that we use BookInfoDao to retrieve the referenced BookInfo instance.
   * One alternative (possibly more efficient) is using a single SELECT statement
   * to join the BookInfo, BookInfo tables and then build each object.
   */
  public BookInfo getBookInfoById(int bookId) throws SQLException {
    String selectBookInfo =
        "SELECT BookId,BookTitle,PublishedDate,Description,InfoLink,Categories,PublisherName,AuthorName,ImageLink " +
            "FROM BookInfo " +
            "WHERE BookId=?; ";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectBookInfo);
      selectStmt.setInt(1, bookId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        int resultBookId = results.getInt("BookId");
        String bookTitle = results.getString("BookTitle");
        int publishedDate = results.getInt("PublishedDate");
        String description = results.getString("Description");
        String infoLink = results.getString("InfoLink");
        String categories = results.getString("Categories");
        String publisherName = results.getString("PublisherName");
        String authorName = results.getString("AuthorName");
        String imageLink = results.getString("ImageLink");

        BookInfo bookInfo = new BookInfo(resultBookId, bookTitle, publishedDate, description,
            infoLink, categories, publisherName, authorName, imageLink);
        return bookInfo;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }

  /**
   * Get the BookInfo record by fetching it from your MySQL instance.
   * This runs a SELECT statement and returns a single BookInfo instance.
   * Note that we use BookInfoDao to retrieve the referenced BookInfo instance.
   * One alternative (possibly more efficient) is using a single SELECT statement
   * to join the BookInfo, BookInfo tables and then build each object.
   */
  public List<BookInfo> getBookInfoByBookTitle(String bookTitle) throws SQLException {
    String selectBookInfo =
        "SELECT BookId,BookTitle,PublishedDate,Description,InfoLink,Categories,PublisherName,AuthorName,ImageLink " +
            "FROM BookInfo " +
            "WHERE BookTitle=?;";
    List<BookInfo> bookInfos = new ArrayList<>();
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectBookInfo);
      selectStmt.setString(1, bookTitle);
      results = selectStmt.executeQuery();
      BookInfoDao bookInfoDao = BookInfoDao.getInstance();
      while (results.next()) {
        int bookId = results.getInt("BookId");
        String resultBookTitle = results.getString("BookTitle");
        int publishedDate = results.getInt("PublishedDate");
        String description = results.getString("Description");
        String infoLink = results.getString("InfoLink");
        String categories = results.getString("Categories");
        String publisherName = results.getString("PublisherName");
        String authorName = results.getString("AuthorName");
        String imageLink = results.getString("ImageLink");

        BookInfo bookInfo = new BookInfo(bookId, resultBookTitle, publishedDate, description,
            infoLink, categories, publisherName, authorName, imageLink);
        bookInfos.add(bookInfo);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return bookInfos;
  }
}
