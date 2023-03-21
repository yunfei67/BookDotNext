package com.application.bookdotnext.servlet;
import com.application.bookdotnext.dal.SearchHistoryDao;
import com.application.bookdotnext.model.SearchHistory;
import java.text.ParseException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchHistoryCreate")
public class CreateResearchHistory extends HttpServlet {
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
    //Just render the JSP.
    req.getRequestDispatcher("/SearchHistoryCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    messages.put("title", "Create search history");

    // Retrieve and validate name.
    String userId = req.getParameter("userId");
    if (userId == null || userId.trim().isEmpty()) {
      messages.put("Fail", "Invalid UserId");
    } else {
      // delete the SearchHistory.
      Integer userID = Integer.parseInt(userId);

      int visitedBookId = Integer.parseInt(req.getParameter("visitedBookId"));
      String stringCreated = req.getParameter("created");
      // created must be in the format yyyy-mm-dd.
      Timestamp createdDate = null;

      try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = dateFormat.parse(stringCreated);
        createdDate = new Timestamp(parsedDate.getTime()) ;
      } catch (ParseException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      try {

        SearchHistory newSearchHistory = new SearchHistory(userID, visitedBookId, createdDate);
        newSearchHistory = searchHistoryDao.create(newSearchHistory);
        messages.put("success", "Successfully created " + userId);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/SearchHistoryCreate.jsp").forward(req, resp);
  }


}
