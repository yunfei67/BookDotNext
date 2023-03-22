

package com.application.bookdotnext.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.application.bookdotnext.model.BookInfo;
import com.application.bookdotnext.model.BookReview;
import com.application.bookdotnext.model.Users;


public class BookReviewDao {
	protected ConnectionManager connectionManager;

	private static BookReviewDao instance = null;
	protected BookReviewDao() {
		connectionManager = new ConnectionManager();
	}
	public static BookReviewDao getInstance() {
		if(instance == null) {
			instance = new BookReviewDao();
		}
		return instance;
	}

	public BookReview create(BookReview bookReview) throws SQLException {
		String insertBookReview =
			"INSERT INTO BookReview(ReviewScore,Content,Created,UserId,BookId) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		java.util.Date utilDate = new java.util.Date();

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBookReview, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setDouble(1, bookReview.getReviewScore());
			insertStmt.setString(2, bookReview.getContent());
			utilDate = bookReview.getCreated();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			insertStmt.setDate(3, sqlDate);
			// insertStmt.setDate(3, new (java.sql.Date) bookReview.getCreated().getTime());
			insertStmt.setInt(4, bookReview.getUser().getUserId());
			insertStmt.setInt(5, bookReview.getBookInfo().getBookId());
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			bookReview.setReviewId(reviewId);
			return bookReview;
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
	 * Delete the BookReviews instance.
	 * This runs a DELETE statement.
	 */
	public BookReview delete(BookReview bookReview) throws SQLException {
		String deleteBookReviews = "DELETE FROM BookReview WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBookReviews);
			deleteStmt.setInt(1, bookReview.getReviewId());
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


	public BookReview getReviewById(int reviewId) throws SQLException {
		String selectBookReview =
			"SELECT ReviewId,ReviewScore,Content,Created,UserId,BookId " +
			"FROM BookReview " +
			"WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBookReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			BookInfoDao bookInfoDao = BookInfoDao.getInstance();
			
			if(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Double reviewScore = results.getDouble("ReviewScore");
				String content = results.getString("Content");
				Date created = results.getDate("Created");
				int userId = results.getInt("UserId");
				int bookId = results.getInt("BookId");
				
				
				Users user = usersDao.getUserByUserId(userId);
				BookInfo bookInfo = bookInfoDao.getBookInfoById(bookId);
				
				BookReview bookReview = new BookReview(resultReviewId, reviewScore, content, created, user, bookInfo);
				return bookReview;
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
	 * Get the all the BookReviews for a user.
	 */
	public List<BookReview> getBookReviewsByUserId(int userId) throws SQLException {
		List<BookReview> bookReviews = new ArrayList<BookReview>();
		
		String selectBookReview =
			"SELECT ReviewId,ReviewScore,Content,Created,UserId,BookId " +
			"FROM BookReview " + 
			"WHERE UserId=?; ";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBookReview);
			selectStmt.setInt(1, userId);
			
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BookInfoDao bookInfoDao = BookInfoDao.getInstance();
			
			while(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Double reviewScore = results.getDouble("ReviewScore");
				String content = results.getString("Content");
				Date created = results.getDate("Created");
				// int userId = results.getInt("UserId");
				int bookId = results.getInt("BookId");
					
				Users user = usersDao.getUserByUserId(userId);
				BookInfo bookInfo = bookInfoDao.getBookInfoById(bookId);
				
				BookReview bookReview = new BookReview(resultReviewId, reviewScore, content, created, user, bookInfo);
				bookReviews.add(bookReview);
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
		return bookReviews;
	}
	
	/**
	 * Get the all the Reviews for a bookId.
	 */
	public List<BookReview> getBookReviewsByBookId(int bookId) throws SQLException {
		List<BookReview> bookReviews = new ArrayList<BookReview>();
		
		String selectBookReview =
			"SELECT ReviewId,ReviewScore,Content,Created,UserId,BookId " +
			"FROM BookReview " + 
			"WHERE BookId=?; ";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBookReview);
			selectStmt.setInt(1, bookId);
			
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BookInfoDao bookInfoDao = BookInfoDao.getInstance();
			
			while(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Double reviewScore = results.getDouble("ReviewScore");
				String content = results.getString("Content");
				Date created = results.getDate("Created");
				int userId = results.getInt("UserId");
				// int bookId = results.getInt("BookId");
					
				Users user = usersDao.getUserByUserId(userId);
				BookInfo bookInfo = bookInfoDao.getBookInfoById(bookId);
				
				BookReview bookReview = new BookReview(resultReviewId, reviewScore, content, created, user, bookInfo);
				bookReviews.add(bookReview);
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
		return bookReviews;
	}
	
	
}
