package com.application.bookdotnext.dal;


import com.application.bookdotnext.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Administrators table in your
 * MySQL instance. This is used to store {@link Administrators} into your MySQL instance and
 * retrieve {@link Administrators} from MySQL instance.
 */
public class AdministratorsDao extends PersonsDao {
  // Single pattern: instantiation is limited to one object.
  private static AdministratorsDao instance = null;
  protected AdministratorsDao() {
    super();
  }
  public static AdministratorsDao getInstance() {
    if(instance == null) {
      instance = new AdministratorsDao();
    }
    return instance;
  }

  public Administrators create(Administrators administrator) throws SQLException {
    // Insert into the superclass table first.
    // For Administrator Create permission is set to True = 1
    Persons person = super.create(new Persons(administrator.getUserId(), administrator.getUserName(), administrator.getFirstName(),
        administrator.getLastName(), administrator.getPassword(), true));

    String insertAdministrator = "INSERT INTO Administrators(UserId,LastLogin) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertAdministrator);
      insertStmt.setInt(1, person.getUserId());
      insertStmt.setTimestamp(2, new Timestamp(administrator.getLastLogin().getTime()));
      insertStmt.executeUpdate();
      return administrator;
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
   * Update the LastName of the Administrators instance.
   * This runs a UPDATE statement.
   */
  public Administrators updateLastName(Administrators administrator, String newLastName) throws SQLException {
    // The field to update only exists in the superclass table, so we can
    // just call the superclass method.
    super.updateLastName(administrator, newLastName);
    return administrator;
  }
  public Administrators updateFastName(Administrators administrator, String newFirstName) throws SQLException {
    // The field to update only exists in the superclass table, so we can
    // just call the superclass method.
    super.updateFirstName(administrator, newFirstName);
    return administrator;
  }

  /**
   * Delete the Administrators instance.
   * This runs a DELETE statement.
   */
  public Administrators delete(Administrators administrator) throws SQLException {
    String deleteAdministrator = "DELETE FROM Administrators WHERE UserId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteAdministrator);
      deleteStmt.setInt(1, administrator.getUserId());
      deleteStmt.executeUpdate();

      // Then also delete from the superclass.
      // Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
      // super.delete() without even needing to delete from Administrators first.
      super.delete(administrator);

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

  public Administrators getAdministratorFromUserName(String userName) throws SQLException {
    // To build an Administrator object, we need the Persons record, too.
    String selectAdministrator =
        "SELECT Persons.UserName AS UserName, FirstName, LastName, LastLogin " +
            "FROM Administrators INNER JOIN Persons " +
            "  ON Administrators.UserId = Persons.UserId " +
            "WHERE Persons.UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAdministrator);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String resultUserName = results.getString("UserName");
        String firstName = results.getString("FirstName");
        String lastName = results.getString("LastName");

        Timestamp lastLogin = results.getTimestamp("LastLogin");
        Administrators administrator = new Administrators(resultUserName, firstName, lastName, lastLogin);
        return administrator;
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
  public Administrators getAdministratorFromFirstName(String firstName) throws SQLException {
    // To build an Administrator object, we need the Persons record, too.
    String selectAdministrator =
        "SELECT Persons.UserName AS UserName, FirstName, LastName, LastLogin " +
            "FROM Administrators INNER JOIN Persons " +
            "  ON Administrators.UserId = Persons.UserId " +
            "WHERE Persons.UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAdministrator);
      selectStmt.setString(1, firstName);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String resultUserName = results.getString("UserName");
        String firstNameA = results.getString("FirstName");
        String lastName = results.getString("LastName");
        Timestamp lastLogin = results.getTimestamp("LastLogin");
        Administrators administrator = new Administrators(resultUserName, firstNameA, lastName, lastLogin);
        return administrator;
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
  public Administrators getAdministratorFromLastName(String LastName) throws SQLException {
    // To build an Administrator object, we need the Persons record, too.
    String selectAdministrator =
        "SELECT Persons.UserName AS UserName, FirstName, LastName, LastLogin " +
            "FROM Administrators INNER JOIN Persons " +
            "  ON Administrators.UserId = Persons.UserId " +
            "WHERE Persons.UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAdministrator);
      selectStmt.setString(1, LastName);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String resultUserName = results.getString("UserName");
        String firstNameA = results.getString("FirstName");
        String lastNameA = results.getString("LastName");
        Timestamp lastLogin = results.getTimestamp("LastLogin");
        Administrators administrator = new Administrators(resultUserName, firstNameA, lastNameA, lastLogin);
        return administrator;
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


  public List<Administrators> getAdministratorsFromUserName(String UserName)
      throws SQLException {
    List<Administrators> administrators = new ArrayList<Administrators>();
    String selectAdministrators =
        "SELECT Persons.UserName AS UserName, FirstName, LastName, LastLogin " +
            "FROM Administrators INNER JOIN Persons " +
            "  ON Administrators.UserId = Persons.UserId " +
            "WHERE Persons.UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAdministrators);
      selectStmt.setString(1, UserName);
      results = selectStmt.executeQuery();
      while(results.next()) {
        String userName = results.getString("UserName");
        String resultFirstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        Timestamp lastLogin = results.getTimestamp("LastLogin");
        Administrators administrator = new Administrators(userName, resultFirstName, lastName, lastLogin);
        administrators.add(administrator);
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
    return administrators;
  }
}
