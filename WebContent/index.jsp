<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>豆瓣</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript">
	function validateForm() {
		var form = document.getElementById("form");
		var mail = /^[A-Za-z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		if (!mail.test(form.email.value)) {
			document.getElementById("error").innerHTML = "邮箱地址无效";
			return false;
		} else if (form.password.value.length < 6) {
			document.getElementById("error").innerHTML = "密码至少6个字符";
			return false;
		} else if (form.num.value.length > 4 || form.num.value.length < 1) {
			document.getElementById("error").innerHTML = "验证码必须为四位";
			return false;
		} else {
			return true;
		}
	}
</script>
</head>

<body>
	<div class="header_up">
		<h1>
			<a href="http://www.douban.com">豆瓣</a>
		</h1>
		<form action="https://www.douban.com/search" method="get">
			<span class="input"> <input type="text" name="search"
				placeholder="书籍、电影、音乐、小站、成员">
			</span> <span class="search_button"> <input type="submit"
				name="submit">
			</span>
		</form>
		<ul>
			<li><a href="https://book.douban.com" target="_blank"
				class="link_book">读书</a></li>
			<li><a href="https://movie.douban.com" target="_blank"
				class="link_movie">电影</a></li>
			<li><a href="https://music.douban.com" target="_blank"
				class="link_music">音乐</a></li>
			<li><a href="https://www.douban.com/group" target="_blank"
				class="link_group">小组</a></li>
			<li><a href="https://www.douban.com/location" target="_blank"
				class="link_location">同城</a></li>
			<li><a href="https://douban.fm" target="_blank" class="link_fm">FM</a></li>
			<li><a href="#" class="link_time">时间</a></li>
			<li><a href="#" class="link_market">市集</a></li>
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
					src="images/icon_qrcode_green.png" alt="app_qr">
				</a>
				<div class="app_qr_expand">
					<img src="./images/doubanapp_qrcode.png" alt=""
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
						<input type="text" name="email" placeholder="邮箱 ">
					</div>
					<div class="item item_password">
						<input type="password" name="password" placeholder="密码">
						<div class="item_help">
							<a href="#">帮助</a>
						</div>
					</div>
					<div class="item item_num">
						<input type="text" name="num" placeholder="验证码 "> <img
							src="verifyCodeServlet" id="verify_image">
						<div class="item_reflesh">
							<a href="index.jsp">刷新</a>
						</div>
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