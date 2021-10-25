<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<style>
/* ボタンを横並びにする */
form {
	display: inline-block;
}

</style>


<!-- ナビゲーション -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<span class="navbar-brand">TODOリスト</span>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto ">
				<li class="nav-item mr-2">
					<form action="/shared_todo_list/MainServlet" method="post">
						<button class="btn btn-outline-light my-2 my-sm-0" type="submit" 
							style="margin-right: 20px">作業一覧</button>
					</form>
				</li>
				<li class="nav-item mr-2">
					<form action="/shared_todo_list/TodoRegisterServlet" method="get">
						<button class="btn btn-outline-light my-2 my-sm-0" type="submit"
							style="margin-right: 20px">作業登録</button>
					</form>
				</li>
				<li class="nav-item mr-2">
					<div class="form-inline"></div>
				</li>
				<li class="nav-item dropdown mr-2" style="margin-right: 30px">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<c:out value="${login_user.name}"></c:out>
					</a>
					
					<ul class="dropdown-menu bg-primary" aria-labelledby="navbarDropdown">
						<li>
							<form action="/shared_todo_list/UserRegisterServlet" method="get" class="dropdown-item">
								<button class="btn btn-outline-light my-2 my-sm-0" type="submit">ユーザー登録</button>
							</form>
						</li>
						<li>
							<form action="/shared_todo_list/UserUpdateServlet" method="get">
								<button class="btn btn-outline-light my-2 my-sm-0" type="submit">ユーザー情報変更</button>
							</form>
						</li>
						<li>
							<form action="/shared_todo_list/LogoutServlet" method="post" class="dropdown-item">
								<button class="btn btn-outline-light my-2 my-sm-0" type="submit">ログアウト</button>
							</form>
						</li>
					</ul>
				</li>
			</ul>
			<form class="d-flex" action="/shared_todo_list/TodoSearchServlet" method="post">
				<div class="row ">
					<div class="col">
						<input class="form-control mr-sm-2" type="search"
							placeholder="Search" aria-label="Search" name="search"
							value="search">
					</div>
					<div class="col">
						<button class="btn btn-outline-light my-2 my-sm-0" type="submit">検索</button>
					</div>
				</div>
			</form>
			
		</div>
	</div>
</nav>
<!-- ナビゲーション ここまで -->

<!-- 必要なJavascriptを読み込む -->
<script src="WEB-INF/src/js/jquery-3.4.1.min.js"></script>
<script src="WEB-INF/src/js/bootstrap.bundle.min.js"></script>