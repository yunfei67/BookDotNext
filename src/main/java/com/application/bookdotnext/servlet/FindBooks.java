package com.application.bookdotnext.servlet;

import com.application.bookdotnext.dal.BookInfoDao;
import com.application.bookdotnext.model.BookInfo;
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

@WebServlet("/findBooks")
public class FindBooks extends HttpServlet {

  protected BookInfoDao bookInfoDao;

  @Override
  public void init() throws ServletException {
    bookInfoDao = BookInfoDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<BookInfo> bookInfo = new ArrayList<BookInfo>();

    // Retrieve and validate name.
    // bookTitle is retrieved from the URL query string.
    String bookTitle = req.getParameter("bookTitle");
    if (bookTitle == null || bookTitle.trim().isEmpty()) {
      messages.put("success", "Please enter a valid bookTitle.");
    } else {
      // Retrieve BookInfo, and store as a message.
      try {
        bookInfo = bookInfoDao.getBookInfoByBookTitle(bookTitle);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + bookTitle);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindBooks.jsp.
      messages.put("previousBookTitle", bookTitle);
    }
    req.setAttribute("bookInfo", bookInfo);

    req.getRequestDispatcher("/FindBooks.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<BookInfo> bookInfo = new ArrayList<BookInfo>();

    // Retrieve and validate name.
    // bookTitle is retrieved from the form POST submission. By default, it
    // is populated by the URL query string (in FindBooks.jsp).
    String bookTitle = req.getParameter("bookTitle");
    if (bookTitle == null || bookTitle.trim().isEmpty()) {
      messages.put("success", "Please enter a valid bookTitle.");
    } else {
      // Retrieve BookInfo, and store as a message.
      try {
        bookInfo = bookInfoDao.getBookInfoByBookTitle(bookTitle);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + bookTitle);
    }
    req.setAttribute("bookInfo", bookInfo);

    req.getRequestDispatcher("/FindBooks.jsp").forward(req, resp);
  }
}
