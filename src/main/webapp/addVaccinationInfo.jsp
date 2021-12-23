<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/17/2021
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Add vaccination info</title>
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
                        <h3 class="text-center text-gray-900">VACCINE INFORMATION FORM</h3>
                    </div>
                    <div class="col-lg-8 mx-auto">
                        <div class="user py-3">
                            <form class="user needs-validation" action="add" method="post" novalidate>
                                <div class="form-group">
                                    <label for="residentID">Resident ID:</label>
                                    <input type="text" name="residentID" id="residentID"
                                           class="form-control form-control-user"
                                           pattern="[0-9]{12}" title="Input must be 12 numbers"
                                           placeholder="1234567789123" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="id">Vaccination ID:</label>
                                    <input type="text" name="id" id="id" class="form-control form-control-user"
                                           max="10" placeholder="12345677891"
                                           required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <label for="vaccineID">Vaccination name:</label>
                                        <select id="vaccineID" name="vaccineID"
                                                class="form-control" required>
                                            <option value="">Select Vaccine</option>
                                            <c:forEach var="dto" items="${vaccineList}">
                                                <option value="${dto.id}">${dto.name}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="valid-feedback">Valid.</div>
                                        <div class="invalid-feedback">Please fill out this field.</div>
                                    </div>
                                    <div class="col-sm-6">
                                        <label for="date">Injection's Date</label>
                                        <input type="date" name="date" id="date"
                                               class="form-control form-control-user"
                                               required/>
                                        <div class="valid-feedback">Valid.</div>
                                        <div class="invalid-feedback">Please fill out this field.</div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="province">Province:</label>
                                    <select id="province" name="provinceID"
                                            class="form-control" required>
                                        <option disabled selected>Select province</option>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="district">District:</label>
                                    <select id="district" name="districtID"
                                            class="form-control" required>
                                        <option disabled selected>Select district</option>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="ward">Ward:</label>
                                    <select id="ward" name="wardID"
                                            class="form-control" required>
                                        <option disabled selected>Select ward</option>
                                    </select>
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
                // let obj = $.parseJSON(data);
                console.log(data[0].id);
                $.each(data, function (key, value) {
                    $('#province').append('<option value="' + value.id + '">' + value.name + '</option>')
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#province').append('<option>Province Unavailable</option>');
            },
            cache: false
        });
        $('#province').change(function () {
            $('#district').find('option').remove();
            $('#district').append('<option value="">Select District</option>');
            $('#ward').find('option').remove();
            $('#ward').append('<option value="">Select Ward</option>');
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
//                        console.log(data);
                    $.each(data, function (key, value) {
                        $('#district').append('<option value="' + value.id + '">' + value.name + '</option>')
                    });
//                        $('select').formSelect();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#district').append('<option value="">District Unavailable</option>');
                },
                cache: false
            });
        });

        $('#district').change(function () {
            $('#ward').find('option').remove();
            $('#ward').append('<option value="">Select Ward</option>');

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
//                        $('select').formSelect();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#ward').append('<option value="">Ward Unavailable</option>');
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
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${not empty requestScope.dateError}">
    <script>
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: '${requestScope.dateError}'
        })
    </script>
</c:if>

<c:if test="${not empty requestScope.notExistedError}">
    <script>
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: '${requestScope.notExistedError}'
        })
    </script>
</c:if>

<c:if test="${not empty requestScope.existedError}">
    <script>
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: '${requestScope.existedError}'
        })
    </script>
</c:if>

<c:if test="${not empty requestScope.numberError}">
    <script>
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: '${requestScope.numberError}'
        })
    </script>
</c:if>
</body>
</html>
