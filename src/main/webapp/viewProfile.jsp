<%@ page import="dto.VaccinationInfoDTO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="dto.ResidentDTO" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/16/2021
  Time: 6:17 PM
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
    <title>Profile</title>
    <!-- Custom fonts for this template-->
    <link href="./static/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="./static/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">

<div class="container">
    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-8 col-lg-10 col-md-8">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="card-header py-3">
                        <h1 class="text-center text-gray-900">Profile</h1>
                    </div>
                    <div class="col-lg-7 mx-auto">
                        <div class="my-2">
                            <div class="card-body py-3">
                                <c:set var="result" value="${requestScope.PROFILE_PAGE}"/>
                                <fmt:formatDate value="${result.DOB}" pattern="dd/MM/yyyy" var="dob"/>
                                <c:choose>
                                    <c:when test="${result.gender eq 'F'}">
                                        <c:set var="gender" value="Female"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="gender" value="Male"/>
                                    </c:otherwise>
                                </c:choose>
                                <c:set var="province" value="${requestScope.PROFILE_PROVINCE}"/>
                                <c:set var="district" value="${requestScope.PROFILE_DISTRICT}"/>
                                <c:set var="ward" value="${requestScope.PROFILE_WARD}"/>
                                <h5 class="text-left text-gray-900 pl-2">
                                    Full name: ${result.firstName} ${result.lastName}
                                </h5>
                                <h5 class="text-left text-gray-900 pl-2">
                                    Date of birth: ${dob}</h5>
                                <h5 class="text-left text-gray-900 pl-2">
                                    Gender: <c:out value="${gender}"/>
                                </h5>
                                <hr/>
                                <h5 class="text-left text-gray-900 pl-2">
                                    Identity card: ${result.id}
                                </h5>
                                <h5 class="text-left text-gray-900 pl-2">
                                    Health insurance number: ${result.healthInsuranceID}</h5>
                                <h5 class="text-left text-gray-900 pl-2">
                                    Phone number: ${result.phoneNumber}
                                </h5>
                                <h5 class="text-left text-gray-900 pl-2">
                                    Email: ${result.email}
                                </h5>
                                <hr/>
                                <h5 class="text-left text-gray-900 pl-2">
                                    Nationality: ${result.nationality}
                                </h5>
                                <h5 class="text-left text-gray-900 pl-2">
                                    Province/City: ${province.name}
                                </h5>
                                <h5 class="text-left text-gray-900 pl-2">
                                    District: ${district.name}
                                </h5>
                                <h5 class="text-left text-gray-900 pl-2">
                                    Ward: ${ward.name}
                                </h5>
                                <h5 class="text-left text-gray-900 pl-2">
                                    House number: ${result.houseNumber}
                                </h5>
                            </div>
                            <hr/>
                            <div class="py-1 mx-auto">
                                <c:url value="view" var="Update_Profile">
                                    <c:param name="btAction" value="UpdateProfile"/>
                                </c:url>
                                <a href="${Update_Profile}" class="btn btn-primary btn-user btn-block">Update profile</a>
                                <a href="changePassword.html" class="btn btn-primary btn-user btn-block">Update password</a>
                                <a href="homepage" class="btn btn-outline-danger btn-user btn-block">Home</a>
                            </div>
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