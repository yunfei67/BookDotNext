package com.application.bookdotnext.dal;


import com.application.bookdotnext.model.*;
import com.application.bookdotnext.model.SearchHistory;
import com.application.bookdotnext.model.Votes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UsersDao extends PersonsDao {
  // Single pattern: instantiation is limited to one object.
  private static UsersDao instance = null;
  protected UsersDao() {
    super();
  }
  public static UsersDao getInstance() {
    if(instance == null) {
      instance = new UsersDao();
    }
    return instance;
  }

  public Users create(Users Users) throws SQLException {
    // Insert into th superclass table first.
    Persons person = create(
        new Persons(Users.getUserId(), Users.getUserName(), Users.getFirstName(),
            Users.getLastName(), Users.getPassword(), Users.isPermission()));

    String insertUser = "INSERT INTO Users(UserId,DOB) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertUser);
      insertStmt.setInt(1, person.getUserId());
      insertStmt.setTimestamp(2, new Timestamp(Users.getDob().getTime()));
      insertStmt.executeUpdate();
      return Users;
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
    }
  }


  /**
   * Delete the BlogUsers instance.
   * This runs a DELETE statement.
   */
  public Users delete(Users User) throws SQLException {
    String deleteUser = "DELETE FROM Persons WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteUser);
      deleteStmt.setString(1, User.getUserName());
      int affectedRows = deleteStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("No records available to delete for UserName=" + User.getUserName());
      }

      super.delete(User);

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

  public Users getUserFromUserName(String userName) throws SQLException {
    // To build an BlogUser object, we need the Persons record, too.
    String selectUser =
        "SELECT Persons.UserName AS UserName, FirstName, LastName, DOB " +
            "FROM Users INNER JOIN Persons " +
            "ON Users.UserName = Persons.UserName " +
            "WHERE Persons.UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUser);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String resultUserName = results.getString("UserName");
        String firstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        Date dob = new Date(results.getTimestamp("DOB").getTime());

        Users User = new Users(resultUserName, firstName, lastName, dob );
        return User;
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
  public Users getUserByUserId(int userId) throws SQLException {
    // To build an BlogUser object, we need the Persons record, too.
    String selectUser =
        "SELECT Persons.UserName AS UserName, FirstName, LastName, DOB " +
            "FROM Users INNER JOIN Persons " +
            "ON Users.UserId = Persons.UserId " +
            "WHERE Users.UserId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUser);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String resultUserName = results.getString("UserName");
        String firstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        Date dob = new Date(results.getTimestamp("DOB").getTime());

        Users User = new Users(resultUserName, firstName, lastName, dob );
        return User;
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




  public List<Users> getUsersFromFirstName(String firstName)
      throws SQLException {
    List<Users> UserList = new ArrayList<Users>();
    String selectUsers =
        "SELECT Persons.UserName AS UserName, FirstName, LastName, DOB " +
            "FROM Users INNER JOIN Persons " +
            "ON Users.UserId = Persons.UserId " +
            "WHERE Persons.UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUsers);
      selectStmt.setString(1, firstName);
      results = selectStmt.executeQuery();
      while(results.next()) {
        String userName = results.getString("UserName");
        String resultFirstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        Date dob = new Date(results.getTimestamp("DOB").getTime());


        Users User = new Users(userName, resultFirstName, lastName, dob);
        UserList.add(User);
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
    return UserList;
  }
}
