<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<div style="min-width: 1000px">
		<header>
			<tiles:insertAttribute name="header" />
		</header>
		<tiles:insertAttribute name="side" />
		<div class="main">
			<tiles:insertAttribute name="main" />
		</div>
	</div>
</body>
</html>