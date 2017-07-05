<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/addAuction.css" rel="stylesheet">
<script src="js/WebCalendar.js" type="text/javascript"></script>
<script type="text/javascript">
	var loadImageFile = (function() {
		if (window.FileReader) {
			var oPreviewImg = null, oFReader = new window.FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;

			oFReader.onload = function(oFREvent) {
				if (!oPreviewImg) {
					var newPreview = document.getElementById("imagePreview");
					oPreviewImg = new Image();
					oPreviewImg.style.width = (newPreview.offsetWidth)
							.toString()
							+ "px";
					oPreviewImg.style.height = (newPreview.offsetHeight)
							.toString()
							+ "px";
					if (newPreview.childNodes.length != 0) {
						newPreview
								.removeChild(document.getElementById("imgid"));
					}
					newPreview.appendChild(oPreviewImg);
				}
				oPreviewImg.src = oFREvent.target.result;
			};

			return function() {
				var aFiles = document.getElementById("imageInput").files;
				if (aFiles.length === 0) {
					return;
				}
				if (!rFilter.test(aFiles[0].type)) {
					alert("You must select a valid image file!");
					return;
				}
				oFReader.readAsDataURL(aFiles[0]);
			}

		}
		if (navigator.appName === "Microsoft Internet Explorer") {
			return function() {
				alert(document.getElementById("imageInput").value);
				document.getElementById("imagePreview").filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = document
						.getElementById("imageInput").value;

			}
		}
	})();
</script>
</head>
<body>
	<div class="all">
		<div class="top">
			<div class="logo">在线拍卖系统</div>
			<s:if test="#session.user != null">
				<input id="logout" class="btn btn-default" type="button"
					onclick="location='doLogout.action'" value="退出">
			</s:if>
			<s:else>
				<input id="logout" class="btn btn-default" type="button"
					onclick="location='login.jsp'" value="登录">
			</s:else>
		</div>
		<hr>


		<div>
			<label class="label">拍卖品信息</label></br>
			<form action="publishAction.action" enctype="multipart/form-data"
				method="post">
				<label class="name">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label><input
					id="form"  placeholder="名称" name="auction.auctionname"
					class="form-control" type="text"> <label class="name1">起拍价</label><input
					id="form1"  placeholder="起拍价" name="auction.auctionstartprice"
					class="form-control" type="text"> <label class="name">底&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价</label><input
					id="form"  placeholder="底价" name="auction.auctionupset"
					class="form-control" type="text"> <label class="starttime">开始时间</label><input
					id="form1"  placeholder="开始时间" name="auction.auctionstarttime" onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"
					class="form-control" type="text"> <label class="endtime">结束时间</label><input
					id="form"  placeholder="结束时间" name="auction.auctionendtime" onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"
					class="form-control" type="text"> <label class="name3">拍卖品图片</label>
					<div id="imagePreview" >
                	<img id="imgid" src="images/ad20.jpg" width="100" height="100" style="border-radius: 5px;"/>
                </div>
				<div class="loadImage">
					<input id="imageInput" onchange="loadImageFile();" name="pic"
						type="file" class="file" />
				</div>
				<label class="ptext">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述</label>
				<textarea placeholder="描述" id="ptext" class="form-control" name="auction.auctiondesc"></textarea>
				<input type="submit" id="save" class="btn btn-primary" value="保存">
				<input type="button" id="back" class="btn btn-primary" value="取消" onclick="location='auctionList.action'">
			</form>
		</div>
</body>
</html>
