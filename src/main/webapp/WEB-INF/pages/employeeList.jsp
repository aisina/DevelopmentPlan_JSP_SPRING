<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="MVC_PROJECT.model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 26.12.2016
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<jsp:useBean id="employeeList" class="MVC_PROJECT.model.dao.EmployeeListDAO" scope="page"/>--%>

<p><a href="/adminPage">На главную</a></p>
<p><a href="${pageContext.servletContext.contextPath}/userLogout">Выход</a></p>


<form method="GET" action="/addEmployee">
    <input type="submit" value="Добавить сотрудника" />
</form>

<br/>



<table border="1">
    <tr>
        <td>ID</td>
        <td>ФИО</td>
        <td>Отдел</td>
        <td>Должность</td>
        <td>e-mail</td>
        <td></td>
    </tr>

    <c:forEach items="${employeeList}" var="employee">
    <tr>

        <td>${employee.id}</td>
        <td>${employee.name}</td>
        <td>${employee.department}</td>
        <td>${employee.position}</td>
        <td>${employee.email}</td>
        <form action="/deleteEmployee">
            <td><input class="button" type="submit" value="Удалить">
                <input type="hidden" name="employeeID" value="${employee.id}">
            </td>
        </form>

    </tr>
    </c:forEach>

</table>
</body>
</html>
