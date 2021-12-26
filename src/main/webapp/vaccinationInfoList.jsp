<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/19/2021
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Vaccination list</title>
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
        <div class="card o-hidden border-0 shadow-lg my-3">
            <div class="card-header py-3">
                <h3 class="text-center text-gray-900">Vaccination list</h3>
            </div>
            <div class="card-body p-0">
                <div class="p-2">
                    <h4 class="text-center text-gray-900">Welcome back, ${sessionScope.name}</h4>
                    <a href="logout" class="btn btn-outline-danger">log out</a>
                    <a href="add" class="btn btn-outline-primary">add new vaccination</a>
                </div>
                <div class="card-body">
                    <c:if test="${not empty requestScope.vaccinationInfoList}">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Vaccination Information ID</th>
                                    <th>Resident ID</th>
                                    <th>Resident First Name</th>
                                    <th>Resident Last Name</th>
                                    <th>Vaccine Name</th>
                                    <th>Vaccination's Province</th>
                                    <th>Vaccination's District</th>
                                    <th>Vaccination's Ward</th>
                                    <th>Vaccination Date</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="vaccineList" value="${requestScope.vaccineList}"/>
                                <c:set var="residentList" value="${requestScope.residentList}"/>
                                <c:set var="locationList" value="${requestScope.locationList}"/>
                                <c:forEach var="dto" items="${requestScope.vaccinationInfoList}" varStatus="obj">
                                    <tr>
                                        <td>${obj.count}</td>
                                        <td>${dto.id}</td>
                                        <td>${dto.residentID}</td>
                                        <td><c:out value="${residentList[obj.index].firstName}"/></td>
                                        <td><c:out value="${residentList[obj.index].lastName}"/></td>
                                        <td><c:out value="${vaccineList[obj.index].name}"/></td>
                                        <td><c:out value="${locationList[obj.index].provinceName}"/></td>
                                        <td><c:out value="${locationList[obj.index].districtName}"/></td>
                                        <td><c:out value="${locationList[obj.index].wardName}"/></td>
                                        <td>
                                            <fmt:formatDate value="${dto.date}" pattern="dd-MM-yyyy" var="date"/>
                                            <c:out value="${date}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                    <c:if test="${empty requestScope.vaccinationInfoList}">
                        <h3 class="text-danger">Vaccination list is not available</h3>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${param.add eq 'success'}">
    <script>
        Swal.fire({
            icon: 'success',
            title: 'Success',
            text: 'Adding successfully'
        })
    </script>
</c:if>
</body>

<!-- Bootstrap core JavaScript-->
<script src="./static/jquery/jquery.min.js"></script>
<script src="./static/bootstrap/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="./static/jquery/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="./static/js/sb-admin-2.min.js"></script>
<script src="./static/jquery/jquery.dataTables.min.js"></script>
<script src="./static/js/dataTables.bootstrap4.min.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTable').DataTable();
    });
</script>
</html>
