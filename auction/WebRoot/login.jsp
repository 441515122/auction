<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
</head>
<body style="background: url('images/background1.jpg'); backgroundRepeat:no-repeat;">
	<!-- <div class="image">
		<img src="images/img1.jpg" width="443" height="314" alt="" />
	</div> -->
	<div class="my_title">拍卖系统登录入口</div>
	<div class="model">
		<p class="title">用户登录</p>
		<form id="form_login" action="loginAction.action" method="post">
			<p class="user">用户名：</p>
			<input class="form-control" type="text" name="user.username"
				required="" placeholder="常用邮箱/用户名/电话号码">
			<p><s:actionmessage /></p>
			<p class="password">密&nbsp;&nbsp;&nbsp;码：</p>
			<input class="form-control" type="password" name="user.userpassword"
				required="" placeholder="密码">
			<p class="inputCode">验证码：</p>
			<input id="inputCode" class="form-control" type="text"
				name="inputCode" placeholder="验证码">
			<p class="img">
				<img src="Number.jsp" width="98" height="33" alt="" /><a href="" title="">看不清</a>
			</p>
			<p><s:fielderror fieldName="inputCode"></s:fielderror></p>
			<p class="check">
				<input type="checkbox">下次自动登录
			</p>
			<input id="login" class="btn btn-primary" type="submit"
				value="登       录"><input id="register"
				class="btn btn-primary" type="button" value="注      册" onclick="location='register.jsp'">
		</form>
	</div>
</body>
</html>