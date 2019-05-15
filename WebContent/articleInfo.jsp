<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>豆瓣</title>
<link type="text/css" rel="stylesheet" href="css/dbNav.css">
<link type="text/css" rel="stylesheet" href="css/articleInfo.css">
<link rel="icon" type="image/x-icon" href="css/images/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon" href="css/images/favicon.ico" />
<script src="js/jquery.min.js">	</script>
<script src="js/articleInfo.js"></script>
</head>
<body>
	<div id="db-global-nav" class="global-nav">
		<div class="bd">

			<div class="top-nav-info">
				<ul>
					<li><a id="top-nav-doumail-link" href="douYou?p=1">豆邮</a></li>
					<li class="nav-user-account"><a class="bn-more" rel="off">
							<input type="hidden" class="user-name" id="${account.name}">
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
															class="signature" type="text" size="30" maxlength="10"
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

													<span class="follow"><a href="javascript: void(0)"
														class="${requestScope.article.author_email}", id="${requestScope.article.id}">关注</a></span>

													<span class="report"><a
														href="douYou?p=1&guest_email=${requestScope.article.author_email}">发豆邮</a></span>

													<c:if test="${sessionScope.account.role == 'admin'}">
														<span class="report"><a
															href="articleInfoServlet?method=delete&id=${requestScope.article.id}&email=${requestScope.article.author_email}">删除</a></span>
													</c:if>

												</c:if>
											</span> <br>
											<h3 class="new-item-down-title">
												<a href="#" id="${requestScope.article.id}" class="article-id"
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
															test="${requestScope.article.articleInfo.forword == 1}">
															<span class="like" style="color: red">转发<em>(${requestScope.articleInfoDetail.forword})</em></span>
														</c:when>
														<c:otherwise>
															<span class="like"><a
																href="articleInfoServlet?method=forword&id=${requestScope.article.id}">转发<em>(${requestScope.articleInfoDetail.forword})</em></a></span>
														</c:otherwise>
													</c:choose> <span class="like" style="color: red">评论<em>(${requestScope.commentCount})</em></span>
												</span>

											</h6>
										</div>
										<textarea class="comment-input" placeholder="输入内容" name="msg" maxlength="225"></textarea>
										<input type="button" value="评论" class="submit-comment" /><br>

										<%--评论--%>
										<c:forEach var="comment" items="${requestScope.comments}">
											<br>
											<div class="comment-read">
												<h3>
													<img src="${comment.avatar}"
														style="width: 20px; height: 20px"> ${comment.name}
												</h3>
												<h6>
													${comment.time}<a
														href="articleInfoServlet?method=goodComment&comment_id=${comment.id}&id=${requestScope.article.id}&counts=${comment.good_count}">
														赞<em>(${comment.good_count})</em>
													</a> <a href="javascript: void(0)" onclick="reply(this)">
														回复<em>(${fn:length(comment.replys)})</em>
													<c:if test="${fn:length(comment.replys) > 0}">
														<a href="javascript: void(0)" onclick="replyDisplay(this)">查看</a>
													</c:if>
													</a> <input type="hidden" class="${comment.user_email}"
														id="${comment.id}" name="${comment.name}">
												</h6>
												<span>${comment.content}</span>

												<%--回复--%>
												<div class="reply-wrap-all" style="display: none">
													<c:forEach var="reply" items="${comment.replys}">
														<div class="reply-wrap">
															<c:choose>
																<c:when test="${reply.reply_email == comment.user_email}">
																	<h4>${reply.replyer_name}：${reply.content}</h4>
																</c:when>
																<c:otherwise>
																	<h4>${reply.replyer_name}<a
																			href="javascript:void(0)">//</a>@${reply.reply_name}：${reply.content}
																	</h4>
																</c:otherwise>
															</c:choose>
															<h6>${reply.time}
																<a href="javascript:void(0)" onclick="goodReply(this)"
																	id="${reply.id}" class="${reply.good_count}">赞<em>(${reply.good_count})</em></a>
																<a href="javascript: void(0)" onclick="reply(this)">回复</a>
																<input type="hidden" class="${reply.replyer_email}"
																	id="${comment.id}" name="${reply.replyer_name}">
															</h6>
														</div>
														<br>														
													</c:forEach>
													<a href="javascript: void(0)" onclick="hideDisplay(this)">收起</a>
												</div>
											</div>
										</c:forEach>

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
					</div>

				</div>

			</div>
		</div>
	</div>

</body>
</html>