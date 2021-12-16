<%@ page import="dto.ResidentDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Password Page</title>
</head>
<body>
    <h1>Update Password</h1>
    <form action="ChangePasswordController">
         ResidentDTO dto = (ResidentDTO) request.getAttribute("LOGIN"); %>
        <input type="hidden" name="txtIdNumber"
               value="<%= dto.getIdNumber() %>" />
        Current Password <input type="text" name="txtPassword" value="">
        New Password<input type="text" name="txtNewPassword" value="">
        Re-type New Password<input type="text" name="txtNewPasswordConfirm" value="">
        <input type="submit" value="Save Changes" name="btAction" />
    </form>
</body>
</html>