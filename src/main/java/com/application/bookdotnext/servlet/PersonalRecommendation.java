package com.application.bookdotnext.servlet;
import com.application.bookdotnext.dal.*;
import com.application.bookdotnext.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/PersonalRecommendation")
public class PersonalRecommendation extends HttpServlet {
	protected SearchHistoryDao searchHistoryDao;
	
	@Override
	  public void init() throws ServletException {
		searchHistoryDao = searchHistoryDao.getInstance();
	  }
	
	
	@Override
	  public void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
	    // Map for storing messages.
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    List<BookInfo> personalRecommend = new ArrayList<BookInfo>();
	    String userId = req.getParameter("userId");
	    if (userId == null || userId.trim().isEmpty()) {
	      messages.put("fail", "Please enter a valid userId.");
	    } else {
	      // Retrieve BookInfo, and store as a message.
	      try {
	    	  personalRecommend = searchHistoryDao.getSearchedBooksByUserId(Integer.parseInt(userId));
	    	  
	      } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	      }
	      messages.put("success", "Displaying results for " + userId);
	      
	      messages.put("previousUserId", userId.toString());
	    }
	    
	    
	    req.setAttribute("personalRecommend", personalRecommend);

	    req.getRequestDispatcher("/PersonalRecommendation.jsp").forward(req, resp);
	  }

	  @Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
	    // Map for storing messages.
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    List<BookInfo> personalRecommend = new ArrayList<BookInfo>();

	    // Retrieve and validate name.
	    String userId = req.getParameter("userId");
	    if (userId == null || userId.trim().isEmpty()) {
	      messages.put("fail", "Please enter a valid userId.");
	    } else {
	      // Retrieve BookInfo, and store as a message.
	      try {
	    	  personalRecommend = searchHistoryDao.getSearchedBooksByUserId(Integer.parseInt(userId));
	      } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	      }
	      messages.put("success", "Displaying results for " + userId);
	    }
	    req.setAttribute("personalRecommend", personalRecommend);
	    req.getRequestDispatcher("/PersonalRecommendation.jsp").forward(req, resp);
	  }
	
	
	
	
}
		
