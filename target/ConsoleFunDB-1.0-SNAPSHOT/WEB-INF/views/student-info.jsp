<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/31/2019
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Student ${student.name} added</h1>
<hr>
<h3>Info</h3>
<table>
    <tr>
        <td>Name</td>
        <td>${student.name}</td>
    </tr>
    <tr>
        <td>Group</td>
        <td>${student.group}</td>
    </tr>
    <tr>
        <td>Age</td>
        <td>${student.age}</td>
    </tr>
    <tr>
        <td>Phone</td>
        <td>${student.phone}</td>
    </tr>
</table>
</body>
</html>
