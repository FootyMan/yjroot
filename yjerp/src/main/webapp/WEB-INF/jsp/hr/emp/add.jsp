<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link href="<%=path%>/css/plugins/datapicker/datepicker3.css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=path%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/inc/web/header.jsp" />


<jsp:include page="/inc/web/pagenav.jsp" />
<div class="ibox float-e-margins">
	<div class="ibox-title">
		<ol class="breadcrumb">
			<h4>员工管理>新增员工</h4>
		</ol>
	</div>
	<div class="ibox-content">
		<form id="empForm" action="<%=path%>/hr/employee/add.do" method="POST"
			class="form-horizontal"  AUTOCOMPLETE="OFF">
			<div class="form-group">
				<label class="col-sm-2 control-label">员工编号：</label>
				<div class="col-sm-4">
					<input type="text" name="empNo" id="empNo" class="form-control"
						maxlength="20" required="">
				</div>
				<label class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-4">
					<input type="text" name="empName" id="empName" class="form-control"
						maxlength="20" required="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">登录名：</label>
				<div class="col-sm-4">
					<input type="text" name="empLogin" id="empLogin"
						class="form-control" maxlength="20" required="">
				</div>
				<label class="col-sm-2 control-label">eMail：</label>
				<div class="col-sm-4">
					<input type="text" name="empEmail" id="empEmail"
						class="form-control" required="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">初始密码：</label>
				<div class="col-sm-4">
					<input type="text" name="empPwd" id="empPwd" class="form-control"
						maxlength="20" required="">
				</div>
				<label class="col-sm-2 control-label">手机</label>
				<div class="col-sm-4">
					<input type="text" name="empPhone" id="empPhone"
						class="form-control" maxlength="20">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">部门：</label>
				<div class="col-sm-4">
					<!-- <input type="text" name="deptCode" id="deptCode"
							class="form-control" > -->
					<select name="deptCode" id="deptCode" class="form-control">

						<c:forEach var="item" items="${depMap}">
							<option value="${item.key}">${item.value}</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-sm-2 control-label">角色</label>
				<div class="col-sm-4">
					<select name="roleId" id="roleId" class="form-control">
						<c:forEach var="item" items="${roleMap}">
							<option value="${item.key}">${item.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">性别：</label>
				<div class="col-sm-4">
					<select id="empGender" name="empGender" class="form-control">
						<option value="0">女</option>
						<option value="1">男</option>
						<%-- 							<c:forEach var="item" items="${sex}"> --%>
						<%-- 								<option value="${item.key}">${item.value}</option> --%>
						<%-- 							</c:forEach> --%>
					</select>

				</div>
				<label class="col-sm-2 control-label">入职日期</label>
				<div class="col-sm-4">
					<div data-link-field="dtp_input1" data-date-format="yyyy-mm-dd"
						class="input-group date form_date ">
						<input type="text" readonly="" name="entryDate" id="entryDate"
							value="" size="16" class="form-control"> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
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
</body>
</html>

<script type="text/javascript">
	$(function() {
		// 		$("#entryDate").datetimepicker({
		// 			format : "dd-MM-yyyy",
		// 			autoclose : true,
		// 			todayBtn : true

		// 		});
		// 		$("#empForm").validate();
	});
	// 		$(document).ready(function() {
	// 			/*  $('#entryDate').datepicker({
	// 			     todayBtn: "linked",
	// 			     keyboardNavigation: false,
	// 			     forceParse: false,
	// 			     calendarWeeks: true,
	// 			     autoclose: true
	// 			 }); */
	// 			$("#entryDate").datetimepicker({
	// 				format : "dd-MM-yyyy",
	// 				autoclose : true,
	// 				todayBtn : true

	// 			});
	// 			/* 	$('empForm').validate({
	// 					wrapper : "span",
	// 					//验证通过执行
	// 					submitHandler : function(form) {
	// 						form.submit();
	// 					}
	// 				}); */
	// 			$("#empForm").validate();

	// 		})
</script>