<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>SearchHistory</title>
</head>
<body>
<form action="findsearchhistory" method="get">
    <h1>Search for search history by UserId</h1>
    <p>
        <label for="UserId">UserId</label>
        <input id="UserId" name="UserId" value="${fn:escapeXml(param.UserId)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<h1>Matching SearchHistory</h1>
<table border="1">
    <tr>
        <th>SearchId</th>
        <th>UserId</th>
        <th>VisitedBooks</th>
        <th>SearchTime</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${searchHistory}" var="searchHistory" >
        <tr>
            <td><c:out value="${searchHistory.getSearchId()}" /></td>
            <td><c:out value="${searchHistory.getUserId()}" /></td>
            <td><c:out value="${searchHistory.getVisitedBookId()}" /></td>
            <td><fmt:formatDate value="${searchHistory.getCreated()}" pattern="yyyy-MM-dd hh:mm:ss.SSS"/></td>

            <td><a href="searchhistorydelete?searchid=<c:out value="${searchHistory.getSearchId()}"/>">Delete</a></td>
                <%--                    <td><a href="blogcomments?username=<c:out value="${blogUser.getUserName()}"/>">BlogComments</a></td>--%>
                <%--                    <td><a href="userdelete?username=<c:out value="${blogUser.getUserName()}"/>">Delete</a></td>--%>
                <%--                    <td><a href="userupdate?username=<c:out value="${blogUser.getUserName()}"/>">Update</a></td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>


