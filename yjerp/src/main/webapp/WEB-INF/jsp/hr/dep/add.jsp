<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/inc/web/header.jsp" />
<div class="gray-bg dashbard-1">
	<jsp:include page="/inc/web/pagenav.jsp" />
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<ol class="breadcrumb">
				<h4>部门管理>新增部门</h4>
			</ol>
		</div>
		<div class="ibox-content">
			<form id="deptForm" action="<%=path%>/hr/dep/add.do" method="POST"
				class="form-horizontal">
				<div class="form-group">

					<label class="col-sm-2 control-label">部门名称：</label>
					<div class="col-sm-4">
						<input type="text" name="deptName" id="deptName"
							class="form-control" maxlength="20" required="">
					</div>

				</div>
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-3">
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>