<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/31/2019
  Time: 1:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Students</title>
</head>
<body>
<link rel="stylesheet" href="${contextPath}/resources/css/table-style.css"/>

<h2>Students</h2>
<table>
    <tr>
        <th>Name</th>
        <th>Group</th>
        <th>Age</th>
        <th>Phone</th>
    </tr>
    <c:forEach items="${studList}" var="stud">
        <tr>
            <td>${stud.name}</td>
            <td>${stud.group}</td>
            <td>${stud.age}</td>
            <td>${stud.phone}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
