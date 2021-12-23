<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Add vaccine</title>
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
                        <h3 class="text-center text-gray-900">VACCINE FORM</h3>
                    </div>
                    <div class="col-lg-8 mx-auto">
                        <div class="user py-3">
                            <form class="user needs-validation" action="add" method="post" novalidate>
                                <div class="form-group">
                                    <label for="id">Vaccine ID:</label>
                                    <input type="text" name="id" id="id" class="form-control form-control-user"
                                           maxlength="10" placeholder="Ex: 1234567890" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="name">Vaccine name:</label>
                                    <input type="text" name="name" id="name" class="form-control form-control-user"
                                           maxlength="20" placeholder="Astrazeneca" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="country">Origin:</label>
                                    <input type="text" name="country" id="country"
                                           class="form-control form-control-user"
                                           maxlength="50" placeholder="England" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="firm">Organization:</label>
                                    <input type="text" name="firm" id="firm" class="form-control form-control-user"
                                           maxlength="20" placeholder="Pharmaceutical industry" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="interval">Time for 2rd dose (week):</label>
                                    <input type="text" name="interval" id="interval"
                                           class="form-control form-control-user"
                                           maxlength="10" placeholder="Pharmaceutical industry" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <hr/>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <a href="homepage" class="btn btn-outline-danger btn-user btn-block">
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

<!--  <button><a href="logout">Logout</a></button>
  <button><a href="homepage">Home Page</a></button> -->

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
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${not empty requestScope.existedError}">
    <script>
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: '${requestScope.existedError}'
        })
    </script>
</c:if>

<c:if test="${not empty requestScope.nameError}">
    <script>
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: '${requestScope.nameError}'
        })
    </script>
</c:if>
</body>
</html>