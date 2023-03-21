package com.application.bookdotnext.dal;

import com.application.bookdotnext.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PublishersDao {
	
	protected ConnectionManager connectionManager;
	
	private static PublishersDao instance = null;
	protected PublishersDao() {
		connectionManager = new ConnectionManager();
	}
	public static PublishersDao getInstance() {
		if(instance == null) {
			instance = new PublishersDao();
		}
		return instance;
	}
	
	
	/**
	 * Create method. This runs a INSERT statement.
	 */
	public Publishers create(Publishers publisher) throws SQLException {
		String insertPublisher = "INSERT INTO Publishers(PublisherName,CountryCode) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPublisher);
			
			insertStmt.setString(1, publisher.getPublisherName());
			insertStmt.setString(2, publisher.getCountryCode());	
			
			insertStmt.executeUpdate();
			return publisher;
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
	 * updatePublisherName method.
	 * This runs a UPDATE statement.
	 */
	public Publishers updatePublisherName(Publishers publisher, String newPublisherName) throws SQLException {
		String updatePublisherName = "UPDATE Publishers SET PublisherName=? WHERE PublisherName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePublisherName);
			updateStmt.setString(1, newPublisherName);
			updateStmt.setString(2, publisher.getPublisherName());
			updateStmt.executeUpdate();
			
			publisher.setPublisherName(newPublisherName);;;
			return publisher;
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
	public Publishers updateCountryCode(Publishers publisher, String newCountryCode) throws SQLException {
		String updatePublisher = "UPDATE Publishers SET CountryCode=? WHERE CountryCode=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePublisher);
			updateStmt.setString(1, newCountryCode);
			updateStmt.setString(2, publisher.getCountryCode());
			updateStmt.executeUpdate();
			
			publisher.setCountryCode(newCountryCode);;;
			return publisher;
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
	 * Delete the Publishers instance.
	 * This runs a DELETE statement.
	 */
	public Publishers delete(Publishers publisher) throws SQLException {
		String deletePublisher = "DELETE FROM Publishers WHERE PublisherName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePublisher);
			deleteStmt.setString(1, publisher.getPublisherName());
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
	 * getPublisherByPublisherName method.
	 * This runs a SELECT statement and returns a matching Publisher.
	 */
	public Publishers getPublisherByPublisherName(String publisherName) throws SQLException {
		String selectPublisher =
			"SELECT PublisherName,CountryCode FROM Publishers WHERE PublisherName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPublisher);
			selectStmt.setString(1, publisherName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultPublisherName = results.getString("PublisherName");
				String countryCode = results.getString("CountryCode");
				
				Publishers publisher = new Publishers(resultPublisherName, countryCode);
			return publisher;
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
	 * getPublisherByCountryCode method.
	 * This runs a SELECT statement and returns a list of matching Publishers.
	 */
	public List<Publishers> getPublisherByCountryCode(String countryCode) throws SQLException {
		List<Publishers> publishers = new ArrayList<Publishers>();
		String selectPublishers =
			"SELECT PublisherName,CountryCode FROM Publishers WHERE CountryCode=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPublishers);
			selectStmt.setString(1, countryCode);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String publisherName = results.getString("PublisherName");
				String resultCountryCode = results.getString("CountryCode");
				
				Publishers publisher = new Publishers(publisherName, resultCountryCode);
				publishers.add(publisher);
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
		return publishers;
	}

	
}
