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
    <title>Home</title>
    <!-- Custom fonts for this template-->
    <link href="./static/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="./static/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">
<c:url value="viewVaccine" var="View_Vaccine">
    <c:param name="btAction" value="View Vaccine"/>
</c:url>
<c:url value="viewDoctor" var="View_Doctor">
    <c:param name="btAction" value="View Doctor"/>
</c:url>
<div class="container">
    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-8 col-lg-10 col-md-8">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="card-header py-3">
                        <h3 class="text-center text-gray-900">ADMIN HOME</h3>
                    </div>
                    <div class="col-lg-6 mx-auto">
                        <div class="p-5">
                            <div class="text-center py-1">
                                <h1 class="h4 text-gray-900 mb-4">Welcome Back, ${sessionScope.name}!</h1>
                            </div>
                            <hr/>
                            <div class="text-center py-2">
                                <a class="btn btn-user btn-outline-primary btn-block" href="${View_Vaccine}">Vaccine
                                    Information</a>
                            </div>
                            <div class="text-center py-2">
                                <a class="btn btn-use btn-outline-primary btn-block" href="${View_Doctor}">Doctor
                                    Information</a>
                            </div>
                            <div class="text-center py-2">
                                <a class="btn btn-user btn-outline-danger btn-block" href="logout">Log out</a>
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
