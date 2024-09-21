<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
</head>
<body>
    <h2>Forgot Password</h2>
    
    <form action="/ltwst2/forgot-password" method="post">
        <c:if test="${alert != null}">
            <h3 class="alert">${alert}</h3>
        </c:if>
        
        <div class="container">
            <label for="email"><b>Email Address</b></label>
            <input type="email" placeholder="Enter your email" name="email" required>
            
            <button type="submit">Send Reset Link</button>
        </div>

        <div class="container">
            <button type="button" class="cancelbtn">Cancel</button>
        </div>
    </form>
</body>
</html>
