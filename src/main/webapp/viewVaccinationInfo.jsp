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
<!-- Tên người dùng -->
<c:set var="result" value="${requestScope.VACCINATION_INFO}"/>
<c:set var="vaccine" value="${requestScope.VACCINE}"/>
<c:set var="province" value="${requestScope.PROVINCE}"/>
<c:set var="district" value="${requestScope.DISTRICT}"/>
<c:set var="ward" value="${requestScope.WARD}"/>
<c:if test="${not empty result}">
    <table>
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
                    <c:forEach items="${vaccine}" var="vaccineDTO" varStatus="i">
                        <c:if test="${vaccineDto.vaccineID eq dto.vaccineID}">
                            ${vaccineDto.vaccineName}
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                        ${dto.date}
                </td>
                <td>
                    <c:forEach items="${province}" var="provinceDTO" varStatus="p">
                        <c:if test="${provinceDTO.id eq dto.province}">
                            <c:set var="provinceName" value="${provinceDTO.name}"/>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${district}" var="districtDTO" varStatus="d">
                        <c:if test="${districtDTO.id eq dto.district}">
                            <c:set var="districtName" value="${districtDTO.name}"/>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${ward}" var="wardDTO" varStatus="w">
                        <c:if test="${wardDTO.id eq dto.ward}">
                            <c:set var="wardName" value="${wardDTO.name}"/>
                        </c:if>
                    </c:forEach>
                        ${provinceName + "," + districtName + ", " + wardName}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<button type="button"><a href="https://github.com/DSC-Backend-Level3">VIEW PROFILE</a></button>
</body>
</html>
