<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a BookReview</title>
</head>
<body>
	<h1>Create BookReview</h1>
	<form action="bookreviewcreate" method="post">
		<p>
			<label for="userId">UserId</label>
			<input id="userId" name="userId" value="">
		</p>
		<p>
			<label for="bookId">BookId</label>
			<input id="bookId" name="bookId" value="">
		</p>
		<p>
			<label for="reviewscore">ReviewScore</label>
			<input id="reviewscore" name="reviewscore" value="">
		</p>
		<p>
			<label for="content">Content</label>
			<input id="content" name="content" value="">
		</p>
		<p>
			<label for="created">Created (yyyy-mm-dd)</label>
			<input id="created" name="created" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>