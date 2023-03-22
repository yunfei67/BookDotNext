<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a BookReview</title>
</head>
<body>
<%--  	<form action="findusers" method="post">
		<h1>Search for a BlogUser by FirstName</h1>
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>  --%>
	<br/>
	<div id="bookreviewcreate"><a href="bookreviewcreate">Create BookReview</a></div>
	<br/>
	<h1>Matching BookReview</h1>
        <table border="1">
            <tr>
                <th>ReviewId</th>
                <th>ReviewScore</th>
                <th>Content</th>
                <th>Created</th>
                <th>UserName</th>
                <th>BookTitle</th>
                <th>Delete BookReview</th>
            </tr>
            <c:forEach items="${bookReview}" var="bookReview" >
                <tr>
                    <td><c:out value="${bookReview.getReviewId()}" /></td>
                    <td><c:out value="${bookReview.getReviewScore()}" /></td>
                    <td><c:out value="${bookReview.getContent()}" /></td>
                    <td><fmt:formatDate value="${bookReview.getCreated()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${bookReview.getUser().getUserName()}" /></td>
                    <td><c:out value="${bookReview.getBookInfo().getBookTitle()}" /></td>

                    <%-- <td><a href="userblogposts?username=<c:out value="${blogUser.getUserName()}"/>">BlogPosts</a></td>
                    <td><a href="blogcomments?username=<c:out value="${blogUser.getUserName()}"/>">BlogComments</a></td> --%>
                    <td><a href="bookreviewdelete?reviewid=<c:out value="${bookReview.getReviewId()}"/>">Delete</a></td>
                    <%-- <td><a href="userupdate?username=<c:out value="${blogUser.getUserName()}"/>">Update</a></td> --%>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
