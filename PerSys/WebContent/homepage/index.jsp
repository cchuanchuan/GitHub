<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Welcome</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href='https://fonts.googleapis.com/css?family=Raleway:400,100,200,300,500,600,700,800,900' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="css/skel-noscript.css" />
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/style-desktop.css" />
		
	</head>
	<body class="homepage">
	<div id="header-wrapper">
		<!-- Header -->
			<div id="header">
			<div align="right" style="margin-top:0px; margin-right:5%;">欢迎<a href="/ServletLogin/userinformation.jsp" target="allFrame"><%=(String)session.getAttribute("username")%></a>使用本系统</div>
				<div class="container">
					<!-- Logo -->
						<div id="logo">
							<h1><a href="#">imagination</a></h1>
						</div>
					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li class="active"><a href="index.jsp">Homepage</a></li>
								<li><a href="newsmessage.jsp">News message</a></li>
								<li><a href="travelinformation.jsp">travel information</a></li>
								<li><a href="websiteinformation.jsp">website information</a></li>
							</ul>
						</nav>
	
				</div>
			</div>
		<!-- Header -->

		<!-- Banner -->
			<div id="banner">
				<div class="container">
	
					<section>
						<span class="fa fa-cubes"></span>
						<header>
							<h2>Welcome come to here</h2>
							<span class="byline">Believe that something wounderful is about to happen</span>
						</header>
						<a href="/PerSys/boss/index.jsp" class="button medium">&nbsp;人事部门首页&nbsp;</a>
					</section>	
				</div>
			</div>
		<!-- /Banner -->

	</div>

	<!-- Extra -->
		<div id="extra">
			<div class="container">
				<div class="row">
					<section class="3u">
						<header>
							<h2>人事部</h2>
						</header>
						<span class="fa fa-magic"></span>
						<p>Welcome come to here！Believe that something wounderful is about to happen</p>
					</section>
					<section class="3u">
						<header>
							<h2>财务部</h2>
						</header>
						<span class="fa fa-wrench"></span>
						<p>Welcome come to here！Believe that something wounderful is about to happen</p>
					</section>
					<section class="3u">
						<header>
							<h2>市场部</h2>
						</header>
						<span class="fa fa-chain"></span>
						<p>Welcome come to here！Believe that something wounderful is about to happen</p>
					</section>
					<section class="3u">
						<header>
							<h2>实习部</h2>
						</header>
						<span class="fa fa-briefcase"></span>
						<p>Welcome come to here！Believe that something wounderful is about to happen</p>
					</section>
				</div>
				<a href="/PerSys/boss/index.jsp" class="button medium">人事部门首页</a>
			</div>
		</div>

	<!-- /Extra -->

	<!-- Main -->
		<div id="main">
		
			<div class="container">

				<section>
					<header>
						<h2>SomeThing our company do</h2>
						<span class="byline">Believe that something wounderful is about to happen</span>
					</header>
				</section>

				<div class="row">
					<section class="6u right">
						<a href="#" class="image full"><img src="images/pics05.jpg" alt=""></a>
						<p>This is <strong>Imagination</strong>, a responsive HTML5 site template freebie. Released for free under the Creative Commons Attribution</a> license, so use it for whatever (personal or commercial) &ndash; just give us credit! Check out more of our stuff at or follow us on.</p>
					</section>				
					<section class="6u left">
						<a href="#" class="image full"><img src="images/pics06.jpg" alt=""></a>
						<p>Duis pretium velit ac mauris. Proin eu wisi suscipit nulla suscipit interdum. Pellentesque adipiscing purus ac magna. Pellentesque habitant morbi tristique senectus aenean lectus lorem, imperdiet at, ultrices eget, ornare et, wisi purus ac magna. Pellentesque habitant morbi</p>
					</section>
				</div>
				<div class="divider"></div>
				<a href="#" class="button medium">View Full Details</a>		
			</div>
		</div>
	<!-- /Main -->

	<!-- Featured -->
		<div id="featured">
			<div class="container">
				<section>
					<header>
						<h2>Fusce ultrices fringilla metus</h2>
						<span class="byline">Posuere eleifend odio quisque semper mattis</span>
					</header>
				</section>
				<div class="row">
					<section class="4u">
						<a href="#" class="image full"><img src="images/pics01.jpg" alt=""></a>
						<p>Quisque dictum. Integer nisl risus, sagittis convallis, rutrum id, elementum congue, nibh. Suspendisse dictum porta lectus.</p>
						<p><a href="#" class="button">View Full Details</a></p>
					</section>
					<section class="4u">
						<a href="#" class="image full"><img src="images/pics03.jpg" alt=""></a>
						<p>Pellentesque tristique ante ut risus. Integer nisl risus, sagittis convallis, rutrum id, elementum congue, nibh. Suspendisse dictum porta lectus.</p>
						<p><a href="#" class="button">View Full Details</a></p>
					</section>
					<section class="4u">
						<a href="#" class="image full"><img src="images/pics04.jpg" alt=""></a>
						<p>Pellentesque tristique ante ut risus. Quisque dictum. Integer nisl rutrum id, elementum congue, nibh. Suspendisse dictum porta lectus.</p>
						<p><a href="#" class="button">View Full Details</a></p>
					</section>
				</div>
			</div>
		</div>
	<!-- /Featured -->

	<!-- Footer -->
		<div id="footer">
			<div class="container">
				<div class="row">
					<section class="4u">
						<header>
							<h2>Nulla eleifend</h2>
						</header>
						<ul class="default">
							<li><a href="#">Pellentesque quis elit non gravida blandit.</a></li>
							<li><a href="#">Lorem ipsum dolor sit consectetuer adipiscing elit.</a></li>
							<li><a href="#">Phasellus nec erat sit nibh pellentesque congue.</a></li>
							<li><a href="#">Cras vitae metus aliquam pellentesque pharetra.</a></li>
							<li><a href="#">Duis non ante in metus commodo euismod lobortis.</a></li>
						</ul>
					</section>
					<section class="4u">
						<header>
							<h2>Etiam posuere</h2>
						</header>
						<ul class="default">
							<li><a href="#">Pellentesque quis elit non gravida blandit.</a></li>
							<li><a href="#">Lorem ipsum dolor sit consectetuer adipiscing elit.</a></li>
							<li><a href="#">Phasellus nec erat sit nibh pellentesque congue.</a></li>
							<li><a href="#">Cras vitae metus aliquam pellentesque pharetra.</a></li>
							<li><a href="#">Duis non ante in metus commodo euismod lobortis.</a></li>
						</ul>
					</section>
					<section class="4u">
						<header>
							<h2>Mauris vulputate</h2>
						</header>
						<ul class="default">
							<li><a href="#">Pellentesque quis elit non gravida blandit.</a></li>
							<li><a href="#">Lorem ipsum dolor sit consectetuer adipiscing elit.</a></li>
							<li><a href="#">Phasellus nec erat sit nibh pellentesque congue.</a></li>
							<li><a href="#">Cras vitae metus aliquam pellentesque pharetra.</a></li>
							<li><a href="#">Duis non ante in metus commodo euismod lobortis.</a></li>
						</ul>
					</section>
				</div>
			</div>
		</div>
	<!-- /Footer -->

	<!-- Copyright -->
		<div id="copyright">
			<div class="container">
				川川科技有限公司 版权所有©2018-2999 技术支持电话：15996351113
			</div>
		</div>


	</body>
</html>