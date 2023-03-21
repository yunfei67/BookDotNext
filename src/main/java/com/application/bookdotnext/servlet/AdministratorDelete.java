package com.application.bookdotnext.servlet;

import com.application.bookdotnext.dal.AdministratorsDao;
import com.application.bookdotnext.model.Administrators;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admindelete")
public class AdministratorDelete extends HttpServlet {
  protected AdministratorsDao adminDao;

  @Override
  public void init() throws ServletException {
    adminDao = AdministratorsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Provide a title and render the JSP.
    messages.put("title", "Delete Admin");
    req.getRequestDispatcher("/AdministratorDelete.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String userName = req.getParameter("username");
    if (userName == null || userName.trim().isEmpty()) {
      messages.put("title", "Invalid UserName");
      messages.put("disableSubmit", "true");
    } else {
      // Delete the Admin.
      Administrators admin = new Administrators(userName);
      try {
        admin = adminDao.delete(admin);
        // Update the message.
        if (admin == null) {
          messages.put("title", "Successfully deleted admin " + userName);
          messages.put("disableSubmit", "true");
        } else {
          messages.put("title", "Failed to delete admin " + userName);
          messages.put("disableSubmit", "false");
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/AdministratorDelete.jsp").forward(req, resp);
  }
}
