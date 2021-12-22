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
    <head>
      <meta charset="UTF-8">
      <meta name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
      <title>Doctor Account</title>
    </head>
    <body>
        <button><a href="logout">Logout</a></button>
        <h1>Doctor Account Form</h1>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <form action="create" method="POST">
            Doctor ID <input type="text" name="id" value="" required/><br/>
            Doctor Password <input type="password" name="password" value="" required/><br/>
            <%--    default  --%>
            First Name <input type="text" name="firstName" value="" required/><br/>
            Last Name <input type="text" name="lastName" value="" required/><br/>
            Phone Number <input type="text" name="phoneNumber" value="" required/><br/>
            Health Insurance ID <input type="text" name="healthInsuranceID" value="" required/><br/>
            Date of birth <input type="date" name="DOB" value="" required/><br>
            <c:if test="${not empty error}">
                <p style="color: red">${error}</p>
            </c:if>
            Nationality <input type="text" name="nationality" value="" required/><br/>
            Gmail <input type="email", name="email" value="" required/><br/>
            <%--    using drop-down list--%>
            Gender
            <select name="gender" id="" required>
                <option value="">Select Gender</option>
                <option value="F">F</option>
                <option value="M">M</option>
            </select>
            Province
            <select id="province" required>
                <option value="">Select province</option>
            </select>
            District
            <select id="district" required>
                <option value="">Select district</option>
            </select>
            Ward
            <select id="ward" name="wardID" required>
                <option value="">Select ward</option>
            </select>
            House Number <input type="text" name="houseNumber" value="" required/><br/>

            <input type="submit" value="Add Doctor" name="btAction">
        </form>

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
    </body>
</html>
