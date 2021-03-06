<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/20/2021
  Time: 12:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Change password</title>

    <!-- Custom fonts for this template-->
    <link href="./static/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="./static/css/sb-admin-2.min.css" rel="stylesheet">

</head>
<body class="bg-gradient-primary">
<c:set var="status" value="${param.status}"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-xl-8 col-lg-10 col-md-8">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="card-header py-3">
                        <h3 class="text-center text-gray-900">Update form</h3>
                    </div>
                    <div class="col-lg-10 mx-auto">
                        <div class="user py-3">
                            <form class="user needs-validation" action="update-password" method="post" novalidate>
                                <div class="form-group ">
                                    <label for="password">Current password:</label>
                                    <input type="password" class="form-control form-control-user"
                                           id="password" name="password"
                                           autocomplete="false" required>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group ">
                                    <label for="newPassword">New password:</label>
                                    <input type="password" class="form-control form-control-user"
                                           id="newPassword" name="newPassword" required>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="confirmPassword">Confirm password:</label>
                                    <input type="password" class="form-control form-control-user"
                                           id="confirmPassword" name="confirmPassword" required>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <hr/>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <a href="view?btAction=ViewProfile"
                                           class="btn btn-outline-danger btn-user btn-block">
                                            Cancel
                                        </a>
                                    </div>
                                    <div class="col-sm-6">
                                        <button type="reset" class="btn btn-outline-warning btn-user btn-block">
                                            Reset
                                        </button>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    Submit
                                </button>
                            </form>
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
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${status eq 'false1'}">
    <script>
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: 'Updated Failed. Password does not match.'
        })
    </script>
</c:if>
<c:if test="${status eq 'false2'}">
    <script>
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: 'Updated Failed. Enter the wrong password'
        })
    </script>
</c:if>
<c:if test="${status eq 'true'}">
    <script>
        Swal.fire({
            icon: 'success',
            title: 'Yeahhh',
            text: 'Updated Successfully'
        })
    </script>
</c:if>
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
