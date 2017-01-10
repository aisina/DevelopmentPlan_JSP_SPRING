<%@ page import="MVC_PROJECT.model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 27.12.2016
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Добавление плана</title>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />--%>
</head>
<body>

<jsp:useBean id="employeeList" class="MVC_PROJECT.model.dao.EmployeeListDAO" scope="page"/>
<jsp:useBean id="planTypes" class="MVC_PROJECT.model.PlanTypes" scope="page"/>

<p><a href="/">На главную</a></p>

<form:form action="/addPlan" method="POST" commandName="addNewPlan">
    <table>
        <tr>
            <td><form:label path="year">Год</form:label></td>
            <td><form:input path="year" /></td>
        </tr>
        <tr>
            <td><form:label path="employeeName">ФИО сотрудника</form:label></td>
            <td>
                <form:select path="employeeName">
                    <%
                        for (Employee employee : employeeList.getAllList()) {
                    %>
                    <option><%= employee.getName()%></option>
                    <%}%>
                </form:select>
            </td>
        </tr>
        <tr>
            <td> <form:label path="employeePosition">Должность сотрудника</form:label></td>
            <td><form:input path="employeePosition" /></td>
        </tr>
        <tr>
            <td><form:label path="planType">План развития</form:label></td>
            <td>
                <form:select path="planType">
                    <%
                        for (String planType : planTypes.getPlantypes()) {
                    %>
                    <option><%= planType%></option>
                    <%}%>
                </form:select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Добавить план"/></td>
        </tr>
    </table>

</form:form>

</body>
</html>
