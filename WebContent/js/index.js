/**
 * 
 */
function refreshcode() {
	document.getElementById("verify_image").src = "verifyCodeServlet?a="
			+ Math.random() + 100;
	return true;
}
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