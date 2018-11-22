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
<link href="/PerSys/css/admin.css" rel='stylesheet' type='text/css' />
<script src="/PerSys/js/admin.js"></script>
</head>
<body>
<div class="maincontent">
      <div class="tablename">
      <Strong>考勤信息</Strong><small>/Table</small>
      </div>
      <div class="table">
	    <table cellspacing="0" border="1">
	      <tr class="tablemenu" align="center">
	      <td colspan="99">
	      <form id="dateform" action="payattendance.jsp">
	        <select name="year" onchange="submitForm(dateform);">
	        <option selected="selected" value=">0">全部</option>
	        <%
	        String year = ">0";
	        String month = ">0";
	        if(request.getParameter("year")!=null){
	        	year = request.getParameter("year");
	        }
	        if(request.getParameter("month")!=null){
	        	month = request.getParameter("month");
	        }
	        String sqlyear="select distinct year from monthcheckin";
	        ResultSet rsyear = db.executeQuery(sqlyear);
	        while(rsyear.next()){
	        	String val = "=" + rsyear.getString("year");
	        	if(year!=null&&year.equals(val)){
		        	%><option selected="selected" value=<%=val %>><%=rsyear.getString("year") %></option><%
	        		continue;
	        	}
	        	%><option value=<%=val %>><%=rsyear.getString("year") %></option><%
	        }
	        %>
	        </select>
	        <span>年</span>&nbsp;
	        <select name="month" onchange="submitForm(dateform);">
	        <option selected="selected" value=">0">全部</option>
	        <%
	        String sqlmonth = "select distinct month from monthcheckin";
	        ResultSet rsmonth = db.executeQuery(sqlmonth);
	        while(rsmonth.next()){
	        	String val = "=" + rsmonth.getString("month");
	        	if(month!=null&&month.equals(val)){
		        	%><option selected="selected" value=<%=val %>><%=rsmonth.getString("month") %></option><%
	        		continue;
	        	}
	        	%><option value=<%=val %>><%=rsmonth.getString("month") %></option><%
	        }
	        %>
	        </select>
	        <span>月</span>
	      </form>
	      
	      </td>
	      </tr>
	      
	      <tr>
	      <%
	      int pagesize=10;
	      int Page=1;
	      int totalpage=1;
	      int totalrecord=0;
	      String sqlpersonnum = "select count(*) from monthcheckin where year"+year+" and month"+month;
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
	      <%
	      String sqlperson="";
	      if(begin!=0){
		      sqlperson = "select top "+end+" no,year,month,workdays,latedays,overtime,bonus,deduct from monthcheckin where year"+year+" and month"+month
		    		  +" except " 
		    		  +"select top "+begin+" no,year,month,workdays,latedays,overtime,bonus,deduct from monthcheckin where year"+year+" and month"+month;
	      }
	      else{
	    	  sqlperson = "select top "+pagesize+" no,year,month,workdays,latedays,overtime,bonus,deduct from monthcheckin where year"+year+" and month"+month;
	      }
	      
	      System.err.print(sqlperson);
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
		      <a href="payattendance.jsp?Page=<%=(Page-1)%>">上一页&nbsp;</a>
		      <a href="payattendance.jsp?Page=<%=(Page+1)%>">下一页</a>
	    	  <%
	      }
	      else{
	    	  %>
	    	  <span>&nbsp;第<%=Page %>页，共<%=totalpage %>页&nbsp;</span>
		      <a href="payattendance.jsp?Page=<%=(Page+1)%>">下一页&nbsp;</a>
		      <a href="payattendance.jsp?Page=<%=(totalpage)%>">最后一页&nbsp;</a>
		      <%
	      }
	      %>
	      </tr>
	      
	      </table>
	        
	      </div>
      
    
    </div>
</body>
</html>