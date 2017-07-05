<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/detailAuction.css">
    <title>物品详情</title>
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
            <div class="img">
                <p><s:property value="auction.auctionname"/></p>
                <img src="upload/<s:property value="auction.auctionpictype"/>" alt="" width="100%" height="80%" style="border-radius: 5px;">
            </div>
            <div class="detail">
                <span class="font">起拍价：</span><label for=""><s:property value="auction.auctionstartprice"/></label></br>
                </br>
                <span class="font">描述：</span><label for=""><s:property value="auction.auctiondesc"/></label></br>
                </br>
                <span class="font">开始时间：</span><label for=""><s:date name="auction.auctionstarttime" format="yyyy-MM-dd hh:mm:dd"/></label></br>
                </br>
                <span class="font">结束时间：</span><label for=""><s:date name="auction.auctionendtime" format="yyyy-MM-dd hh:mm:dd"/></label></br>
            </div>
        </div>
        <div class="auction">
            <input type="button" id="auctionButton2" class="btn btn-primary" value="返回列表" onclick="location='auctionList.action'">
        </div>
</body>

</html>
