<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">

<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp"%>
    <title>ユーザー登録</title>
</head>

<body>
	
	<h1>登録完了</h1>
	<p>登録完了しました</p>
	
	<form action="/shared_todo_list/MainServlet" method="get">
		<input type="submit" value="TOPへ" class="btn btn-primary">
	</form>
	
</body>
</html>