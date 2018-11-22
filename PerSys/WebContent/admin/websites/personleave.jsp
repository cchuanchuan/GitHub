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
      <Strong>员工离职</Strong><small>/Leave</small>
      </div>
      <%
      String no = request.getParameter("no");
      %>
      <%
      String sql = "select * from person where no='"+no+"'";
      ResultSet rs = db.executeQuery(sql);
      rs.next();
      String name = rs.getString("name");
      String sex = rs.getString("sex");
      String phone = rs.getString("phone");
      String birthday = rs.getString("birthday");
      String education = rs.getString("education");
      String entrytime = rs.getString("entrytime");
      String deptno = rs.getString("deptno");
      String positionno = rs.getString("positionno");
      %>
      <form action="/PerSys/servlet/PersonLeave?no=<%=no %>" onsubmit="return checkperson(this)" method="post">
		<div class="formcontent">
		<div class="title">工号</div>
		<div class="input"><%=no%></div>
		</div>
		
		<div class="formcontent">
		<div class="title">姓名</div>
		<div class="input"><%=name%></div>
		</div>
		
		<div class="formcontent">
		<div class="title">性别</div>
		<div class="input">
		<%if(sex.equals("男")){
			%>
			男
			<%
		}else{
			%>
			女
			<%
		}
		%>
		</div>
		</div>
		
		<div class="formcontent">
		<div class="title">电话</div>
		<div class="input"><%=phone%></div>
		</div>
		
		<div class="formcontent">
		<div class="title">出生日期</div>
		<div class="input">
		<%=birthday %>
		</div>
		</div>
		
		<div class="formcontent">
		<div class="title">入职时间:</div>
		<div class="input">
		<%=entrytime %>
		</div>
		</div>
		
		<div class="formcontent">
		<div class="title">学历:</div>
		<div class="input"><%=education%></div>
		</div>
		
		<div class="formcontent">
		<div class="title">部门:</div>
		<div class="input">
		  <%
		        String sqldept = "select * from dept";
		        ResultSet rs1 = db.executeQuery(sqldept);
		        while(rs1.next()){
		        	if(rs1.getString("deptno").equals(deptno)){
		        		%><%=rs1.getString("deptname")%><%
		        	}
		        }
		  %>
		</div>
		</div>
		
		<div class="formcontent">
		<div class="title">职位:</div>
		<div class="input">
		  <%
		        String sqlposition = "select * from position";
		        ResultSet rs2 = db.executeQuery(sqlposition);
		        while(rs2.next()){
		        	if(positionno.equals(rs2.getString("positionno"))){
			        	%><%=rs2.getString("positionname")%><%
		        	}
		        }
		  %>
		</div>
		</div>
		
		<div class="button">
		<input type="submit" value="提交">
		<input type="reset" value="重置">
		</div>
		
		
      </form>
      
      </div>
      
    
    </div>
</body>
</html>