<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">

<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp"%>
    <title>ユーザー登録</title>
</head>

<body>
	<!-- ナビゲーション -->
	<%@ include file="/WEB-INF/jsp/include/nav.jsp"%>
	<!-- ナビゲーション ここまで -->

	<!-- コンテナ -->
	<div class="container">
		<div class="row my-2">
			<div class="alert alert-info">ユーザーを登録してください</div>
		</div>

		<!-- 入力フォーム -->
		<div class="row my-2">
 			<form action="/shared_todo_list/UserRegisterServlet" method="post">
				<div class="mb-3">
					<div class="row">
						<label for="email" class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-10">
							<c:if test="${requestScope.error[0] != null}">
								<div class="row my-2">
									<div class="col-sm-1"></div>
									<div class="col-sm-10 alert alert-danger alert-dismissble fade show">
										<c:out value="${error[0]}" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</c:if>
						</div>
					</div>
					<div class="col-sm-12">
						<input type="text" class="form-control" id="email" name="email"
							placeholder="255文字以下の半角英数字">
					</div>
				</div>
				<div class="mb-3">
					<div class="row">
						<label for="pass1" class="col-sm-2 col-form-label">password</label>
						<div class="col-sm-10">
							<!-- エラーメッセージ -->
							<c:if test="${requestScope.error[1] != null}">
								<div class="row my-2">
									<div class="col-sm-1"></div>
									<div class="col-sm-10 alert alert-danger alert-dismissble fade show">
										<c:out value="${error[1]}" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</c:if>
						</div>
					</div>
					<div class="col-sm-12">
						<input type="password" id="pass1" name="pass1" class="form-control"
							placeholder="8文字以上20文字以下の半角英数字">
					</div>
				</div>
				<div class="mb-3">
					<div class="row">
						<label for="pass2" class="col-sm-2 col-form-label">password（確認）</label>
						<div class="col-sm-10">
							<!-- エラーメッセージ -->
							<c:if test="${requestScope.error[2] != null}">
								<div class="row my-2">
									<div class="col-sm-1"></div>
									<div class="col-sm-10 alert alert-danger alert-dismissble fade show">
										<c:out value="${error[2]}" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</c:if>
						</div>
					</div>
					<div class="col-sm-12">
						<input type="password" id="pass2" name="pass2" class="form-control"
							placeholder="8文字以上20文字以下の半角英数字">
					</div>
				</div>
				<div class="mb-5">
					<div class="row">
						<label for="name" class="col-sm-2 col-form-label">氏名</label>
						<div class="col-sm-10">
							<c:if test="${requestScope.error[3] != null}">
								<div class="row my-2">
									<div class="col-sm-1"></div>
									<div class="col-10 alert alert-danger alert-dismissble fade show">
										<c:out value="${error[3]}" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</c:if>
						</div>
					</div>
					<div class="col-sm-12">
						<input type="text" id="name" name="name" class="form-control"
							placeholder="50文字以下
							">
					</div>
				</div>

				<input type="submit" value="登録" class="btn btn-primary">
				<input type="button" value="キャンセル" class="btn btn-outline-primary"
					onclick="location.href='/shared_todo_list/MainServlet'">
		  	</form>
		</div>
		<!-- 入力フォーム ここまで -->
	<!-- コンテナ ここまで -->

	<!-- 必要なJavascriptを読み込む -->
    <%@ include file="/WEB-INF/jsp/include/js.jsp"%>
    
    </body>

</html>