<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>ADMIN</title>
    </head>
    <body>
    <c:set var="userName" value="${sessionScope.name}"/>
    <div>
        <h1>ADMIN HOME</h1>
        <h2>Welcome ${userName} </h2>
    </div>

    <div>
        <c:url value="viewVaccine" var="View_Vaccine">
            <c:param name="btAction" value="View Vaccine"/>
        </c:url>
        <a href="${View_Vaccine}">Vaccine Information</a>
        <c:url value="viewDoctor" var="View_Doctor">
            <c:param name="btAction" value="View Doctor"/>
        </c:url>
        <a href="${View_Doctor}">Doctor Information</a>
    </div>
    </body>
</html>
