<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>找回密码</title>
<link type="text/css" rel="stylesheet" href="css/forgetPassword.css">
<link rel="icon" type="image/x-icon" href="css/images/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon"
	href="css/images/favicon.ico" />
<script src="js/jquery.min.js"></script>
<script src="js/forgetPassword.js"></script>
</head>
<body>
	<div id="account">

		<div id="db-nav-sns" class="nav">
			<div class="nav-wrap">
				<div class="nav-primary">
					<a href=""> <img src="css/images/logo_db.png" width="142"
						alt="豆瓣">
					</a>
				</div>
			</div>
		</div>

		<div class="account-wrap">

			<div class="account-main" id="account-main">
				<c:if test="${requestScope.flag != true}">
					<form id="form_email" method="post" action="forgetPassword"
						onsubmit="return validateEmail();">
						<div id="tmpl_email">
							<h2 class="account-body-title login-label-email">
								<span class="account-body-text">用邮箱找回密码</span> <span
									class="account-body-tips">请输入你的邮箱地址</span>
							</h2>
							<div class="account-form">

								<div class="account-form-raw">
									<label class="account-form-label">邮 箱：</label>
									<div class="account-form-field">
										<input id="email" name="email" type="text"
											class="account-form-input" placeholder="邮箱地址" tabindex="1"
											maxlength="30"> <span class="icon clear-input hide"></span>
									</div>
								</div>

								<div class="account-form-field-submit ">
									<div class="account-form-error">
										<span id="error_email">${ requestScope.msg }</span>
									</div>
									<input type="submit" class="btn btn-active btn-email"
										value="确定">
								</div>

							</div>

						</div>
					</form>
				</c:if>
				<c:if test="${requestScope.flag == true}">
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
											placeholder="新密码（最少6位数）" tabindex="1" maxlength="20">
									</div>
								</div>
								<div class="account-form-raw">
									<label class="account-form-label">再次确认：</label>
									<div class="account-form-field">
										<span class="icon openpwd"></span> <input type="password"
											name="repassword" class="account-form-input"
											placeholder="再次确认" tabindex="2" maxlength="20">
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
				</c:if>
			</div>

		</div>

	</div>


</body>
</html>