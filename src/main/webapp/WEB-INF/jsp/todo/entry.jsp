<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">

<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp"%>
    <title>作業登録</title>
</head>

<body>
	<!-- ナビゲーション -->
	<%@ include file="/WEB-INF/jsp/include/nav.jsp"%>
	<!-- ナビゲーション ここまで -->

	<!-- コンテナ -->
	<div class="container">
		<div class="row my-2">
			<div class="col-sm-3"></div>
			<div class="col-sm-6 alert alert-info">作業を登録してください</div>
			<div class="col-sm-3"></div>
		</div>

		<!-- 入力フォーム -->
		<div class="row my-2">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<form action="/shared_todo_list/TodoRegisterServlet" method="post">
					<div class="form-group">
						<label for="item_name">項目名</label>
						<c:if test="${requestScope.error[0] != null}">
							<div class="row my-2">
								<div class="col-sm-1"></div>
								<div class="col-sm-10 alert alert-danger alert-dismissble fade show">
									<c:out value="${error[0]}" />
								</div>
								<div class="col-sm-1"></div>
							</div>
						</c:if>
						<input type="text" class="form-control" id="item_name" name="item_name">
					</div>
					<div class="form-group">
						<label for="user_id">担当者</label>
						<select name="user_id" id="user_id" class="form-control">
							<option value="">--選択してください--</option>
							<c:forEach var="user" items="${users}">
								<c:choose>
									<c:when test="${user.getId() ==  login_user.getId()}">
										<option value="${user.getId()}" selected>
											<c:out value="${user.getName()}"></c:out>
										</option>
									</c:when>
									<c:otherwise>
										<option value="${user.getId()}">
											<c:out value="${user.getName()}"></c:out>
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="expire_date">期限</label>
						<c:if test="${requestScope.error[1] != null}">
							<div class="row my-2">
								<div class="col-sm-1"></div>
								<div class="col-10 alert alert-danger alert-dismissble fade show">
									<c:out value="${error[1]}" />
								</div>
								<div class="col-sm-1"></div>
							</div>
						</c:if>
						<input type="date" class="form-control" id="expire_date" name="expire_date">
					</div>
					<div class="form-group form-check">
						<input type="checkbox" class="form-check-input" id="finished" name="finished">
						<label for="finished">完了</label>
					</div>

					<input type="submit" value="登録" class="btn btn-primary">
					<input type="button" value="キャンセル" class="btn btn-outline-primary"
						onclick="location.href='/shared_todo_list/MainServlet'">
				</form>
			</div>
			<div class="col-sm-3"></div>
		</div>
		<!-- 入力フォーム ここまで -->

	</div>
	<!-- コンテナ ここまで -->

	<!-- 必要なJavascriptを読み込む -->
	<%@ include file="/WEB-INF/jsp/include/js.jsp"%>
</body>

</html>