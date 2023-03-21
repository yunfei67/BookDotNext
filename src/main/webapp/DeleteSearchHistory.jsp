<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 3/20/23
  Time: 6:45 PM
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
  <title>Delete a User</title>
</head>
<body>
<h1>${messages.title}</h1>
<form action="searchhistorydelete" method="post">
  <p>
  <div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
    <label for="searchId">SearchId</label>
    <input id="searchId" name="searchId" value="${fn:escapeXml(param.searchid)}">
  </div>
  </p>
  <p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
  </p>
</form>
<br/><br/>

</body>
</html>
