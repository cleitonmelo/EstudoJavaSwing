package br.com.estudo.java.swing.cadastropessoa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtils {

	public static Connection getConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

		Properties properties = new Properties();
		properties.setProperty("user", "root");
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
}
