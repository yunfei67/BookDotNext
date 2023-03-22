package com.application.bookdotnext.servlet;
import com.application.bookdotnext.dal.*;
import com.application.bookdotnext.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TopTenListCreate")
public class TopTenListCreate extends HttpServlet {
		protected TopTenListsDao topTenListsDao;
		protected BookInfoDao bookInfoDao;
		protected UsersDao userDao;
		
		@Override
		public void init() throws ServletException {
			topTenListsDao = TopTenListsDao.getInstance();
			userDao = UsersDao.getInstance();
			bookInfoDao = BookInfoDao.getInstance();
		}
		
		
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse resp)
			      throws ServletException, IOException {
			    // Map for storing messages.
			    Map<String, String> messages = new HashMap<String, String>();
			    req.setAttribute("messages", messages);
			    //Just render the JSP.   
			    req.getRequestDispatcher("/TopTenListCreate.jsp").forward(req, resp);
			  }

	  @Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
	    // Map for storing messages.
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);
	
	    // Retrieve and validate name.
	    String bookId = req.getParameter("bookId");
	    if (bookId == null || bookId.trim().isEmpty()) {
            messages.put("fail", "Invalid BookId");
        } 
	    
	    BookInfo bookInfo = null;
		try {
			bookInfo = bookInfoDao.getBookInfoById(Integer.parseInt(bookId));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	    
	    String userId = req.getParameter("userId");
	    if (userId == null || userId.trim().isEmpty()) {
            messages.put("fail", "Invalid userId"); 
        }
	    
	    Users user = null;
		try {
			user = userDao.getUserByUserId(Integer.parseInt(userId));
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
	    	
	      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	      String stringCreated = req.getParameter("created");
	      Date created = new Date();

	      try {
	    	 created = dateFormat.parse(stringCreated);
	      } catch (ParseException e) {
	    		e.printStackTrace();
				throw new IOException(e);
	      }
	      Users users = new Users(Integer.parseInt(userId));
	      BookInfo bookInfos = new BookInfo(Integer.parseInt(bookId));
	      try {
	        TopTenLists topTenLists = new TopTenLists(users, bookInfos,created);
	        topTenLists = topTenListsDao.create(topTenLists);
	        messages.put("success", "Successfully created ");
	      } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	      }
	    }
	
	    req.getRequestDispatcher("/TopTenListCreate.jsp").forward(req, resp);
	  }
}
