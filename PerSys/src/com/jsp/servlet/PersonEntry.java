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

public class PersonEntry extends HttpServlet implements javax.servlet.Servlet{
    public PersonEntry() {
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
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String education = request.getParameter("education");
		String dept = request.getParameter("dept");
		String position = request.getParameter("position");
		String birthday = year+"-"+month+"-"+day;
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String entrytime = dateFormat.format(date);
		try {
			String sql="insert into person(No,Name,Sex,Phone,Birthday,Education,Entrytime,DeptNo,PositionNo) values(?,?,?,?,?,?,?,?,?)";
			PoolDB db=new PoolDB();
			PreparedStatement prestate;
			prestate = db.PreparedStatement(sql);
			prestate.setString(1, no);
			prestate.setString(2, name);
			prestate.setString(3, sex);
			prestate.setString(4, phone);
			prestate.setString(5, birthday);
			prestate.setString(6, education);
			prestate.setString(7, entrytime);
			prestate.setString(8, dept);
			prestate.setString(9, position);
			prestate.executeUpdate();
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>插入成功</h1>");
			out.print("<h2>工号："+no+"</h2>");
			out.print("<h2>姓名："+name+"</h2>");
			out.print("<h2>性别："+sex+"</h2>");
			out.print("<h2>电话："+phone+"</h2>");
			out.print("<h2>出生日期："+birthday+"</h2>");
			out.print("<h2>入职时间："+entrytime+"</h2>");
			out.print("<h2>教育背景："+education+"</h2>");
			out.print("<h2>部门："+dept+"</h2>");
			out.print("<h2>职位："+position+"</h2>");
			out.print("<a href='/PerSys/admin/websites/personentry.jsp'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
		} catch (SQLException e) {
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>插入失败</h1>");
			out.print("<h2>工号："+no+"</h2>");
			out.print("<h2>姓名："+name+"</h2>");
			out.print("<h2>性别："+sex+"</h2>");
			out.print("<h2>电话："+phone+"</h2>");
			out.print("<h2>出生日期："+birthday+"</h2>");
			out.print("<h2>入职时间："+entrytime+"</h2>");
			out.print("<h2>教育背景："+education+"</h2>");
			out.print("<h2>部门："+dept+"</h2>");
			out.print("<h2>职位："+position+"</h2>");
			out.print("<h2>"+e.getMessage()+"</h2>");
			out.print("<a href='/PerSys/admin/websites/personentry.jsp'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
			System.err.println(e.getMessage());
		}
		
		
	}

}
