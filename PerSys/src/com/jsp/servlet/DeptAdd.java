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

public class DeptAdd extends HttpServlet implements javax.servlet.Servlet{
    public DeptAdd() {
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
		
		String deptno = request.getParameter("deptno");
		String deptname = request.getParameter("deptname");
		String managerno = request.getParameter("managerno");
		Date date = new Date();
		PoolDB db=new PoolDB();
		String sql = "insert into dept(deptno,deptname,managerno)values('"+deptno+"','"+deptname+"','"+managerno+"')";
		int jud = db.executeUpdate(sql);
		if(jud ==1){
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>添加部门成功</h1>");
			out.print("<h2>部门编号："+deptno+"</h2>");
			out.print("<h2>部门名字："+deptname+"</h2>");
			out.print("<h2>部门经理："+managerno+"</h2>");
			out.print("<a href='/PerSys/admin/websites/deptedit.jsp'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
		}
		else{
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>添加部门失败</h1>");
			out.print("<h2>部门编号："+deptno+"</h2>");
			out.print("<h2>部门名字："+deptname+"</h2>");
			out.print("<h2>部门经理："+managerno+"</h2>");
			out.print("<a href='/PerSys/admin/websites/deptedit.jsp'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
		}
		
	}

}
