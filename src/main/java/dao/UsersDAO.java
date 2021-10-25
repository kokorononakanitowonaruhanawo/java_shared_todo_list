package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.UsersModel;
import settings.DatabaseSettings;

public class UsersDAO {

	/**
	 * 	全てのデータを取得
	 * 	@return	userList
	 */
	public List<UsersModel> findAll() {
		List<UsersModel> usersList = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement pStmt = null;
		
		try {
			//データベースに接続
			DBConnection db = new DBConnection();
			connection = db.getConnection();
			
			//SQL文を準備
			String sql = "SELECT "
							+ "id, "
							+ "email, "
							+ "pass, "
							+ "name "
							+ "FROM users "
							+ "WHERE is_deleted = 0";
			
			//SELECT文を実行
			pStmt = connection.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			// 取得したUSERデータをLISTに保存
			while(rs.next()) {
				UsersModel user = new UsersModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPass(rs.getString("pass"));
				user.setName(rs.getString("name"));
				usersList.add(user);
			}
			return usersList;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
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
	 * ユーザーを検索
	 * 	@param user_id
	 * 	@param pass
	 */
	public UsersModel search(String name, String pass) {
		UsersModel user = new UsersModel();
		
		Connection connection = null;
		PreparedStatement pStmt = null;
		
		try {
			//データベースに接続
			DBConnection db = new DBConnection();
			connection = db.getConnection();
			
			//SQL文を準備
			String sql = "SELECT "
							+ "id, "
							+ "email, "
							+ "pass, "
							+ "name "
							+ "FROM users "
							+ "WHERE is_deleted = 0 "
							+ "AND name = ? "
							+ "AND pass = ?";
			
			//SELECT文を実行
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, pass);
			ResultSet rs = pStmt.executeQuery();
			
			
			// 取得したUSERデータをLISTに保存
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPass(rs.getString("pass"));
				user.setName(rs.getString("name"));
			}
			if(user.getName() == null) {
				return null;
			}
			return user;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 	ユーザー登録
	 * 	@param UsersModel
	 * 	@retrun DB_EXECUTION_SUCCESS か DB_EXECUTION_FAILURE 
	 */
	public int insert(UsersModel user) {
		// 戻り値
		DatabaseSettings dbs = new DatabaseSettings();
		
		Connection connection = null;
		PreparedStatement pStmt = null;
		
		try {
			//データベースに接続
			DBConnection db = new DBConnection();
			connection = db.getConnection();
			
			//SQL文を準備
			String sql = "INSERT "
							+ "INTO "
							+ "users ("
							+ "email, "
							+ "pass, "
							+ "name"
							+ " ) VALUES ("
							+ "?, "	//email
							+ "?, "	//pass
							+ "?"	//name
							+ ")";
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, user.getEmail());
			pStmt.setString(2, user.getPass());
			pStmt.setString(3, user.getName());
			//INSERT文を実行
			int result = pStmt.executeUpdate();
			if(result != 1) {return dbs.DB_EXECUTION_FAILURE;}
			return dbs.DB_EXECUTION_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return dbs.DB_EXECUTION_FAILURE;
		}  finally {
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
	 *  ユーザー情報の更新
	 *  @param UsersModel
	 *  @return DB_EXECUTION_SUCCESS か DB_EXECUTION_FAILURE 
	 */
	public int update(UsersModel user) {
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
							+ "users "
							+ "SET "
							+ "email = ?, "
							+ "pass = ?, "
							+ "name = ?, "
							+ "is_deleted = ? "
							+ "WHERE id = ?";
			pStmt = connection.prepareStatement(sql);
			pStmt.setString	(1, user.getEmail());
			pStmt.setString	(2, user.getPass());
			pStmt.setString	(3, user.getName());
			pStmt.setInt	(4, user.getIsDeleted());
			pStmt.setInt	(5, user.getId());
			//INSERT文を実行
			int result = pStmt.executeUpdate();
			if(result != 1) {return dbs.DB_EXECUTION_FAILURE;}
			return dbs.DB_EXECUTION_SUCCESS;
		} catch (ClassNotFoundException | SQLException e) {
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
