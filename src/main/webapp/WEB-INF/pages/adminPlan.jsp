<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 28.12.2016
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Просмотр плана</title>
</head>
<body>

<p><a href="/userLogout">Выйти</a></p>
<p><a href="/addPlan">Добавить план</a></p>

<table border="1">
    <tr>
        <td>Год</td>
        <td>ФИО</td>
        <td>Должность сотрудника</td>
        <td>Тип плана</td>
    </tr>

    <c:forEach items="${adminPlanByYear}" var="plan" varStatus="status">
        <tr valign="top">
            <td>${plan.getYear()}</td>
            <td>${plan.getEmployeeName()}</td>
            <td>${plan.getEmployeePosition()}</td>
            <td>${plan.getPlanType()}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
