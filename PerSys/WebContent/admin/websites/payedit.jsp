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
      <Strong>员工奖惩管理</Strong><small>/Table</small>
      </div>
      
      <div class="table">
      <%
      int pagesize=10;
      int Page=1;
      int totalpage=1;
      int totalrecord=0;
      String sqlpersonnum = "select count(*) from person";
      ResultSet rspernum = db.executeQuery(sqlpersonnum);
      if(rspernum.next()){
    	  totalrecord=rspernum.getInt(1);
      }
      if(totalrecord%pagesize==0){
    	  totalpage=totalrecord/pagesize;
      }
      else{
    	  totalpage=totalrecord/pagesize+1;
      }
      if(totalpage==0){
    	  totalpage=1;
      }
      if(request.getParameter("Page")==null || request.getParameter("Page").equals("")){
    	  Page=1;
      }
      else{
    	  try{
    		  Page=Integer.parseInt(request.getParameter("Page"));
    	  }catch(NumberFormatException e){
    		  Page=1;
    	  }
      }
      if(Page<1)
    	  Page=1;
      if(Page>totalpage)
    	  Page=totalpage;
      int begin=(Page-1)*pagesize;
      int end=begin+pagesize;
      
      %>
      <table cellspacing="0" border="1">
      <tr class="tablemenu" align="center">
	      <td colspan="99">
	      <form class="search" action="personedit.jsp">
	        <input type="text" name="no">
	        <input type="submit" value="搜索">
	      </form>
	      </td>
	      </tr>
      <tr>
      <%
      String sqlperson="";
      if(request.getParameter("no")==null){
	      if(begin!=0){
		      sqlperson = "select top "+end+" no,name,sex,phone,deptname,positionname from (person inner join dept on person.deptno=dept.deptno) inner join position on person.positionno=position.positionno"
		    		  +" except " 
		    		  +"select top "+begin+" no,name,sex,phone,deptname,positionname from (person inner join dept on person.deptno=dept.deptno) inner join position on person.positionno=position.positionno";
	      }
	      else{
	    	  sqlperson = "select top "+pagesize+" no,name,sex,phone,deptname,positionname from (person inner join dept on person.deptno=dept.deptno) inner join position on person.positionno=position.positionno";
	      }
      }else{
    	  sqlperson = "select top "+pagesize+" no,name,sex,phone,deptname,positionname from (person inner join dept on person.deptno=dept.deptno) inner join position on person.positionno=position.positionno where person.no='"+request.getParameter("no")+"'";
      }
      ResultSet rsper = db.executeQuery(sqlperson);
      ResultSetMetaData rsmdper=rsper.getMetaData();
		for(int i=1;i<=rsmdper.getColumnCount();i++){
			%><th><%=rsmdper.getColumnName(i)%></th><%
		} 
	  %>
	  <th>操作</th>
      </tr>
      <%
      for(int i=0;rsper.next();i++){
    	  %><tr><%
    	  for(int j=0;j<rsmdper.getColumnCount()&&j<=10;j++){
    		  %><td><%=rsper.getString(j+1)%></td><%
    	  }
    	  %>
    	  <td>
    	    <button><a href="/PerSys/admin/websites/paybonus.jsp?no=<%=rsper.getString("no") %>&name=<%=rsper.getString("name")%>" target="mainframe">奖励</a></button>
    	    <button><a href="/PerSys/admin/websites/paydeduct.jsp?no=<%=rsper.getString("no") %>&name=<%=rsper.getString("name")%>" target="mainframe">罚款</a></button>
    	  </td>
    	  </tr>
    	  <%
      }
      %>
      <tr>
      <%if(Page!=1){
    	  %>
    	  <span>&nbsp;第<%=Page %>页，共<%=totalpage %>页&nbsp;</span>
	      <a href="payedit.jsp?Page=<%=(Page-1)%>">上一页&nbsp;</a>
	      <a href="payedit.jsp?Page=<%=(Page+1)%>">下一页</a>
    	  <%
      }
      else{
    	  %>
    	  <span>&nbsp;第<%=Page %>页，共<%=totalpage %>页&nbsp;</span>
	      <a href="payedit.jsp?Page=<%=(Page+1)%>">下一页&nbsp;</a>
	      <a href="payedit.jsp?Page=<%=(totalpage)%>">最后一页&nbsp;</a>
	      <%
      }
      %>
      </tr>
      </table>   
      </div>
    </div>
</body>
</html>