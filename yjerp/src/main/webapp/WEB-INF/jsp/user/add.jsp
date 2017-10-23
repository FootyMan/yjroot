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
					<a href="#">商品管理</a>><a href="/ope/goods/list">商品列表</a>><a href="#">商品发布</a>
				</h5>
			</div>
		</div>
	</div>
	<form id="deptForm" action="../user/add.do" method="POST"
		class="form-horizontal">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<h3>头像</h3>
				</div>
			</div>

			<div class="ibox-content" id="addImgDiv">
				<input id="uploadFileInputHead" isUpdate="-1"
					onchange="uploadFileFunHead()" multiple="true"
					style="display: none;" type="file" name="uploadFileInputHead" /> <img
					id="addImgDefHead" src="../img/add_img.png" width="100px"
					height="100px"
					onclick='javascript:$("#uploadFileInputHead").attr("isUpdate","-1");$("#uploadFileInputHead").click();return false;' />
					<input type="hidden" id="headImage"   class="goodsImg" name="headImage" value="">
			</div>

			<!-- 		<div class="ibox-content" id="addImgDiv"> -->
			<!-- 			<input id="uploadFileInput" isUpdate="-1" onchange="uploadFileFun()" -->
			<!-- 				multiple="true" name="uploadFileInput" style="display: none;" -->
			<!-- 				type="file" /> <img id="addImgDef" src="../img/add_img.png" -->
			<!-- 				onclick='javascript:$("#uploadFileInput").attr("isUpdate","-1");$("#uploadFileInput").click();return false;' /> -->
			<!-- 		</div> -->
		</div>
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<h3>图像库</h3>
				</div>
			</div>

			<div class="ibox-content" id="addImgDiv">
				<input id="uploadFileInput" isUpdate="-1" onchange="uploadFileFun()"
					multiple="true" name="uploadFileInput" style="display: none;"
					type="file" /> <img id="addImgDef" src="../img/add_img.png"
					onclick='javascript:$("#uploadFileInput").attr("isUpdate","-1");$("#uploadFileInput").click();return false;' />
			</div>


		</div>


		<div class="ibox-content">

			<div class="form-group">
				<label class="col-sm-2 control-label">手机号：</label>
				<div class="col-sm-4">
					<input type="text" name="phone" id="phone" class="form-control"
						maxlength="20" required="" />
				</div>
				<label class="col-sm-2 control-label">昵称：</label>
				<div class="col-sm-4">
					<input type="text" name="nickName" id="nickName"
						class="form-control" maxlength="20" required="" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">年龄：</label>
				<div class="col-sm-4">
					<input type="text" name="age" id="age" class="form-control"
						maxlength="20" required="" />
				</div>
				<label class="col-sm-2 control-label">性别：</label>
				<div class="col-sm-4">
					<select id="sex" name="sex" class="form-control">
						<option value="1">男</option>
						<option value="2">女</option>
					</select>

				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">所属城市：</label>
				<div class="col-sm-4">
					<select id="cityId" name="cityId" class="form-control">
						<c:forEach var="item" items="${listPro}">
							<option value="${item.provinceId}">${item.name}</option>
						</c:forEach>

					</select>
				</div>
				<label class="col-sm-2 control-label">体重：</label>
				<div class="col-sm-4">
					<input type="text" name="weight" id="weight" class="form-control"
						maxlength="20" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">身高：</label>
				<div class="col-sm-4">
					<input type="text" name="height" id="height" class="form-control" />
				</div>
				<label class="col-sm-2 control-label">体型：</label>
				<div class="col-sm-4">
					<select id="shape" name="shape" class="form-control">
						<option value="瘦弱">瘦弱</option>
						<option value="匀称">匀称</option>
						<option value="运动">运动</option>
						<option value="肌肉">肌肉</option>
						<option value="微胖">微胖</option>
						<option value="超重">超重</option>
					</select>


				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">性取向：</label>
				<div class="col-sm-4">
					<select id="sexuat" name="sexuat" class="form-control">
						<option value="异性恋">异性恋</option>
						<option value="同性恋">同性恋</option>
						<option value="双性恋">双性恋</option>
						<option value="泛性">泛性</option>
						<option value="不确定">不确定</option>
					</select>

				</div>
				<label class="col-sm-2 control-label">个性签名：</label>
				<div class="col-sm-4">
					<input type="text" name="sign" id="sign" class="form-control"
						required="">
				</div>
			</div>


			<div class="form-group">
				<label class="col-sm-2 control-label">设备类型：</label>
				<div class="col-sm-4">
					<select id="deviceType" name="deviceType" class="form-control">
						<option value="1">IOS</option>
						<option value="2">Android</option>
					</select>
				</div>
				<label class="col-sm-2 control-label">邀请码：</label>
				<div class="col-sm-4">
					<input type="text" name="inviteCode" id="inviteCode" class="form-control"
						required="">
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
</div>
<script type="text/javascript" src="../js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script src="../js/goods/goods.js?v=20170216"></script>
<script type="text/javascript">
	
</script>

