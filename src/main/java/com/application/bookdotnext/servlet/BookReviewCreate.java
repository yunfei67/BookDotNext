package com.application.bookdotnext.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.bookdotnext.dal.BookInfoDao;
import com.application.bookdotnext.dal.BookReviewDao;
import com.application.bookdotnext.dal.UsersDao;
import com.application.bookdotnext.model.BookInfo;
import com.application.bookdotnext.model.BookReview;
import com.application.bookdotnext.model.Users;


@WebServlet("/bookreviewcreate")
public class BookReviewCreate extends HttpServlet {
	
	protected BookReviewDao bookReviewDao;
	protected BookInfoDao bookInfoDao;
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		bookReviewDao = BookReviewDao.getInstance();
		bookInfoDao = bookInfoDao.getInstance();
		usersDao = usersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/BookReviewCreate.jsp").forward(req, resp);
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        

        // Retrieve book by bookId
        String bookId = req.getParameter("bookId");
        if (bookId == null || bookId.trim().isEmpty()) {
            messages.put("fail", "Invalid BookId");
        }
		BookInfo bookInfo = null;
		try {
			bookInfo = bookInfoDao.getBookInfoById(Integer.valueOf(bookId));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// Retrieve user by userId
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("fail", "Invalid userId"); 
        }
		Users user = null;
		try {
			user = usersDao.getUserByUserId(Integer.valueOf(userId));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// Verify that bookId should exist
		if (bookInfo == null) {
			messages.put("fail", "Book not exist");
		// Verify that userId should exist
		} else if (user == null) {
            messages.put("fail", "User not exist");
        } else {
        	// Create the BookReview.
        	Double reviewScore = Double.valueOf(req.getParameter("reviewscore"));
        	String content = req.getParameter("content");
        	
        	// created must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String stringCreated = req.getParameter("created");
        	Date created = new Date();
        	try {
        		created = dateFormat.parse(stringCreated);
        	} catch (ParseException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
        	
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	BookReview bookReview = new BookReview(reviewScore, content, created, user, bookInfo);
	        	bookReview = bookReviewDao.create(bookReview);
	        	messages.put("success", "Successfully created " + bookReview.toString());
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BookReviewCreate.jsp").forward(req, resp);
    }
}
