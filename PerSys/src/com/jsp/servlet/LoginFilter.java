package com.jsp.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.lah.toolBean.PoolDB;
import java.sql.*;
public class LoginFilter implements Filter{
	String backUrl;//如果没有登录，就返回到指定页面
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//diFilter()方法参数并不是HTTP对象，如果必要，需要将servletresponse转换为由HttpServletResponse
		HttpServletRequest httprequest = (HttpServletRequest)request;
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		
		//获取session对象
		HttpSession session = httprequest.getSession();
		
		//如果没有登录就中断过滤器连接，返回到登录页面
		if(session.getAttribute("username") == null || session.getAttribute("no") == null){
			httpresponse.sendRedirect(backUrl);
		}
		else{
			String sql = "select positionno from person where no='"+session.getAttribute("no")+"'";
			PoolDB db = new PoolDB();
			ResultSet rs = db.executeQuery(sql);
			try {
				if(rs.next()){
					if(rs.getString("positionno").equals("00201")){
						chain.doFilter(request, response);//放行,让其走到下个链或目标资源中
					}else{
						httpresponse.sendRedirect(backUrl);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			    
		}
	}

	public void init(FilterConfig filterconfig) throws ServletException {
		//从过滤器的配置中获得初始化参数，如果没有就使用默认值
		if(filterconfig.getInitParameter("backurl")!=null){
			backUrl = filterconfig.getInitParameter("backurl");
		}
		else{
			backUrl = "/PerSys/boss/warning.jsp";
		}
	}
	
	

}
