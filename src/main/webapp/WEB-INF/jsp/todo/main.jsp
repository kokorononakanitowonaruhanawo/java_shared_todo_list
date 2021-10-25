<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>
<html lang="ja">

<head>
	<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
    <title>作業一覧</title>
    <style>
        /* ボタンを横並びにする */
        form {
            display: inline-block;
        }

        /* 打消し線を入れる */
        tr.del>td {
            text-decoration: line-through;
        }

        /* ボタンのセルは打消し線を入れない */
        tr.del>td.button {
            text-decoration: none;
        }
    </style>
</head>

<body>
    <!-- ナビゲーション -->
	<%@ include file="/WEB-INF/jsp/include/nav.jsp"%>
    <!-- ナビゲーション ここまで -->

	<!-- コンテナ -->
    <div class="container">
        <table class="table table-striped table-hover table-sm my-2">
            <thead>
                <tr>
                    <th scope="col">項目名</th>
                    <th scope="col">担当者</th>
                    <th scope="col">登録日</th>
                    <th scope="col">期限日</th>
                    <th scope="col">完了日</th>
                    <th scope="col">操作</th>
                </tr>
            </thead>

            <tbody>
            <!-- 組み込み -->
            <c:forEach var="item" items="${sessionScope.todoItems}" >
				<c:choose>
					<c:when test="${item.getFinishedDate() != null}">
						<tr class="del">
					</c:when>
					<c:when test="${item.getExpirationDate().before(today) }">
						<tr class="text-danger">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>
					<td class="align-middle"><c:out value="${item.getTodoItem()}"/></td>
					<td class="align-middle"><c:out value="${item.getUsersModel().getName()}"/></td>
					<td class="align-middle"><c:out value="${item.getRegistrationDate()}"/></td>
					<td class="align-middle"><c:out value="${item.getExpirationDate()}"/></td>
					<c:choose>
						<c:when test="${item.getFinishedDate() != null}">
							<td class="align-middle"><c:out value="${item.getFinishedDate()}" /></td>
						</c:when>
						<c:otherwise>
							<td class="align-middle">未</td>
						</c:otherwise>
					</c:choose>
							<td class="align-middle button">
						<form action="/shared_todo_list/TodoUpdateServlet?action=complete" method="post" class="my-sm-1">
							<input type="hidden" name="item_id" value="${item.getId()}">
							<button class="btn btn-primary my-0" type="submit">完了</button>
						</form>
						<form action="/shared_todo_list/TodoUpdateServlet?action=edit" method="post" class="my-sm-1">
							<input type="hidden" name="item_id" value="${item.getId()}">
							<input class="btn btn-primary my-0" type="submit" value="修正">
						</form>
						<form action="/shared_todo_list/TodoUpdateServlet?action=delete" method="post" class="my-sm-1">
							<input type="hidden" name="item_id" value="${item.getId()}">
							<input class="btn btn-primary my-0" type="submit" value="削除">
						</form>
					</td>
				</tr>
				</c:forEach>
            </tbody>
        </table>


    </div>
    <!-- コンテナ ここまで -->

    <!-- 必要なJavascriptを読み込む -->
    <%@ include file="/WEB-INF/jsp/include/js.jsp"%>

</body>

</html>