<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/" var="URL"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/commons/admin/header.jsp"%>

	<sitemesh:write property="body" />

	<script src="${URL}assets/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script>
		function chooseFile(fileInput) {
			if (fileInput.files && fileInput.files[0]) {
			}
			var reader = new FileReader();
			reader.onload = function(e) {
			}
			$('#images').attr('src', e.target.result);
			reader.readAsDataURL(fileInput.files[0]);
		}
	</script>


	<%@ include file="/commons/admin/footer.jsp"%>
</body>
</html>