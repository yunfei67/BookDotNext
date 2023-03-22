<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="findusers" method="post">
		<h1>Search for a User by UserName</h1>
		<p>
			<label for="firstname">UserName</label>
			<input id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
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

    <br/>
    <div id="FindAdmin"><a href="findadmin">Find Administrator</a></div>
    <br/>
    <div id="getuniversalrecommendation"><a href="getuniversalrecommendation"> Universal Recommendations</a></div>
    <br/>

    <br/>
    <div id="FindBooks"><a href="findBooks"> Search Books </a></div>
    <br/>


    <br/>
	<h1>Matching Users</h1>
        <table border="1">
            <tr>
                <%-- <th>User ID</th> --%>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>DoB</th>
                <th>Reviews</th>
                <th>Search History</th>
                    <th>Personal Recommendation</th>

                <th>Delete User</th>
                <th>Update User</th>
            </tr>
            <c:forEach items="${Users}" var="User" >
                <tr>
                    <td><c:out value="${User.getUserName()}" /></td>
                    <td><c:out value="${User.getFirstName()}" /></td>
                    <td><c:out value="${User.getLastName()}" /></td>
                    <td><fmt:formatDate value="${User.getDob()}" pattern="yyyy-MM-dd"/></td>
<%--                    findBookReviewByUserId--%>
                    <td><a href="findBookReviewByUserId?userId=<c:out value="${User.getUserId()}"/>">Reviews</a></td>
                    <td><a href="findsearchhistory?userId=<c:out value="${User.getUserId()}"/>">Search History</a></td>
                    <td><a href="PersonalRecommendation?userId=<c:out value="${User.getUserId()}"/>">Personal Recommendations</a></td>
                    <td><a href="userdelete?username=<c:out value="${User.getUserName()}"/>">Delete</a></td>
                    <td><a href="usercreate?username=<c:out value="${User.getUserName()}"/>">Update</a></td>



                </tr>
            </c:forEach>
       </table>
</body>
</html>
