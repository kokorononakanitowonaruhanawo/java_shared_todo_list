package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import settings.DatabaseSettings;

public class DBConnection {
	private Connection connection = null;

	/**
	 * データベース接続クラス
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  DBConnection() throws ClassNotFoundException, SQLException {
		// JDBCドライバを読み込み
		Class.forName(DatabaseSettings.DRIVER_NAME);
		// データベースへ接続
		this.connection= DriverManager.getConnection(DatabaseSettings.JDBC_URL, 
													 DatabaseSettings.DB_USER,
													 DatabaseSettings.DB_PASS);
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}
