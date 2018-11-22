<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
<head>
  <meta charset="utf-8">
  <title>后台管理系统</title>
  <script src="/PerSys/js/admin.js"></script>
  <link rel="stylesheet" href="/PerSys/css/admin.css">
</head>
<body>
<header>
<div class="header">
  <div class="headerleft">
    <strong>CC</strong> <small>企业后台管理</small>
  </div>
  <div class="headerright">
    <ul>
      <li><a href="notice.jsp">通知 </a></li>
      <li class="headerlist">
        <a href="#">管理员 </a>
        <ul id="headersub">
          <li><a href="#">资料</a></li>
          <li><a href="#">发布公告</a></li>
          <li><a href="#top">刷新</a></li>
        </ul>
      </li>
      <li><a href="#">退出</a></li>
    </ul>
  </div>
</div>
</header>

<div class="content">
<div class="sidebar">
    <ul id="list">
      <li>
      <h1><a class="mainlist" href="/PerSys/boss/index.jsp">人事首页</a></h1>
      <ul></ul>
      </li>
      <li>
        <h1><a class="mainlist" href="#">人事管理 <span class="fr">></span></a></h1>
        <ul class="sublist">
          <li><a href="#">员工入职</a></li>
          <li><a href="#">员工离职</a></li>
          <li><a href="#">职位变更</a></li>
          <li><a href="#">职位变更记录</a></li>
        </ul>
      </li>
      <li>
        <h1><a class="mainlist" href="#">工资管理<span class="fr">></span></a></h1>
        <ul class="sublist">
          <li><a href="#">工资信息</a></li>
          <li><a href="#">发放奖金</a></li>
          <li><a href="#">罚款登记</a></li>
          <li><a href="#">考勤信息报表</a></li>
        </ul>
      </li>
      <li>
        <h1><a class="mainlist" href="#">部门管理<span class="fr">></span></a></h1>
        <ul class="sublist">
          <li><a href="#">增加部门</a></li>
          <li><a href="#">管理部门</a></li>
          <li><a href="#">公司部门信息</a></li>
        </ul>
      </li>
      <li>
        <h1><a class="mainlist" href="#">职位管理<span class="fr">></span></a></h1>
        <ul class="sublist">
          <li><a href="#">增加职位</a></li>
          <li><a href="#">管理职位</a></li>
          <li><a href="#">公司职位信息</a></li>
        </ul>
      </li>
      <li>
      <h1><a class="mainlist" href="/PerSys/boss/index.jsp">帮助信息</a></h1>
      <ul></ul>
      </li>
     
    </ul>

    <div class="notice">
      <div >
        <p>公告</p>
        <p>时光静好，与君语；细水流年，与君同。—— Amaze</p>
      </div>
    </div>

    <div class="notice">
      <div>
        <p>welcome</p>
        <p>Welcome to the cc'Company</p>
      </div>
    </div>
</div>

<div class="maincontent">

<h2>Main Content</h2>

</div>

</div>


<footer>
<p>川川科技有限公司 版权所有©2018-2999 技术支持电话：15996351113</p>
</footer>
</body>
</html>
