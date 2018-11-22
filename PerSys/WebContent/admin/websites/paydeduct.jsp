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
  <%
  String no = request.getParameter("no");
  String name = request.getParameter("name");
  %>
<form action="/PerSys/servlet/PayDeduct?no=<%=no %>" onsubmit="return checkpay(this)" method="post">
  <div class="tablename">
  <Strong>进行罚款</Strong><small>/Deduct</small>
  </div>
  
  <div class="formcontent">
  <div class="title">工号</div>
  <div class="input"><%=no %></div>
  </div>

  <div class="formcontent">
  <div class="title">姓名</div>
  <div class="input"><%=name %></div>
  </div>
  
  <div class="formcontent">
  <div class="title">罚款金额</div>
  <div class="input"><input type="text" name="deduct"></div>
  </div>

  <div class="button">
  <input type="submit" value="提交">
  <input type="reset" value="重置">
  </div>

</div>
</form>
</div>
</body>
</html>