<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>豆瓣</title>
<link type="text/css" rel="stylesheet" href="css/dbNav.css">
<link type="text/css" rel="stylesheet" href="css/articleInfo.css">
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
		//关闭好友申请输入框
		$(".cancel").click(function() {
			$(".edit-sign").attr("style", "display: none");
		});
		//提交好友申请
		$(".submit").click(function() {
			var group = $(".signature").val();
			if (group.length == 0) {
				alert("分类不能为空");
			} else {
				$.get("friendServlet", {
					method : "applyFriend",
					msg : group,
					guest_email : $(".signature").attr("id")
				}, function(data) {
					alert(data);
				});
				$(".edit-sign").attr("style", "display: none");
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
	//好友申请输入框
	function apply() {
		$(".edit-sign").attr("style", "display: inline");
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
					<form action="homePage" method="get"
						onsubmit="return validateForm();">
						<fieldset>
							<legend>搜索：</legend>
							<label for="inp-query" style="display: none;">搜索你感兴趣的内容和人...</label>
							<div class="inp">
								<input name="method" value="getSearchArticleByPage"
									type="hidden"> <input name="p" value="1" type="hidden">
								<input id="intp-query" name="q" size="22" maxlength="60"
									autocomplete="off" placeholder="搜索你感兴趣的内容和人...">
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

							<div class="new-item-wrapper">
								<div class="new-item">
									<div class="new-item-up">
										<div class="user-pic">
											<img src="${requestScope.article.avatar}">
										</div>
										<div class="user-name">
											<a href="#" target="_blank">${requestScope.article.name}</a>
											<span style="margin-left: 10px; color: red">${requestScope.msg.result}</span>
										</div>

									</div>

									<div class="new-item-down">
										<div class="new-item-down-content">
											<span class="new-item-down-type">
												${requestScope.article.type} <c:if
													test="${sessionScope.account.email != requestScope.article.author_email}">
													<span class="add-friend"><a
														href="javascript:apply()">加好友</a></span>
													<div style="display: none;" class="edit-sign">
														<input id="${requestScope.article.author_email}"
															class="signature" type="text" size="30" maxlength="30"
															placeholder="请输入分类" list="groups"> <input
															class="submit" type="submit" value="申请"> <input
															class="cancel" type="button" value="取消">
													</div>
													<%--好友分组信息--%>
													<datalist id="groups">
														<c:forEach var="group" items="${requestScope.groups}">
															<option value="${group}">
														</c:forEach>
													</datalist>

													<span class="follow"><a
														href="articleInfoServlet?method=follow&author_email=${requestScope.article.author_email}&id=${requestScope.article.id}">关注</a></span>

													<span class="report"><a href="douYou?p=1&guest_email=${requestScope.article.author_email}">发豆邮</a></span>

													<c:choose>
														<c:when test="${sessionScope.account.role == 'admin'}">
															<span class="report"><a
																href="articleInfoServlet?method=delete&id=${requestScope.article.id}">删除</a></span>
														</c:when>
														<c:otherwise>
															<span class="report"><a href="javascript:void(0)">举报</a></span>
														</c:otherwise>
													</c:choose>


												</c:if>
											</span> <br>
											<h3 class="new-item-down-title">
												<a href="#" title="${requestScope.article.title}"
													target="_blank" class="title-link">${requestScope.article.title}</a>
											</h3>
											<br>
											<div class="content-all">${requestScope.article.content}</div>

											<c:forEach var="img"
												items="${requestScope.article.picture_urls}">
												<div class="big-pic">
													<img src="${img}" class="big-pic-list">
												</div>
											</c:forEach>

											<h6>${requestScope.article.modify_time}
												<c:if test="${requestScope.article.hot == 1}">
													<span style="color: red">(热)</span>
												</c:if>

												<%--显示是否置顶--%>
												<c:choose>
													<c:when
														test="${sessionScope.account.role == 'admin' && requestScope.article.hot == 1}">
														<span class="report"><a
															href="articleInfoServlet?method=cancelHot&id=${requestScope.article.id}">取消置顶</a></span>
													</c:when>
													<c:when
														test="${sessionScope.account.role == 'admin' && requestScope.article.hot != 1}">
														<span class="report"><a
															href="articleInfoServlet?method=setHot&id=${requestScope.article.id}">置顶</a></span>
													</c:when>
													<c:otherwise>
													</c:otherwise>
												</c:choose>


												<%--显示数量，已点的不能再点--%>
												<span class="new-item-down-menu"> <c:choose>
														<%--为1就是已经点赞了，不能再点--%>
														<c:when
															test="${requestScope.article.articleInfo.good == 1}">
															<span class="like" style="color: red">赞<em>(${requestScope.articleInfoDetail.good})</em></span>
														</c:when>
														<c:otherwise>
															<span class="like"><a
																href="articleInfoServlet?method=good&id=${requestScope.article.id}">赞<em>(${requestScope.articleInfoDetail.good})</em></a></span>
														</c:otherwise>
													</c:choose> <c:choose>
														<c:when
															test="${requestScope.article.articleInfo.collect == 1}">
															<span class="like" style="color: red">收藏<em>(${requestScope.articleInfoDetail.collect})</em></span>
														</c:when>
														<c:otherwise>
															<span class="like"><a
																href="articleInfoServlet?method=collect&id=${requestScope.article.id}">收藏<em>(${requestScope.articleInfoDetail.collect})</em></a></span>
														</c:otherwise>
													</c:choose> <c:choose>
														<c:when
															test="${requestScope.article.articleInfo.reply == 1}">
															<span class="like" style="color: red">回复<em>(${requestScope.articleInfoDetail.reply})</em></span>
														</c:when>
														<c:otherwise>
															<span class="like"><a href="javascript:void(0)">回复<em>(${requestScope.articleInfoDetail.reply})</em></a></span>
														</c:otherwise>
													</c:choose> <c:choose>
														<c:when
															test="${requestScope.article.articleInfo.forword == 1}">
															<span class="like" style="color: red">转发<em>(${requestScope.articleInfoDetail.forword})</em></span>
														</c:when>
														<c:otherwise>
															<span class="like"><a
																href="articleInfoServlet?method=forword&id=${requestScope.article.id}">转发<em>(${requestScope.articleInfoDetail.forword})</em></a></span>
														</c:otherwise>
													</c:choose>

												</span>

											</h6>
										</div>

									</div>
								</div>
							</div>

						</div>
					</div>

				</div>

			</div>
		</div>
	</div>

</body>
</html>