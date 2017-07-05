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
    <title>竞拍</title>
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
         <form action="doAuctionBid.action" method="post">
  		<input type="hidden" name="auctionId" value="<s:property value="auction.auctionid"/>">
            <label for="">出价：</label><input type="text" class="form-control" name="auctionPrice"><input id="auctionButton" type="submit" value="竞拍" class="btn btn-primary"><br></form>
            <input type="button" id="auctionButton1" class="btn btn-default" value="刷新" onclick="location='auctionAction?auctionid=<s:property value="auctionid"/>'">
            <input type="button" id="auctionButton2" class="btn btn-primary" value="返回列表" onclick="location='auctionList.action'">
        </div>

        <div class="bottom">
            <p class="jilu">出价记录</p>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th style="text-align: center;">竞拍时间</th>
                        <th style="text-align: center;">竞拍价格</th>
                        <th style="text-align: center;">竞拍人</th>
                    </tr>
                </thead>
                 <s:iterator value="auction.auctionRecords" status="state">
                <tbody style="text-align: center;">
                    <td><s:date name="auctiontime" format="yyyy年MM月dd日"/></td>

                    <td><s:property value="auctionprice"/></td>


                    <td><s:property value="auctionUser.username"/></td>

                </tbody>
                	</s:iterator>
            </table>
        </div>
</body>

</html>

