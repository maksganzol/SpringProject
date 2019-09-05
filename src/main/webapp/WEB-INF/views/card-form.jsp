<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/31/2019
  Time: 6:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Student</title>
</head>
<body>
<spring:form method="post" modelAttribute="name" action="${contextPath}/students/card">
    <spring:label path="name">Name:</spring:label>
    <spring:input path="name"/>
    <spring:button>Get journal</spring:button>
</spring:form>
</body>
</html>
