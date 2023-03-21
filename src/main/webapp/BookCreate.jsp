<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User</title>
</head>
<body>
	<h1>Create BookInfo</h1>
	<form action="bookcreate" method="post">
		<p>
			<label for="bookTitle">bookTitle</label>
			<input id="bookTitle" name="bookTitle" value="">
		</p>
		<p>
			<label for="publishedYear">publishedYear</label>
			<input id="publishedYear" name="publishedYear" value="">
		</p>
		<p>
			<label for="description">description</label>
			<input id="description" name="description" value="">
		</p>
		<p>
			<label for="infoLink">infoLink</label>
			<input id="infoLink" name="infoLink" value="">
		</p>
		<p>
			<label for="categories">categories</label>
			<input id="categories" name="categories" value="">
		</p>
		<p>
			<label for="publisherName">publisherName</label>
			<input id="publisherName" name="publisherName" value="">
		</p>
		<p>
			<label for="authorName">authorName</label>
			<input id="authorName" name="authorName" value="">
		</p>
		<p>
			<label for="imageLink">imageLink</label>
			<input id="imageLink" name="imageLink" value="">
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