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
<div class="sidebar">
    <ul id="list">
      <li>
      <h1><a class="mainlist" href="/PerSys/boss/index.jsp" target="_blank">人事首页</a></h1>
      <ul></ul>
      </li>
      <li>
      <h1><a class="mainlist" href="websites/index.jsp" target="mainframe">员工信息</a></h1>
      <ul></ul>
      </li>
      <li>
        <h1><a class="mainlist" href="#">人事管理 <span class="fr">></span></a></h1>
        <ul class="sublist">
          <li><a href="websites/personentry.jsp" target="mainframe">员工入职</a></li>
          <li><a href="websites/personedit.jsp" target="mainframe">员工离职</a></li>
          <li><a href="websites/personedit.jsp" target="mainframe">职位变更</a></li>
          <li><a href="websites/personchangeinform.jsp" target="mainframe">职位变更记录</a></li>
        </ul>
      </li>
      <li>
        <h1><a class="mainlist" href="#">工资管理<span class="fr">></span></a></h1>
        <ul class="sublist">
          <li><a href="websites/payinform.jsp" target="mainframe">工资信息</a></li>
          <li><a href="websites/payedit.jsp" target="mainframe">发放奖金</a></li>
          <li><a href="websites/payedit.jsp" target="mainframe">罚款登记</a></li>
          <li><a href="websites/payattendance.jsp" target="mainframe">考勤信息报表</a></li>
        </ul>
      </li>
      <li>
        <h1><a class="mainlist" href="#">部门管理<span class="fr">></span></a></h1>
        <ul class="sublist">
          <li><a href="websites/deptadd.jsp" target="mainframe">增加部门</a></li>
          <li><a href="websites/deptedit.jsp" target="mainframe">管理部门</a></li>
          <li><a href="websites/deptinform.jsp" target="mainframe">公司部门信息</a></li>
        </ul>
      </li>
      <li>
        <h1><a class="mainlist" href="#">职位管理<span class="fr">></span></a></h1>
        <ul class="sublist">
          <li><a href="websites/positionadd.jsp" target="mainframe">增加职位</a></li>
          <li><a href="websites/positionedit.jsp" target="mainframe">管理职位</a></li>
          <li><a href="websites/positioninform.jsp" target="mainframe">公司职位信息</a></li>
        </ul>
      </li>
      <li>
      <h1><a class="mainlist" href="websites/help.jsp" target="mainframe">帮助信息</a></h1>
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
</body>
</html>