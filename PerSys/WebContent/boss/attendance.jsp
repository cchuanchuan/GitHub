<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.lah.toolBean.PoolDB"%>
<%@page import="java.io.*" %>
<%PoolDB db = new PoolDB(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>考勤信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/PerSys/css/style.css" rel='stylesheet' type='text/css' />
<script src="/PerSys/js/index.js"></script>
</head> 

<body onLoad="clock()">
<div class="page-container">
    <div class="left-menu">
  <div class="usermessage">
    <center><span>
    <%if(session.getAttribute("username")!=null){
    	%>
    	用户：<%=session.getAttribute("username") %><br>
            欢迎登录本系统！<br>
            您的身份是：<%=session.getAttribute("positionname") %>
    	<%
    }
    else{
    	%>
    	您好，您的身份是游客<br>
    	<a href="/PerSys/login.jsp">请登录！</a>
    	<%
    }
    %>
    <!-- 用户：<%=session.getAttribute("username") %><br>
            欢迎登录本系统！<br>
            您的身份是：<%=session.getAttribute("positionname") %> -->
            </span>
    </center>
  </div>
  <div class="menu">
    <ul id="menu" >
      <li><a href="index.jsp"><span>人事首页</span></a></li>
      <li><a href="person.jsp"><span>员工信息</span></a></li>
      <li><a href="dept.jsp"><span> 部门信息</span><span class="fr">></span></a>
        <ul class="menusub">
        <%
        String sqldept = "select * from dept";
        ResultSet rs1 = db.executeQuery(sqldept);
        while(rs1.next()){
        	%>
            <li><a href="deptlist.jsp?dept=<%=rs1.getString("deptname")%>&deptno=<%=rs1.getString("deptno")%>"><%=rs1.getString("deptname")%></a></li>
            <%
        }
        %>
		</ul>
      </li>
      <li><a href="position.jsp"><span>职位信息</span><span class="fr">></span></a>
        <ul class="menusub">
             <%
		        String sqlposition = "select * from position";
		        ResultSet rs2 = db.executeQuery(sqlposition);
		        while(rs2.next()){
		        	%>
		            <li><a href="positionlist.jsp?position=<%=rs2.getString("positionname")%>&positionno=<%=rs2.getString("positionno")%>"><%=rs2.getString("positionname")%></a></li>
		            <%
		        }
		     %>
        </ul>
      </li>
      <li class="focus"><a href="attendance.jsp"><span>考勤信息</span></a></li>
      <li><a href="pay.jsp"><span>工资信息</span></a></li>
      <li><a href="change.jsp"><span>人事异动</span></a></li>
      <li><a href="help.jsp"><span>帮助信息</span></a></li>
  </div>
  </div>
  
  <div class="right-content">
    <div class="top-menu">
	  <div class="top_left">
	    <ul>
	      <li><a href="/PerSys/homepage/index.jsp">企业首页</a></li>|
		  <li><a href="check.jsp">打卡</a></li>|
		  <li><a href="/PerSys/admin/index.jsp">企业管理</a></li>|
		  <li><a href="help.jsp">帮助信息</a></li>
		</ul>
      </div>
      <div class="top_right" id="bgclock">
      </div>
    </div>
    
    <div class="maincontent">
      <div class="tablename">
      <Strong>考勤信息</Strong><small>/Table</small>
      </div>
      <%
      if(session.getAttribute("no")!=null){
    	  String sqlname = "select name from person where no='"+session.getAttribute("no")+"'";
    	  ResultSet rsname = db.executeQuery(sqlname);
    	  String name="";
    	  if(rsname.next()){
    		  name=rsname.getString("name");
    	  }
    	  %>
    	  <div class="tablename">
	      <Strong>你好，<%=name%>&nbsp;</Strong><small>您的工号为：<%=session.getAttribute("no") %></small>
	      </div>
    	  <div class="table">
	      <table cellspacing="0" border="1">
	      <tr class="tablemenu" align="center">
	      <td colspan="99">
	      <form id="dateform" action="attendance.jsp">
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
	        String sqlyear="select distinct year from monthcheckin where monthcheckin.no='"+(String)session.getAttribute("no")+"'";
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
	        String sqlmonth = "select distinct month from monthcheckin where monthcheckin.no='"+(String)session.getAttribute("no")+"'";
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
	      String sqlpersonnum = "select count(*) from monthcheckin where monthcheckin.no='"+(String)session.getAttribute("no")+"' and year"+year+" and month"+month;
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
		      sqlperson = "select top "+end+" year,month,workdays,latedays,overtime,bonus,deduct from monthcheckin where monthcheckin.no='"+(String)session.getAttribute("no")+"' and year"+year+" and month"+month
		    		  +" except " 
		    		  +"select top "+begin+" year,month,workdays,latedays,overtime,bonus,deduct from monthcheckin where monthcheckin.no='"+(String)session.getAttribute("no")+"' and year"+year+" and month"+month;
	      }
	      else{
	    	  sqlperson = "select top "+pagesize+" year,month,workdays,latedays,overtime,bonus,deduct from monthcheckin where monthcheckin.no='"+(String)session.getAttribute("no")+"' and year"+year+" and month"+month;
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
		      <a href="attendance.jsp?Page=<%=(Page-1)%>">上一页&nbsp;</a>
		      <a href="attendance.jsp?Page=<%=(Page+1)%>">下一页</a>
	    	  <%
	      }
	      else{
	    	  %>
	    	  <span>&nbsp;第<%=Page %>页，共<%=totalpage %>页&nbsp;</span>
		      <a href="attendance.jsp?Page=<%=(Page+1)%>">下一页&nbsp;</a>
		      <a href="attendance.jsp?Page=<%=(totalpage)%>">最后一页&nbsp;</a>
		      <%
	      }
	      %>
	      </tr>
	      
	      </table>
	        
	      </div>
    	  <%
      }
      else{
    	  %>
    	  <div class="tablename">
    	  <strong>您未登录，不能查询考勤信息</strong>
    	  <br><br>
    	  <strong><a href="/PerSys/login.jsp">请登录！</a></strong>
    	  </div>
    	  <%
      }
      %>
      
    
    </div>
    
  </div>
</div>
<footer>
<p>川川科技有限公司 版权所有©2018-2999 技术支持电话：15996351113</p>
</footer>
</body>
</html>