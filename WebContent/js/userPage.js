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

	// 上传头像
	$(".user-img").click(function() {
		$("#uploadfile").click();
	});
	$("#uploadfile").change(function() {

		var files = $(this)[0].files[0]; // 获取文件信息
		if (files && files.size <= 1120000) {// 图片小于1M
			var reader = new FileReader(); // 调用FileReader
			reader.onload = function(evt) { // 读取操作完成时触发。
				var imgFile = evt.target.result;
				var newsIndex = imgFile.indexOf(",");
				var img = imgFile.substr(parseInt(newsIndex + 1));

				$.post("accountInfo", {
					picture : img
				}, function(data) {
					if (data == "上传成功") {
						$(".user-img").attr("src", imgFile);
					}
					alert(data);
				});
			}
			reader.readAsDataURL(files); // 将文件读取为 DataURL(base64)
		} else {
			alert("上传失败，图片应小于1M");
		}
	});

	// 签名
	$(".sign-text").click(function() {
		$("#edi").attr("style", "display: none");
		$(".edit-sign").attr("style", "display: inline");
	});
	$(".cancel").click(function() {
		$("#edi").attr("style", "display: inline");
		$(".edit-sign").attr("style", "display: none");
	});
	$(".submit").click(function() {
		var signature = $(".signature").val();
		if (signature.length == 0) {
			alert("签名为空");
		} else {
			$.post("accountInfo", {
				sign : signature
			}, function(data) {
				if (data == "签名成功") {
					$(".sign-text").text(signature);
				}
				alert(data);
			});
			$("#edi").attr("style", "display: inline");
			$(".edit-sign").attr("style", "display: none");
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
// 通过好友申请输入框显示关闭
function openAllow(dom) {
	$(dom).parent().prev().children(":first").attr("style", "display: inline");
}
function closeAllow(dom) {
	$(dom).parent().attr("style", "display: none");
}

// 关注人
function fellow(dom) {
	$.get("friendServlet", {
		guest_email : $(dom).attr("class"),
		method : "fellow"
	}, function(data) {
		alert(data);
	});
}

//申请好友
function apply(dom) {
	$(dom).parent().append("<span><input type='text' size='30' maxlength='10' name='msg' " +
			"placeholder='请输入分类，默认为好友' list='groups'> " + 
			"<input type='button' value='确定' onclick='submitApply(this)'> " +
					"<input type='button' value='取消' onclick='cancelApply(this)'></span>");
}

//关闭申请框
function cancelApply(dom) {
	$(dom).parent().remove();
}

//提交申请
function submitApply(dom) {
	var group = $(dom).prev().val();
	if (group.length == 0) {
		alert("分类不能为空");
	} else {
		$.get("friendServlet", {
			method : "applyFriend",
			msg : group,
			guest_email : $(dom).parent().parent().attr("class")
		}, function(data) {
			alert(data);
		});
	}
}