package com.application.bookdotnext.dal;

import com.application.bookdotnext.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AuthorDao {
	
	protected ConnectionManager connectionManager;
	
	private static AuthorDao instance = null;
	protected AuthorDao() {
		connectionManager = new ConnectionManager();
	}
	public static AuthorDao getInstance() {
		if(instance == null) {
			instance = new AuthorDao();
		}
		return instance;
	}
	
	
	/**
	 * Create method.
	 * This runs a INSERT statement.
	 */
	public Author create(Author author) throws SQLException {
		String insertAuthor = "INSERT INTO Author(AuthorName,CountryCode) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAuthor);
			
			insertStmt.setString(1, author.getAuthorName());
			insertStmt.setString(2, author.getCountryCode());	
			
			insertStmt.executeUpdate();
			return author;
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
	 * updateAuthorName method.
	 * This runs a UPDATE statement.
	 */
	public Author updateAuthorName(Author author, String newAuthorName) throws SQLException {
		String updateAuthor = "UPDATE Author SET AuthorName=? WHERE AuthorName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAuthor);
			updateStmt.setString(1, newAuthorName);
			updateStmt.setString(2, author.getAuthorName());
			updateStmt.executeUpdate();
			
			author.setAuthorName(newAuthorName);;
			return author;
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
	 * updateCountryCode method.
	 * This runs a UPDATE statement.
	 */
	public Author updateCountryCode(Author author, String newCountryCode) throws SQLException {
		String updateAuthor = "UPDATE Author SET CountryCode=? WHERE CountryCode=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAuthor);
			updateStmt.setString(1, newCountryCode);
			updateStmt.setString(2, author.getAuthorName());
			updateStmt.executeUpdate();
			
			author.setCountryCode(newCountryCode);;
			return author;
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
	 * Delete the Author instance.
	 * This runs a DELETE statement.
	 */
	public Author delete(Author author) throws SQLException {
		String deleteAuthor = "DELETE FROM Author WHERE AuthorName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAuthor);
			deleteStmt.setString(1, author.getAuthorName());
			deleteStmt.executeUpdate();

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
	 * getAuthorByAuthorName method. 
	 * This runs a SELECT statement and returns a matching author.
	 */
	public Author getAuthorByAuthorName(String authorName) throws SQLException {
		String selectAuthor =
				"SELECT AuthorName,CountryCode FROM Author WHERE AuthorName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuthor);
			selectStmt.setString(1, authorName);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String resultAuthorName = results.getString("AuthorName");
				String countryCode = results.getString("CountryCode");
				
				Author Author = new Author(resultAuthorName,countryCode);
				return Author;
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
	 * getAuthorByCountryCode method. 
	 * This runs a SELECT statement and returns a list of matching Publishers.
	 */
	public List<Author> getAuthorByCountryCode(String countryCode) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		String selectAuthors =
			"SELECT AuthorName,CountryCode FROM Author WHERE CountryCode=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuthors);
			selectStmt.setString(1, countryCode);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String authorName = results.getString("AuthorName");
				String resultCountryCode = results.getString("CountryCode");
				
				Author author = new Author(authorName, resultCountryCode);
				authors.add(author);
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
		return authors;
	}
	
	
}
