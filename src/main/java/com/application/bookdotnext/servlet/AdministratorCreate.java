package com.application.bookdotnext.servlet;
import com.application.bookdotnext.dal.AdministratorsDao;
import com.application.bookdotnext.model.Administrators;
import com.application.bookdotnext.model.Users;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
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


@WebServlet("/admincreate")
public class AdministratorCreate extends HttpServlet {
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
    //Just render the JSP.
    req.getRequestDispatcher("/AdministratorCreate.jsp").forward(req, resp);
  }
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    messages.put("title", "Create new Admin");

    // Retrieve and validate name.
    String userName = req.getParameter("username");
    if (userName == null || userName.trim().isEmpty()) {
      messages.put("Fail", "Invalid UserName");
    } else {
      // Create the Admin.

      String firstName = req.getParameter("firstname");
      String lastName = req.getParameter("lastname");
      String stringPassword = req.getParameter("password");
      // login must be in the format yyyy-MM-dd hh:mm:ss.SSS
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");





      try {

        Administrators newAdmin = new Administrators(userName, firstName, lastName,stringPassword, true,
            new Timestamp(new Date().getTime()));
        newAdmin = adminDao.create(newAdmin);
        messages.put("success", "Successfully created Admin " + userName);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/AdministratorCreate.jsp").forward(req, resp);
  }
}



