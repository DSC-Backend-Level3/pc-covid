<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/17/2021
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Page</title>
</head>
<body>
<h1>Update Personal Information</h1>
<c:set var="result" value="${requestScope.PROFILE_PAGE}"/>
<c:set var="province" value="${requestScope.PROFILE_PROVINCE}"/>
<c:set var="district" value="${requestScope.PROFILE_DISTRICT}"/>
<c:set var="ward" value="${requestScope.PROFILE_WARD}"/>
<c:set var="provinceList" value="${requestScope.PROVINCE_LIST}"/>
<c:set var="listDictrict" value="${requestScope.LIST_DISTRICT_BY_PROVINCE}"/>
<form action="update-info" method="post">
    <c:if test="${not empty result}">
        First name:<br>
        <input type="text" name="txtFirstName" value="${result.firstName}"> <br>
        Last name :<br>
        <input type="text" name="txtLastName" value="${result.lastName}"> <br>
        Gender :<br>
        <select name="cboGender">
        <c:choose>
            <c:when test="${result.gender == 'F'}">
                <option>Female</option>
                <option>Male</option>
            </c:when>
            <c:otherwise>
                <option>Male</option>
                <option>Female</option>
            </c:otherwise>
        </c:choose>
    </select><br>
        Date of birth: <br>
        <input type="text" name="txtDOB" value="${result.DOB}"><br>
        Identity card:<br>
        <input type="text" name="txtID" value="${result.id}"><br>
        Phone number: <br>
        <input type="text" name="txtPhoneNumber" value="${result.phoneNumber}"><br>
        Health insurance card number:<br>
        <input type="text" name="txtHealthInsuranceID" value="${result.healthInsuranceID}"><br>
        Email:<br>
        <input type="text" name="txtEmail" value="${result.email}"><br>

        Province/City: <br>
        <select name="cboProvince">
            <option value="${province.id}">${province.name}</option>
            <c:forEach items="${provinceList}" var="provinceVar">
                <c:if test="${provinceVar.id != province.id}">
                    <option value="${provinceVar.id}">${provinceVar.name}</option>
                </c:if>
            </c:forEach>
        </select><br>

        District: <br>
        <select name="cboDistrict">
            <option value="${district.id}">${district.name}</option>
            <c:forEach items="${listDictrict}" var="districtVar">
                <option value="${districtVar.id}">${districtVar.name}</option>
            </c:forEach>
        </select><br>
        Ward: <br>
        <select name="cboWard">
            <option value="${ward.id}">${ward.name}</option>
        </select> <br>
        House number: <br>
        <input type="text" name="txtHouseNumber" value="${result.houseNumber}"><br>
    </c:if>
    <input type="submit" value="Save Changes" name="btUpdate">
</form>
</body>
</html>
