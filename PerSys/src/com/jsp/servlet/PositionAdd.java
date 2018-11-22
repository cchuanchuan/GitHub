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

public class PositionAdd extends HttpServlet implements javax.servlet.Servlet{
    public PositionAdd() {
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
		
		String positionno = request.getParameter("positionno");
		String positionname = request.getParameter("positionname");
		String base = request.getParameter("base");
		Date date = new Date();
		PoolDB db=new PoolDB();
		String sql = "insert into position(positionno,positionname,base)values('"+positionno+"','"+positionname+"','"+base+"')";
		int jud = db.executeUpdate(sql);
		if(jud ==1){
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>添加职位成功</h1>");
			out.print("<h2>职位编号："+positionno+"</h2>");
			out.print("<h2>职位名字："+positionname+"</h2>");
			out.print("<h2>职位基本工资："+base+"</h2>");
			out.print("<a href='/PerSys/admin/websites/positionedit.jsp'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
		}
		else{
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>添加职位失败</h1>");
			out.print("<h2>职位编号："+positionno+"</h2>");
			out.print("<h2>职位名字："+positionname+"</h2>");
			out.print("<h2>职位基本工资："+base+"</h2>");
			out.print("<a href='/PerSys/admin/websites/positionedit.jsp'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
		}
		
	}

}
