<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">

<title>注册豆瓣帐号</title>

<link type="text/css" rel="stylesheet" href="css/register.css">

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
		} else if (form.nick.value.length > 7 || form.nick.value.length < 1) {
			document.getElementById("error").innerHTML = "昵称过长或为空";
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
					<a href=""> <img src="images/logo_db.png" width="142" alt="豆瓣">
					</a>
				</div>
			</div>
		</div>

		<div class="account-wrap">

			<div class="account-main">

				<h1>注册豆瓣帐号</h1>
				<div class="tips">输入邮箱，创建密码，设置昵称</div>
				<div class="account-form">
					<form id="form" method="post" action="register"
						onsubmit="return validateForm();">
						<div class="account-form-raw">
							<label class="account-form-label">输入邮箱：</label>
							<div class="account-form-field">
								<input id="email" name="email" type="text"
									class="account-form-input" placeholder="邮箱" tabindex="2">
								<span class="account-form-field-tip">请输入有效邮箱地址</span>
							</div>

						</div>
						<div class="account-form-raw">
							<label class="account-form-label">设置密码：</label>
							<div class="account-form-field">
								<input id="password" type="password" name="password"
									maxlength="20" class="account-form-input password"
									placeholder="密码" tabindex="3" aria-autocomplete="list">
								<span class="icon openpwd hide"></span> <span
									class="account-form-field-tip">密码至少6个字符</span>
							</div>

						</div>
						<div class="account-form-raw">
							<label class="account-form-label">设置昵称：</label>
							<div class="account-form-field">
								<input id="nick" name="nick" type="text"
									class="account-form-input" maxlength="14" placeholder="昵称"
									tabindex="1"> <span class="account-form-field-tip">昵称最多7个中文字符</span>
							</div>

						</div>

						<div class="account-form-field-submit mrg0">
							<div class="account-form-error">
								<span id="error">${ requestScope.msg.result }</span>
							</div>
							<input type="submit" class="confirm" value="确定">
						</div>
					</form>
				</div>

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