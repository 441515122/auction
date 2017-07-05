<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
	 <link rel="stylesheet" href="css/listAuction.css">
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
		<p class="font">名称</p><input name="auctionExample.auctionname" id="name" value="${auctionExample.auctionname}" class="form-control" type="text">
		<p class="font">描述</p><input name="auctionExample.auctiondesc" id="name" value="${auctionExample.auctiondesc}" class="form-control" type="text">
		<p class="font"> 开始时间</p><input name="auctionExample.auctionstarttime" id="name" value="<s:date name="auctionExample.auctionstarttime" format="yyyy-MM-dd"/>" class="form-control" type="text"  onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')">
		<p class="font"> 结束时间</p><input name="auctionExample.auctionendtime" id="name" value="<s:date name="auctionExample.auctionendtime" format="yyyy-MM-dd"/>" class="form-control" type="text"  onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')">
		<p class="font"> 起拍价</p><input name="auctionExample.auctionstartprice" id="name" value="${auctionExample.auctionstartprice}" class="form-control" type="text">
		<input  id="search" class="btn btn-default" type="submit" value="查询">
		</form>

		<s:if test="#session.user.userisadmin==true">
   			<input id="add" type="button" value="发布" onclick="location='addAuction.jsp'">
		 </s:if>
		 <s:if test="#session.user.userisadmin==false">
   			<input id="addOne" type="button" value="竞拍结果" onclick="location='auctionResultAction.action'">
		 </s:if>
	</div>

	<div class="bottom">
		<table class="table table-bordered table-hover">
   <thead>
      <tr>
         <th style="text-align: center;">名称</th>
         <th style="text-align: center;">描述</th>
		 <th style="text-align: center;">开始时间</th>
		 <th style="text-align: center;">结束时间</th>
		 <th style="text-align: center;">起拍价</th>
		 <th style="text-align: center;">操作</th>
      </tr>
   </thead>
   <tbody>
	     <s:iterator value="auctionList" status="state">
      <tr style='text-align: center;' <s:if test="#state.even">class="success"</s:if><s:else>class="warning"</s:else>>
         <td><a href="detailauctionAction?auctionid=<s:property value="auctionid"/>" title="详情"><s:property value="auctionname"/></a></td>
         
         <td><s:property value="auctiondesc"/></td>
		 
		 <td><s:date name="auctionstarttime" format="yyyy-MM-dd"/></td>
		 
		 <td><s:date name="auctionendtime" format="yyyy-MM-dd"/></td>
		 
		 <td><s:property value="auctionstartprice"/></td>
		 
		 <td>
			 <s:if test="#session.user.userisadmin==false">
        		 <a href="auctionAction?auctionid=<s:property value="auctionid"/>" title="竞拍">竞拍</a>
        	</s:if>
        	<s:if test="#session.user.userisadmin==true">
        		  <a href="detailAction?auctionid=<s:property value="auctionid"/>" title="修改">修改</a>|
          		  <a href="#" title="删除" onclick="dele(<s:property value="auctionid"/>);">删除</a>
        	</s:if>
		 </td>
      </tr>
	  </s:iterator>
	  
   </tbody>
</table>
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
