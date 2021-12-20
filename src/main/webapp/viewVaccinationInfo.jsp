<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/16/2021
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vaccination Information Page</title>
</head>
<body>
<h1>Vaccination Information</h1>

<c:set var="resident" value="${requestScope.USER_INFO}"/>
<c:set var="result" value="${requestScope.VACCINATION_INFO}"/>
<c:set var="vaccines" value="${requestScope.VACCINES}"/>
<c:set var="provinces" value="${requestScope.PROVINCES}"/>
<c:set var="districts" value="${requestScope.DISTRICTS}"/>
<c:set var="wards" value="${requestScope.WARDS}"/>
<h2>${resident.firstName} ${resident.lastName}</h2>
<c:if test="${not empty result}">
    <table border="1">
        <thead>
        <tr>
            <th>Dose.</th>
            <th>Vaccine</th>
            <th>Date</th>
            <th>Vaccination facility</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${result}" var="dto" varStatus="obj">
            <tr>
                <td>
                        ${obj.count}
                </td>
                <td>
                    <c:forEach items="${vaccines}" var="vaccineDTO" varStatus="i">
                        <c:if test="${vaccineDTO.id eq dto.vaccineID}">
                            <c:set var="vaccineName" value="${vaccineDTO}"/>
                        </c:if>
                    </c:forEach>
                    ${vaccineName.name}
                </td>
                <td>
                        ${dto.date}
                </td>
                <td>

                    <c:forEach items="${wards}" var="wardDTO" varStatus="w">
                        <c:if test="${wardDTO.id eq dto.wardID}">
                            <c:set var="ward" value="${wardDTO}"/>
                        </c:if>
                    </c:forEach>

                    <c:forEach items="${districts}" var="districtDTO" varStatus="d">
                        <c:if test="${districtDTO.id eq ward.districtID}">
                            <c:set var="district" value="${districtDTO}"/>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${provinces}" var="provinceDTO" varStatus="p">
                        <c:if test="${provinceDTO.id == district.provinceID}">
                            <c:set var="province" value="${provinceDTO}"/>
                        </c:if>
                    </c:forEach>
                    ${province.name}, ${district.name}, ${ward.name}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>

<br>
<form action="view">
    <input type="submit" value="View Profile" name="btAction">
</form>
<button><a href="changePassword.html">Change Password</a></button>
</body>
</html>
