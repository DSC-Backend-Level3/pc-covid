<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/22/2021
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>addVaccinationInfoInvalid</title>
</head>
    <body>
        <c:set var="vaccine" value="${requestScope.vaccine}"/>
        <c:set var="province" value="${requestScope.province}"/>
        <c:set var="district" value="${requestScope.district}"/>
        <c:set var="ward" value="${requestScope.ward}"/>
        <h1>Vaccination Information Form</h1>
        <form action="add" method="POST">
            Resident ID <input type="text" name="residentID" value="${param.residentID}" required/><br/>
            ${requestScope.notExistedError}<br/>
            Vaccination ID <input type="text" name="id" value="${param.id}" required/><br/>
            ${requestScope.ExistedError}<br/>
            Vaccine Name
            <select name="vaccineID"  required>
                <option value="${param.vaccineID}">${vaccine.name}</option>
            </select>
            Province
            <select id="province" name="provinceID" required>
                <option value="${province.id}">${province.name}</option>
            </select>
            District
            <select id="district" name="districtID" required>
                <option value="${district.id}">${district.name}</option>
            </select>
            Ward
            <select id="ward" name="wardID"  required>
                <option value="${ward.id}">${ward.name}</option>
            </select>
            Injection's Date <input type="date" name="date" value="${param.date}" required/><br/>${requestScope.dateErrorMessage}
            <input type="submit" value="Add Vaccination" name="btAction">
        <%--    <input type="submit" value="Add Vaccination" name="btAction">--%>
        </form>
    </body>
</html>
