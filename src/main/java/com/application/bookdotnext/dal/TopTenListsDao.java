

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


public class TopTenListsDao {
	protected ConnectionManager connectionManager;

	private static TopTenListsDao instance = null;
	protected TopTenListsDao() {
		connectionManager = new ConnectionManager();
	}
	public static TopTenListsDao getInstance() {
		if(instance == null) {
			instance = new TopTenListsDao();
		}
		return instance;
	}
	
	
	public TopTenLists create(TopTenLists topTenList) throws SQLException{
		String insertTopTenList = "INSERT INTO TopTenLists(UserId, BookId,Created) "
				+ "VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTopTenList,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, topTenList.getUser().getUserId());
			insertStmt.setInt(2, topTenList.getBookInfo().getBookId());
			insertStmt.setTimestamp(3, new Timestamp (topTenList.getCreated().getTime()));
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int topTenListsId = -1;
			if(resultKey.next()) {
				topTenListsId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			topTenList.setTopTenListId(topTenListsId);
			return topTenList;
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

	public TopTenLists delete(TopTenLists topTenList) throws SQLException {
		String deleteTopTenLists = "DELETE FROM TopTenLists WHERE TopTenListId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTopTenLists);
			deleteStmt.setInt(1, topTenList.getTopTenListId());
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

	public TopTenLists getTopTenListById(int topTenListId) throws SQLException {
		String selectTopTenList = 
				"SELECT TopTenListId, UserId, BookId, Created "
				+ "FROM TopTenLists "
				+ "WHERE TopTenListId=? "
				+ "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTopTenList);
			selectStmt.setInt(1, topTenListId);
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			BookInfoDao bookInfoDao = BookInfoDao.getInstance();
			
			if(results.next()) {
				int resultTopTenListId = results.getInt("TopTenListId");
				int userId = results.getInt("UserId");
				int bookId = results.getInt("BookId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				Users user = usersDao.getUserByUserId(userId);
				BookInfo bookInfo = bookInfoDao.getBookInfoById(bookId);
				
				TopTenLists topTenList = new TopTenLists(resultTopTenListId, user, bookInfo,created);
				return topTenList;
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

	public List<TopTenLists> getTopTenListsByUserId(int userId) throws SQLException {
		List<TopTenLists> topTenLists = new ArrayList<TopTenLists>();
		String selectTopTenLists = 
		"SELECT TopTenListId, UserId, BookId, Created "
		+ "FROM TopTenLists "
		+ "WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTopTenLists);
			selectStmt.setInt(1,userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BookInfoDao bookInfoDao = BookInfoDao.getInstance();
			while(results.next()) {
				int topTenListId = results.getInt("TopTenListId");
				int resultUserId = results.getInt("UserId");
				int bookId = results.getInt("BookId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				Users user = usersDao.getUserByUserId(resultUserId);
				BookInfo bookInfo = bookInfoDao.getBookInfoById(bookId);
				
				TopTenLists topTenList = new TopTenLists(topTenListId,user, bookInfo,created);
				topTenLists.add(topTenList);
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
		return topTenLists;
	}

}
