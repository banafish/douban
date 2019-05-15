/**
 * 
 */
$(document).ready(function() {
		//账户菜单
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
		
		//发信息
		$(".but-submit").click(function() {
			if ($(".content-input").val().length == 0) {
				alert("不能为空");
			} else {
				$.post("chatServlet", {
					msg : $(".content-input").val(),
					to_email : $(".to-email").attr("id")
				}, function(data) {
					if (data == "发送成功") {
						$(".article").append("<p class='chat-me'><span>" + $(".content-input").val() 
								+ "：<img src='" + $(".user-avatar").attr("id") + "'><span></p><br><br>");
						$(".content-input").val("");
					}
				});
			}
		});
		
	})

	//搜索
	function validateForm() {
		if ($("#intp-query").val().length == 0) {
			alert("不能为空");
			return false;
		} else {
			return true;
		}
	}
	
	//接收信息
	setInterval("read()",500);
	function read() {
		$.get("chatServlet", {
			from_email : $(".to-email").attr("id")
		}, function(data) {
			if (data != "") {
				$(".article").append("<p class='chat-you'><span><img src='" + $(".avatar").attr("id") + "'>：" + data + "<span></p>");
				$(".content-input").val("");
			}
		});
	} 