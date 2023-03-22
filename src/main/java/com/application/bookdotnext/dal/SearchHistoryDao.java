package com.application.bookdotnext.dal;


import com.application.bookdotnext.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SearchHistoryDao {
  protected ConnectionManager connectionManager;

  private static SearchHistoryDao instance = null;
  protected SearchHistoryDao() {
    connectionManager = new ConnectionManager();
  }
  public static SearchHistoryDao getInstance() {
    if(instance == null) {
      instance = new SearchHistoryDao();
    }
    return instance;
  }

  public SearchHistory create(SearchHistory searchHistory) throws SQLException {
    String insertSearchHistory =
        "INSERT INTO SearchHistory(UserId,VisitedBooks,SearchTime) " +
            "VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      // BookInfo has an auto-generated key. So we want to retrieve that key.
      insertStmt = connection.prepareStatement(insertSearchHistory,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, searchHistory.getUserId());
      // Note: for the sake of simplicity, just set Picture to null for now.
      insertStmt.setInt(2,  searchHistory.getVisitedBookId());
      insertStmt.setTimestamp(3, searchHistory.getCreated());

      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      // For more details, see:
      // http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
      resultKey = insertStmt.getGeneratedKeys();
      int searchHistoryId = -1;
      if(resultKey.next()) {
        searchHistoryId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      searchHistory.setSearchId(searchHistoryId);
      return searchHistory;
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
   * Delete the SearchHistory instance.
   * This runs a DELETE statement.
   */
  public SearchHistory delete(SearchHistory searchHistory) throws SQLException {
    // Note: SearchHistory has a fk constraint on User with the reference option
    // ON DELETE CASCADE. So this delete operation will delete all the referencing
    // SearchHistory.
    String deleteSearchHistory = "DELETE FROM SearchHistory WHERE SearchId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteSearchHistory);
      deleteStmt.setInt(1, searchHistory.getSearchId());
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
   * Get the SearchHistory record by fetching it from your MySQL instance.
   * This runs a SELECT statement and returns a list of SearchHistory instance.
   * Note that we use BookInfoDao to retrieve the referenced BookInfo instance.
   * One alternative (possibly more efficient) is using a single SELECT statement
   * to join the BookInfo, BookInfo tables and then build each object.
   */

  public List<SearchHistory> getSearchHistoryByUserId(int userId) throws SQLException {
    String selectSearchHistory =
        "SELECT SearchId, UserId, VisitedBooks, SearchTime " +
            "FROM SearchHistory " +
            "WHERE UserId=?;";
    List<SearchHistory> searchHistories = new ArrayList<>();
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectSearchHistory);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();
      SearchHistoryDao searchHistoryDao = SearchHistoryDao.getInstance();
      while (results.next()) {
        int searchId = results.getInt("SearchId");
        int resUserId  = results.getInt("UserId");
        int visitedBook = results.getInt("VisitedBooks");
        Timestamp searchTime = results.getTimestamp("SearchTime");
        SearchHistory searchHistory = new SearchHistory(searchId,resUserId,visitedBook,searchTime);

        searchHistories.add(searchHistory);
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
    return searchHistories;
  }

  public List<BookInfo> getSearchedBooksByUserId(int userId) throws SQLException {
    String selectSearchedBooksByUserId =
        "SELECT UserId, VisitedBooks, COUNT(*) AS CNT "+
        "FROM SearchHistory " +
        "WHERE UserId=? "+
        "GROUP BY VisitedBooks "+
        "ORDER BY CNT "+
        "LIMIT 5;";

    List<BookInfo> searchedBooks = new ArrayList<>();
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectSearchedBooksByUserId);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int visitedBook = results.getInt("VisitedBooks");
        BookInfo tmp = BookInfoDao.getInstance().getBookInfoById(visitedBook);
        searchedBooks.add(tmp);
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
    return searchedBooks;
  }

}
