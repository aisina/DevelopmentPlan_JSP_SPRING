<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 26.12.2016
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Добавить сотрудника</title>
</head>
<body>

<%--<form action="<%= request.getContextPath()%>/addEmployee" method="post">--%>
<%--<form action="/addEmployee" method="POST"> тоже работает--%>

<%--commandName устарело. Использовать modelAttribute--%>
<form:form action="/addEmployee" method="POST" modelAttribute="addNewEmployee">
    <table>
        <tr>
            <td><form:label path="name">ФИО</form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="department">Отдел</form:label></td>
            <td><form:input path="department" /></td>
        </tr>
        <tr>
            <td><form:label path="position">Должность</form:label></td>
            <td><form:input path="position" /></td>
        </tr>
        <tr>
            <td><form:label path="email">E-mail</form:label></td>
            <td><form:input path="email" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Добавить"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
