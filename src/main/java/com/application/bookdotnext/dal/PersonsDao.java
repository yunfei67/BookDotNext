package com.application.bookdotnext.dal;


import com.application.bookdotnext.model.Persons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve
 * {@link Persons} from MySQL instance.
 */
public class PersonsDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static PersonsDao instance = null;
  protected PersonsDao() {
    connectionManager = new ConnectionManager();
  }
  public static PersonsDao getInstance() {
    if(instance == null) {
      instance = new PersonsDao();
    }
    return instance;
  }

  /**
   * Save the Persons instance by storing it in your MySQL instance.
   * This runs a INSERT statement.
   */
  public Persons create(Persons person) throws SQLException {
    String insertPerson = "INSERT INTO Persons(UserName,FirstName,LastName,Password,Permission) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertPerson, Statement.RETURN_GENERATED_KEYS);

      insertStmt.setString(1, person.getUserName());
      insertStmt.setString(2, person.getFirstName());
      insertStmt.setString(3, person.getLastName());
      insertStmt.setString(4, person.getPassword());
      insertStmt.setBoolean(5, person.isPermission());


      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int personId = -1;

      if(resultKey.next()) {
        personId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      person.setUserId(personId);

      return person;

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
  public Persons delete(Persons person) throws SQLException {
    String deletePerson = "DELETE FROM Persons WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deletePerson);
      deleteStmt.setString(1, person.getUserName());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the Persons instance.
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

  public Persons updateFirstName(Persons person, String newFirstName) throws SQLException {
    String updatePerson = "UPDATE Persons SET FirstName=? WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updatePerson);
      updateStmt.setString(1, newFirstName);
      updateStmt.setString(2, person.getUserName());
      updateStmt.executeUpdate();

      // Update the person param before returning to the caller.
      person.setFirstName(newFirstName);
      return person;
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
   * Update the LastName of the Persons instance.
   * This runs a UPDATE statement.
   */
  public Persons updateLastName(Persons person, String newLastName) throws SQLException {
    String updatePerson = "UPDATE Persons SET LastName=? WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updatePerson);
      updateStmt.setString(1, newLastName);
      updateStmt.setString(2, person.getUserName());
      updateStmt.executeUpdate();

      // Update the person param before returning to the caller.
      person.setLastName(newLastName);
      return person;
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

  public Persons updateUserName(Persons person, String newUserName) throws SQLException {
    String updatePerson = "UPDATE Persons SET UserName=? WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updatePerson);
      updateStmt.setString(1, newUserName);
      updateStmt.setString(2, person.getUserName());
      updateStmt.executeUpdate();

      // Update the person param before returning to the caller.
      person.setUserName(newUserName);
      return person;
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


  public Persons getPersonFromUserName(String userName) throws SQLException {
    String selectPerson = "SELECT UserName,FirstName,LastName FROM Persons WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectPerson);
      selectStmt.setString(1, userName);
      // Note that we call executeQuery(). This is used for a SELECT statement
      // because it returns a result set. For more information, see:
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
      results = selectStmt.executeQuery();
      // You can iterate the result set (although the example below only retrieves
      // the first record). The cursor is initially positioned before the row.
      // Furthermore, you can retrieve fields by name and by type.
      if(results.next()) {
        String resultUserName = results.getString("UserName");
        String firstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        Persons person = new Persons(resultUserName, firstName, lastName);
        return person;
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
   * Get the matching Persons records by fetching from your MySQL instance.
   * This runs a SELECT statement and returns a list of matching Persons.
   */
  public List<Persons> getPersonsFromFirstName(String firstName) throws SQLException {
    List<Persons> persons = new ArrayList<Persons>();
    String selectPersons =
        "SELECT UserName,FirstName,LastName FROM Persons WHERE FirstName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectPersons);
      selectStmt.setString(1, firstName);
      results = selectStmt.executeQuery();
      while(results.next()) {
        String userName = results.getString("UserName");
        String resultFirstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        Persons person = new Persons(userName, resultFirstName, lastName);
        persons.add(person);
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
    return persons;
  }
}
