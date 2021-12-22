<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/17/2021
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Vaccination Information</title>
</head>
<body>
    <button><a href="logout">Logout</a></button>
    <h1>Vaccination Information Form</h1>
    <form action="add" method="POST">
        <c:set var="vaccineList" value="${requestScope.vaccineList}"/>
        Resident ID <input type="text" name="residentID" pattern="[0-9]{12}" title="Input must be 12 numbers" value="" required/><br/>
        Vaccination Name <input type="text" name="id" value="" required/><br/>
<%--        using drop-down list to presentation--%>
        Vaccine name:
        <select name="vaccineID" required>
            <option>Select Vaccine</option>
                <c:forEach var="dto" items="${vaccineList}">
                    <option value="${dto.id}">${dto.name}</option>
                </c:forEach>
        </select>
        Province
        <select id="province" name="provinceID" required>
            <option>Select province</option>
        </select>
        District
        <select id="district" name="districtID" required>
            <option>Select district</option>
        </select>
        Ward
        <select id="ward" name="wardID" required>
            <option value="">Select ward</option>
        </select>

        Injection's Date <input type="date" name="date" value="" required/><br/>
        <input type="submit" value="Add Vaccination" name="btAction">
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
//                        console.log(data);
                        $.each(data, function (key, value) {
                            $('#district').append('<option value="' + value.id + '">' + value.name + '</option>')
                        });
//                        $('select').formSelect();
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
//                        $('select').formSelect();
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
