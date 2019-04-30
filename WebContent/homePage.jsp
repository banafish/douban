<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
	window.onload = function() {
		var moreItems = document.getElementsByClassName("more-items");
		moreItems.flag = 0;
		$(document).ready(function() {
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
							<span>${account.name}的帐号</span><span class="arrow"></span>
					</a>
						<div class="more-items">
							<table cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td><a href="#">个人主页</a></td>
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
										<td><a href="#">退出登录</a></td>
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
					<form action="#" method="get">
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
								<a href="articleEdit.jsp"><input type="button" value="发文章"></a>
							</div>
							
							<div class="new-item-wrapper">
								<div class="new-item">
									<div class="new-item-up">		
											<div class="user-pic">
												<img src="images/bananice.jpg"></img>
											</div>
											<div class="user-name">
												<a href="#"	target="_blank">bananice</a>
											</div>								
									
									</div>

									<div class="new-item-down">
										<div class="new-item-down-content">
											<h4 class="new-item-down-title">
											<a href="#" title="电子游戏的画面经历哪几个阶段呢？" target="_blank"
												class="title-link">电子游戏的画面经历哪几个阶段呢？</a>
											</h4>
											<h5 class="content-some">
												最近在补老游戏，对比一下现在的新游，突然觉得日新月异。有没有大佬指条路，让我了解一下电子游戏画面发展史啊</h5>

																				
											<div class="small-pic">														
													<img src="images/71849616_p0.png" class="small-pic-list-1">	
													<img src="images/72072963_p0.png" class="small-pic-list-2">														

											</div>												
											<h6>2019-4-26 9:53</h6>
										</div>

										<div class="new-item-down-reply">
										</div>
										
									</div>
								</div>
							</div>
							<div class="new-item-wrapper">
								<div class="new-item">
									<div class="new-item-up">		
											<div class="user-pic">
												<img src="images/bananice.jpg"></img>
											</div>
											<div class="user-name">
												<a href="#"	target="_blank">bananice</a>
											</div>								
									
									</div>

									<div class="new-item-down">
										<div class="new-item-down-content">
											<h4 class="new-item-down-title">
											<a href="#" title="电子游戏的画面经历哪几个阶段呢？" target="_blank"
												class="title-link">电子游戏的画面经历哪几个阶段呢？</a>
											</h4>
											<h5 class="content-some">
												最近在补老游戏，对比一下现在的新游，突然觉得日新月异。有没有大佬指条路，让我了解一下电子游戏画面发展史啊</h5>

																				
											<div class="small-pic">														
													<img src="images/71849616_p0.png" class="small-pic-list-1">	
													<img src="images/72072963_p0.png" class="small-pic-list-2">														

											</div>												
											<h6>2019-4-26 9:53</h6>
										</div>

										<div class="new-item-down-reply">
										</div>
										
									</div>
								</div>
							</div>
							<div class="new-item-wrapper">
								<div class="new-item">
									<div class="new-item-up">		
											<div class="user-pic">
												<img src="images/bananice.jpg"></img>
											</div>
											<div class="user-name">
												<a href="#"	target="_blank">bananice</a>
											</div>								
									
									</div>

									<div class="new-item-down">
										<div class="new-item-down-content">
											<h4 class="new-item-down-title">
											<a href="#" title="电子游戏的画面经历哪几个阶段呢？" target="_blank"
												class="title-link">电子游戏的画面经历哪几个阶段呢？</a>
											</h4>
											<h5 class="content-some">
												最近在补老游戏，对比一下现在的新游，突然觉得日新月异。有没有大佬指条路，让我了解一下电子游戏画面发展史啊</h5>

																				
											<div class="small-pic">														
													<img src="images/71849616_p0.png" class="small-pic-list-1">	
													<img src="images/72072963_p0.png" class="small-pic-list-2">														

											</div>												
											<h6>2019-4-26 9:53</h6>
										</div>

										<div class="new-item-down-reply">
										</div>
										
									</div>
								</div>
							</div>
							
							
						</div>						
					</div>
					<div class="paginator">
						<span class="prev"> &lt;前页 </span> <span class="thispage"
							data-total-page="9223372036854775807">1</span> <a href="?p=2">2</a>

						<a href="?p=3">3</a> <a href="?p=4">4</a> <a href="?p=5">5</a> <a
							href="?p=6">6</a> <a href="?p=7">7</a> <a href="?p=8">8</a> <a
							href="?p=9">9</a> <span class="break">...</span> <span
							class="next">
							<link rel="next" href="?p=2"> <a href="?p=2">后页&gt;</a>
						</span>

					</div>

				</div>

			</div>
		</div>
	</div>

</body>
</html>