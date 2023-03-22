<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Book</title>
</head>
<body>
	<form action="findBooks" method="post">
		<h1>Search for a Book by BookTitle</h1>
		<p>
			<label for="bookTitle">BookTitle</label>
			<input id="bookTitle" name="bookTitle" value="${fn:escapeXml(param.bookTitle)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="bookCreate"><a href="bookcreate">Create Book</a></div>
	<br/>
	<h1>Matching BookInfo</h1>
        <table border="1">
            <tr>
                <th>BookId</th>
                <th>BookTitle</th>
                <th>PublishedYear</th>
                <th>Description</th>
                <th>InfoLink</th>
                <th>Categories</th>
                <th>PublisherName</th>
                <th>AuthorName</th>
                <th>ImageLink</th>
<%--                <th>Delete Book</th>--%>
                <th>Update Book</th>
                <th>Reviews</th>
            </tr>
            <c:forEach items="${bookInfo}" var="bookInfo" >
                <tr>
                    <td><c:out value="${bookInfo.getBookId()}" /></td>
                    <td><c:out value="${bookInfo.getBookTitle()}" /></td>
                    <td><c:out value="${bookInfo.getPublishedDate()}" /></td>
                    <td><c:out value="${bookInfo.getDescription()}" /></td>
                    <td><c:out value="${bookInfo.getInfoLink()}" /></td>
                    <td><c:out value="${bookInfo.getCategories()}" /></td>
                    <td><c:out value="${bookInfo.getPublisherName()}" /></td>
                    <td><c:out value="${bookInfo.getAuthorName()}" /></td>
                    <td><img src="${bookInfo.getImageLink()}"></td>
<%--                    <td><img src=<c:out value="${bookInfo.getImageLink()}"/> alt=""></td>--%>
<%--                    <td><a href="userblogposts?username=<c:out value="${blogUser.getUserName()}"/>">BlogPosts</a></td>--%>
<%--                    <td><a href="blogcomments?username=<c:out value="${blogUser.getUserName()}"/>">BlogComments</a></td>--%>
<%--                    <td><a href="userdelete?username=<c:out value="${blogUser.getUserName()}"/>">Delete</a></td>--%>
                    <td><a href="bookupdate?bookId=<c:out value="${bookInfo.getBookId()}"/>">Update</a></td>
                    <td><a href="findBookReviewByBookId?bookId=<c:out value="${bookInfo.getBookId()}"/>">Reviews</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>

