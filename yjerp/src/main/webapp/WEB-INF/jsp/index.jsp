
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">

<title>众合致远ERP系统</title>
<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
<link
	href="./css/font-awesome.min.css"
	rel="stylesheet">
<link href="./css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="./css/animate.min.css" rel="stylesheet">
<link href="./css/style.min.css?v=4.1.0" rel="stylesheet">
<style>
.btn-primary {
	background-color: #1ab394 !important
}
</style>
</head>
<%-- <%@page import="com.ypwh.erp.api.sys.bean.SysRoleFunc"%> --%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%-- <%@page import="com.ypwh.erp.utils.GlobalsKey"%> --%>
<%-- <%@page import="com.ypwh.erp.utils.GlobalsConstant,com.ypwh.erp.api.sys.bean.SessionEmp"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// SessionEmp sEmp=(SessionEmp)session.getAttribute(GlobalsConstant.EMP_SESSION_KEY);
	// if(sEmp==null){
	// 	response.sendRedirect(GlobalsConstant.INDEX);
	// }
%>
<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span> <img alt="image" class="img-circle"
								src="<%=path%>/images/profile_small.jpg" />
							</span> <a data-toggle="dropdown" class="dropdown-toggle"
								href="index.html#"> <span class="clear"> <span
									class="block m-t-xs"> <strong class="font-bold">${sessionScope.xsUserSession.employee.empName}</strong>
								</span> <span class="text-muted text-xs block">${sessionScope.xsUserSession.role.roleName}
										<b class="caret"></b>
								</span>
							</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a href="/self/uppass">修改密码</a></li>
								<!--   <li><a href="profile.html" >个人资料</a>
                                </li> -->
								<li class="divider"></li>
								<li><a href="/accounts/logout">安全退出</a></li>
							</ul>
						</div>
						<div class="logo-element">心上</div>
					</li>

					<li><a href="/main#"><i class="fa fa-th-large"></i> <span
							class="nav-label">会员管理</span> <span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a class="J_menuItem" href="./user/list">会员管理</a></li>
							<li><a class="J_menuItem" href="./user/home">首页用户</a>
							<li><a class="J_menuItem" href="./order/list">订单管理</a>
							<li><a class="J_menuItem" href="./profit/wdList">用户提现</a></li>
<!-- 							<li><a class="J_menuItem" href="/sys/func/list">组织架构管理</a></li> -->
<!-- 							<li><a class="J_menuItem" href="/sys/config/list">系统配置</a></li> -->
<!-- 							<li><a class="J_menuItem" href="/sys/log/list">系统日志</a></li> -->
						</ul></li>


<!-- 					<li><a href="/main#"><i class="fa fa-th-large"></i> <span -->
<!-- 							class="nav-label">人力管理</span> <span class="fa arrow"></span></a> -->
<!-- 						<ul class="nav nav-second-level"> -->
<!-- 							<li><a class="J_menuItem" href="./hr/employee/list">员工管理</a></li> -->
<!-- 							<li><a class="J_menuItem" href="/sys/roleFunc/list">角色权限</a> -->
<!-- 							</li> -->
<!-- 							<li><a class="J_menuItem" href="/sys/func/list">组织架构管理</a></li> -->
<!-- 							<li><a class="J_menuItem" href="/sys/config/list">系统配置</a></li> -->
<!-- 							<li><a class="J_menuItem" href="/sys/log/list">系统日志</a></li> -->
<!-- 						</ul></li> -->

				</ul>
			</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">

			<div class="row content-tabs">
				<button class="navbar-minimalize minimalize-styl-2 btn-primary ">
					<i class="fa fa-bars"></i>
				</button>
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="javascript:;" class="active J_menuTab" data-id="/home">首页</a>
					</div>

				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>

					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
				<a href="/accounts/logout" class="roll-nav roll-right J_tabExit"><i
					class="fa fa fa-sign-out"></i> 退出</a>
			</div>

			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="<%=path%>/accounts/home" frameborder="0" data-id="/home"
					seamless></iframe>
			</div>
		</div>
		<!--右侧部分结束-->

	</div>
	<script src="<%=path%>/js/jquery.min.js?v=2.1.4"></script>
	<script src="<%=path%>/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="<%=path%>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="<%=path%>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="<%=path%>/js/plugins/layer/layer.min.js"></script>
	<script src="<%=path%>/js/hplus.js?v=4.1.0"></script>
	<script src="<%=path%>/js/contabs.min.js"></script>
	<script src="<%=path%>/js/plugins/pace/pace.min.js"></script>
</body>

</html>