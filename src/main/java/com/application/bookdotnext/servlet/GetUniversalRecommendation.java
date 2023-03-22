package com.application.bookdotnext.servlet;

import com.application.bookdotnext.dal.SearchHistoryDao;
import com.application.bookdotnext.dal.VotesDao;
import com.application.bookdotnext.model.BookInfo;
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

@WebServlet("/getuniversalrecommendation")
public class GetUniversalRecommendation extends HttpServlet {

  protected VotesDao votesDao;


  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    List<BookInfo> top5Books = new ArrayList<>();

    try {
      top5Books = VotesDao.getInstance().getTop5BookInfo();
    } catch (SQLException e) {
      // Handle the exception gracefully
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }

    // Set the list of top 5 books as a request attribute
    req.setAttribute("top5Books", top5Books);

    // Forward to a JSP file to display the list of top 5 books
    req.getRequestDispatcher("/UniversalRecommendation.jsp").forward(req, resp);
  }


}

