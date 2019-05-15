/**
 * 
 */
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