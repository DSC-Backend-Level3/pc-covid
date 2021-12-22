<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/19/2021
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Doctor list</title>
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
    <div class="row justify-content-center">
        <div class="col-xl-12 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="card-header py-3">
                        <h3 class="text-center text-gray-900">Doctor list</h3>
                    </div>
                    <div class="card-body">
                        <div class="pb-2">
                            <a href="homepage" class="btn btn-outline-warning">home</a>
                            <a href="addDoctorAccount.jsp" class="btn btn-outline-primary">add new doctor</a>
                        </div>
                        <c:if test="${not empty requestScope.doctorList}">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Doctor ID</th>
                                        <th>Doctor name</th>
                                        <th>Gender</th>
                                        <th>Gmail</th>
                                        <th>Phone number</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="dto" items="${requestScope.doctorList}" varStatus="obj">
                                        <tr>
                                            <td>${obj.count}</td>
                                            <td>${dto.id}</td>
                                            <td>${dto.firstName} ${dto.lastName}</td>
                                            <td>
                                                <c:if test="${dto.gender eq 'F'}">
                                                    Female
                                                </c:if>
                                                <c:if test="${dto.gender eq 'M'}">
                                                    Male
                                                </c:if>
                                            </td>
                                            <td>${dto.email}</td>
                                            <td>${dto.phoneNumber}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                        <c:if test="${empty requestScope.doctorList}">
                            <h4 class="text-danger">Doctor list is unavailable!</h4>
                        </c:if>
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