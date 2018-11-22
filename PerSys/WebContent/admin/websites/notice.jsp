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
      <Strong>通知信息</Strong><small>/Table</small>
      </div>
      
      <div class="table">
      <%
      int pagesize=10;
      int Page=1;
      int totalpage=1;
      int totalrecord=0;
      String sqlpersonnum = "select count(*) from notice";
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
      
      <%
      String sqlperson="";
	  if(begin!=0){
		  sqlperson = "select top "+end+" person.no,number,name,title,content,filename,filepath,noticetime from notice inner join person on person.no=notice.no order by number desc"
		    	  +" except " 
		    	  +"select top "+begin+" person.no,number,name,title,content,filename,filepath,noticetime from notice inner join person on person.no=notice.no order by number desc";
		  }
	      else{
	    	  sqlperson = "select top "+pagesize+" person.no,number,name,title,content,filename,filepath,noticetime from notice inner join person on person.no=notice.no order by number desc";
	  }
      ResultSet rsper = db.executeQuery(sqlperson);
	  %>
	  <tr>
	  <th>序号</th>
	  <th>发布者</th>
	  <th>标题</th>
	  <th>内容</th>
	  <th>附件</th>
	  <th>发布时间</th>
	  <th>操作</th>
      </tr>
      <%
      for(int i=0;rsper.next();i++){
    	  %>
    	  <tr>
    	    <td><%=rsper.getString("number") %></td>
    	    <td><a href="personedit.jsp?no=<%=rsper.getString("no")%>"><%=rsper.getString("name") %></a></td>
    	    <td><%=rsper.getString("title") %></td>
    	    <%
    	    String realcontent = (rsper.getString("content").length())>10?(rsper.getString("content").substring(0,15)+"..."):rsper.getString("content");
    	    %>
    	    <td><%=realcontent %></td>
    	    <%
    	    String realfilename = (rsper.getString("filename").length())>10?(rsper.getString("filename").substring(0,10)+"..."):rsper.getString("filename");
    	    %>
    	    <td><a href="<%=rsper.getString("filepath")%>"><%=realfilename %></a></td>
    	    <td><%=rsper.getString("noticetime") %></td>
    	    
    	    <td>
    	      <button><a href="/PerSys/admin/websites/noticedetail.jsp?number=<%=rsper.getString("number") %>" target="mainframe">详情</a></button>
    	      <button><a href="/PerSys/admin/websites/noticedelete.jsp?number=<%=rsper.getString("number") %>" target="mainframe">删除</a></button>
    	    </td>
    	  </tr>
    	  <%
      }
      %>
      <tr>
      <%if(Page!=1){
    	  %>
    	  <span>&nbsp;第<%=Page %>页，共<%=totalpage %>页&nbsp;</span>
	      <a href="deptedit.jsp?Page=<%=(Page-1)%>">上一页&nbsp;</a>
	      <a href="deptedit.jsp?Page=<%=(Page+1)%>">下一页</a>
    	  <%
      }
      else{
    	  %>
    	  <span>&nbsp;第<%=Page %>页，共<%=totalpage %>页&nbsp;</span>
	      <a href="deptedit.jsp?Page=<%=(Page+1)%>">下一页&nbsp;</a>
	      <a href="deptedit.jsp?Page=<%=(totalpage)%>">最后一页&nbsp;</a>
	      <%
      }
      %>
      </tr>
      </table>   
      </div>
    </div>
</body>
</html>