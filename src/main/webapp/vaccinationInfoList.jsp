<%@ page import="dto.VaccinationInfoDTO" %>
<%@ page import="dto.ResidentDTO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/19/2021
  Time: 8:35 PM
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
        <c:set var="result" value="${requestScope.vaccinationInfoList}"/>
        <c:set var="vaccineList" value="${requestScope.vaccineList}"/>
        <c:set var="residentList" value="${requestScope.residentList}"/>
        <c:set var="locationList" value="${requestScope.locationList}"/>
        <c:set var="userName" value="${sessionScope.name}"/>

        <button><a href="logout">Logout</a></button>
        <div>
            <h1>HOME PAGE</h1>
            <h2>Welcome ${userName} </h2>
        </div>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                <tr>
                    <th>No.</th>
                    <th>Vaccination Information ID</th>
                    <th>Resident ID</th>
                    <th>Resident First Name</th>
                    <th>Resident Last Name</th>
                    <th>Vaccine Name</th>
                    <th>Vaccination's Province</th>
                    <th>Vaccination's District</th>
                    <th>Vaccination's Ward</th>
                    <th>Vaccination Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="dto" items="${result}" varStatus="obj" >
                    <tr>
                        <td>${obj.count}</td>
                        <td>${dto.id}</td>
                        <td>${dto.residentID}</td>
                        <td><c:out value="${residentList[obj.index].firstName}"/></td>
                        <td><c:out value="${residentList[obj.index].lastName}"/></td>

                        <td><c:out value="${vaccineList[obj.index].name}"/></td>

                        <td><c:out value="${locationList[obj.index].provinceName}"/></td>
                        <td><c:out value="${locationList[obj.index].districtName}"/></td>
                        <td><c:out value="${locationList[obj.index].wardName}"/></td>
                        <td>
                            <%
                                VaccinationInfoDTO dto = (VaccinationInfoDTO) pageContext.getAttribute("dto");
                                Timestamp date = dto.getDate();
                                LocalDateTime localDateTime = date.toLocalDateTime();
                                String formattedDate = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);

                            %>
                            <%= formattedDate %>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty result}">
            <h2>Doctors' information is unavailable!</h2>
        </c:if>
        <c:url value="add" var="Add_Vaccination">
            <c:param name="btAction" value="Add Vaccination"/>
        </c:url>
        <button><a href="${Add_Vaccination}">Adding New Vaccination</a></button>
    </body>
</html>
