package com.application.bookdotnext.servlet;
import com.application.bookdotnext.dal.*;
import com.application.bookdotnext.model.*;

import java.io.IOException;
import java.sql.SQLException;
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


@WebServlet("/topTenListDelete")
public class TopTenListDelete extends HttpServlet {
		protected TopTenListsDao topTenListsDao;
		protected BookInfoDao bookInfoDao;
		protected UsersDao userDao;
		
		@Override
		public void init() throws ServletException {
			topTenListsDao = TopTenListsDao.getInstance();
		}
		
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse resp)
			      throws ServletException, IOException {
			    // Map for storing messages.
			    Map<String, String> messages = new HashMap<String, String>();
			    req.setAttribute("messages", messages);
			    //Just render the JSP.   
			    messages.put("title", "Delete TopTenList");
			    req.getRequestDispatcher("/TopTenListDelete.jsp").forward(req, resp);
			  }

	  @Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
	    // Map for storing messages.
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);
	
	    // Retrieve and validate name.
	    String topTenListId = req.getParameter("topTenListId");
	    // Problem topTenlistId is null
	    System.out.println(topTenListId);
	    
        if (topTenListId == null ||topTenListId.trim().isEmpty()) {
            messages.put("title", "Invalid TopTenListId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the TopTenListId.
	        TopTenLists topTenList = new TopTenLists(Integer.parseInt(topTenListId));
	        try {
	        	topTenList = topTenListsDao.delete(topTenList);
	        	// Update the message.
		        if (topTenList == null) {
		            messages.put("title", "Successfully deleted " + topTenListId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + topTenListId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/TopTenListDelete.jsp").forward(req, resp);
	  }
}
