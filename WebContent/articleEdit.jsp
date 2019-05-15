<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>写文章</title>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap-combined.min.css" rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/articleEdit.css" rel="stylesheet">
<link rel="icon" type="image/x-icon" href="css/images/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon" href="css/images/favicon.ico" />
<script src="js/articleEdit.js"></script>
</head>
<body>
	<div class="content">
		<div class="content-title" id="${requestScope.article.id}">
			<textarea class="content-title-input" placeholder="添加标题">${requestScope.article.title}</textarea>
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

					<div id='editor' class='span9' style='' contenteditable>${requestScope.article.content}</div>

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