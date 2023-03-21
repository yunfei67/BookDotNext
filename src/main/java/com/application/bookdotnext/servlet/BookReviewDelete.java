package com.application.bookdotnext.servlet;


import com.application.bookdotnext.dal.BookReviewDao;
import com.application.bookdotnext.model.BookReview;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/bookreviewdelete")
public class BookReviewDelete extends HttpServlet {
	
	protected BookReviewDao bookReviewDao;
	
	@Override
	public void init() throws ServletException {
		bookReviewDao = BookReviewDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("reviewid", "Delete BookReview");        
        req.getRequestDispatcher("/BookReviewDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String reviewId = req.getParameter("reviewid");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("reviewid", "Invalid reviewId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        BookReview bookReview = new BookReview(Integer.valueOf(reviewId));
	        try {
	        	bookReview = bookReviewDao.delete(bookReview);
	        	// Update the message.
		        if (bookReview == null) {
		            messages.put("reviewid", "Successfully deleted " + reviewId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("reviewid", "Failed to delete " + reviewId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BookReviewDelete.jsp").forward(req, resp);
    }
}
