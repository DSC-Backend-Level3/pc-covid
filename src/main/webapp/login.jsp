<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 12/18/2021
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

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

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="card-header py-3">
                        <h3 class="text-center text-gray-900">PC-COVID</h3>
                    </div>
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                </div>
                                <c:if test="${not empty requestScope.missingParameterError}">
                                    <div class="text-center">
                                        <h3 class="h3 text-danger-500 mb-4">
                                            ${requestScope.missingParameterError}
                                        </h3>
                                    </div>
                                </c:if>
                                <form class="user needs-validation" action="login" method="post" novalidate>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               id="idNumber" aria-describedby="emailHelp"
                                               name="idNumber" placeholder="Enter ID number..." required>
                                        <div class="valid-feedback">Valid.</div>
                                        <div class="invalid-feedback">Please fill out this field.</div>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user"
                                               id="password" name="password"
                                               placeholder="Password" required>
                                        <div class="valid-feedback">Valid.</div>
                                        <div class="invalid-feedback">Please fill out this field.</div>
                                    </div>
                                    <hr/>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">
                                        Login
                                    </button>
                                </form>
                                <div class="text-center">
                                    <a class="small" href="register.html">Create an Account!</a>
                                </div>
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
<script>
    // Disable form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Get the forms we want to add validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

</body>

</html>
