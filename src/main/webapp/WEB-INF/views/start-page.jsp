<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/31/2019
  Time: 12:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Student Checking System</title>
</head>
<body>
<h1>Student education system</h1>
<small>by max tsurkan</small>
<br>
<i>User: ${username}</i>
<hr>
<strong>Option</strong>
<ul>
    <li><a href="${contextPath}/students/list">Show whole students list</a></li>
    <li><a href="${contextPath}/students/form">Add student</a></li>
    <li><a href="${contextPath}/students/card-form">Show student card</a></li>
    <li><a href="${contextPath}/students/group">Show group journal</a></li>
</ul>
</body>
</html>