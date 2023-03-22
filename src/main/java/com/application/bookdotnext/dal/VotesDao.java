package com.application.bookdotnext.dal;

import com.application.bookdotnext.model.BookInfo;
import com.application.bookdotnext.model.SearchHistory;
import com.application.bookdotnext.model.Votes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class VotesDao {
  protected ConnectionManager connectionManager;

  private static VotesDao instance = null;
  protected VotesDao() {
    connectionManager = new ConnectionManager();
  }
  public static VotesDao getInstance() {
    if(instance == null) {
      instance = new VotesDao();
    }
    return instance;
  }

  public Votes create(Votes vote) throws SQLException {
    String insertVote =
        "INSERT INTO Votes(UserId,BookId,Created) " +
            "VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      // BookInfo has an auto-generated key. So we want to retrieve that key.
      insertStmt = connection.prepareStatement(insertVote,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, vote.getUserId());
      // Note: for the sake of simplicity, just set Picture to null for now.
      insertStmt.setInt(2,  vote.getBookId());
      insertStmt.setTimestamp(3, vote.getCreated());

      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      // For more details, see:
      // http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
      resultKey = insertStmt.getGeneratedKeys();
      int voteId = -1;
      if(resultKey.next()) {
        voteId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      vote.setVoteId(voteId);
      return vote;
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
  public Votes delete(Votes vote) throws SQLException {
    // Note: SearchHistory has a fk constraint on User with the reference option
    // ON DELETE CASCADE. So this delete operation will delete all the referencing
    // SearchHistory.
    String deleteVote = "DELETE FROM Votes WHERE VoteId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteVote);
      deleteStmt.setInt(1, vote.getVoteId());
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


  public List<BookInfo> getTop5BookInfo() throws SQLException {
    String countVote =
        "SELECT BookId, Count(*) AS CNT " +
            "FROM Votes " +
            "WHERE 1=1 "+
            "GROUP BY BookId "+
            "ORDER BY CNT " +
            "LIMIT 10; ";

    List<BookInfo> bookInfos = new ArrayList<>();

    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(countVote);
      //selectStmt.setInt(1, bookId);
      results = selectStmt.executeQuery();
      int k = 0;

      while (results.next() && k<5) {
        int bookId = results.getInt("BookId");
        BookInfo tmp = BookInfoDao.getInstance().getBookInfoById(bookId);
        bookInfos.add(tmp);
        k += 1;
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
