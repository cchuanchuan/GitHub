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
      <Strong>增加部门</Strong><small>/AddDept</small>
      </div>
      
      <div class="table">
      <form action="/PerSys/servlet/DeptAdd" method="post" onsubmit="checkdept(this)">
      
      <div class="formcontent">
	  <div class="title">部门编号</div>
	  <div class="input"><input type="text" name="deptno"></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">部门名字</div>
	  <div class="input"><input type="text" name="deptname"></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">部门经理工号</div>
	  <div class="input"><input type="text" name="managerno"></div>
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