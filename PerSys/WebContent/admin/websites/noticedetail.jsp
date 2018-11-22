<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.lah.toolBean.PoolDB"%>
<%@page import="java.io.*" %>
<%PoolDB db = new PoolDB(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/PerSys/js/admin.js"></script>
<link rel="stylesheet" href="/PerSys/css/admin.css">
</head>
<body>
<div class="maincontent">
      <div class="tablename">
      <Strong>公告信息</Strong><small>/AddNotice</small>
      </div>
      <div class="table">
      <%
      String number = request.getParameter("number");
      if(number !=null){

      String sql = "select * from notice where number='"+number+"'";
      ResultSet rs = db.executeQuery(sql);
      if(rs.next()){
    	  %>
	  <div class="formcontent">
	  <div class="title">标题</div>
	  <div class="content"><%=rs.getString("title") %></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">内容</div>
	  <div class="content"><%=rs.getString("content") %></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">附件</div>
	  <div class="content"><td><a href="<%=rs.getString("filepath")%>"><%=rs.getString("filename") %></a></td></div>
	  </div>

	  <div class="button">
	  <a href="notice.jsp">点击返回</a>
	  </div>
      <%
      }
	  
  }
      %>

      </div>
</div>
</body>
</html>