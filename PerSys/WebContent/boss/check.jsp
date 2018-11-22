<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.lah.toolBean.PoolDB"%>
<%@page import="java.io.*" %>
<%@page import="java.util.Calendar" %>
<%PoolDB db = new PoolDB(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>正在打卡</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <meta http-equiv="refresh" content="5;url=javascript :history.back(-1);">  -->
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
      <li><a href="attendance.jsp"><span>考勤信息</span></a></li>
      <li><a href="pay.jsp"><span>工资信息</span></a></li>
      <li><a href="boss/change.jsp"><span>人事异动</span></a></li>
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
      <Strong>欢迎来到川川的公司</Strong><small>/Company</small>
      </div>
      <%
      String no = "";
      if(session.getAttribute("no")!=null){
    	  String sqlname = "select name from person where no='"+session.getAttribute("no")+"'";
    	  ResultSet rsname = db.executeQuery(sqlname);
    	  String name="";
    	  if(rsname.next()){
    		  name=rsname.getString("name");
    	  }
    	  %>
    	  <div class="tablename">
	      <Strong>你好，<%=name%>&nbsp;您的工号为：<%=session.getAttribute("no")%></Strong>
	      </div>
	      <%
    	  no=(String)session.getAttribute("no");
    	  int year=Calendar.getInstance().get(Calendar.YEAR);
		  int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		  int day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		  int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		  int minute=Calendar.getInstance().get(Calendar.MINUTE);
		  ResultSet rswork=db.executeQuery("select * from WorkTime");
		  rswork.next();
		  int dhour=new Integer(rswork.getInt("hour"));
		  int dminute=new Integer(rswork.getInt("minute"));
		  if(hour<dhour){
			  String sqlcheck = "insert into daycheckin(No,Year,Month,Day,Night)"
						+"values('"+no+"',"+year+","+month+","+day+",0)";
			  db.executeUpdate(sqlcheck);
			  %>
	          <div class="tablename">
	          <strong>打卡成功！</strong>
	          <strong><a href="javascript:history.back(-1)">点击返回</a></strong>
	          </div>
	          <%	  
		  }
		  else if(hour>18&&hour<24){
			  String sqlcheck = "insert into daycheckin(No,Year,Month,Day,Night)"
						+"values('"+no+"',"+year+","+month+","+"0,"+day+")";
			  db.executeUpdate(sqlcheck);
			  %>
	          <div class="tablename">
	          <strong>加班打卡成功！</strong>
	          <strong>恭喜获得奖金100元！</strong>
	          <br><br>
	          <strong><a href="javascript:history.back(-1)">点击返回</a></strong>
	          </div>
	          <%
		  }
		  else if(hour>dhour&&hour<18){
			  String sqlcheck1 = "insert into daycheckin(No,Year,Month,Day,Night)"
						+"values('"+no+"',"+year+","+month+","+day+",0)";
			  String sqlcheck2 = "update monthcheckin set Latedays=latedays+1,Deduct=Deduct+50 where monthcheckin.No='"
						+no+"' and monthcheckin.Year='"+year+"' and monthcheckin.Month='"+month+"'";
			  db.executeUpdate(sqlcheck1);
			  db.executeUpdate(sqlcheck2);
			  %>
	          <div class="tablename">
	          <strong>打卡成功，你迟到了！</strong>
	          <strong><a href="javascript:history.back(-1)">点击返回</a></strong>
	          </div>
	          <%
		  } 
      }
      else{
    	  %>
    	  <div class="tablename">
    	  <strong>您未登录，不能打卡</strong>
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