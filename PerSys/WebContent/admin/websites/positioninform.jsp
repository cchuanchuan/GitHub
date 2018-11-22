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
      <Strong>部门信息</Strong><small>/Table</small>
      </div>
      
      <div class="table">
      <%
      int pagesize=10;
      int Page=1;
      int totalpage=1;
      int totalrecord=0;
      String sqlpersonnum = "select count(*) from position";
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
      <tr>
      <%
      String sqlperson="";
      if(begin!=0){
	      sqlperson = "select top "+end+" position.positionno,positionname,base as 'base pay' from position order by position.positionno"
	    		  +" except " 
	    		  +"select top "+begin+" position.positionno,positionname,base as 'base pay' from position order by position.positionno";
      }
      else{
    	  sqlperson = "select top "+pagesize+" position.positionno,positionname,base as 'base pay' from position order by position.positionno";
      }
      ResultSet rsper = db.executeQuery(sqlperson);
      ResultSetMetaData rsmdper=rsper.getMetaData();
		for(int i=1;i<=rsmdper.getColumnCount();i++){
			%><th><%=rsmdper.getColumnName(i)%></th><%
		} 
	  %>
      </tr>
      <%
      for(int i=0;rsper.next();i++){
    	  %><tr><%
    	  for(int j=0;j<rsmdper.getColumnCount()&&j<=10;j++){
    		  %><td><%=rsper.getString(j+1)%></td><%
    	  }
    	  %></tr><%
      }
      %>
      <tr>
      <%if(Page!=1){
    	  %>
    	  <span>&nbsp;第<%=Page %>页，共<%=totalpage %>页&nbsp;</span>
	      <a href="positioninform.jsp?Page=<%=(Page-1)%>">上一页&nbsp;</a>
	      <a href="positioninform.jsp?Page=<%=(Page+1)%>">下一页</a>
    	  <%
      }
      else{
    	  %>
    	  <span>&nbsp;第<%=Page %>页，共<%=totalpage %>页&nbsp;</span>
	      <a href="positioninform.jsp?Page=<%=(Page+1)%>">下一页&nbsp;</a>
	      <a href="positioninform.jsp?Page=<%=(totalpage)%>">最后一页&nbsp;</a>
	      <%
      }
      %>
      </tr>
      </table>
        
      </div>
    
    </div>
</body>
</html>