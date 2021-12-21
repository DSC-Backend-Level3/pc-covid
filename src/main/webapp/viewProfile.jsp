<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/16/2021
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile Page</title>
</head>
<body>
<button><a href="homepage">Home Page</a></button>
<h1>Personal Information</h1>
<c:set var="result" value="${requestScope.PROFILE_PAGE}"/>
<c:set var="province" value="${requestScope.PROFILE_PROVINCE}"/>
<c:set var="district" value="${requestScope.PROFILE_DISTRICT}"/>
<c:set var="ward" value="${requestScope.PROFILE_WARD}"/>
<c:if test="${not empty result}">
    <p>
        Full name: <br>
        ${result.firstName} ${result.lastName} <br>
        Gender :<br>
        <c:choose>
        <c:when test="${result.gender == 'F'}">Female</c:when>
        <c:otherwise>Male</c:otherwise>
    </c:choose> <br>
        Date of birth:<br>
            ${result.DOB}<br>
        Identity card:<br>
            ${result.id}<br>
        Phone number :<br>
            ${result.phoneNumber}<br>
        Health insurance card number:<br>
            ${result.healthInsuranceID}<br>
        Email:<br>
            ${result.email} <br>
        Province/City: <br>
            ${province.name} <br>
        District: <br>
            ${district.name}<br>
        Ward: <br>
            ${ward.name} <br>
        House number: <br>
            ${result.houseNumber}
    </p>
</c:if>
<%--<form action="view">--%>
<%--    <input type="submit" value="Update Profile" name="btAction">--%>
<%--</form>--%>
<c:url value="view" var="Update_Profile">
    <c:param name="btAction" value="Update Profile"/>
</c:url>
<button><a href="${Update_Profile}">Update Profile</a></button>
<button><a href="changePassword.html">Change Password</a></button>

</body>
</html>
