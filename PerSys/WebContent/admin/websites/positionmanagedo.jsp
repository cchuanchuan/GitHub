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
request.setCharacterEncoding("UTF-8");
String positionno = request.getParameter("positionno");
String positionname = request.getParameter("positionname");
String base = request.getParameter("base");
String sqlchange = "update position set positionname='"+positionname+"',base='"+base+"' where positionno='"+positionno+"'";
int jud = db.executeUpdate(sqlchange);
if(jud == 1){
	%>
	<div class="maincontent">
    <div class="tablename">
    <Strong>修改成功</Strong><small>/ManagePosition</small>
    </div>
    <%
    String sql = "select * from position where positionno='"+positionno+"'";
    ResultSet rs = db.executeQuery(sql);
    if(rs.next()){
  	  positionname = rs.getString("positionname");
  	  base = rs.getString("base");
    }
    %>
    <div class="table">
    
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
	  
	  <div class="tablename">
    <Strong><a href='/PerSys/admin/websites/positionedit.jsp'>点击返回</a></Strong>
    </div>
    </div>
  
</div>
<%
}
else{
	%>
	<div class="maincontent">
    <div class="tablename">
    <Strong>修改失败</Strong><small>/ManagerPosition</small>
    </div>
    <div class="table">
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
	  
	  <div class="tablename">
    <Strong><a href='/PerSys/admin/websites/positionedit.jsp'>点击返回</a></Strong>
    </div>
    </div>

    </div>
  
</div>
<%
}
%>

</body>
</html>