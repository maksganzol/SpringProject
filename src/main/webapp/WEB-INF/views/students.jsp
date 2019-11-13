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
    <style>
        table {
            border-spacing: 0 10px;
            font-family: 'Open Sans', sans-serif;
            font-weight: bold;
        }
        th {
            padding: 10px 20px;
            background: #56433D;
            color: #F9C941;
            border-right: 2px solid;
            font-size: 0.9em;
        }
        th:first-child {
            text-align: left;
        }
        th:last-child {
            border-right: none;
        }
        td {
            vertical-align: middle;
            padding: 10px;
            font-size: 14px;
            text-align: center;
            border-top: 2px solid #56433D;
            border-bottom: 2px solid #56433D;
            border-right: 2px solid #56433D;
        }
        td:first-child {
            border-left: 2px solid #56433D;
            border-right: none;
        }
        td:nth-child(2){
            text-align: left;
        }
    </style>
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
            <td><a href="${contextPath}/students/card?name=${stud.name}">${stud.name}</a></td>
            <td>${stud.group}</td>
            <td>${stud.age}</td>
            <td>${stud.phone}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
