<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户注册</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/register.css" rel="stylesheet">
</head>
<body style="background: url('images/background1.jpg'); backgroundRepeat:no-repeat;">
    <div class="register">
         <p class="title">用户注册</p>
           <form action="registerAction.action" method="post">
     		 <input type="hidden" name="action" value="doRegister"/>
        <label class="name">*用户名</label><input id="form" name="user.username" placeholder="用户名" class="form-control" type="text" > <s:fielderror fieldName="user.username"></s:fielderror>
        <label class="name">*密码</label><input id="form" name="user.userpassword" placeholder="密码" class="form-control" type="password" ><s:fielderror fieldName="user.userpassword"></s:fielderror>
        <label class="name">*身份证号</label><input id="form" name="user.usercardno" placeholder="身份证号" class="form-control" type="text" ><s:fielderror fieldName="user.usercardno"></s:fielderror>
        <label class="name">*电话</label><input id="form" name="user.usertel" placeholder="电话" class="form-control" type="text" > <s:fielderror fieldName="user.usertel"></s:fielderror>
        <label class="name">*住址</label><input id="form" name="user.useraddress" placeholder="住址" class="form-control" type="text" >
        <label class="name">*邮政编码</label><input id="form" name="user.userpostnumber" placeholder="邮政编码" class="form-control" type="text" >
        <label class="name">*验证码</label><input id="inputcode" name="inputCode" placeholder="验证码" class="form-control" type="text" >
        <p class="img">
				<img src="Number.jsp" width="97" height="30" alt="" /><a href="" title="">看不清</a>
			</p>
        <div class="checkbox"><input type="checkbox" value="">我同意<a href="#">《服务条款》</a></div>
        <input type="submit" id="registetButton" class="btn btn-primary" value="立即注册">
        </form>
    </div>
</body>
</html>
