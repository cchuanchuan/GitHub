package com.jsp.servlet;


import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.lah.toolBean.PoolDB;
import java.sql.*;



public class Login extends HttpServlet implements javax.servlet.Servlet{
    public Login() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String sql = "select * from useraccount where username='"+username+"'and password='"+password+"'";
		String sql2 = "select positionname from "
				+ "(position inner join person on person.positionno=position.positionno) "
				+ "inner join UserAccount on person.no=useraccount.no where useraccount.username='"
				+username+"'";
		PoolDB db = new PoolDB();
		try{
			ResultSet rs1 = db.executeQuery(sql);
			if(rs1.next()){
				session.setAttribute("username", username);
				String no = rs1.getString("no");
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
				session.setAttribute("username", username);
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
