package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//���õ���ģʽ(singleton)
public class DBConnectionUtil {

	private static DBConnectionUtil dbUtil;

	public final String url = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf-8";
	public final String user = "root";
	public final String password = "root";

	private DBConnectionUtil() {
		// ����������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("����������ʧ��");
		}
	}

	/**
	 * �õ���������
	 * 
	 * @return
	 */
	public static DBConnectionUtil getDBConnectionUtil() {
		if (dbUtil == null) {
			dbUtil = new DBConnectionUtil();
		}
		return dbUtil;
	}

	/**
	 * �õ����ݿ�����
	 * 
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close(ResultSet rs, Statement st, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	 public static void main(String[] args) {
	 DBConnectionUtil util = DBConnectionUtil.getDBConnectionUtil();
	 System.out.println(util.getConnection());
	 }

}
