<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="wrap">
<!-- main begin-->
  <div class="sale">
    <h1 class="lf">拍卖结束的商品</h1>
    <div class="right rulse">当前用户是：<span class="blue strong"><a href="#" title=""><s:property value="#session.user.username"/></a></span></div>
    <div class="cl"></div>
  </div>
<div class="items">
      <ul class="rows even strong">
        <li>名称</li>
        <li>开始时间</li>
        <li>结束时间</li>
        <li>起拍价</li>
        <li class="list-wd">成交价</li>
        <li class="borderno">买家</li>
      </ul>
      <s:iterator value="endList" status="state">
		<ul <s:if test="#state.even">class="rows even"</s:if><s:else>class="rows"</s:else>>
        <li><a href="#" title=""><s:property value="auctionname"/></a></li>
        <li><s:date name="auctionstarttime" format="yyyy-MM-dd"/></li>
        <li><s:date name="auctionendtime" format="yyyy-MM-dd"/></li>
        <li><s:property value="auctionstartprice"/></li>
        <li class="list-wd"><s:property value="auctionPrice"/></li>
        <li class="borderno red"><a href="#"><s:property value="username"/></a></li>
      </ul>
	</s:iterator>
      
  </div>
  <h1>拍卖中的商品</h1>
  <div class="items records">
      <ul class="rows even strong rowh"> 
        <li>名称</li>
        <li>开始时间</li>
        <li>结束时间</li>
        <li>起拍价</li>
        <li class="borderno record">出价记录</li>
        <div class="cl"></div>
      </ul>
      
       <s:iterator value="noendList" status="state">
		<ul <s:if test="#state.even">class="rows even"</s:if><s:else>class="rows"</s:else>>
        <li><a href="#" title=""><s:property value="auctionname"/></a></li>
        <li><s:date name="auctionstarttime" format="yyyy-MM-dd"/></li>
        <li><s:date name="auctionendtime" format="yyyy-MM-dd"/></li>
        <li><s:property value="auctionstartprice"/></li>
        <li class="borderno blue record">
        	<s:iterator value="auctionRecords">
        		 <p><s:property value="auctionUser.username"/>&nbsp;&nbsp;<s:property value="auctionprice"/>元</p>
        	</s:iterator>
        </li>
        <div class="cl"></div>
      </ul>
	</s:iterator>
  </div>
<!-- main end-->
</div>
</body>
</html>
