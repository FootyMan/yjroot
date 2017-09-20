<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="inc/web/header.jsp" />
<%
	//response.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>登录页</title>
<link rel="stylesheet" href="css/base.css" />
<link rel="stylesheet" href="css/login.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<style type="text/css">
body {
	background: url(images/pt-loginbg.png) no-repeat center center;
	background-size: 100% 100%;
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
	<div id="login-center">
		<div class="login-box">
			<form id="loginForm" action="<%=path%>/accounts/login" method="post">
				<h3>
					后台管理系统<i></i>
				</h3>
				<div>
					<%
						String error = request.getParameter("error");
						if (error != null) {
							error = new String(request.getParameter("error").getBytes("iso-8859-1"), "utf-8");
						}
						else
						{
							error="";
						}
					%>
					
				</div>
<%-- 				<div>${param.error}</div> --%>
				<div class="login-inputBox">
					<input type="text" name="userName" placeholder="用户名" /> <label
						id="loginNameLab" style="color: red"></label> <input
						type="password" name="userPwd" placeholder="密码" /> <label
						id="pwdLab" style="color: red"></label>
					<button type="submit" class="btn btn-primary block full-width m-b">登
						录</button>
					<label id="loginMessageLab" style="color: red"><%=error%></label>
					<!-- 										<input class="login-sub" type="submit" value="登录" /> <label -->
					<!-- 											id="loginMessageLab" style="color: red"></label> -->
				</div>
			</form>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$("#loginForm input").change(function() {
			var name = $(this).attr("name");
			if (name == "userPwd") {
				var tem = $("#pwdLab").text();
				if (tem.length > 0) {
					$("#pwdLab").text("");
				}
			} else {
				var tem = $("#loginNameLab").text();
				if (tem.length > 0) {
					$("#loginNameLab").text("");
				}
			}

		})
		$("#loginForm").validate({
			// #JQForm是form表单的ID
			rules : {
				userName : {
					required : true
				},
				userPwd : {
					required : true,
				}
			},
			messages : {
				userName : {
					required : "请输入登录名称"
				},
				userPwd : {
					required : "请输入密码"
				}
			},
			errorPlacement : function(error, element) { // 自定义错误信息放置的位置
				// error.appendTo(element);
				var name = $(element).attr("name");
				if (name == "userPwd") {
					$("#pwdLab").text($(error).text());
					$("#loginMessageLab").text("");
				} else {
					$("#loginNameLab").text($(error).text());
					$("#loginMessageLab").text("");
				}

			},
			submitHandler : function(form) { //通过之后回调
				var param = $(form).serialize();
				$('#loginForm').submit();
				// 				$.ajax({
				// 					url : "/Home/Login",
				// 					type : "post",
				// 					dataType : "json",
				// 					data : param,
				// 					success : function(data) {
				// 						if (data.Success) {
				// 							document.location.href = "/";
				// 						} else {
				// 							$("#loginMessageLab").text(data.Messages);
				// 						}
				// 					}
				// 				});
			}
		});
	})
</script>

