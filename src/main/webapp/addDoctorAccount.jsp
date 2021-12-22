<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/17/2021
  Time: 7:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Add new doctor</title>
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
        <div class="col-xl-8 col-lg-10 col-md-8">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="card-header py-3">
                        <h3 class="text-center text-gray-900">Add account form</h3>
                    </div>
                    <div class="col-lg-10 mx-auto">
                        <div class="user py-3">
                            <form class="user needs-validation" action="create" method="post" novalidate>
                                <div class="form-group">
                                    <label for="id">Doctor ID:</label>
                                    <input type="text" name="id" id="id" class="form-control form-control-user"
                                           pattern="[0-9]{12}" title="Input must be 12 numbers"
                                           placeholder="Ex: 123456789123" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password:</label>
                                    <input name="password" id="password"
                                           type="password" class="form-control form-control-user"
                                           required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="confirmPassword">Password:</label>
                                    <input name="confirmPassword" id="confirmPassword"
                                           type="password" class="form-control form-control-user"
                                           required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <label for="firstName">First name:</label>
                                        <input name="firstName" id="firstName"
                                               type="text" class="form-control form-control-user"
                                               placeholder="Ex: Andrew" required/>
                                        <div class="valid-feedback">Valid.</div>
                                        <div class="invalid-feedback">Please fill out this field.</div>
                                    </div>
                                    <div class="col-sm-6">
                                        <label for="lastName">Last name:</label>
                                        <input name="lastName" id="lastName"
                                               type="text" class="form-control form-control-user"
                                               placeholder="Ex: Edison" required/>
                                        <div class="valid-feedback">Valid.</div>
                                        <div class="invalid-feedback">Please fill out this field.</div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <label for="DOB">Date of birth:</label>
                                        <input name="DOB" id="DOB"
                                               type="date" class="form-control form-control-user"
                                               required/>
                                        <div class="valid-feedback">Valid.</div>
                                        <div class="invalid-feedback">Please fill out this field.</div>
                                    </div>
                                    <div class="col-sm-6">
                                        <label class="form-check-label" for="gender">Gender:</label>
                                        <select class="form-control" id="gender" name="gender" required>
                                            <option value="M" selected>Male</option>
                                            <option value="F">Female</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="phoneNumber">Phone number:</label>
                                    <input name="phoneNumber" id="phoneNumber"
                                           pattern="[0-9]{10}" title="Input must be 10 numbers"
                                           type="tel" class="form-control form-control-user"
                                           placeholder="Ex: 0937456123" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <input name="email" id="email"
                                           type="email" class="form-control form-control-user"
                                           placeholder="Ex: abc123@domain.xyz" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="healthInsuranceID">Health Insurance ID:</label>
                                    <input name="healthInsuranceID" id="healthInsuranceID"
                                           pattern="[A-Z|a-z]{2}[0-9]{13}" title="Input must be 15 characters"
                                           type="text" class="form-control form-control-user"
                                           placeholder="Ex: ex1234567891234" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="nationality">Nationality:</label>
                                    <input name="nationality" id="nationality"
                                           type="text" class="form-control form-control-user"
                                           placeholder="Ex: Việt Nam" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="province">Province:</label>
                                    <select class="form-control" id="province"
                                            name="province" required>
                                        <option value="">Select province</option>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="district">District:</label>
                                    <select class="form-control" id="district"
                                            name="district" required>
                                        <option value="">Select district</option>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="wardID">Ward:</label>
                                    <select class="form-control" id="wardID" name="wardID"
                                            required>
                                        <option value="">Select ward</option>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="houseNumber">House number:</label>
                                    <input name="houseNumber" id="houseNumber"
                                           type="text" class="form-control form-control-user"
                                           placeholder="Ex: 245/12 Phạm Văn Đồng" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <hr>
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

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"
        integrity="sha512-n/4gHW3atM3QqRcbCn6ewmpxcLAHGaDjpEBu4xZd47N0W2oQ+6q7oc3PXstrJYXcbNU1OHdQ1T7pAP+gi5Yu8g=="
        crossorigin="anonymous"
        referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            // check url
            url: "loadLocation",
            method: "GET",
            data: {operation: 'province'},
            success: function (data, textStatus, jqXHR) {
                $.each(data, function (key, value) {
                    $('#province').append('<option value="' + value.id + '">' + value.name + '</option>')
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#province').append('<option>province Unavailable</option>');
            },
            cache: false
        });

        $('#province').change(function () {
            $('#district').find('option').remove();
            $('#district').append('<option>Select District</option>');
            $('#ward').find('option').remove();
            $('#ward').append('<option>Select Ward</option>');

            let pid = $('#province').val();
            let data = {
                operation: "district",
                id: pid
            };

            $.ajax({
                url: "loadLocation",
                method: "GET",
                data: data,
                success: function (data, textStatus, jqXHR) {
                    $.each(data, function (key, value) {
                        $('#district').append('<option value="' + value.id + '">' + value.name + '</option>')
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#district').append('<option>District Unavailable</option>');
                },
                cache: false
            });
        });

        $('#district').change(function () {
            $('#ward').find('option').remove();
            $('#ward').append('<option>Select Ward</option>');

            let did = $('#district').val();
            let data = {
                operation: "ward",
                id: did
            };

            $.ajax({
                url: "loadLocation",
                method: "GET",
                data: data,
                success: function (data, textStatus, jqXHR) {
                    $.each(data, function (key, value) {
                        $('#ward').append('<option value="' + value.id + '">' + value.name + '</option>')
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#ward').append('<option>Ward Unavailable</option>');
                },
                cache: false
            });
        });
    });
</script>
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
