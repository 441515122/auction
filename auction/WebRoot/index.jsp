<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
	 <link rel="stylesheet" href="css/imageList.css">
	 <script src="js/WebCalendar.js" type="text/javascript"></script>
    <title>商品列表</title>
</head>
<body>
<div class="all">
	<div class="top">
		<div class="logo">在线拍卖系统</div>
		  <s:if test="#session.user != null">
		<input id="logout" class="btn btn-default" type="button" onclick="location='doLogout.action'" value="退出">
		  </s:if>
    <s:else>
		<input id="logout" class="btn btn-default" type="button" onclick="location='login.jsp'" value="登录">
		</s:else>
	</div>
		<hr>
	<div class="center">
		<form action="auctionList.action" method="post">
			<input type="hidden" name="pageNO" value="0"/>
		<input placeholder="名称" name="auctionExample.auctionname" id="name1" value="${auctionExample.auctionname}" class="form-control" type="text">
		<input placeholder="描述" name="auctionExample.auctiondesc" id="name" value="${auctionExample.auctiondesc}" class="form-control" type="text">
		<input placeholder="开始时间" name="auctionExample.auctionstarttime" id="name" value="<s:date name="auctionExample.auctionstarttime" format="yyyy-MM-dd"/>" class="form-control" type="text"  onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')">
		<input placeholder="结束时间" name="auctionExample.auctionendtime" id="name" value="<s:date name="auctionExample.auctionendtime" format="yyyy-MM-dd"/>" class="form-control" type="text"  onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')">
		<input placeholder="起拍价" name="auctionExample.auctionstartprice" id="name" value="${auctionExample.auctionstartprice}" class="form-control" type="text">
		<input  id="search" class="btn btn-default" type="submit" value="查询">
		</form>

		<s:if test="#session.user.userisadmin==true">
   			<input id="add" type="button" value="发布" onclick="location='addAuction.jsp'">
		 </s:if>
		 <s:if test="#session.user.userisadmin==false">
   			<input id="addOne" type="button" value="竞拍结果" onclick="location='auctionResultAction.action'">
		 </s:if>
	</div>

<hr>
	<p style=" font-size: 16px;font-weight: bold; color: cadetblue;">商品列表</p>
	
	<s:iterator value="auctionList" status="state">
		<div class="tableList">
			<img  class="image" src="upload/<s:property value="auctionpictype"/>" onclick="location='detailauctionAction?auctionid=<s:property value="auctionid"/>'">
			<p class="listfont1"><a href="detailauctionAction?auctionid=<s:property value="auctionid"/>" title="详情"><s:property value="auctionname"/></a></p>
			<p class="listfont2">￥<s:property value="auctionstartprice"/></p>
			<s:if test="#session.user.userisadmin==false">
				<input class="auctionButton" type="button" value="竞拍" onclick="location='auctionAction?auctionid=<s:property value="auctionid"/>'">
        	</s:if>
        	<s:if test="#session.user.userisadmin==true">
        		<input class="auctionButton" type="button" value="修改" onclick="location='detailAction?auctionid=<s:property value="auctionid"/>'">
        		<input class="auctionButton" type="button" value="删除" onclick="dele(<s:property value="auctionid"/>);">
        	</s:if>
			
		</div>
	 </s:iterator>
		
<div class="page">
<ul class="pagination">
	<li><a href="auctionList.action?pageNo=1">首页</a></li>
	
	<s:if test="pageNo!=prev">
		<li><a href="auctionList.action?pageNo=<s:property value="prev"/>"><s:property value="prev"/></a></li>
	</s:if>
	<li class="active"><a href="#"><s:property value="pageNo"/></a></li>
	
	<s:if test="pageNo!=next&&next<=allPages">
	<li><a href="auctionList.action?pageNo=<s:property value="next"/>"><s:property value="next"/></a></li>
	</s:if>
	
	<li><a href="auctionList.action?pageNo=<s:property value="allPages"/>">尾页</a></li>
</ul>
</div>
	</div>
	<script>
   function dele(auctionId){
	   if(confirm("你真的确认要删除吗？请确认")){
	    	window.location.href = "deleteAction?auctionid=" + auctionId;
		 }
	 };	
	function update(){
		   if(confirm("你真的确认要修改吗？请确认")){
			   return true;
			   }
			   else{
				   return false;
				   }
		   }
  </script>
</body>
</html>