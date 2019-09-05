<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/31/2019
  Time: 12:58 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Student Checking System</title>
</head>
<body>
<h1>Student education system  </h1><small>by max tsurkan</small>
<br>
<br>
<spring:form method="POST"  action="${contextPath}/login/check" modelAttribute="user">
    <spring:label path="login">Login</spring:label>
    <spring:input path="login"/>
    <spring:label path="password">Password</spring:label>
    <spring:input path="password"/>
    <spring:button>Log in</spring:button>
</spring:form>
</body>
</html>
