<%@ page import="dto.VaccinationInfoDTO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="dto.ResidentDTO" %><%--
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
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Update Page</title>
</head>
<body>

<button><a href="homepage">Home Page</a></button>
<button><a href="logout">Logout</a></button>
<h1>Update Personal Information</h1>
<c:set var="result" value="${requestScope.PROFILE_PAGE}"/>
<c:set var="province" value="${requestScope.PROFILE_PROVINCE}"/>
<c:set var="district" value="${requestScope.PROFILE_DISTRICT}"/>
<c:set var="ward" value="${requestScope.PROFILE_WARD}"/>
<c:set var="provinceList" value="${requestScope.PROVINCE_LIST}"/>
<c:set var="listDictrict" value="${requestScope.DISTRICT_LIST}"/>
<c:set var="listWard" value="${requestScope.WARD_LIST}"/>
<form action="update-info" method="post">
    <c:if test="${not empty result}">
        First name:<br>
        <input type="text" name="firstName" value="${result.firstName}" maxlength="50"> <br>
        Last name :<br>
        <input type="text" name="lastName" value="${result.lastName}" maxlength="50"> <br>
        Gender :<br>
        <select name="gender">
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
        <%
            ResidentDTO dto = (ResidentDTO) pageContext.getAttribute("result");
            Timestamp date = dto.getDOB();
            String formattedDate = "";
            if (date != null ) {
                LocalDateTime localDateTime = date.toLocalDateTime();
                formattedDate = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
            }

        %>

        <input type="date" name="DOB" value="<%= formattedDate %>" required><br>
        Identity card:<br>
        <input type="text" name="txtID" value="${result.id}" disabled><br>
        Phone number: <br>
        <input type="text" name="phoneNumber" value="${result.phoneNumber}" pattern="[0-9]{10}" title="Phone numbers must include 10 numbers."><br>
        Health insurance card number:<br>
        <input type="text" name="healthInsuranceID" value="${result.healthInsuranceID}" pattern="[A-Z|a-z]{2}[0-9]{13}" title="Health insurance ID must contain 15 characters including 2 first letters and 13 numbers."><br>
        Email:<br>
        <input type="email" name="email" value="${result.email}"><br>
        Nationality: <br>
        <input type="text" name="nationality" value="${result.nationality}" maxlength="25"><br>
        Province/City: <br>
        <select onchange="selectProvince()" name="cboProvince" id="province">
            <option value="${province.id}">${province.name}</option>
            <c:forEach items="${provinceList}" var="provinceVar">
                <c:if test="${provinceVar.id != province.id}">
                    <option value="${provinceVar.id}">${provinceVar.name}</option>
                </c:if>
            </c:forEach>
        </select><br>
        District: <br>
        <select onchange="selectDistrict()" name="cboDistrict" id="district">
            <c:choose>
                <c:when test="${not empty district}">
                    <option value="${district.id}">${district.name}</option>
                </c:when>
                <c:otherwise>
                    <option>Select district</option>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${listDictrict}" var="districtVar">
                <option value="${districtVar.id}">${districtVar.name}</option>
            </c:forEach>
        </select><br>
        Ward: <br>
        <select name="cboWard">
            <c:choose>
                <c:when test="${not empty ward}">
                    <option value="${ward.id}">${ward.name}</option>
                </c:when>
                <c:otherwise>
                    <option>Select ward</option>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${listWard}" var="wardVar">
                <option value="${wardVar.id}">${wardVar.name}</option>
            </c:forEach>
        </select> <br>
        House number: <br>
        <input type="text" name="txtHouseNumber" value="${result.houseNumber}"><br>
    </c:if>
    <input type="submit" value="SaveChanges" name="btAction">
</form>

<script>
    function  selectProvince(){
        var e = document.getElementById("province");
        var value = e.options[e.selectedIndex].value;
        document.location.href = "update-info?cboProvince=" + value;
    }
    function  selectDistrict(){
        var e1 = document.getElementById("province");
        var value1 = e1.options[e1.selectedIndex].value;
        var e = document.getElementById("district");
        var value = e.options[e.selectedIndex].value;
        document.location.href = "update-info?cboDistrict=" + value + "&cboProvince=" + value1;
    }
</script>
</body>
</html>
