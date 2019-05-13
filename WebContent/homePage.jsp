<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

		//分类浏览菜单
		var moreItems = document.getElementsByClassName("more-items1");
		moreItems.flag = 0;
		$(".more-items1").hide();
		$(".bn-more1").click(function() {
			if (!moreItems.flag) {
				$(".more-items1").show();
				moreItems.flag = 1;
			} else {
				$(".more-items1").hide();
				moreItems.flag = 0;
			}
		});

	});
	//搜索
	function validateForm() {
		if ($("#intp-query").val().length == 0) {
			alert("不能为空");
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body>
	<div id="db-global-nav" class="global-nav">
		<div class="bd">

			<div class="top-nav-info">
				<ul>
					<li><a id="top-nav-doumail-link" href="douYou?p=1">豆邮</a></li>
					<li class="nav-user-account"><a class="bn-more" rel="off">
							<span>${account.name}的帐号</span><span class="arrow"></span>
					</a>
						<div class="more-items">
							<table>
								<tbody>
									<tr>
										<td><a target="_blank" href="userPage?p=1">个人主页</a></td>
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
										<td><a href="logout">退出登录</a></td>
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
							<div class="release-type-wrap">	
							<div class="release-wrap">
								<a href="articleEdit.jsp" target="_blank"><button
										type="button">发文章</button></a>
							</div>					
							<div class="type-wrap">
								<button type="button" class="bn-more1">分类浏览</button>

								<div class="more-items1">
									<table>
										<tbody>
											<tr>
												<td><a
													href="homePage?method=getTypeArticleByPage&type=言论&p=1">言论</a></td>
											</tr>
											<tr>
												<td><a
													href="homePage?method=getTypeArticleByPage&type=情感&p=1">情感</a></td>
											</tr>
											<tr>
												<td><a
													href="homePage?method=getTypeArticleByPage&type=美食&p=1">美食</a></td>
											</tr>
											<tr>
												<td><a
													href="homePage?method=getTypeArticleByPage&type=思想&p=1">思想</a></td>
											</tr>
											<tr>
												<td><a
													href="homePage?method=getTypeArticleByPage&type=读书&p=1">读书</a></td>
											</tr>
											<tr>
												<td><a
													href="homePage?method=getTypeArticleByPage&type=音乐&p=1">音乐</a></td>
											</tr>
											<tr>
												<td><a
													href="homePage?method=getTypeArticleByPage&type=社会&p=1">社会</a></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>							
							
							</div>
							<c:forEach var="article" items="${requestScope.list}">
								<div class="new-item-wrapper">
									<div class="new-item">
										<div class="new-item-up">
											<div class="user-pic">
												<img src="${article.avatar}"></img>
											</div>
											<div class="user-name">
												<a href="#" target="_blank">${article.name}</a>
											</div>

										</div>

										<div class="new-item-down">
											<div class="new-item-down-content">
												<h4 class="new-item-down-title">
													<a href="articleInfoServlet?p=1&id=${article.id}"
														title="${article.title}" target="_blank"
														class="title-link">${article.title}</a>
												</h4>
												<h5 class="content-some">${article.content}</h5>

												<div class="small-pic">
													<c:forEach var="img" items="${article.picture_urls}">
														<img src="${img}" class="small-pic-list">
													</c:forEach>
												</div>
												<h6>${article.modify_time}
													<c:if test="${article.hot == 1}">
														<span style="color: red">(热)</span>
													</c:if>
													<c:if test="${article.origin_author != null && article.author_email != article.origin_author}">
														<span>(转自账号：${article.origin_author})</span>
													</c:if>
												</h6>
											</div>

										</div>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>

					<div class="paginator">
						<%--根据当前页数来初始化页码--%>
						<c:choose>
							<c:when test="${param.p == 1}">
								<span class="prev"> &lt;前页 </span>
							</c:when>
							<c:otherwise>
								<a href="${requestScope.target}p=${param.p - 1}">&lt;前页</a>
							</c:otherwise>
						</c:choose>
						<%--初始化起始页码--%>
						<c:choose>
							<c:when test="${param.p - 5 > 0}">
								<c:set var="begin" value="${1 + param.p - 5}" />
							</c:when>
							<c:otherwise>
								<c:set var="begin" value="1" />
							</c:otherwise>
						</c:choose>
						<%--初始化终止页码--%>
						<c:choose>
							<c:when test="${begin + 8 < requestScope.totalPages}">
								<c:set var="end" value="${begin + 8}" />
							</c:when>
							<c:otherwise>
								<c:set var="end" value="${requestScope.totalPages}" />
							</c:otherwise>
						</c:choose>

						<c:forEach var="i" begin="${begin}" end="${end}" step="1">
							<c:choose>
								<c:when test="${param.p == i}">
									<span class="thispage">${i}</span>
								</c:when>
								<c:otherwise>
									<a href="${requestScope.target}p=${i}">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<span class="break">...</span>
						<c:choose>
							<c:when test="${param.p >= requestScope.totalPages}">
								<span class="next">后页 &gt; </span>
							</c:when>
							<c:otherwise>
								<a href="${requestScope.target}p=${param.p + 1}">后页&gt;</a>
							</c:otherwise>
						</c:choose>

					</div>

				</div>

			</div>
		</div>
	</div>

</body>
</html>