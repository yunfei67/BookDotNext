package com.application.bookdotnext.servlet;

import com.application.bookdotnext.dal.SearchHistoryDao;
import com.application.bookdotnext.model.SearchHistory;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
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

@WebServlet("/searchhistorydelete")
public class DeleteSearchHistory extends HttpServlet {
  protected SearchHistoryDao searchHistoryDao;

  public void init() throws ServletException {
    searchHistoryDao = SearchHistoryDao.getInstance();
  }
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Provide a title and render the JSP.
    messages.put("title", "Delete search history");
    req.getRequestDispatcher("/DeleteSearchHistory.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String searchId = req.getParameter("searchId");
    //Integer searchId = Integer.parseInt(req.getParameter("searchId"));
    if (searchId == null || searchId.trim().isEmpty()) {
      messages.put("Fail", "Invalid searchId");
      messages.put("disableSubmit", "true");
    } else {
      // Delete  the SearchHistory.
      int searchId1 = Integer.parseInt(searchId);
      SearchHistory searchHistory = new SearchHistory(searchId1);

      try {
        searchHistory = searchHistoryDao.delete(searchHistory);
        // Update the message.
        if (searchHistory == null) {
          messages.put("title", "Successfully deleted " + searchId);
          messages.put("disableSubmit", "true");
        } else {
          messages.put("title", "Failed to delete " + searchId);
          messages.put("disableSubmit", "false");
        }

      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }

    }

    req.getRequestDispatcher("/DeleteSearchHistory.jsp").forward(req, resp);
  }


}
