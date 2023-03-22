package com.application.bookdotnext.dal;

import com.application.bookdotnext.model.UniversalRecommendation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UniversalRecommendDao {
  protected bookReview.dal.ConnectionManager connectionManager;

  private static UniversalRecommendDao instance = null;
  protected UniversalRecommendDao() {
    connectionManager = new bookReview.dal.ConnectionManager();
  }
  public static UniversalRecommendDao getInstance() {
    if(instance == null) {
      instance = new UniversalRecommendDao();
    }
    return instance;
  }

  public UniversalRecommendation delete(UniversalRecommendation universalRecommendation) throws SQLException {
    String deleteUniversalRecommendations = "DELETE FROM UniversalRecommendation WHERE UniversalRecomId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteUniversalRecommendations);
      deleteStmt.setInt(1, universalRecommendation.getUniversalRecomId());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the Reviews instance.
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




}

