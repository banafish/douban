<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>豆瓣</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="icon" type="image/x-icon" href="css/images/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon"
	href="css/images/favicon.ico" />
<script src="js/index.js"></script>
</head>
<body>
	<div class="header_up">
		<h1>
			<a href="homePage?p=1">豆瓣</a>
		</h1>
		<form action="#" method="get">
			<span class="input"> <input type="text" name="search"
				placeholder="书籍、电影、音乐、小站、成员">
			</span> <span class="search_button"> <input type="submit"
				name="submit">
			</span>
		</form>
		<ul>
			<li><a href="javascript:void(0)" class="link_book">读书</a></li>
			<li><a href="javascript:void(0)" class="link_movie">电影</a></li>
			<li><a href="javascript:void(0)" class="link_music">音乐</a></li>
			<li><a href="javascript:void(0)" class="link_group">小组</a></li>
			<li><a href="javascript:void(0)" class="link_location">同城</a></li>
			<li><a href="javascript:void(0)" class="link_fm">FM</a></li>
			<li><a href="javascript:void(0)" class="link_time">时间</a></li>
			<li><a href="javascript:void(0)" class="link_market">市集</a></li>
		</ul>
	</div>
	<div class="header_down">
		<div class="bg"></div>
		<div class="register_app">
			<p class="app_title">
				豆瓣 <span>6.0</span>
			</p>
			<p class="app_slogan">
				<span>我们的精神角落</span>
			</p>
			<a href="#" class="link_app">下载豆瓣 App</a>
			<div class="app_qr">
				<a href="#" class="app_qr_a"> <img
					src="css/images/icon_qrcode_green.png" alt="app_qr">
				</a>
				<div class="app_qr_expand">
					<img src="css/images/doubanapp_qrcode.png" alt=""
						class="app_qrExpand">
					<p>iOS / Android 扫码直接下载</p>
				</div>
			</div>
		</div>
		<div class="field">
			<div class="account-form-error">
				<span id="error">${ requestScope.msg.result }</span>
			</div>
			<form id="form" method="post" action="login"
				onsubmit="return validateForm();">
				<fieldset>
					<legend>登录</legend>
					<div class="item item_account">
						<input type="text" name="email" placeholder="邮箱 " maxlength="30">
					</div>
					<div class="item item_password">
						<input type="password" name="password" placeholder="密码"
							maxlength="20">
						<div class="item_help">
							<a href="#">帮助</a>
						</div>
					</div>
					<div class="item item_num">
						<input type="text" name="num" placeholder="验证码 " maxlength="4">
						<img src="verifyCodeServlet" id="verify_image"
							onclick="return refreshcode()" />
					</div>
					<div class="item_login">
						<input type="submit" value="登录豆瓣">
					</div>
					<div class="others">
						<input type="checkbox" name="item_remember">下次自动登录 <a
							href="forgetPassword.jsp" class="forget">忘记密码</a>
						<ul class="item_ul">
							<li class="item_li_first"><a href="#" target="_blank"></a></li>
							<li class="item_li_last"><a href="#" target="_blank"></a></li>
						</ul>
						<a href="register.jsp" class="register">没有账号？去注册</a>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>