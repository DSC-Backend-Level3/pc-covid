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
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Change Password</title>
</head>
<body>
<c:set var="oldpassword" value="${requestScope.OLD_PASSWORD}"/>
<c:set var="newpassword" value="${requestScope.NEW_PASSWORD}"/>
<c:set var="checkValid" value="${requestScope.CHECK_VALID}"/>
<button><a href="homepage">Home Page</a></button>
<h1>Update Password</h1>
<form action="update-password" method="post">
    Current Password: <input type="password" name="txtPassword" value="${oldpassword}"> <br>
    New Password: <input type="password" name="txtNewPassword" value="${newpassword}"> <br>
    Re-type New Password: <input type="password" name="txtNewPasswordConfirm" value="${newpassword}"> <br>
    <input type="submit" value="Save Changes" name="btAction" />
    <c:choose>
        <c:when test="${checkValid == false}"><h2 style="color: red">Update failed.</h2></c:when>
        <c:otherwise><h2 style="color: red">Updated Successfully</h2></c:otherwise>
    </c:choose>
</form>
</body>
</html>
