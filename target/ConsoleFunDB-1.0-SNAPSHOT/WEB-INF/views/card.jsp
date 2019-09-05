<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/31/2019
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Journal</title>
</head>
<body>
<ul>
<c:forEach items="${subList}" var="subj">
        <li>${subj}</li>
</c:forEach>
</ul>
</body>
</html>
