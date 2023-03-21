package com.application.bookdotnext.servlet;

import com.application.bookdotnext.dal.BookInfoDao;
import com.application.bookdotnext.model.BookInfo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookupdate")
public class BookUpdate extends HttpServlet {

  protected BookInfoDao bookInfoDao;

  @Override
  public void init() throws ServletException {
    bookInfoDao = BookInfoDao.getInstance();
  }

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve user and validate.
    Integer bookId = Integer.valueOf(req.getParameter("bookId"));
    if (bookId == null || bookId < 0) {
      messages.put("success", "Please enter a valid bookId.");
    } else {
      try {
        BookInfo bookInfo = bookInfoDao.getBookInfoById(bookId);
        if(bookId == null) {
          messages.put("success", "BookId does not exist.");
        }
        req.setAttribute("bookInfo", bookInfo);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/BookUpdate.jsp").forward(req, resp);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve user and validate.
    Integer bookId = Integer.valueOf(req.getParameter("bookId"));
    if (bookId == null || bookId < 0) {
      messages.put("success", "Please enter a valid bookId.");
    } else {
      try {
        BookInfo bookInfo = bookInfoDao.getBookInfoById(bookId);
        if(bookInfo == null) {
          messages.put("success", "UserName does not exist. No update to perform.");
        } else {
          String newDescription = req.getParameter("description");
          if (newDescription == null || newDescription.trim().isEmpty()) {
            messages.put("success", "Please enter a valid description.");
          } else {
            bookInfo = bookInfoDao.updateDescription(bookInfo, newDescription);
            messages.put("success", "Successfully updated " + newDescription);
          }
        }
        req.setAttribute("bookInfo", bookInfo);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/BookUpdate.jsp").forward(req, resp);
  }
}
