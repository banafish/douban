<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人主页</title>
<link type="text/css" rel="stylesheet" href="css/userPage.css">
<link type="text/css" rel="stylesheet" href="css/dbNav.css">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
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

		//上传头像
		$(".user-img").click(function() {
			$("#uploadfile").click();
		});
		$("#uploadfile").change(function() {

			var files = $(this)[0].files[0]; //获取文件信息
			if (files && files.size <= 1120000) {//图片小于1M
				var reader = new FileReader(); //调用FileReader
				reader.onload = function(evt) { //读取操作完成时触发。
					var imgFile = evt.target.result;
					var newsIndex = imgFile.indexOf(",");
					var img = imgFile.substr(parseInt(newsIndex + 1));

					$.post("accountInfo", {
						picture : img
					}, function(data) {
						if (data == "上传成功") {
							$(".user-img").attr("src", imgFile);
						}
						alert(data);
					});
				}
				reader.readAsDataURL(files); //将文件读取为 DataURL(base64)
			} else {
				alert("上传失败，图片应小于1M");
			}
		});

		//签名
		$(".sign-text").click(function() {
			$("#edi").attr("style", "display: none");
			$(".edit-sign").attr("style", "display: inline");
		});
		$(".cancel").click(function() {
			$("#edi").attr("style", "display: inline");
			$(".edit-sign").attr("style", "display: none");
		});
		$(".submit").click(function() {
			var signature = $(".signature").val();
			if (signature.length == 0) {
				alert("签名为空");
			} else {
				$.post("accountInfo", {
					sign : signature
				}, function(data) {
					if (data == "签名成功") {
						$(".sign-text").text(signature);
					}
					alert(data);
				});
				$("#edi").attr("style", "display: inline");
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
</script>
</head>
<body>

	<div id="db-global-nav" class="global-nav">
		<div class="bd">

			<div class="top-nav-info">
				<ul>
					<li><a id="top-nav-doumail-link" href="#">豆邮</a></li>
					<li class="nav-user-account"><a class="bn-more" rel="off">
							<span>${sessionScope.account.name}的帐号</span><span class="arrow"></span>
					</a>
						<div class="more-items">
							<table cellpadding="0" cellspacing="0">
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
					<form action="searchArticle" method="get"
						onsubmit="return validateForm();">
						<fieldset>
							<legend>搜索：</legend>
							<label for="inp-query" style="display: none;">搜索你感兴趣的内容和人...</label>
							<div class="inp">
								<input name="p" value="1" type="hidden"> <input
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
								<li><a id="" href="#">我的文章</a></li>
								<li><a id="usr-profile-nav-statuses" href="#">关注的文章</a></li>
								<li><a id="usr-profile-nav-photos" href="#">关注的人</a></li>
								<li><a id="usr-profile-nav-notes" href="#">好友</a></li>
								<li><a id="usr-profile-nav-doulists" href="#">收藏</a></li>
								<li><a id="" href="#">修改个人信息</a></li>
							</ul>
						</div>
					</div>

				</div>

				<div class="article">

					<div id="statuses">

						<div class="stream-item">

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
													<a href="#" title="${article.title}" target="_blank"
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