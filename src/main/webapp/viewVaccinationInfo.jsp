<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/16/2021
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home</title>
    <!-- Custom fonts for this template-->
    <link href="./static/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="./static/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<c:set var="resident" value="${requestScope.USER_INFO}"/>
<c:set var="result" value="${requestScope.VACCINATION_INFO}"/>
<c:set var="vaccines" value="${requestScope.VACCINES}"/>
<c:set var="provinces" value="${requestScope.PROVINCES}"/>
<c:set var="districts" value="${requestScope.DISTRICTS}"/>
<c:set var="wards" value="${requestScope.WARDS}"/>

<body class="bg-gradient-primary">

<div class="container">
    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-8 col-lg-10 col-md-8">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="card-header py-3">
                        <h3 class="text-center text-gray-900">Vaccination Information</h3>
                    </div>
                    <div class="col-lg-8 mx-auto">
                        <div class="my-2">
                            <div class="text-center py-1">
                                <h3 class="h4 text-gray-900 mb-4">${resident.firstName} ${resident.lastName}</h3>
                            </div>
                            <c:if test="${not empty result}">
                                <div class="text-center py-1">
                                    <h3 class="h4 text-success mb-4">Vaccinated with ${result.size()} dose(s).</h3>
                                </div>
                                <hr/>
                                <c:forEach items="${result}" var="dto" varStatus="obj">
                                    <c:set var="count" value="${obj.count}"/>
                                    <c:forEach items="${vaccines}" var="vaccineDTO">
                                        <c:if test="${vaccineDTO.id eq dto.vaccineID}">
                                            <c:set var="vaccineName" value="${vaccineDTO}"/>
                                        </c:if>
                                    </c:forEach>
                                    <fmt:formatDate value="${dto.date}" pattern="dd-MM-yyyy" var="date"/>
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
                                    <div class="card-body py-3">
                                        <h5 class="text-left text-gray-900 py-2 pl-2">Dose ${count}</h5>
                                        <h6 class="text-left text-gray-900 pl-2">Vaccine: ${vaccineName.name}</h6>
                                        <h6 class="text-left text-gray-900 pl-2">Date: ${date}</h6>
                                        <h6 class="text-left text-gray-900 pl-2">Vaccination
                                            facility: ${province.name}, ${district.name}, ${ward.name}</h6>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty result}">
                                <div class="text-center py-1">
                                    <h3 class="h4 text-danger mb-4">You have not been vaccinated.</h3>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <div class="col-lg-6 mx-auto">
                        <div class="my-2">
                            <c:url value="view" var="View_Profile">
                                <c:param name="btAction" value="ViewProfile"/>
                            </c:url>
                            <a href="${View_Profile}" class="btn btn-primary btn-user btn-block">
                                View profile
                            </a>
                            <a href="logout" class="btn btn-danger btn-user btn-block">
                                Log out
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="./static/jquery/jquery.min.js"></script>
<script src="./static/bootstrap/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="./static/jquery/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="./static/js/sb-admin-2.min.js"></script>
</body>
</html>
