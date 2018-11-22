<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.lah.toolBean.PoolDB"%>
<%@page import="java.io.*" %>
<%PoolDB db = new PoolDB(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>人事首页</title>
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
      <li class="focus"><a href="index.jsp"><span>人事首页</span></a></li>
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
      <Strong>人事首页</Strong><small>/Table</small>
      </div>
      <div class="tablename">
      <Strong>欢迎来到人事部门主页！</Strong>
      </div>
    </div>
    
  </div>
</div>
<footer>
<p>川川科技有限公司 版权所有©2018-2999 技术支持电话：15996351113</p>
</footer>
</body>
</html>