<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a TopTenLists</title>
</head>
<body>
	<form action="FindTopTenList" method="post">
		<h1>Search for a TopTenList by UserId</h1>
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
	<br/>
	<div id="TopTenListCreate"><a href=TopTenListCreate>Create TopTenLists</a></div>
	<br/>
	<h1>Matching TopTenLists</h1>
        <table border="1">
            <tr>
            	<th>BookTitle</th>
            	<th>AuthorName</th>
            	<th>UserName</th>
            	<th>UserId</th>
                <th>Created</th>
                <th>Delete TopTenLists</th>
            </tr>
            <c:forEach items="${topTenList}" var="topTenList" >
                <tr>
                	<td><c:out value="${topTenList.getBookInfo().getBookTitle()}" /></td>
                	<td><c:out value="${topTenList.getBookInfo().getAuthorName()}" /></td>
                	<td><c:out value="${topTenList.getUser().getUserName()}" /></td>
                	<td><c:out value="${topTenList.getUser().getUserId()}" /></td>
                    <td><fmt:formatDate value="${topTenList.getCreated()}" pattern="yyyy-MM-dd"/></td>
                    
                    
                    <td><a href="topTenListDelete?toptenlistId=<c:out value="${topTenList.getTopTenListId()}"/>">Delete</a></td> 

                </tr>
            </c:forEach>
       </table>
</body>
</html>