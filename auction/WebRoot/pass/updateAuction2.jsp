<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
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
					if(newPreview.childNodes.length != 0){
						newPreview.removeChild(document.getElementById("imgid"));
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
<div class="wrap">
  <!-- main begin-->
  <div class="sale">
    <h1 class="lf">在线拍卖系统</h1>
    <div class="logout right">
    <s:if test="#session.user != null">
    	<a href="doLogout.action" title="注销">注销</a>
    </s:if>
    <s:else>
    	<a href="login.jsp" title="登录">登录</a>
    </s:else>
    </div>
  </div>
  <form action="updateAction" enctype="multipart/form-data" method="post">
      <div class="login logns produce">
        <h1 class="blues">拍卖品信息</h1>
        <input type="hidden" name="auction.auctionid" value="${auction.auctionid}">
          <dl>
            <dd >
              <label>名称：</label>
              <input type="text" name="auction.auctionname" class="inputh lf" value="<s:property value="auction.auctionname"/>" />
              <div class="xzkbg spbg lf"></div>
            </dd>
            <dd>
              <label>起拍价：</label>
              <input type="text" name="auction.auctionstartprice" class="inputh lf" value="<s:property value="auction.auctionstartprice"/>" />
              <div class="lf red laba">必须为数字</div>
            </dd>
            <dd>
              <label>底价：</label>
              <input type="text" name="auction.auctionupset" class="inputh lf" value="<s:property value="auction.auctionupset"/>" />
              <div class="lf red laba">必须为数字</div>
            </dd>
            <dd>
              <label>开始时间：</label>
              <input type="text" name="auction.auctionstarttime" class="inputh lf" value="<s:date name="auction.auctionstarttime" format="yyyy-MM-dd hh:mm:dd"/>" onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')" />
              <div class="lf red laba">格式：2011-05-05 12:30:00</div>
            </dd>
            <dd>
              <label>结束时间：</label>
              <input type="text" name="auction.auctionendtime" class="inputh lf" value="<s:date name="auction.auctionendtime" format="yyyy-MM-dd hh:mm:dd"/>" onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"/>
              <div class="lf red laba">格式：2011-05-05 12:30:00</div>
            </dd>
            <dd class="dds">
              <label>描述：</label>
              <textarea name="auction.auctiondesc" cols="" rows="" class="textarea"><s:property value="auction.auctiondesc"/></textarea>
            </dd>
            <dd>
              <label>修改图片：</label>
               <div class="lf salebd">
                <div id="imagePreview" >
                	<img id="imgid" src="upload/<s:property value="auction.auctionpictype"/>" width="100" height="100" />
                </div>
               </div>
              <input id="imageInput" onchange="loadImageFile();" name="pic" type="file" class="offset10 lf" />
             <div id="picid" class="lf red laba">请上传图片</div>
               <input type="hidden" name="auction.auctionpictype" value="${auction.auctionpictype}">
            </dd>
            <dd class="hegas">
                <input name="" type="submit" value="保 存" class="spbg buttombg buttombgs f14 lf buttomb" />
                <input name="" type="button" value="取 消" onclick="location='auctionList.action'" class="spbg buttombg buttombgs f14 lf buttomb" />
                
            </dd>
          </dl>
    </div>
      </form>
<!-- main end-->
<!-- footer begin-->

</div>
 <!--footer end-->
 
</div>
</body>
</html>
