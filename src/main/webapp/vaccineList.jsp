<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/19/2021
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Vaccine</title>
    </head>
    <body>
        <c:set var="result" value="${requestScope.vaccineList}"/>
        <c:if test="${not empty result}">
            <table>
                <thead>
                    <tr>
                    <th>No.</th>
                    <th>Vaccine ID</th>
                    <th>Vaccine name</th>
                    <th>Origin</th>
                    <th>Organization</th>
                    <th>Time for 2rd dose</th>
                    </tr>
                </thead>
            <tbody>
            <c:forEach var="dto" items="${result}" varStatus="obj" >
                <tr>
                    <td>${obj.count}</td>
                    <td>${dto.id}</td>
                    <td>${dto.name}</td>
                    <td>${dto.country}</td>
                    <td>${dto.firm}</td>
                    <td>${dto.interval}</td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
        </c:if>

        <c:if test="${empty result}">
            <h2>Vaccine' information is unavailable!</h2>
        </c:if>

        <a href="addVaccine.html">Adding New Vaccine</a>
        <a href="adminHome.jsp">Home Page</a>
    </body>
</html>