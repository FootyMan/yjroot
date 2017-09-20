<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="/css/plugins/datapicker/datepicker3.css"  rel="stylesheet">
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/inc/web/header.jsp" />

<div class="gray-bg dashbard-1">
	<jsp:include page="/inc/web/pagenav.jsp" />
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<ol class="breadcrumb">
				<h4>员工管理>新增员工</h4>
			</ol>
		</div>
		<div class="ibox-content">
			<form   class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">员工编号：</label>
					<div class="col-sm-4">
						<input type="text" name="empNo" id="empNo"
							class="form-control" maxlength="20" value="${emp.empNo}"  readonly="readonly">
					</div>
					<label class="col-sm-2 control-label">姓名：</label>
					<div class="col-sm-4">
						<input type="text" name="empName" id="empName"
							class="form-control" maxlength="20" value="${emp.empName}" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">登录名：</label>
					<div class="col-sm-4">
						<input type="text" name="empLogin" id="empLogin"
							class="form-control" maxlength="20" value="${emp.empLogin}" readonly="readonly">
					</div>
					<label class="col-sm-2 control-label">eMail：</label>
					<div class="col-sm-4">
						<input type="text" name="empEmail" id="empEmail"
							class="form-control" value="${emp.empEmail}" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
				
					<label class="col-sm-2 control-label">手机</label>
					<div class="col-sm-4">
						<input type="text" name="empPhone" id="empPhone"
							class="form-control" value="${emp.empPhone}"  maxlength="20" readonly="readonly">
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>
 <script src="/js/plugins/datapicker/bootstrap-datepicker.js">
	<script type="text/javascript">
		$(document).ready(function() {
            $("#entryDate").datetimepicker({
            format: "dd-MM-yyyy",
            autoclose: true,
            todayBtn: true
            }); 
            $("#empGender option[value='${emp.empGender}']").attr("selected","true");
            $("#roleId option[value='${emp.roleId}']").attr("selected","true");
            $("#deptCode option[value='${emp.deptCode}']").attr("selected","true");
            $("#empStatus option[value='${emp.empStatus}']").attr("selected","true");
			  $("#empForm").validate();
		})
	</script>
	</body>
	</html>