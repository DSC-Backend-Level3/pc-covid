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
    <title>Change Password</title>
</head>
<body>
<h1>Update Password</h1>
<c:set var="oldpassword" value="${requestScope.OLD_PASSWORD}"/>
<c:set var="newpassword" value="${requestScope.NEW_PASSWORD}"/>
Current Password: <input type="password" name="txtPassword" value="${oldpassword}"> <br>
New Password: <input type="password" name="txtNewPassword" value="${newpassword}"> <br>
Re-type New Password: <input type="password" name="txtNewPasswordConfirm" value="${newpassword}"> <br>
<h1 style="color: red">Updated Successfully!</h1>
<form action="homepage">
    <input type="submit" value="Home page">
</form>
</body>
</html>
