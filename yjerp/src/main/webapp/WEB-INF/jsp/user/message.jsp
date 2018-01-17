<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%> --%>
<jsp:include page="/inc/web/header.jsp" />
<div class="gray-bg dashbard-1">
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<div class="row">
				<h5>
					<a href="../user/list">用户管理</a>><a href="#">用户编辑</a>
				</h5>
			</div>
		</div>
	</div>
	<form id="deptForm" action="../user/add.do" method="POST"
		class="form-horizontal">
		<div class="ibox-content">
			<div class="form-group">
				<label class="col-sm-2 control-label">用户类型：</label>
				<div class="col-sm-4">
					<select id="userType" name="userType" class="form-control" onchange="SetSelect()">
						<option value="1">单个用户</option>
						<option value="2">所有用户</option>

					</select>
				</div>
				<label class="col-sm-2 control-label">短信内容：</label>
				<div class="col-sm-4">
					<textarea  name="msgContent" id="msgContent" class="form-control"></textarea>
				</div>
			</div>
			<div class="form-group" id="divPhone">
				<label class="col-sm-2 control-label">手机号：</label>
				<div class="col-sm-4">
					<input type="text" name="phone" id="phone" class="form-control"
						maxlength="20" />
				</div>

			</div>

		</div>
		<div class="form-group">
			<div class="col-sm-4 col-sm-offset-3">
				<button type="button" class="btn btn-primary" onclick="SendMessage()">发送</button>
			</div>
		</div>
	</form>
</div>

</div>
</div>
 
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script src="../js/goods/userMessage.js?v=20170216"></script>

