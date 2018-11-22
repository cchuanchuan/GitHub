package com.lah.toolBean;

import java.sql.*;

public class PoolDB {
	public Connection con = null;
	ResultSet rs = null;
	Statement stmt = null;
	String url = "jdbc:sqlserver://47.106.97.135:1433;databaseName=personnel";
	String username = "ccc";
	String password = "257173";
	int result = 0;
	public PoolDB(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		try {
			this.con = DriverManager.getConnection(url,username,password);
			stmt=con.createStatement();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public ResultSet executeQuery(String sql){
		try{
			rs = stmt.executeQuery(sql);
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return rs;
	}
	
	public int executeUpdate(String sql){
		try {
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	public void closeDB(){
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	public PreparedStatement PreparedStatement(String sql) throws SQLException
	{
		return con.prepareStatement(sql);
	}

}
