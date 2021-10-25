package settings;

public class DatabaseSettings {
	/** JDBCドライバ名 */
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	/** JDBC接続文字列（文字コードをutf8mb4_general_ciに設定） */
	public static final String JDBC_URL = "jdbc:mysql://localhost/shared_todo_list?connectionCollation=utf8mb4_general_ci";
	
	public static final String DB_USER = "root";
	
	public static final String DB_PASS = "root";
	
	public static final int DB_EXECUTION_SUCCESS = 1;

	public static final int DB_EXECUTION_FAILURE = 0;
}
