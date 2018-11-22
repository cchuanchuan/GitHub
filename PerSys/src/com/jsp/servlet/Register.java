package com.jsp.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.lah.valueBean.*;
import com.lah.toolBean.*;



public class Register extends HttpServlet implements javax.servlet.Servlet{
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		String no = request.getParameter("no");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		UserAccount user = new UserAccount();
		user.setNo(no);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		request.setAttribute("username", username);
		PoolDB db = new PoolDB();
		String sql = "insert into userAccount values('"+no+"','"+username+"','"+password+"','"+email+"')";
		String sql2 = "select positionname from position inner join person on person.positionno=position.positionno where no='"+no+"'";
		int complish = db.executeUpdate(sql);
		if(complish == 1){
			session.setAttribute("username", username);
			session.setAttribute("no", no);
			String positionname;
			try {
				ResultSet rs = db.executeQuery(sql2);
				rs.next();
				positionname = rs.getString("positionname");
				session.setAttribute("positionname", positionname);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			response.sendRedirect("/PerSys/boss/index.jsp");
		}
		else{
			response.sendRedirect("/PerSys/error.jsp");
		}
	}

}
