<%@page import="com.alipay.api.internal.util.AlipaySignature"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	/* *
	 功能：支付宝页面跳转同步通知页面
	 版本：3.2
	 日期：2011-03-17
	 说明：
	 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
	 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
	 //***********页面功能说明***********
	 该页面可在本机电脑测试
	 可放入HTML等美化页面的代码、商户业务逻辑程序代码
	 TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
	 TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
	 //********************************
	 * */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.api.*"%>
<!DOCTYPE html>
<html>
<style>
.new-btn-login {
	background-color: #02aaf1;
	color: #FFFFFF;
	font-weight: bold;
	border: none;
	width: 100%;
	height: 30px;
	border-radius: 5px;
	font-size: 16px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/index.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/reset.css">
<title>支付成功</title>
</head>
<body onload="Successfun()">
	<div class="tip_icon">
		<img src="<%=path%>/images/zhifubao.png" class="tip_img" />
	</div>
	<p class="tips_status">恭喜您成为会员</p>
	<div class="click_return"  onclick="Successfun()">
		返回
	</div>
</body>
</html>
<script language="javascript">
	//rem换算
	(function(win, doc) {
		doc.documentElement.style.fontSize = doc.documentElement.clientWidth
				* 100 / 750 + 'px';
		win.onresize = function() {
			doc.documentElement.style.fontSize = doc.documentElement.clientWidth
					* 100 / 750 + 'px';
		};
	})(window, document);
	function Successfun() {
		paySuccess();
		//alert("11");
	}
</script>