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
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonLeave extends HttpServlet implements javax.servlet.Servlet{
    public PersonLeave() {
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
		
		String no = request.getParameter("no");
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String leavetime = dateFormat.format(date);
		try {
			PoolDB db=new PoolDB();
			String sqlpeople = "select * from person where no='"+no+"'";
		    ResultSet rs = db.executeQuery(sqlpeople);
		    rs.next();
		    String name = rs.getString("name");
		    String sex = rs.getString("sex");
		    String phone = rs.getString("phone");
		    String birthday = rs.getString("birthday");
		    String education = rs.getString("education");
		    String entrytime = rs.getString("entrytime");
		    String deptno = rs.getString("deptno");
		    String positionno = rs.getString("positionno");
		    
			String sql="delete from person where no='"+no+"'";
			db.executeUpdate(sql);
			
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>离职成功</h1>");
			out.print("<h2>工号："+no+"</h2>");
			out.print("<h2>姓名："+name+"</h2>");
			out.print("<h2>性别："+sex+"</h2>");
			out.print("<h2>电话："+phone+"</h2>");
			out.print("<h2>出生日期："+birthday+"</h2>");
			out.print("<h2>入职时间："+entrytime+"</h2>");
			out.print("<h2>教育背景："+education+"</h2>");
			out.print("<h2>部门："+deptno+"</h2>");
			out.print("<h2>职位："+positionno+"</h2>");
			out.print("<a href='/PerSys/admin/websites/index.jsp'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
		} catch (SQLException e) {
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>删除失败</h1>");
			out.print("<h2>工号："+no+"</h2>");
			out.print("<h2>"+e.getMessage()+"</h2>");
			out.print("<a href='/PerSys/admin/websites/index.jsp'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
			System.err.println(e.getMessage());
		}
		
		
	}

}
