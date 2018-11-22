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
<%
String deptno = request.getParameter("deptno");
String sql = "select * from dept where deptno='"+deptno+"'";
ResultSet rs = db.executeQuery(sql);
String deptname = "";
String managerno = "";
if(rs.next()){
	  deptname = rs.getString("deptname");
	  managerno = rs.getString("managerno");
}
String sqldelete = "delete from dept where deptno='"+deptno+"'";
int jud = db.executeUpdate(sqldelete);
if(jud == 1){
	%>
	<div class="maincontent">
    <div class="tablename">
    <Strong>删除成功</Strong><small>/DeleteDept</small>
    </div>
    <div class="table">
    <form action="/PerSys/servlet/deptdeletedo" method="post" onsubmit="checkdept(this)">
    
    <div class="formcontent">
	  <div class="title">部门编号</div>
	  <div class="input"><%=deptno %></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">部门名字</div>
	  <div class="input"><%=deptname %></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">部门经理工号</div>
	  <div class="input"><%=managerno %></div>
	  </div>
	  
	  <div class="tablename">
    <Strong><a href='/PerSys/admin/websites/deptedit.jsp'>点击返回</a></Strong>
    </div>
	  
    </form>
    </div>
  
</div>
<%
}
else{
	%>
	<div class="maincontent">
    <div class="tablename">
    <Strong>删除失败</Strong><small>/DeleteDept</small>
    </div>
    <div class="table">
    <form action="/PerSys/servlet/deptdeletedo" method="post" onsubmit="checkdept(this)">
    
    <div class="formcontent">
	  <div class="title">部门编号</div>
	  <div class="input"><%=deptno %></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">部门名字</div>
	  <div class="input"><%=deptname %></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">部门经理工号</div>
	  <div class="input"><%=managerno %></div>
	  </div>
	  
	  <div class="tablename">
    <Strong><a href='/PerSys/admin/websites/deptedit.jsp'>点击返回</a></Strong>
    </div>
	  
    </form>
    </div>
  
</div>
<%
}
%>

</body>
</html>