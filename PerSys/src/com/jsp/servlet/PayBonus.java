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

public class PayBonus extends HttpServlet implements javax.servlet.Servlet{
    public PayBonus() {
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
		String bonus = request.getParameter("bonus");
		Date date = new Date();
		String year = (date.getYear()+1900)+"";
		String month = (date.getMonth()+1)+"";
		String sql="update monthcheckin set bonus=bonus+"+bonus+" where year='"+year+"'and month='"+month+"'and no='"+no+"'";
		PoolDB db=new PoolDB();
		int jud = db.executeUpdate(sql);
		if(jud ==1){
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>奖励成功</h1>");
			out.print("<h2>工号："+no+"</h2>");
			out.print("<h2>奖金："+bonus+"</h2>");
			out.print("<a href='/PerSys/admin/websites/payedit.jsp'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
		}
		else{
			out.print("<html>");
			out.print("<body>");
			out.println("<h1>奖励失败</h1>");
			out.print("<h2>工号："+no+"</h2>");
			out.print("<h2>奖金："+bonus+"</h2>");
			out.print("<a href='/PerSys/admin/websites/payedit.jsp'><h1>点击返回</h1></a>");
			out.print("</body>");
			out.print("</html>");
		}
		
	}

}
