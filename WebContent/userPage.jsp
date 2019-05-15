<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人主页</title>
<link type="text/css" rel="stylesheet" href="css/userPage.css">
<link type="text/css" rel="stylesheet" href="css/dbNav.css">
<link rel="icon" type="image/x-icon" href="css/images/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon"
	href="css/images/favicon.ico" />
<script src="js/jquery.min.js"></script>
<script src="js/userPage.js"></script>
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
					<li class=""><a
						href="homePage?method=getTypeArticleByPage&type=言论&p=1">言论</a></li>
					<li class=""><a
						href="homePage?method=getTypeArticleByPage&type=情感&p=1">情感</a></li>
					<li class=""><a
						href="homePage?method=getTypeArticleByPage&type=美食&p=1">美食</a></li>
					<li class=""><a
						href="homePage?method=getTypeArticleByPage&type=思想&p=1">思想</a></li>
					<li class=""><a
						href="homePage?method=getTypeArticleByPage&type=读书&p=1">读书</a></li>
					<li class=""><a
						href="homePage?method=getTypeArticleByPage&type=音乐&p=1">音乐</a></li>
					<li class=""><a
						href="homePage?method=getTypeArticleByPage&type=社会&p=1">社会</a></li>
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
					<form action="friendServlet" method="get"
						onsubmit="return validateForm();">
						<fieldset>
							<legend>搜索：</legend>
							<label for="inp-query" style="display: none;">搜索你感兴趣的人...</label>
							<div class="inp">
								<input name="method" value="searchPeople" type="hidden">
								<input name="p" value="1" type="hidden"> <input
									id="intp-query" name="keyWord" size="22" maxlength="60"
									autocomplete="off" placeholder="搜索你感兴趣的人...">
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
			<div class="grid-16-8 clearfix">

				<div class="article drop-area ui-sortable">


					<div id="db-usr-profile">

						<div class="pic">
							<input type="file" style="display: none" id="uploadfile"
								accept="image/*" /> <a href="javascript:void(0);"> <img
								src=${sessionScope.account.avatar } class="user-img">
							</a>
						</div>
						<div class="info">
							<h1>
								${sessionScope.account.name}


								<div id="edit-signature" class="a-edit-signature">
									<span id="display" style="display: inline;"></span> <span
										id="edi" style="display: inline;"> (<a
										class="sign-text" href="javascript:void(0);">${sessionScope.account.sign}</a>)
									</span>
									<div style="display: none;" class="edit-sign">
										<input class="signature" type="text" size="30" maxlength="30"
											value=""> <input class="submit" type="submit"
											value="修改"> <input class="cancel" type="button"
											value="取消">
									</div>

									<span id="error" class="attn" style="display: none;"></span>
								</div>

							</h1>
							<ul>
								<li><a id="" href="userPage?p=1">我的文章</a></li>
								<li><a id="" href="userPage?p=1&method=getFollowArticle">关注的文章</a></li>
								<li><a id="" href="friendServlet?p=1&method=getFollow">关注的人</a></li>
								<li><a id="" href="friendServlet?p=1&method=getFriendList">好友</a></li>
								<li><a id="" href="userPage?p=1&method=getCollect">收藏</a></li>
								<li><a id="" href="userPage?p=1&method=getForword">转发的文章</a></li>
								<li><a id="" href="userPage?p=1&method=getGood">赞过的文章</a></li>
								<br>
								<li><a id="" href="friendServlet?p=1&method=getApply">好友申请</a></li>
								<li><a id="" href="friendServlet?p=1&method=getBlack">黑名单</a></li>
								<li><a id="" href="friendServlet?p=1&method=getChatFriend">密友</a></li>
								<c:if test="${sessionScope.account.role == 'admin'}">
									<li><a id="" href="friendServlet?p=1&method=getReport">举报名单</a></li>
								</c:if>
							</ul>
						</div>
					</div>

				</div>

				<div class="article">

					<div id="statuses">

						<div class="stream-item">

							<%--文章--%>
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
													<a href="articleInfoServlet?id=${article.id}"
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
													<c:if test="${requestScope.delete != null}">
														<span class="report""><a
															href="userPage?method=${requestScope.delete}&id=${article.id}">删除</a></span>
													</c:if>
													<c:if
														test="${article.author_email == sessionScope.account.email}">
														<span class="report""><a
															href="userPage?method=modifyArticle&id=${article.id}">修改</a></span>
													</c:if>
												</h6>
											</div>

										</div>
									</div>
								</div>
							</c:forEach>

							<%--好友组别--%>
							<c:if test="${requestScope.show == 'friend'}">
								<c:forEach var="group" items="${requestScope.groups}">
									<a style="margin-right: 10px"
										href="friendServlet?p=1&method=getFriendByGroup&group=${group}">${group}</a>
								</c:forEach>
							</c:if>

							<%--跟好友有关的界面--%>
							<c:forEach var="friend" items="${requestScope.friendList}">
								<div class="new-item-wrapper" style="height: 135px">
									<div class="new-item">
										<div class="new-item-up">
											<div class="user-pic">
												<img src="${friend.avatar}"></img>
											</div>
											<div class="user-name">
												<a href="javascript:void(0)" target="_blank">${friend.name}</a>
											</div>

										</div>

										<div class="new-item-down">
											<div class="new-item-down-content">
												<h4 class="new-item-down-title">
													<a href="javascript:void(0)" title="${friend.sign}"
														target="_blank" class="title-link">${friend.sign}</a> <br>
													<c:if
														test="${requestScope.show == 'black' || requestScope.show == 'friend'}">
														<span>${friend.msg}</span>
													</c:if>
												</h4>

												<%--关注的人的选项--%>
												<c:if test="${requestScope.show == 'follow'}">
													<span style="float: right"><a
														href="friendServlet?method=cancelFollow&guest_email=${friend.guest_email}">取消关注</a><br>
														<a href="douYou?p=1&guest_email=${friend.guest_email}">发豆邮</a></span>
												</c:if>

												<%--好友的选项--%>
												<c:if test="${requestScope.show == 'friend'}">
													<span style="float: right"><a
														href="friendServlet?method=deleteFriend&guest_email=${friend.guest_email}">删除好友</a>
														<br> <a
														href="douYou?p=1&guest_email=${friend.guest_email}">发豆邮</a>
														<a
														href="friendServlet?method=report&guest_email=${friend.guest_email}&name=${friend.name}">举报</a>
														<br> <a
														href="friendServlet?method=setBlack&guest_email=${friend.guest_email}">拉黑</a>
													</span>
												</c:if>

												<%--黑名单的选项--%>
												<c:if test="${requestScope.show == 'black'}">
													<span style="float: right"> <a
														href="friendServlet?method=cancelBlack&guest_email=${friend.guest_email}">移除</a>
													</span>
												</c:if>

												<%--密友的选项--%>
												<c:if test="${requestScope.show == 'chat'}">
													<span style="float: right"> <a
														href="chatServlet?method=goChat&email=${friend.guest_email}&avatar=${friend.avatar}&name=${friend.name}">实时聊天</a>
													</span>
												</c:if>

												<%--搜人的选项--%>
												<c:if test="${requestScope.show == 'search'}">
													<span style="float: right"> <a
														href="javascript: void(0)" onclick="fellow(this)"
														class="${friend.guest_email}">关注</a>
													</span>
													<br>
													<span style="float: right"> <a
														href="douYou?p=1&guest_email=${friend.guest_email}">发豆邮</a>
													</span>
													<br>
													<span style="float: right" class="${friend.guest_email}">
														<a href="javascript:void(0)" onclick="apply(this)">申请好友</a>
													</span>
												</c:if>

												<%--举报名单的选项--%>
												<c:if test="${requestScope.show == 'report'}">
													<h6>邮箱：${friend.guest_email}</h6>
													<form action="friendServlet" method="get">
														<input type="hidden" name="method" value="setReport" /> <input
															type="hidden" name="guest_email"
															value="${friend.guest_email}" /> 请选择封到什么时候：<input
															type="datetime-local" value="" name="end_time" /> <input
															type="submit" value="封号" />
													</form>
													<span style="float: right"><a
														href="friendServlet?method=deleteReport&guest_email=${friend.guest_email}">删除</a><span>
															<h6>${friend.time}</h6>
												</c:if>

												<%--好友申请的选项--%>
												<c:if test="${requestScope.show == 'apply'}">
													<%--分组输入框--%>
													<form method="get" action="friendServlet">
														<div style="display: none;" class="edit-group">
															<input name="method" value="allow" type="hidden">
															<input name="applyGroup" value="${friend.msg}"
																type="hidden"> <input name="guest_email"
																value="${friend.host_email}" type="hidden"> <input
																type="text" size="30" maxlength="10" name="msg"
																placeholder="请输入分类，默认为好友" list="groups"> <input
																class="submit1" type="submit" value="确定"> <input
																class="cancel-group" type="button" value="取消"
																onclick="closeAllow(this)">
														</div>
													</form>
													<span style="float: right"><a
														href="javascript: void(0)" onclick="openAllow(this)"
														class="allow_apply_a">同意申请</a> <span> <a
															href="friendServlet?method=denyApply&guest_email=${friend.host_email}">拒绝申请</a></span>
														<br> <br>
														<h6>${friend.time}</h6> </span>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>

							<%--好友分组信息--%>
							<datalist id="groups">
								<c:forEach var="group" items="${requestScope.groups}">
									<option value="${group}">
								</c:forEach>
							</datalist>

						</div>
					</div>

					<%--页码--%>
					<div class="paginator">
						<%--根据当前页数来初始化页码--%>
						<c:choose>
							<c:when test="${param.p == 1 || param.p == null}">
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