<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <Strong>添加公告信息</Strong><small>/AddNotice</small>
      </div>
      <div class="table">
      <!-- 上传文件对应表单的连个必要条件：method="post",enctype="multipart/form-data -->
      <form action = "/PerSys/servlet/NoticeAdd?no="<%=session.getAttribute("no") %>" method="post" enctype="multipart/form-data">

	  <div class="formcontent">
	  <div class="title">标题</div>
	  <div class="input"><input type="text" name="title"></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">内容</div>
	  <div class="input"><textarea rows="6" cols="22" name="content"></textarea></div>
	  </div>
	  
	  <div class="formcontent">
	  <div class="title">附件</div>
	  <div class="input"><input type="file" name="attachment"></div>
	  </div>

	  <div class="button">
	  <input type="submit" value="发布">
	  <input type="reset" value="重置">
	  </div>


    
      </form>
      </div>
</div>
</body>
</html>