<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/10/2019
  Time: 7:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Group journal</title>
</head>
<body>
<spring:form action="${contextPath}/students/journal" method="post" modelAttribute="student">
    <spring:label path="group">Group</spring:label>
    <spring:input path="group"/>
    <spring:button>Get journal</spring:button>
</spring:form>
<input type="button" value="Показать спиксок групп" id="get-grp">
<div id = "grp-list"></div>
<script>
    window.onload = function () {
        document.querySelector("#get-grp").onclick = function () {
            groupListWithAjax();
        }
    };

    function groupListWithAjax(){
        var request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if(request.readyState == 4) {
                document.querySelector("#grp-list").innerHTML = request.responseText;
            }
        };
        request.open("GET", "${contextPath}/students/grp-journal-ajax");
        request.send();


    }
</script>
</body>
</html>
