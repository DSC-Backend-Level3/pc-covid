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
<form action="UpdateProfileController">
    <c:if test="${not empty result}">
        First name: <input type="text" name="txtFirstName" value="${result.firstName}">
        Last name : <input type="text" name="txtLastName" value="${result.lastName}">
        Gender : <select name="cboGender">
        <c:choose>
            <c:when test="${result.gender eq F}">
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
        <select name="txtProvince">
            <option value="${province.id}">${province.name}</option>
            <c:forEach items="${provinceList}" var="provinceVar">
                <c:if test="${provinceVar.id != province.id}">
                    <option value="${provinceVar.id}">${provinceVar.name}</option>
                </c:if>
            </c:forEach>
        </select>
        <!--WARD/ district!-->
        House number: <br>
        <input type="text" name="txtHouseNumber" value="${result.houseNumber}">
    </c:if>
    <input type="button" value="Save Changes" name="btUpdate">
</form>

</body>
</html>
