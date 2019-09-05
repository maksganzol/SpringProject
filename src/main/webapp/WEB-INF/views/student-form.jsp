<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/31/2019
  Time: 12:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Add student</title>
</head>
<body>
<spring:form action="${contextPath}/students/add" method="post" modelAttribute="student">
    <spring:label path="name">Name</spring:label>
    <spring:input path="name"/>
    <spring:label path="group">Group</spring:label>
    <spring:input path="group"/>
    <spring:label path="age">Age</spring:label>
    <spring:input path="age"/>
    <spring:label path="phone">Phone</spring:label>
    <spring:input path="phone"/>
    <spring:button>Add</spring:button>
</spring:form>

</body>
</html>
