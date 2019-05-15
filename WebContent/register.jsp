<%@page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册豆瓣帐号</title>
<link type="text/css" rel="stylesheet" href="css/register.css">
<link rel="icon" type="image/x-icon" href="css/images/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon" href="css/images/favicon.ico" />
<script src="js/register.js"></script>
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

			<div class="account-main">

				<h1>注册豆瓣帐号</h1>
				<div class="tips">输入邮箱，创建密码，设置昵称</div>
				<div class="account-form">
					<form id="form" method="post" action="register"
						onsubmit="return validateForm();" accept-charset="UTF-8">
						<div class="account-form-raw">
							<label class="account-form-label">输入邮箱：</label>
							<div class="account-form-field">
								<input id="email" name="email" type="text"
									class="account-form-input" placeholder="邮箱" tabindex="2" maxlength="30">
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
									class="account-form-input" maxlength="7" placeholder="昵称"
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

		</div>

	</div>

</body>
</html>