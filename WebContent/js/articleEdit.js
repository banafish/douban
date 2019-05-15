/**
 * 
 */
$(function() {
	// 格式工具
	$('#editControls a').click(
			function(e) {
				switch ($(this).data('role')) {
				case 'h1':
				case 'h2':
				case 'p':
					document.execCommand('formatBlock', false, '<'
							+ $(this).data('role') + '>');
					break;
				default:
					document.execCommand($(this).data('role'), false, null);
					break;
				}
			});

	// 上传图片
	var imgs = new Array();
	$("#image").click(function() {
		$("#uploadfile").click();
	});
	$("#uploadfile").change(function() {

		var files = $(this)[0].files[0]; // 获取文件信息
		if (files && files.size <= 1120000 && imgs.length < 5) {// 图片小于1M
			var reader = new FileReader(); // 调用FileReader
			reader.onload = function(evt) { // 读取操作完成时触发。
				var imgFile = evt.target.result;
				var newsIndex = imgFile.indexOf(",");
				var img = imgFile.substr(parseInt(newsIndex + 1));
				imgs.push(img); // 把多个图片的base64存进数组
				$("#image").before("<img src=" + evt.target.result + ">");

			}
			reader.readAsDataURL(files); // 将文件读取为 DataURL(base64)
		} else {
			alert("上传失败，图片应小于1M,不得超过5张");
		}
	});

	// 发送文章
	$(".btn-summit").click(
			function() {
				// 获取文章类型
				var type = "";
				for (var i = 0; i < $(":checkbox").length; i++) {
					if ($(":checkbox")[i].checked) {
						type = type + $(":checkbox")[i].value + "、";
					}
				}

				if ($(".content-title-input").val().length == 0
						|| $("#editor").html().length == 0) {
					alert("标题和内容不能为空");
				} else {
					$.post("addArticle", {
						id : $(".content-title").attr("id"),
						title : $(".content-title-input").val(),
						contentType : type,
						content : $("#editor").html(),
						pictures : imgs.join()
					}, function(data) {
						alert(data);
					});
				}

			});

});