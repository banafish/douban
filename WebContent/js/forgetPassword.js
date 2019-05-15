/**
 * 
 */
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