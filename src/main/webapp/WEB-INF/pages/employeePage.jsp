
<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 26.12.2016
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>Личный кабинет сотрудника</title>
</head>
<body>

<%--<jsp:useBean id="employeePlanList" class="innopolisProject.dao.PlanDAO" scope="page"/>--%>

<%--<h2>Здравствуйте, ${authorizedUser.username}!</h2>--%>
<h2>Здравствуйте, ${username}!</h2>
<p><form action="/userLogout"><input type="submit" value="Выйти" /></form></p>

<p><form:form action="/showChangePassAndLoginView" commandName="authorizedUser" method="get">
    <input type="submit" value="Изменить логин и пароль" />
</form:form></p>


<%--<%request.setCharacterEncoding("UTF-8");%>
<%= "Здравствуйте, " + request.getParameter("username") + "!" %>
<%--${param["username"]} &lt;%&ndash;EL&ndash;%&gt;--%>

<table border="1">
    <tr>
        <td>Год</td>
        <td>Тип</td>
    </tr>

    <c:forEach items="${plans}" var="plan" varStatus="status">
        <tr valign="top">
            <td>${plan.getYear()}</td>
            <td>${plan.getPlanType()}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
