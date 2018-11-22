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

public class PersonChange extends HttpServlet implements javax.servlet.Servlet{
    public PersonChange() {
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
		String notice = request.getParameter("notice");
		String birthday = year+"-"+month+"-"+day;
		String event = "备注："+notice;
		try {
			PoolDB db=new PoolDB();
			String sql="update person set name=?,sex=?,phone=?,Birthday=?,education=?,deptno=?,positionno=? where no=?";
			PreparedStatement prestate;
			prestate = db.PreparedStatement(sql);
			prestate.setString(1, name);
			prestate.setString(2, sex);
			prestate.setString(3, phone);
			prestate.setString(4, birthday);
			prestate.setString(5, education);
			prestate.setString(6, dept);
			prestate.setString(7, position);
			prestate.setString(8, no);
			prestate.executeUpdate();
			
			if(!notice.equals("")){
			Date date = new Date();
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String changetime = dateFormat.format(date);
			
			
			String sqlchange="insert into PersonChange(No,Name,Event,Time) values('"+no+"','"+name+"','"+event+"','"+changetime+"')";
			db.executeUpdate(sqlchange);
			}
			/*PreparedStatement prechange;
			prechange = db.PreparedStatement(sql);
			prechange.setString(1, no);
			prechange.setString(2, name);
			prechange.setString(3, event);
			prechange.setString(4, changetime);
			prechange.setString(5, dept);
			prechange.setString(6, position);
			prechange.executeUpdate();*/
			
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>修改成功</h1>");
			out.print("<h2>工号："+no+"</h2>");
			out.print("<h2>姓名："+name+"</h2>");
			out.print("<h2>性别："+sex+"</h2>");
			out.print("<h2>电话："+phone+"</h2>");
			out.print("<h2>出生日期："+birthday+"</h2>");
			out.print("<h2>教育背景："+education+"</h2>");
			out.print("<h2>部门："+dept+"</h2>");
			out.print("<h2>职位："+position+"</h2>");
			out.print("<h2>备注："+event+"</h2>");
			out.print("<a href='/PerSys/admin/websites/personchange.jsp?no="+no+"'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
		} catch (SQLException e) {
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>修改失败</h1>");
			out.print("<h2>工号："+no+"</h2>");
			out.print("<h2>姓名："+name+"</h2>");
			out.print("<h2>性别："+sex+"</h2>");
			out.print("<h2>电话："+phone+"</h2>");
			out.print("<h2>出生日期："+birthday+"</h2>");
			out.print("<h2>教育背景："+education+"</h2>");
			out.print("<h2>部门："+dept+"</h2>");
			out.print("<h2>职位："+position+"</h2>");
			out.print("<h2>备注："+event+"</h2>");
			out.print("<h2>"+e.getMessage()+"</h2>");
			out.print("<a href='/PerSys/admin/websites/personchange.jsp?no="+no+"'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
			System.err.println(e.getMessage());
		}
		
		
	}

}
