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
	// 关闭好友申请输入框
	$(".cancel").click(function() {
		$(".edit-sign").attr("style", "display: none");
	});
	// 提交好友申请
	$(".submit").click(function() {
		var group = $(".signature").val();
		if (group.length == 0) {
			alert("分类不能为空");
		} else {
			$.get("friendServlet", {
				method : "applyFriend",
				msg : group,
				guest_email : $(".signature").attr("id")
			}, function(data) {
				alert(data);
			});
			$(".edit-sign").attr("style", "display: none");
		}
	});

	// 关注
	$(".follow a").click(function() {
		$.get("articleInfoServlet", {
			method : "follow",
			id : $(".follow a").attr("id"),
			author_email : $(".follow a").attr("class")
		}, function(data) {
			alert(data);
		});
	});

	// 评论文章
	$(".submit-comment").click(function() {
		var commentInput = $(".comment-input").val();
		var articleId = $(".article-id").attr("id");
		if (commentInput.length == 0) {
			alert("评论不能为空");
		} else {
			$.post("articleInfoServlet", {
				method : "comment",
				id : articleId,
				content : commentInput
			}, function(data) {
				alert(data);
			});
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
// 好友申请输入框
function apply() {
	$(".edit-sign").attr("style", "display: inline");
}
// 回复框
function reply(dom) {
	$(dom)
			.parent()
			.append(
					"<span><input type='text' size='30' maxlength='50' class='reply-input'>"
							+ "<input class='submit-reply' type='button' value='回复' onclick='replySubmit(this)'> "
							+ "<input class='cancel-reply' type='button' value='取消' onclick='remove(this)'></span>");
}
// 去除回复框
function remove(dom) {
	$(dom).parent().remove();
}
// 提交回复
function replySubmit(dom) {
	var but = $(dom);
	var ipt = but.parent().prev();
	var name = $(".user-name").attr("id");
	if (but.prev().val().length == 0) {
		alert("回复不能为空");
	} else {
		$
				.post(
						"articleInfoServlet",
						{
							method : "reply",
							id : ipt.attr("id"),
							reply_email : ipt.attr("class"),
							reply_name : ipt.attr("name"),
							content : but.prev().val()
						},
						function(data) {
							alert(data);
							if (data == "回复成功") {
								ipt
										.parent()
										.parent()
										.append(
												"<div class='reply-wrap'><h4> " + name + "："
														+ but.prev().val()
														+ "</h4>	"
														+ "<h6>刚刚 <a href='javascript:void(0)'>赞</a> <a href='javascript:void(0)'>回复</a></h6></div><br>");
							}
						});
	}
}
// 点赞回复
function goodReply(dom) {
	var a = $(dom);
	$.get("articleInfoServlet", {
		method : "goodReply",
		id : a.attr("id"),
		count : a.attr("class")
	}, function(data) {
		a.attr("class", data);
		a.children().text("(" + data + ")");
	});
}

//查看回复
function replyDisplay(dom) {
	$(dom).parent().next().next().attr("style", "display: inline");
}

//收起回复
function hideDisplay(dom) {
	$(dom).parent().attr("style", "display: none");
}

