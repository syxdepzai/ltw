<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
</head>
<body>
    <h2>Reset Your Password</h2>

    <form action="/ltwst2/reset-password" method="post">
        <c:if test="${alert != null}">
            <h3 class="alert">${alert}</h3>
        </c:if>

        <div class="container">
            <label for="password"><b>New Password</b></label>
            <input type="password" placeholder="Enter new password" name="password" required>

            <label for="confirmPassword"><b>Confirm New Password</b></label>
            <input type="password" placeholder="Confirm new password" name="confirmPassword" required>

            <button type="submit">Reset Password</button>
        </div>
        
        <div class="container">
            <button type="button" class="cancelbtn">Cancel</button>
        </div>
    </form>
</body>
</html>
