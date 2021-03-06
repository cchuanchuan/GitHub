<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.lah.toolBean.PoolDB"%>
<%@page import="java.io.*" %>
<%PoolDB db = new PoolDB(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>企业首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel='stylesheet' type='text/css' />
</head> 

<body>
<div class="page-container">
  <div class="right-content">
    <div class="top-menu">
	  <div class="top_left">
	    <ul>
	      <li><a href="#">企业介绍</a></li>|
		  <li><a href="#">打卡</a></li>|
		  <li><a href="adminindex.jsp">企业管理</a></li>|
		  <li><a href="#">帮助信息</a></li>
		</ul>
      </div>
      <div class="top_right">
        <ul><li><a>Call us : 159 9635 1113</a> </li></ul>
      </div>
    </div>
    
	<div class="maincontent">
      <div class="tablename">
      <Strong>员工信息</Strong><small>/Table</small>
      </div>
      
      <div class="table">
      <%
      int pagesize=10;
      int Page=1;
      int totalpage=1;
      int totalrecord=0;
      String sqlpersonnum = "select count(*) from (person inner join dept on person.deptno=dept.deptno) inner join position on person.positionno=position.positionno";
      ResultSet rspernum = db.executeQuery(sqlpersonnum);
      rspernum.next();
      totalrecord=rspernum.getInt(1);
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
	      sqlperson = "select top "+end+" no,name,sex,phone,birthday,education,entrytime,deptname,positionname from (person inner join dept on person.deptno=dept.deptno) inner join position on person.positionno=position.positionno"
	    		  +" except " 
	    		  +"select top "+begin+" no,name,sex,phone,birthday,education,entrytime,deptname,positionname from (person inner join dept on person.deptno=dept.deptno) inner join position on person.positionno=position.positionno";
      }
      else{
    	  sqlperson = "select top "+pagesize+" no,name,sex,phone,birthday,education,entrytime,deptname,positionname from (person inner join dept on person.deptno=dept.deptno) inner join position on person.positionno=position.positionno";
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
      <trcolspan="9">
      <%if(Page!=1){
    	  %>
    	  <span>&nbsp;第<%=Page %>页，共<%=totalpage %>页&nbsp;</span>
	      <a href="bossindex.jsp?Page=<%=(Page-1)%>">上一页&nbsp;</a>
	      <a href="bossindex.jsp?Page=<%=(Page+1)%>">下一页</a>
    	  <%
      }
      else{
    	  %>
    	  <span>&nbsp;第<%=Page %>页，共<%=totalpage %>页&nbsp;</span>
	      <a href="bossindex.jsp?Page=<%=(Page+1)%>">下一页&nbsp;</a>
	      <a href="bossindex.jsp?Page=<%=(totalpage)%>">最后一页&nbsp;</a>
	      <%
      }
      %>
      </tr>
      </table>
        
      </div>
    
    </div>
    
  </div>
  
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
    	<a href="login.jsp">请登录！</a>
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
      <li><a href="#"><span>企业首页</span></a></li>
      <li class="focus"><a href="#"><span>员工信息</span></a></li>
      <li id="menulist" ><a href="#"><span> 部门信息</span><span class="fr">></span></a>
        <ul id="menusub" >
        <%
        String sqldept = "select deptname from dept";
        ResultSet rs1 = db.executeQuery(sqldept);
        while(rs1.next()){
        	%>
            <li><a href="#"><%=rs1.getString("deptname")%></a></li>
            <%
        }
        %>
		</ul>
      </li>
      <li id="menulist"><a href="#"><span>职位信息</span><span class="fr">></span></a>
        <ul id="menusub" >
             <%
		        String sqlposition = "select positionname from position";
		        ResultSet rs2 = db.executeQuery(sqlposition);
		        while(rs2.next()){
		        	%>
		            <li><a href="#"><%=rs2.getString("positionname")%></a></li>
		            <%
		        }
		     %>
        </ul>
      </li>
      <li id="menulist"><a href="#"><span>考勤信息</span></a></li>
      <li id="menulist"><a href="#"><span>工资信息</span></a></li>
      <li id="menulist"><a href="#"><span>人事异动</span></a></li>
      <li id="menulist"><a href="#"><span>帮助信息</span></a></li>
  </div>
  </div>
</div>
<footer>
<p>川川科技有限公司 版权所有©2018-2999 技术支持电话：15996351113</p>
</footer>
</body>
</html>