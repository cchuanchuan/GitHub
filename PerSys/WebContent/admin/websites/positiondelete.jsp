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
      <Strong>确认删除职位</Strong><small>/DeletePosition</small>
      </div>
      <%
      String positionno = request.getParameter("positionno");
      String sql = "select * from position where positionno='"+positionno+"'";
      ResultSet rs = db.executeQuery(sql);
      String positionname = "";
      String base = "";
      if(rs.next()){
    	  positionname = rs.getString("positionname");
    	  base = rs.getString("base");
      }
      %>
      <div class="table">
      <form action="positiondeletedo.jsp?positionno=<%=positionno %>" method="post" onsubmit="checkposition(this)">
      
      <div class="formcontent">
	  <div class="title">职位编号</div>
	  <div class="input"><%=positionno %></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">职位名字</div>
	  <div class="input"><%=positionname%></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">职位基本工资</div>
	  <div class="input"><%=base%></div>
	  </div>
	  
	  <div class="button">
	  <input type="submit" value="提交">
	  <input type="reset" value="重置">
	  </div>
	  
      </form>
      </div>
    
</div>
</body>
</html>