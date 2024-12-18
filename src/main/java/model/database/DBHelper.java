package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	private final String dbName = "ltm";
	private final String userID = "root";
	private final String password = "";
	private final String url = "jdbc:mysql://127.0.0.1:3306/" + dbName;
	private Connection conn;
	private PreparedStatement ps;
	private static DBHelper _Instance;

	public static DBHelper GetDBHelper() {
		if (_Instance == null) {
			_Instance = new DBHelper();
		}
		return _Instance;
	}

	private DBHelper() {
	}

	public ResultSet GetRecords(String sql, Object... parameterValues) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, userID, password);
		ps = conn.prepareStatement(sql);
		for (int i = 0; i < parameterValues.length; i++) {
			ps.setObject(i + 1, parameterValues[i]);
		}
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public void ExecuteDB(String sql, Object... parameterValues) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, userID, password);
		ps = conn.prepareStatement(sql);
		for (int i = 0; i < parameterValues.length; i++) {
			ps.setObject(i + 1, parameterValues[i]);
		}
		ps.executeUpdate();
	}

	public Integer addAndReturnId(String sql, Object... parameterValues) throws ClassNotFoundException, SQLException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, userID, password);

			// Chuẩn bị câu lệnh với RETURN_GENERATED_KEYS
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < parameterValues.length; i++) {
				ps.setObject(i + 1, parameterValues[i]);
			}

			// Thực thi câu lệnh thêm
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// Đảm bảo đóng PreparedStatement và Connection
			CloseConn();
		}
		return 1;
	}

	public void CloseConn() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
	}
}
