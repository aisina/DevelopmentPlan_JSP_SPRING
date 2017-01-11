<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 28.12.2016
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Изменение логина и пароля</title>
</head>
<body>

<form:form action="/changePassAndLogin" modelAttribute="authorizedUser" method="POST">
<table>
    <tr>
        <td><form:label path="username">Новый логин:</form:label></td>
        <td><form:input path="username" /></td>
    </tr>
    <tr>
        <td><form:label path="confirmUsername">Повторите логин:</form:label></td>
        <td><form:input path="confirmUsername" /></td>
    </tr>
    <tr>
        <td><form:label path="password">Новый пароль:</form:label></td>
        <td><form:password path="password" /></td>
    </tr>
    <tr>
        <td><form:label path="confirmPassword">Повторите пароль:</form:label></td>
        <td><form:password path="confirmPassword" /></td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" value="Сохранить"/></td>
    </tr>
</table>
</form:form>

</body>
</html>
