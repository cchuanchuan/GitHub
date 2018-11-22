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
      
      <div class="table">
      <%
      String number = request.getParameter("number");
      if(number !=null){
      String sql = "delete from notice where number='"+number+"'";
      System.err.print(sql);
      int jud = db.executeUpdate(sql);
      if(jud == 1){
      %>
	  <div class="tablename">
      <Strong>删除成功</Strong><small>/DeleteNotice</small>
      </div>
      
      <div class="tablename">
      <Strong><a href="notice.jsp">点击返回</a></Strong>
      
      </div>
      <%
      }
      else{
    	  %>
    	  <div class="tablename">
          <Strong>删除失败</Strong><small>/DeleteNotice</small>
          </div>
          
          <div class="tablename">
          <Strong><a href="notice.jsp">点击返回</a></Strong>
          
          </div>
          <%
      }
  	  }
      %>

      </div>
</div>
</body>
</html>