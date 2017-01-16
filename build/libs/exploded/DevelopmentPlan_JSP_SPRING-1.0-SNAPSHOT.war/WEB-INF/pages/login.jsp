<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 22.12.2016
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Главная</title>
</head>
<body>

<form name="frm" action="<c:url value='/userLogon'/>" method="post">
    <table>
        <tr>
            <td>User:</td>
            <td><input type="text" name="username"></td>
        </tr>

        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>

        <tr><td colspan="2"><input name="submit" type="submit"></td></tr>
        <tr><td colspan="2"><input name="reset" type="reset"></td></tr>
    </table>

    <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>"
           value="<c:out value="${_csrf.token}"/>"/>
</form>

<%--<form:form method="POST" modelAttribute="userCommandName" action="/userLogon">--%>

    <%--&lt;%&ndash;в форму передали объект User при помощи commandName. path должны называться также, как и поля класса User&ndash;%&gt;--%>
    <%--<form:label path="username">Логин:</form:label>--%>
    <%--<form:input path="username" />--%>

    <%--<form:label path="password">Пароль:</form:label>--%>
    <%--<form:password path="password" />--%>

    <%--<input type="submit" value="Войти"/>--%>
    <%--&lt;%&ndash;после того, как заполним форму, значения запишутся в User, User передастся в /userLogon&ndash;%&gt;--%>

    <%--&lt;%&ndash;<input type="hidden" name="${_csrf.parameterName}"&ndash;%&gt;--%>
           <%--&lt;%&ndash;value="${_csrf.token}" />&ndash;%&gt;--%>

<%--</form:form>--%>

</body>
</html>



