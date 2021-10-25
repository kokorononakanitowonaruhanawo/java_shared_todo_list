<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/include/head.jsp" %>
<title>エラー</title>
</head>
<body>
	<!-- ナビゲーション -->
	<%@ include file="/WEB-INF/jsp/include/nav.jsp" %>
	<!-- ナビゲーション ここまで -->
	
	<!-- エラーメッセージ -->
	<div class="row my-2">
		<div class="col-sm-3"></div>
		<div class="col-sm-6 alert alert-danger alert-dismissble fade show">
			<c:out value="${error}" />
		</div>
		<div class="col-sm-3"></div>
	</div>
	<!-- エラーメッセージ ここまで -->
	<%@ include file="/WEB-INF/jsp/include/js.jsp"%>
</body>
</html>