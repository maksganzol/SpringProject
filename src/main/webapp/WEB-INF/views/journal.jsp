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
    <style>
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            border-collapse: collapse;
            text-align: center;
        }
        th, td:first-child {
            background: #AFCDE7;
            color: white;
            padding: 10px 20px;
        }
        th, td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: white;
        }
        td {
            background: #D8E6F3;
        }
        th:first-child, td:first-child {
            text-align: left;
        }
    </style>
</head>
<body>
<h2>${group}</h2>
<table>
    <tr>
        <th>Name</th>
        <c:forEach items="${subjectList}" var="sub">
            <th>${sub}</th>
        </c:forEach>
    </tr>

    <c:forEach items="${studList}" var="stud">
        <tr>
            <td>${stud.name}</td>
            <c:forEach items="${stud.keySet}" var="subject">
                <td>${stud.card[subject]}</td>
            </c:forEach>
        </tr>
    </c:forEach>


</table>
</body>
</html>
