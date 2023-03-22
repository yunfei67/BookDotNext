<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find a Admin</title>
</head>
<body>
<form action="findadmin" method="post">
    <h1>Search for a Admin by UserName</h1>
    <p>
        <label for="username">UserName</label>
        <input id="username" name="username" value="${fn:escapeXml(param.username)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<br/>
<div id="userCreate"><a href="usercreate">Create User</a></div>
<br/>
<br/>
<div id="AdminCreate"><a href="admincreate">Create Admin</a></div>
<br/>
<h1>Matching Users</h1>
<table border="1">
    <tr>
        <%-- <th>User ID</th> --%>
        <th>UserName</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Last Login</th>
        <th>Delete</th>

    </tr>
    <c:forEach items="${adminList}" var="adminList" >
        <tr>
            <td><c:out value="${adminList.getUserName()}" /></td>
            <td><c:out value="${adminList.getFirstName()}" /></td>
            <td><c:out value="${adminList.getLastName()}" /></td>
            <td><fmt:formatDate value="${adminList.getLastLogin()}" pattern="yyyy-MM-dd"/></td>
            <td><a href="admindelete?username=<c:out value="${adminList.getUserName()}"/>">Delete</a></td>
<%--            <td><a href="uservote?username=<c:out value="${adminList.getUserName()}"/>">TopTen List</a></td>--%>
<%--            <td><a href="userdelete?username=<c:out value="${adminList.getUserName()}"/>">Delete</a></td>--%>
<%--            <td><a href="usercreate?username=<c:out value="${adminList.getUserName()}"/>">Update</a></td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
