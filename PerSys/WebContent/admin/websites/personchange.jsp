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
      <Strong>员工信息修改</Strong><small>/Change</small>
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
      <form action="/PerSys/servlet/PersonChange?no=<%=no %>" onsubmit="return checkperson(this)" method="post">
		<div class="formcontent">
		<div class="title">工号</div>
		<div class="input"><%=no%></div>
		</div>
		
		<div class="formcontent">
		<div class="title">姓名</div>
		<div class="input"><input type="text" name="name" value="<%=name%>"></div>
		</div>
		
		<div class="formcontent">
		<div class="title">性别</div>
		<div class="input">
		<%if(sex.equals("男")){
			%>
			<input type="radio" name="sex" value="男" checked>男
			<input type="radio" name="sex" value="女">女
			<%
		}else{
			%>
			<input type="radio" name="sex" value="男">男
			<input type="radio" name="sex" value="女" checked>女
			<%
		}
		%>
		</div>
		</div>
		
		<div class="formcontent">
		<div class="title">电话</div>
		<div class="input"><input type="text" name="phone" value="<%=phone%>"></div>
		</div>
		
		<div class="formcontent">
		<div class="title">出生日期</div>
		<div class="input">
		<select name="year">
		<%
		int y = Integer.parseInt(birthday.substring(0, 4));
		int m = Integer.parseInt(birthday.substring(5, 7));
		int d = Integer.parseInt(birthday.substring(8, 10));
		for(int i=1990;i<=2020;i++){
			if(i == y)
			{
				%><option value=<%=i %> selected="selected"><%=i %></option><%
			}
			else{
				%><option value=<%=i %>><%=i %></option><%
			}
		}
		%>
		</select>
		<select name="month">
		<%
		for(int i=1;i<=12;i++){
			if(i == m){
				%><option value=<%=i %> selected="selected"><%=i %></option><%
			}
			else{
				%><option value=<%=i %>><%=i %></option><%
			}
		}
		%>
		</select>
		<select name="day">
		<%
		for(int i=1;i<=31;i++){
			if(i == d){
				%><option value=<%=i %> selected="selected"><%=i %></option><%
			}
			else{
				%><option value=<%=i %>><%=i %></option><%
			}
		}
		%>
		</select>
		</div>
		</div>
		
		<div class="formcontent">
		<div class="title">学历</div>
		<div class="input"><input type="text" name="education" value="<%=education%>"></div>
		</div>
		
		<div class="formcontent">
		<div class="title">部门</div>
		<div class="input">
		<select name="dept">
		  <%
		        String sqldept = "select * from dept";
		        ResultSet rs1 = db.executeQuery(sqldept);
		        while(rs1.next()){
		        	if(rs1.getString("deptno").equals(deptno)){
		        		%><option value=<%=rs1.getString("deptno")%> selected="selected"><%=rs1.getString("deptname")%></option><%
		        	}
		        	else{
		        		%><option value=<%=rs1.getString("deptno")%>><%=rs1.getString("deptname")%></option><%
		        	}
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
		        	if(positionno.equals(rs2.getString("positionno"))){
			        	%><option value=<%=rs2.getString("positionno")%> selected="selected"><%=rs2.getString("positionname")%></option><%
		        	}
		        	else{
			        	%><option value=<%=rs2.getString("positionno")%>><%=rs2.getString("positionname")%></option><%
		        	}
		        }
		  %>
		  </select>
		</div>
		</div>
		
		<div class="formcontent">
		<div class="title">备注</div>
		<div class="input"><textarea name="notice" rows="3" cols="23"></textarea></div>
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