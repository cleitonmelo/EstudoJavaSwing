package br.com.estudo.java.swing.people.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtils {

	/*
	 * 
	 */
	public static Connection getConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

		Properties properties = new Properties();
		properties.setProperty("user", "dev_estudo");
		properties.setProperty("password", "Estudo123@#");
		properties.setProperty("useSSL", "false");
		properties.setProperty("serverTimezone", "GMT");

		String stringConexao = "jdbc:mysql://localhost:3306/estudo";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(stringConexao, properties);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn) throws SQLException {
		if ( conn != null){
			conn.close();
		}
	}

	/*
	 * 
	 */
	public static ResultSet getResultSet(Connection conn, String sql) throws SQLException {
		Statement statement = conn.createStatement();
		return statement.executeQuery(sql);
	}

	/*
	 * 
	 */
	public static PreparedStatement getPreparedStatement(Connection conn, String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}
}
