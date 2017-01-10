<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 28.12.2016
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Изменение данных</title>
</head>
<body>
Данные успешно изменены.
<p><form:form action="/userPage" commandName="authorizedUser">
    <input type="submit" value="На главную"/>
</form:form></p>

</body>
</html>
