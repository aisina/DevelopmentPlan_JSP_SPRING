<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 22.12.2016
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Главная</title>
</head>
<body>

<form:form method="POST" modelAttribute="userCommandName" action="/userLogon">

    <%--в форму передали объект User при помощи commandName. path должны называться также, как и поля класса User--%>
    <form:label path="username">Логин:</form:label>
    <form:input path="username" />

    <form:label path="password">Пароль:</form:label>
    <form:password path="password" />

    <input type="submit" value="Войти"/>
    <%--после того, как заполним форму, значения запишутся в User, User передастся в /userLogon--%>

</form:form>

</body>
</html>



