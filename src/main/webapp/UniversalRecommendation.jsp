<%@ page import="com.application.bookdotnext.model.BookInfo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wujiena
  Date: 2023/3/20
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>UniversalRecommendation</title>
</head>
<body>
<form action="getuniversalRecommendation" method="get">
    <h1>Get Universal Recommendation</h1>
    <table border="1">
        <tr>
            <th>BookId</th>
            <th>BookTitle</th>
            <th>PublishedDate</th>
            <th>Description</th>
            <th>InfoLink</th>
            <th>Categories</th>
            <th>PublisherName</th>
            <th>AuthorName</th>
            <th>ImageLink</th>
        </tr>
        <c:forEach items="${top5Books}" var="top5Books" >
            <tr>
                <td><c:out value="${top5Books.getBookId()}" /></td>
                <td><c:out value="${top5Books.getBookTitle()}" /></td>
                <td><c:out value="${top5Books.getPublishedDate()}" /></td>
                <td><c:out value="${top5Books.getDescription()}" /></td>
                <td><c:out value="${top5Books.getInfoLink()}" /></td>
                <td><c:out value="${top5Books.getCategories()}" /></td>
                <td><c:out value="${top5Books.getPublisherName()}" /></td>
                <td><c:out value="${top5Books.getAuthorName()}" /></td>
                <td><c:out value="${top5Books.getImageLink()}" /></td>


            </tr>
        </c:forEach>
    </table>
</form>
    </body>
</html>