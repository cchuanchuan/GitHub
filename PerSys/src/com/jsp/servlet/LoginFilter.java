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
	String backUrl;//���û�е�¼���ͷ��ص�ָ��ҳ��
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//diFilter()��������������HTTP���������Ҫ����Ҫ��servletresponseת��Ϊ��HttpServletResponse
		HttpServletRequest httprequest = (HttpServletRequest)request;
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		
		//��ȡsession����
		HttpSession session = httprequest.getSession();
		
		//���û�е�¼���жϹ��������ӣ����ص���¼ҳ��
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
						chain.doFilter(request, response);//����,�����ߵ��¸�����Ŀ����Դ��
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
		//�ӹ������������л�ó�ʼ�����������û�о�ʹ��Ĭ��ֵ
		if(filterconfig.getInitParameter("backurl")!=null){
			backUrl = filterconfig.getInitParameter("backurl");
		}
		else{
			backUrl = "/PerSys/boss/warning.jsp";
		}
	}
	
	

}
