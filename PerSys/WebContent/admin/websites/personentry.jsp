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
<form action="/PerSys/servlet/PersonEntry" onsubmit="return checkperson(this)" method="post">
  <div class="tablename">
  <Strong>员工入职</Strong><small>/Entry</small>
  </div>
<div class="formcontent">
<div class="title">工号</div>
<div class="input"><input type="text" name="no"></div>
</div>

<div class="formcontent">
<div class="title">姓名</div>
<div class="input"><input type="text" name="name"></div>
</div>

<div class="formcontent">
<div class="title">性别</div>
<div class="input">
  <input type="radio" name="sex" value="男">男
  <input type="radio" name="sex" value="女" checked>女
</div>
</div>

<div class="formcontent">
<div class="title">电话</div>
<div class="input"><input type="text" name="phone"></div>
</div>

<div class="formcontent">
<div class="title">出生日期</div>
<div class="input">
<select name="year">
<%
for(int i=1990;i<=2020;i++){
	%><option value=<%=i %>><%=i %></option><%
}
%>
</select>
<select name="month">
<%
for(int i=1;i<=12;i++){
	%><option value=<%=i %>><%=i %></option><%
}
%>
</select>
<select name="day">
<%
for(int i=1;i<=31;i++){
	%><option value=<%=i %>><%=i %></option><%
}
%>
</select>
</div>
</div>

<div class="formcontent">
<div class="title">学历</div>
<div class="input"><input type="text" name="education"></div>
</div>

<div class="formcontent">
<div class="title">部门</div>
<div class="input">
<select name="dept">
  <%
        String sqldept = "select * from dept";
        ResultSet rs1 = db.executeQuery(sqldept);
        while(rs1.next()){
        	%><option value=<%=rs1.getString("deptno")%>><%=rs1.getString("deptname")%></option><%
        }
  %>
  </select>
</div>
</div>

<div class="formcontent">
<div class="title">职位</div>
<div class="input">
<select name="position">
  <%
        String sqlposition = "select * from position";
        ResultSet rs2 = db.executeQuery(sqlposition);
        while(rs2.next()){
        	%><option value=<%=rs2.getString("positionno")%>><%=rs2.getString("positionname")%></option><%
        }
  %>
  </select>
</div>
</div>

<div class="button">
<input type="submit" value="提交">
<input type="reset" value="重置">
</div>


</form>
</div>
</body>
</html>