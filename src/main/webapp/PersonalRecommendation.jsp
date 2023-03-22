<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Personal Recommendation</title>
</head>
<body>
	<form action="PersonalRecommendation" method="post">
	    <h1>Search for personal recommendation by UserId</h1>
	    <p>
	        <label for="userId">UserId</label>
	        <input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
	    </p>
	    <p>
	        <input type="submit">
	        <br/><br/><br/>
	        <span id="successMessage"><b>${messages.success}</b></span>
	    </p>
	</form>
	<h1>Personal Recommendation</h1>
        <table border="1">
            <tr>
            	<th>BookTitle</th>
            	<th>AuthorName</th>
            	<th>PublishedDate</th>
            	<th>PublishedName</th>
                <th>Categories</th>
                <th>Description</th>
            </tr>
            <c:forEach items="${personalRecommend}" var="personalRecommend" >
                <tr>
                	<td><c:out value="${personalRecommend.getBookTitle()}" /></td>
                	<td><c:out value="${personalRecommend.getAuthorName()}" /></td>
                	<td><c:out value="${personalRecommend.getPublishedDate()}" /></td>
                	<td><c:out value="${personalRecommend.getPublisherName()}" /></td>
                	<td><c:out value="${personalRecommend.getCategories()}" /></td>
                	<td><c:out value="${personalRecommend.getDescription()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>