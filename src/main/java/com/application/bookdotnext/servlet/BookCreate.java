package com.application.bookdotnext.servlet;


import com.application.bookdotnext.dal.BookInfoDao;
import com.application.bookdotnext.model.BookInfo;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/bookcreate")
public class BookCreate extends HttpServlet {
  protected BookInfoDao bookInfoDao;
  // test

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
    //Just render the JSP.   
    req.getRequestDispatcher("/BookCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String bookTitle = req.getParameter("bookTitle");
    if (bookTitle == null || bookTitle.trim().isEmpty()) {
      messages.put("success", "Invalid bookTitle");
    }

    else {
      // Create the BlogUser.
      String description = req.getParameter("description");
      String infoLink = req.getParameter("infoLink");
      String publisherName = req.getParameter("publisherName");
      String authorName = req.getParameter("authorName");
      String imageLink = req.getParameter("imageLink");
      String categories = req.getParameter("categories");

      int publishedYear = Integer.parseInt(req.getParameter("publishedYear"));

      // dob must be in the format yyyy-mm-dd.
//      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//      int publishedYear = Integer.parseInt(req.getParameter("publishedYear"));
      try {
        // Exercise: parse the input for StatusLevel.
        BookInfo bookInfo = new BookInfo(bookTitle, publishedYear, description, infoLink, categories, publisherName, authorName, imageLink);
        bookInfo = bookInfoDao.create(bookInfo);
        messages.put("success", "Successfully created " + bookTitle);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/BookCreate.jsp").forward(req, resp);
  }
}
