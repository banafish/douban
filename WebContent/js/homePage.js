/**
 * 
 */
$(document).ready(function() {
	// 账户菜单
	var moreItems = document.getElementsByClassName("more-items");
	moreItems.flag = 0;
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
// 搜索
function validateForm() {
	if ($("#intp-query").val().length == 0) {
		alert("不能为空");
		return false;
	} else {
		return true;
	}
}
//验证邮箱
function validateEmail() {
	var mail = /^[A-Za-z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	if (!mail.test($(".ipt-email").val())) {
		alert("邮箱地址无效");
		return false;
	}  else {
		return true;
	}
}