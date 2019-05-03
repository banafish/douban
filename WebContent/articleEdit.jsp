<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>写文章</title>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<link href="css/bootstrap-combined.min.css" rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/articleEdit.css" rel="stylesheet">
<script>
	$(function() {
		//格式工具
		$('#editControls a')
				.click(
						function(e) {
							switch ($(this).data('role')) {
							case 'h1':
							case 'h2':
							case 'p':
								document.execCommand('formatBlock', false, '<'
										+ $(this).data('role') + '>');
								break;
							default:
								document.execCommand($(this).data('role'),
										false, null);
								break;
							}
						});

		//上传图片
		var imgs = new Array();
		$("#image").click(function() {
			$("#uploadfile").click();
		});
		$("#uploadfile").change(function() {

			var files = $(this)[0].files[0]; //获取文件信息
			if (files && files.size <= 1120000 && imgs.length < 5) {//图片小于1M
				var reader = new FileReader(); //调用FileReader
				reader.onload = function(evt) { //读取操作完成时触发。
					var imgFile = evt.target.result;
					var newsIndex = imgFile.indexOf(",");
					var img = imgFile.substr(parseInt(newsIndex + 1));
					imgs.push(img); //把多个图片的base64存进数组
					$("#image").before("<img src=" + evt.target.result + ">");

				}
				reader.readAsDataURL(files); //将文件读取为 DataURL(base64)
			} else {
				alert("上传失败，图片应小于1M,不得超过5张");
			}
		});

		//发送文章
		$(".btn-summit").click(function() {
			//获取文章类型
			var type = "";
			for (var i = 0; i < $(":checkbox").length; i++) {
				if ($(":checkbox")[i].checked) {
					type = type + $(":checkbox")[i].value + "、";
				}
			}
			
			if ($(".content-title-input").val().length == 0 || $("#editor").html().length == 0) {
				alert("标题和内容不能为空");
			} else {
				$.post("addArticle", {
					title: $(".content-title-input").val(),
					contentType : type,
					content : $("#editor").html(),
					pictures : imgs.join()
				},
				function (data) {
				     alert(data);
				});			
			}			
			
		});

	});
</script>
</head>
<body>
	<div class="content">
		<div class="content-title">
			<textarea class="content-title-input" placeholder="添加标题"></textarea>
		</div>
		<div class="container-fluid">
			<div id='pad-wrapper'>
				<div id="editparent">
					<div id='editControls' class='span9' style='padding: 5px;'>
						<div class='btn-group'>
							<a class='btn' data-role='undo' href='#'><i class='icon-undo'></i></a>
							<a class='btn' data-role='redo' href='#'><i
								class='icon-repeat'></i></a>
						</div>
						<div class='btn-group'>
							<a class='btn' data-role='bold' href='#'><b>Bold</b></a> <a
								class='btn' data-role='italic' href='#'><em>Italic</em></a> <a
								class='btn' data-role='underline' href='#'><u><b>U</b></u></a> <a
								class='btn' data-role='strikeThrough' href='#'><strike>abc</strike></a>
						</div>
						<div class='btn-group'>
							<a class='btn' data-role='justifyLeft' href='#'><i
								class='icon-align-left'></i></a> <a class='btn'
								data-role='justifyCenter' href='#'><i
								class='icon-align-center'></i></a> <a class='btn'
								data-role='justifyRight' href='#'><i
								class='icon-align-right'></i></a> <a class='btn'
								data-role='justifyFull' href='#'><i
								class='icon-align-justify'></i></a>
						</div>
						<div class='btn-group'>
							<a class='btn' data-role='indent' href='#'><i
								class='icon-indent-right'></i></a> <a class='btn'
								data-role='outdent' href='#'><i class='icon-indent-left'></i></a>
						</div>
						<div class='btn-group'>
							<a class='btn' data-role='insertUnorderedList' href='#'><i
								class='icon-list-ul'></i></a> <a class='btn'
								data-role='insertOrderedList' href='#'><i
								class='icon-list-ol'></i></a>
						</div>
						<div class='btn-group'>
							<a class='btn' data-role='h1' href='#'>h<sup>1</sup></a> <a
								class='btn' data-role='h2' href='#'>h<sup>2</sup></a> <a
								class='btn' data-role='p' href='#'>p</a>
						</div>
						<div class='btn-group'>
							<a class='btn' data-role='subscript' href='#'><i
								class='icon-subscript'></i></a> <a class='btn'
								data-role='superscript' href='#'><i class='icon-superscript'></i></a>
						</div>
					</div>

					<div id='editor' class='span9' style='' contenteditable></div>

				</div>
			</div>
		</div>

		<div class="fileupload-box">
			<p>上传图片:</p>
			<div class="img-box">
				<input type="file" style="display: none" id="uploadfile"
					accept="image/*" /> <img src="css/images/uploadpic.png" id="image">
			</div>
		</div>

		<div class="content-nav">
			<section title="言论">
				<div class="roundedTwo">
					<input type="checkbox" value="言论" id="roundedTwo1" name="check" />
					<label for="roundedTwo1"></label>
				</div>
			</section>
			<section title="情感">
				<div class="roundedTwo">
					<input type="checkbox" value="情感" id="roundedTwo2" name="check" />
					<label for="roundedTwo2"></label>
				</div>
			</section>
			<section title="美食">
				<div class="roundedTwo">
					<input type="checkbox" value="美食" id="roundedTwo3" name="check" />
					<label for="roundedTwo3"></label>
				</div>
			</section>
			<section title="思想">
				<div class="roundedTwo">
					<input type="checkbox" value="思想" id="roundedTwo4" name="check" />
					<label for="roundedTwo4"></label>
				</div>
			</section>
			<section title="读书">
				<div class="roundedTwo">
					<input type="checkbox" value="读书" id="roundedTwo5" name="check" />
					<label for="roundedTwo5"></label>
				</div>
			</section>
			<section title="音乐">
				<div class="roundedTwo">
					<input type="checkbox" value="音乐" id="roundedTwo6" name="check" />
					<label for="roundedTwo6"></label>
				</div>
			</section>
			<section title="社会">
				<div class="roundedTwo">
					<input type="checkbox" value="社会" id="roundedTwo7" name="check" />
					<label for="roundedTwo7"></label>
				</div>
			</section>
			<button class="btn-summit" type="button">发布</button>
		</div>

	</div>
</body>
</html>