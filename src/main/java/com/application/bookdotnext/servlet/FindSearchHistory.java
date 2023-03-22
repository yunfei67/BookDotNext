package com.application.bookdotnext.servlet;

import com.application.bookdotnext.dal.SearchHistoryDao;
import com.application.bookdotnext.model.SearchHistory;
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

@WebServlet("/findsearchhistory")
public class FindSearchHistory extends HttpServlet{

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

    List<SearchHistory> searchHistories = new ArrayList<SearchHistory>();

    // Retrieve and validate name.
    // userId is retrieved from the URL query string.
    String userId = req.getParameter("UserId");
    if (userId == null || userId.trim().isEmpty()) {
      messages.put("fail", "Please enter a valid userId.");
    } else {
      // Retrieve userId, and store as a message.
      try {
        searchHistories = searchHistoryDao.getSearchHistoryByUserId(Integer.parseInt(userId));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + userId);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindBooks.jsp.
      messages.put("previousSearchHistory", userId);
    }

    req.setAttribute("searchHistory", searchHistories);

    req.getRequestDispatcher("/FindSearchHistory.jsp").forward(req, resp);
  }



}
