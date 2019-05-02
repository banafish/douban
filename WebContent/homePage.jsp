<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>豆瓣</title>
<link type="text/css" rel="stylesheet" href="css/dbNav.css">
<link type="text/css" rel="stylesheet" href="css/homePage.css">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
	
</script>
<script>
	$(document).ready(function() {
		//账户菜单
		var moreItems = document.getElementsByClassName("more-items");
		moreItems.flag = 0;
		$(".more-items").hide();
		$(".bn-more").click(function() {
			if (!moreItems.flag) {
				$(".more-items").show();
				moreItems.flag = 1;
			} else {
				$(".more-items").hide();
				moreItems.flag = 0;
			}
		});

	});
</script>
</head>
<body>
	<div id="db-global-nav" class="global-nav">
		<div class="bd">

			<div class="top-nav-info">
				<ul>
					<li><a id="top-nav-doumail-link" href="#">豆邮</a></li>
					<li class="nav-user-account"><a class="bn-more" rel="off">
							<span>${account.name}的帐号</span><span class="arrow"></span>
					</a>
						<div class="more-items">
							<table cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td><a target="_blank" href="userPage.jsp">个人主页</a></td>
									</tr>
									<tr>
										<td><a>我的订单</a></td>
									</tr>
									<tr>
										<td><a>我的钱包</a></td>
									</tr>
									<tr>
										<td><a target="_blank" href="#">帐号管理</a></td>
									</tr>
									<tr>
										<td><a href="index.jsp">退出登录</a></td>
									</tr>
								</tbody>
							</table>
						</div></li>
				</ul>
			</div>

			<div class="top-nav-reminder">
				<a>提醒</a>
			</div>

			<div class="top-nav-doubanapp">
				<a class="lnk-doubanapp">下载豆瓣客户端</a>
			</div>

			<div class="global-nav-items">
				<ul>
					<li class="on"><a>豆瓣</a></li>
					<li class=""><a>读书</a></li>
					<li class=""><a>电影</a></li>
					<li class=""><a>音乐</a></li>
					<li class=""><a>同城</a></li>
					<li class=""><a>小组</a></li>
					<li class=""><a>阅读</a></li>
					<li class=""><a>FM</a></li>
					<li class=""><a>时间</a></li>
					<li class=""><a>豆品</a></li>
					<li class=""><a>更多</a></li>
				</ul>
			</div>

		</div>
	</div>

	<div id="db-nav-sns" class="nav">
		<div class="nav-wrap">
			<div class="nav-primary">

				<div class="nav-logo">
					<a href="#">豆瓣社区</a>
				</div>

				<div class="nav-search">
					<form action="getArticleByPage" method="get">
						<fieldset>
							<legend>搜索：</legend>
							<label for="inp-query" style="display: none;">搜索你感兴趣的内容和人...</label>
							<div class="inp">
								<input type="hidden" name="source" value="suggest"> <input
									id="inp-query" name="q" size="22" maxlength="60"
									autocomplete="off" value="" placeholder="搜索你感兴趣的内容和人...">
							</div>
							<div class="inp-btn">
								<input type="submit" value="搜索">
							</div>
						</fieldset>
					</form>
				</div>

				<div class="nav-items">
					<ul>
						<li><a href="#">首页</a></li>
						<li><a href="#">我的豆瓣</a></li>
						<li><a href="#"> 浏览发现 </a></li>
						<li><a href="#"> 话题广场 </a></li>
					</ul>
				</div>

			</div>
		</div>
	</div>

	<div id="wrapper">

		<div id="content">

			<div class="grid-16-8 clearfix">
				<div class="article">

					<div id="statuses">

						<div class="stream-item">
							<div>
								<a href="articleEdit.jsp" target="_blank"><input
									type="button" value="发文章"></a>
							</div>

							<c:forEach var="article" items="${list}">
								<div class="new-item-wrapper">
									<div class="new-item">
										<div class="new-item-up">
											<div class="user-pic">
												<img src=""></img>
											</div>
											<div class="user-name">
												<a href="#" target="_blank">${article.user_email}</a>
											</div>

										</div>

										<div class="new-item-down">
											<div class="new-item-down-content">
												<h4 class="new-item-down-title">
													<a href="#" title="${article.title}" target="_blank"
														class="title-link">${article.title}</a>
												</h4>
												<h5 class="content-some">${article.content}</h5>

												<div class="small-pic">
													<c:forEach var="img" items="${article.picture_urls}">
														<img src="${img}" class="small-pic-list">
													</c:forEach>
												</div>
												<h6>${article.modify_time}</h6>
											</div>

										</div>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>

					<div class="paginator">

						<c:choose>
							<c:when test="${param.p == 1}">
								<span class="prev"> &lt;前页 </span>
							</c:when>
							<c:otherwise>
								<a href="getArticleByPage?p=${param.p - 1}">&gt;前页</a>
							</c:otherwise>
						</c:choose>

						<c:forEach var="i" begin="1" end="9" step="1">
							<c:choose>
								<c:when test="${param.p == i}">
									<span class="thispage">${i}</span>
								</c:when>
								<c:otherwise>
									<a href="getArticleByPage?p=${i}">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${param.p == requestScope.totalPages}">
								<span class="next">后页 &lt; </span>
							</c:when>
							<c:otherwise>
								<a href="getArticleByPage?p=${param.p + 1}">后页&gt;</a>
							</c:otherwise>
						</c:choose>


					</div>

				</div>

			</div>
		</div>
	</div>

</body>
</html>