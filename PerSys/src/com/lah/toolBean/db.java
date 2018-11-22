package com.lah.toolBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class db {
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	int result = 0;
	public db(){
		Context ctx;
		try {
			ctx = new InitialContext();
			//java:comp/env/后面的值就是context.xml文件里resource项下name的值
	    	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/personnel");
	    	conn = ds.getConnection();
	    	stmt = conn.createStatement();
		} catch (NamingException | SQLException e) {
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

}
