<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>找回密码</title>

<link type="text/css" rel="stylesheet" href="css/forgetPassword.css">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var flag = "${requestScope.flag}";
		if (flag == "true") {
			$("#form_email").hide();
		} else {
			$("#form_pw").hide();
		}	
	});
	function validateEmail() {
		var form = document.getElementById("form_email");
		var mail = /^[A-Za-z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		if (!mail.test(form.email.value)) {
			document.getElementById("error_email").innerHTML = "邮箱地址无效";
			return false;
		} else {
			return true;
		}
	}
	function validateFormPw() {
		var form = document.getElementById("form_pw");
		
		if (form.password.value.length < 6) {
			document.getElementById("error_pw").innerHTML = "密码至少6个字符";
			return false;
		} else if (form.password.value != form.repassword.value) {
			document.getElementById("error_pw").innerHTML = "密码不一致";
			return false;
		} else {
			return true;
		}	
	}
	
</script>
</head>
<body>
	<div id="account">

		<div id="db-nav-sns" class="nav">
			<div class="nav-wrap">
				<div class="nav-primary">
					<a href=""> <img src="css/images/logo_db.png" width="142" alt="豆瓣">
					</a>
				</div>
			</div>
		</div>

		<div class="account-wrap">

			<div class="account-main" id="account-main">
				<form id="form_email" method="post" action="forgetPassword"
					onsubmit="return validateEmail();">
					<div id="tmpl_email">
						<h2 class="account-body-title login-label-email">
							<span class="account-body-text">用邮箱找回密码</span> <span
								class="account-body-tips">请输入你的帐号绑定的邮箱地址</span>
						</h2>
						<div class="account-form">

							<div class="account-form-raw">
								<label class="account-form-label">邮 箱：</label>
								<div class="account-form-field">
									<input id="email" name="email" type="text"
										class="account-form-input" placeholder="邮箱地址" tabindex="1">
									<span class="icon clear-input hide"></span>
								</div>
							</div>

							<div class="account-form-field-submit ">
								<div class="account-form-error">
									<span id="error_email">${ requestScope.msg }</span>
								</div>
								<input type="submit" class="btn btn-active btn-email" value="确定">
							</div>

						</div>

					</div>
				</form>

				<form id="form_pw" method="post" action="resetPassword"
					onsubmit="return validateFormPw();">
					<div id="tmpl_reset">
						<h2 class="account-body-title login-label-email">
							<span class="account-body-text">重设密码</span> <span
								class="account-body-tips">为保障你的帐号安全，请重新设置密码；密码不少于6位</span>
						</h2>
						<div class="account-form">
							<div class="account-form-raw">
								<label class="account-form-label">新密码：</label>
								<div class="account-form-field">
									<span class="icon openpwd"></span> <input type="password"
										name="password" class="account-form-input password"
										placeholder="新密码（最少6位数）" tabindex="1">
								</div>
							</div>
							<div class="account-form-raw">
								<label class="account-form-label">再次确认：</label>
								<div class="account-form-field">
									<span class="icon openpwd"></span> <input type="password"
										name="repassword" class="account-form-input"
										placeholder="再次确认" tabindex="2">
								</div>
							</div>

							<div class="account-form-field-submit ">
								<div class="account-form-error">
									<span id="error_pw"></span>
								</div>
								<input type="submit" class="btn btn-active btn-reset"
									value="保存并重新登录">
							</div>
						</div>
					</div>
				</form>

			</div>
			<div class="account-side">

				<ul class="aside-link">
					<li><a href="https://www.douban.com/mobile/">&gt; 下载豆瓣App</a></li>
				</ul>

			</div>
		</div>

	</div>


</body>
</html>