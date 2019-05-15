<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人主页</title>
<link type="text/css" rel="stylesheet" href="css/dbNav.css">
<link type="text/css" rel="stylesheet" href="css/chat.css">
<link rel="icon" type="image/x-icon" href="css/images/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon" href="css/images/favicon.ico" />
<script src="js/jquery.min.js"></script>
<script src="js/chat.js"></script>
</head>
<body>

	<div id="db-global-nav" class="global-nav">
		<div class="bd">

			<div class="top-nav-info">
				<ul>
					<li><a id="top-nav-doumail-link" href="douYou?p=1">豆邮</a></li>
					<li class="nav-user-account"><a class="bn-more" rel="off">
							<input type="hidden" class="user-avatar" id="${sessionScope.account.avatar}">
							<span>${sessionScope.account.name}的帐号</span><span class="arrow"></span>
					</a>
						<div class="more-items">
							<table>
								<tbody>
									<tr>
										<td><a target="_blank" href="userPage?p=1">个人主页</a></td>
									</tr>
									<tr>
										<td><a href="logout">退出登录</a></td>
									</tr>
								</tbody>
							</table>
						</div></li>
				</ul>
			</div>

			<div class="global-nav-items">
				<ul>
					<li class="on"><a href="homePage?p=1">豆瓣</a></li>
					<li class=""><a	href="homePage?method=getTypeArticleByPage&type=言论&p=1">言论</a></li>
					<li class=""><a	href="homePage?method=getTypeArticleByPage&type=情感&p=1">情感</a></li>
					<li class=""><a href="homePage?method=getTypeArticleByPage&type=美食&p=1">美食</a></li>
					<li class=""><a href="homePage?method=getTypeArticleByPage&type=思想&p=1">思想</a></li>
					<li class=""><a href="homePage?method=getTypeArticleByPage&type=读书&p=1">读书</a></li>
					<li class=""><a href="homePage?method=getTypeArticleByPage&type=音乐&p=1">音乐</a></li>
					<li class=""><a href="homePage?method=getTypeArticleByPage&type=社会&p=1">社会</a></li>
				</ul>
			</div>

		</div>
	</div>

	<div id="db-nav-sns" class="nav">
		<div class="nav-wrap">
			<div class="nav-primary">

				<div class="nav-logo">
					<a href="homePage?p=1">豆瓣社区</a>
				</div>

				<div class="nav-search">
					<form action="homePage" method="get"
						onsubmit="return validateForm();">
						<fieldset>
							<legend>搜索：</legend>
							<label for="inp-query" style="display: none;">搜索你感兴趣的内容...</label>
							<div class="inp">
								<input name="method" value="getSearchArticleByPage"
									type="hidden"> <input name="p" value="1" type="hidden">
								<input id="intp-query" name="q" size="22" maxlength="60"
									autocomplete="off" placeholder="搜索你感兴趣的内容...">
							</div>
							<div class="inp-btn">
								<input type="submit" value="搜索">
							</div>
						</fieldset>
					</form>
				</div>

				<div class="nav-items">
					<ul>
						<li><a href="homePage?p=1">首页</a></li>
						<li><a href="articleEdit.jsp" target="_blank"> 发文章</a></li>						
					</ul>
				</div>

			</div>
		</div>
	</div>

	<div id="wrapper">
		<div id="content">
			<h1>聊天</h1>
			<div class="grid-16-8 clearfix">
			<input type="hidden" class="to-email" id="${requestScope.email}">
			<input type="hidden" class="avatar" id="${requestScope.avatar}">
			<br><h4>${requestScope.name}</h4>
				<div class="article">	
				</div>
				<textarea class="content-input" placeholder="请输入内容" maxlength="225"></textarea>
							<br> <input type="button" value="发送" class="but-submit"/>
			</div>


		</div>
	</div>
</body>
</html>