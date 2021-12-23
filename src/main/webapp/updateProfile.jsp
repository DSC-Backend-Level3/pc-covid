<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/17/2021
  Time: 11:28 AM
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
    <title>Update profile</title>
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
                        <h3 class="text-center text-gray-900">Update form</h3>
                    </div>
                    <div class="col-lg-10 mx-auto">
                        <div class="user py-3">
                            <c:set var="result" value="${requestScope.PROFILE_PAGE}"/>
                            <fmt:formatDate value="${result.DOB}" var="dob" pattern="yyyy-MM-dd"/>
                            <c:set var="province" value="${requestScope.PROFILE_PROVINCE}"/>
                            <c:set var="district" value="${requestScope.PROFILE_DISTRICT}"/>
                            <c:set var="ward" value="${requestScope.PROFILE_WARD}"/>
                            <c:set var="provinceList" value="${requestScope.PROVINCE_LIST}"/>
                            <c:set var="listDictrict" value="${requestScope.DISTRICT_LIST}"/>
                            <c:set var="listWard" value="${requestScope.WARD_LIST}"/>
                            <form class="user needs-validation" action="update-info" method="post" novalidate>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <label for="firstName">First name:</label>
                                        <input name="firstName" id="firstName"
                                               type="text" class="form-control form-control-user"
                                               maxlength="50" value="${result.firstName}"
                                               placeholder="Ex: Andrew" required/>
                                        <div class="valid-feedback">Valid.</div>
                                        <div class="invalid-feedback">Please fill out this field.</div>
                                    </div>
                                    <div class="col-sm-6">
                                        <label for="lastName">Last name:</label>
                                        <input name="lastName" id="lastName"
                                               maxlength="50" value="${result.lastName}"
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
                                               value="${dob}" required/>
                                        <div class="valid-feedback">Valid.</div>
                                        <div class="invalid-feedback">Please fill out this field.</div>
                                    </div>
                                    <div class="col-sm-6">
                                        <label class="form-check-label" for="gender">Gender:</label>
                                        <select class="form-control" id="gender" name="gender" required>
                                            <c:choose>
                                                <c:when test="${result.gender eq 'F'}">
                                                    <option value="F" selected>Female</option>
                                                    <option value="M">Male</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="M" selected>Male</option>
                                                    <option value="F">Female</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                        <div class="valid-feedback">Valid.</div>
                                        <div class="invalid-feedback">Please fill out this field.</div>
                                    </div>
                                </div>
                                <hr/>
                                <div class="form-group">
                                    <label for="txtID">Identity card:</label>
                                    <input name="txtID" id="txtID" value="${result.id}"
                                           type="text" class="form-control form-control-user"
                                           disabled readonly required>
                                </div>
                                <div class="form-group">
                                    <label for="phoneNumber">Phone number:</label>
                                    <input name="phoneNumber" id="phoneNumber"
                                           pattern="[0-9]{10}" title="Input must be 10 numbers"
                                           type="tel" class="form-control form-control-user"
                                           placeholder="Ex: 0937456123"
                                           value="${result.phoneNumber}" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <input name="email" id="email"
                                           type="email" class="form-control form-control-user"
                                           placeholder="Ex: abc123@domain.xyz"
                                           value="${result.email}" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="healthInsuranceID">Health Insurance ID:</label>
                                    <input name="healthInsuranceID" id="healthInsuranceID"
                                           pattern="[A-Z|a-z]{2}[0-9]{13}" title="Input must be 15 characters"
                                           type="text" class="form-control form-control-user"
                                           placeholder="Ex: ex1234567891234"
                                           value="${result.healthInsuranceID}" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <hr/>
                                <div class="form-group">
                                    <label for="nationality">Nationality:</label>
                                    <input name="nationality" id="nationality"
                                           type="text" class="form-control form-control-user"
                                           placeholder="Ex: Việt Nam"
                                           value="${result.nationality}" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="province">Province:</label>
                                    <select class="form-control" id="province"
                                            name="cboProvince"
                                            onchange="selectProvince()" required>
                                        <option value="${province.id}">${province.name}</option>
                                        <c:forEach items="${provinceList}" var="provinceVar">
                                            <c:if test="${provinceVar.id ne province.id}">
                                                <option value="${provinceVar.id}">${provinceVar.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="district">District:</label>
                                    <select class="form-control" id="district"
                                            name="cboDistrict"
                                            onchange="selectDistrict()" required>
                                        <c:choose>
                                            <c:when test="${not empty district}">
                                                <option value="${district.id}">${district.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>Select district</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach items="${listDictrict}" var="districtVar">
                                            <option value="${districtVar.id}">${districtVar.name}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="ward">Ward:</label>
                                    <select class="form-control" id="ward"
                                            name="cboWard" required>
                                        <c:choose>
                                            <c:when test="${not empty ward}">
                                                <option value="${ward.id}">${ward.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>Select ward</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach items="${listWard}" var="wardVar">
                                            <option value="${wardVar.id}">${wardVar.name}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="houseNumber">House number:</label>
                                    <input name="houseNumber" id="houseNumber"
                                           type="text" class="form-control form-control-user"
                                           placeholder="Ex: 245/12 Phạm Văn Đồng"
                                           value="${result.houseNumber}" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <hr/>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <c:url value="view" var="View_Profile">
                                            <c:param name="btAction" value="ViewProfile"/>
                                        </c:url>
                                        <a href="${View_Profile}" class="btn btn-outline-danger btn-user btn-block">
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

<script>
    function selectProvince() {
        var e = document.getElementById("province");
        var value = e.options[e.selectedIndex].value;
        document.location.href = "update-info?cboProvince=" + value;
    }

    function selectDistrict() {
        var e1 = document.getElementById("province");
        var value1 = e1.options[e1.selectedIndex].value;
        var e = document.getElementById("district");
        var value = e.options[e.selectedIndex].value;
        document.location.href = "update-info?cboDistrict=" + value + "&cboProvince=" + value1;
    }
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