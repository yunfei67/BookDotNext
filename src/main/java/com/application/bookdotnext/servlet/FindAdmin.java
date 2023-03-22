package com.application.bookdotnext.servlet;

import com.application.bookdotnext.dal.AdministratorsDao;
import com.application.bookdotnext.model.Administrators;

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

@WebServlet("/findadmin")
public class FindAdmin extends HttpServlet {
  protected AdministratorsDao adminDao;

  @Override
  public void init() throws ServletException {
    adminDao = adminDao.getInstance();
  }
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Administrators> adminList = new ArrayList<Administrators>();

    // Retrieve and validate name.
    // firstname is retrieved from the URL query string.
    String userName = req.getParameter("username");
    if (userName == null || userName.trim().isEmpty()) {
      messages.put("success", "Please enter a valid UserName.");
    } else {
      // Retrieve admin, and store as a message.
      try {
        adminList = adminDao.getAdministratorsFromUserName(userName);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + userName);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousFirstName", userName);
    }
    req.setAttribute("Admin", adminList);

    req.getRequestDispatcher("/FindAdmin.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Administrators> adminList = new ArrayList<Administrators>();

    // Retrieve and validate name.
    // firstname is retrieved from the form POST submission. By default, it
    // is populated by the URL query string (in FindUsers.jsp).
    String userName = req.getParameter("username");
    if (userName == null || userName.trim().isEmpty()) {
      messages.put("success", "Please enter a valid username.");
    } else {
      // Retrieve admin, and store as a message.
      try {
        adminList = adminDao.getAdministratorsFromUserName(userName);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + userName);
    }
    req.setAttribute("adminList", adminList);

    req.getRequestDispatcher("/FindAdmin.jsp").forward(req, resp);
  }

}
