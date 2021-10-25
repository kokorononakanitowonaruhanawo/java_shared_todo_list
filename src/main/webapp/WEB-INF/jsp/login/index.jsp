<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ja">

<head>
	<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
</head>

<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-primary">
        <span class="navbar-brand">TODOリスト</span>
    </nav>

    <div class="container">
        <div class="row my-2">
            <div class="col-sm-3"></div>
            <div class="col-sm-3">
                <h1></h1>
            </div>
            <div class="col-sm-3"></div>
        </div>

        <div class="row my-2">
            <div class="col-sm-3"></div>
            <c:if test="${error != null}">
				<div class="alert alert-danger" role="alert">${error}</div>
			</c:if>
			<div class="col-sm-3"></div>
        </div>

        <div class="row my-2">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <form action="/shared_todo_list/LoginServlet" method="post">
                    <div class="form-group">
                        <label for="user">ユーザー名</label>
                        <input type="text" class="form-control" id="user" name="user">
                    </div>
                    <div class="form-group">
                        <label for="password">パスワード</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary">ログイン</button>
                </form>

            </div>
            <div class="col-sm-3"></div>
        </div>

    </div>

    <!-- 必要なJavascriptを読み込む -->
    <script src="WEB=INF/src/js/jquery-3.4.1.min.js"></script>
    <script src="WEB-INF/src/js/bootstrap.bundle.min.js"></script>

</body>

</html>