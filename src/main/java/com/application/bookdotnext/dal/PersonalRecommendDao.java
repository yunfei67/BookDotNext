

package com.application.bookdotnext.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.application.bookdotnext.model.*;

public class PersonalRecommendDao {
	protected ConnectionManager connectionManager;

	private static PersonalRecommendDao instance = null;
	protected PersonalRecommendDao() {
		connectionManager = new ConnectionManager();
	}
	public static PersonalRecommendDao getInstance() {
		if(instance == null) {
			instance = new PersonalRecommendDao();
		}
		return instance;
	}
	
		
	public PersonalRecommend create(PersonalRecommend personalRecommend) throws SQLException{
		String insertPeronalRecommend = "INSERT INTO PersonalRecommend(UserId, BookId,Created) VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPeronalRecommend, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, personalRecommend.getUser().getUserId());
			insertStmt.setInt(2, personalRecommend.getBookInfo().getBookId());
			insertStmt.setTimestamp(3, new Timestamp (personalRecommend.getCreated().getTime()));
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int personalRecommendId = -1;
			if(resultKey.next()) {
				personalRecommendId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			personalRecommend.setRecommendId(personalRecommendId);
			return personalRecommend;
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

	public PersonalRecommend delete(PersonalRecommend personalRecommend) throws SQLException {
		String deletePersonalRecommend = "DELETE FROM PersonalRecommend WHERE RecomID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePersonalRecommend);
			deleteStmt.setInt(1, personalRecommend.getRecommendId());
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

	public PersonalRecommend getPersonalRecommendById(int personalRecommendId) throws SQLException {
		String selectPersonalRecommend = 
				"SELECT RecomId,UserId,BookId,Created "
				+ "FROM PersonalRecommend "
				+ "WHERE RecomId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPersonalRecommend);
			selectStmt.setInt(1, personalRecommendId);
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			BookInfoDao bookInfoDao = BookInfoDao.getInstance();
			
			if(results.next()) {
				int resultPersonalRecommendId = results.getInt("RecomId");
				int userId = results.getInt("UserId");
				int bookId = results.getInt("BookId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				Users user = usersDao.getUserByUserId(userId);
				BookInfo bookInfo = bookInfoDao.getBookInfoById(bookId);
				
				PersonalRecommend personalRecommend = new PersonalRecommend(resultPersonalRecommendId, created,user, bookInfo);
				return personalRecommend;
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

	public List<PersonalRecommend> getPersonalRecommendByUserId(int userId) throws SQLException {
		List<PersonalRecommend> personalRecommends = new ArrayList<PersonalRecommend>();
		String selectPersonalRecommend = 
		"SELECT RecomId, UserId, BookId, Created "
		+ "FROM PersonalRecommend "
		+ "WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPersonalRecommend);
			selectStmt.setInt(1,userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BookInfoDao bookInfoDao = BookInfoDao.getInstance();
			while(results.next()) {
				int personalRecommend = results.getInt("RecomId");
				int resultUserId = results.getInt("UserId");
				int bookId = results.getInt("BookId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				Users user = usersDao.getUserByUserId(resultUserId);
				BookInfo bookInfo = bookInfoDao.getBookInfoById(bookId);
				
				PersonalRecommend resultPersonalRecommend = new PersonalRecommend(personalRecommend,created, user, bookInfo);
				personalRecommends.add(resultPersonalRecommend);
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
		return personalRecommends;
	}

	public List<PersonalRecommend> getPersonalRecommendByBookId(int bookId) throws SQLException {
		List<PersonalRecommend> personalRecommends = new ArrayList<PersonalRecommend>();
		String selectPersonalRecommend = 
		"SELECT RecomId, UserId, BookId, Created "
		+ "FROM PersonalRecommend "
		+ "WHERE BookId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPersonalRecommend);
			selectStmt.setInt(1,bookId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BookInfoDao bookInfoDao = BookInfoDao.getInstance();
			while(results.next()) {
				int personalRecommend = results.getInt("RecomId");
				int userId = results.getInt("UserId");
				int resultBookId = results.getInt("BookId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				Users user = usersDao.getUserByUserId(userId);
				BookInfo bookInfo = bookInfoDao.getBookInfoById(resultBookId);
				
				PersonalRecommend resultPersonalRecommend = new PersonalRecommend(personalRecommend,created, user, bookInfo);
				personalRecommends.add(resultPersonalRecommend);
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
		return personalRecommends;
	}

}
