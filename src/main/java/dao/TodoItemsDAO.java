package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.ToDoItemsModel;
import model.UsersModel;
import settings.DatabaseSettings;

public class TodoItemsDAO {
	// ベースとなるSQL文
	private final String SQL_BASE = "SELECT "
										+ "t.id, "
										+ "t.user_id, "
										+ "t.registration_date, "
										+ "t.expiration_date, "
										+ "t.finished_date, "
										+ "t.todo_item, "
										+ "t.is_deleted, "
										+ "t.created_at, "
										+ "t.updated_at, "
										+ "u.email, "
										+ "u.pass, "
										+ "u.name, "
										+ "u.is_deleted, "
										+ "u.created_at, "
										+ "u.updated_at "
									+ "FROM todo_items t "
									+ "INNER JOIN users u ON t.user_id = u.id ";
	
	
	/**
	 * SELECT文で取得した全ての内容をlistに保存する
	 * @param SQL文 (SELECT)
	 * @return List
	 */
	private static List<ToDoItemsModel> selectAll(String sql) {
		List<ToDoItemsModel> todoList = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement pStmt = null;
		
		try {
			//DBに接続
			DBConnection db = new DBConnection();
			connection = db.getConnection();
			
			//SELECT文を実行
			pStmt = connection.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			// 取得したUSERデータをLISTに保存
			while(rs.next()) {
				UsersModel user = new UsersModel();
				user.setId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setPass(rs.getString("pass"));
				user.setName(rs.getString("name"));
				ToDoItemsModel todo = new ToDoItemsModel();
				todo.setId(rs.getInt("id"));
				todo.setUserId(rs.getInt("user_id"));
				todo.setRegistrationDate(rs.getDate("registration_date"));
				todo.setExpirationDate(rs.getDate("expiration_date"));
				todo.setFinishedDate(rs.getDate("finished_date"));
				todo.setTodoItem(rs.getString("todo_item"));
				todo.setIsDeleted(rs.getInt("is_deleted"));
				todo.setUsersModel(user);
				todoList.add(todo);
			}
			return todoList;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		} finally{
			// 後始末
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			if(pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 全てのデータを取得
	 * @return list
	 */
	public List<ToDoItemsModel> findAll() {
		List<ToDoItemsModel> todoList = new ArrayList<>();
		
		//SELECT文を作成
		String sql = SQL_BASE
						+ "WHERE t.is_deleted=0 AND u.is_deleted=0 "
						+ "ORDER BY t.expiration_date asc, t.id desc";
		//SELECT文を実行
		todoList.addAll(selectAll(sql));

		return todoList;
	}
	

	/**
	 * todo_item を検索
	 * @param String todo もしくは 作業者
	 * @return list
	 */
	public List<ToDoItemsModel> findItems(String sSearch) {
		List<ToDoItemsModel> todoList = new ArrayList<>();
		
		String sql = null;
		//SELECT文を作成 (item名または作業者名で検索)
		sql = SQL_BASE
				+ "WHERE t.is_deleted=0 AND u.is_deleted=0 "
				+ "AND ( t.todo_item LIKE '%" + sSearch + "%' "
						+ "OR "
						+ "u.name LIKE '%" + sSearch + "%' ) "
				+ "ORDER BY t.expiration_date asc, t.id desc";
		
		//SELECT文を実行
		todoList.addAll(selectAll(sql));
		return todoList;
	}
	
	
	/**
	 * TodoItemのidでToDoItemsModelを取得
	 * @param id : ToDoItemsModelのid
	 * @return ToDoItemsModel
	 */
	public ToDoItemsModel findOneByID(String id) {
		List<ToDoItemsModel> todoList = new ArrayList<>();
		
		String sql = SQL_BASE
						+ "WHERE t.id=" + id;
		
		todoList.addAll(selectAll(sql));
		return todoList.get(0);
	}
	
	/**
	 * TODOItemを追加
	 * @param ToDoItemsModel
	 * @return DB_EXECUTION_SUCCESS か DB_EXECUTION_FAILURE
	 */
	public int insert(ToDoItemsModel toDoItems) {
		// 戻り値
		DatabaseSettings dbs = new DatabaseSettings();
		
		Connection connection = null;
		PreparedStatement pStmt = null;
		
		try {
			//データベースに接続
			DBConnection db = new DBConnection();
			connection = db.getConnection();
			
			//SQL文を準備
			String sql = "INSERT INTO "
							+ "todo_items ("
							+ "user_id, "
							+ "registration_date, "
							+ "expiration_date, "
							+ "todo_item "
							+ ") VALUES ( "
							+ "?, "	// user_id
							+ "?, "	// registration_date
							+ "?, "	// expiration_date
							+ "? " // todo_item
							+ ")";
			pStmt = connection.prepareStatement(sql);
			pStmt.setInt	(1, toDoItems.getUserId());
			pStmt.setDate	(2, toDoItems.getRegistrationDate());
			pStmt.setDate	(3, toDoItems.getExpirationDate());
			pStmt.setString	(4, toDoItems.getTodoItem());
			
			//INSERT文を実行
			int result = pStmt.executeUpdate();
			if(result != 1) {return dbs.DB_EXECUTION_FAILURE;}
			return dbs.DB_EXECUTION_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return dbs.DB_EXECUTION_FAILURE;
		} finally {
			// 後始末
			if(connection != null)	{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 *  todo item の更新
	 * @param ToDoItemsModel
	 * @return DB_EXECUTION_SUCCESS か DB_EXECUTION_FAILURE
	 */
	public int update(ToDoItemsModel ToDoItems) {
		//戻り値
		DatabaseSettings dbs = new DatabaseSettings();

		Connection connection = null;
		PreparedStatement pStmt = null;

		try {
			//データベースに接続
			DBConnection db = new DBConnection();
			connection = db.getConnection();
			
			//UPDATE文を準備
			String sql = "UPDATE "
							+ "todo_items "
							+ "SET "
							+ "user_id = ?, "
							+ "expiration_date = ?, "
							+ "finished_date = ?, "
							+ "todo_item = ?, "
							+ "is_deleted = ? "
							+ "WHERE id = ?";
			pStmt = connection.prepareStatement(sql);
			pStmt.setInt	(1, ToDoItems.getUserId());
			pStmt.setDate	(2, ToDoItems.getExpirationDate());
			pStmt.setDate	(3, ToDoItems.getFinishedDate());
			pStmt.setString	(4, ToDoItems.getTodoItem());
			pStmt.setInt	(5, ToDoItems.getIsDeleted());
			pStmt.setInt	(6, ToDoItems.getId());
			
			//INSERT文を実行
			int result = pStmt.executeUpdate();
			if(result != 1) {return dbs.DB_EXECUTION_FAILURE;}
			return dbs.DB_EXECUTION_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return dbs.DB_EXECUTION_FAILURE;
		} finally {
			// 後始末
			if(connection != null)	{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
