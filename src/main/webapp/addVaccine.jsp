<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Adding Vaccine Page</title>
</head>
<body>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <h1>Vaccine Form</h1>
    <form action="add" method="POST">
        Vaccine ID <input type="text" name="id" value="${param.id}" required/><br/>
        ${requestScope.errorMessage}<br/>
        Vaccine Name <input type="text" name="name" value="${param.name}" required/><br/>
        Origin <input type="text" name="country" value="${param.country}" required/><br/>
        Organization <input type="text" name="firm" value="${param.firm}" required/><br/>
        Time for 2rd dose <input type="text" name="interval" value="${param.interval}" required/><br/>
        <input type="submit" value="Add Vaccine" name="btAction">
    </form>

</body>
</html>