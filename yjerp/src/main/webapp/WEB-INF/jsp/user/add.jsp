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
		<input type="hidden" id="userId" name="userId" value="${obj.userId}" />
		<input type="hidden" id="cityName" name="cityName" />
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<h3>头像</h3>
				</div>
			</div>

			<div class="ibox-content" id="addImgDiv">
				<input id="uploadFileInputHead" isUpdate="-1"
					onchange="uploadFileFunHead()" multiple="true"
					style="display: none;" type="file" name="uploadFileInputHead" />
				<c:choose>
					<c:when test="${obj.headImage==null || obj.headImage==''}">
						<img id="addImgDefHead" src="../img/add_img.png" width="100px"
							height="100px"
							onclick='javascript:$("#uploadFileInputHead").attr("isUpdate","-1");$("#uploadFileInputHead").click();return false;' />
					</c:when>
					<c:otherwise>
						<img id="addImgDefHead" src="${obj.headImage}" width="100px"
							height="100px"
							onclick='javascript:$("#uploadFileInputHead").attr("isUpdate","-1");$("#uploadFileInputHead").click();return false;' />
					</c:otherwise>
				</c:choose>

				<input type="hidden" id="headImage" class="goodsImg"
					name="headImage" value="${obj.headImage}">
			</div>
		</div>
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<h3>图像库</h3>
				</div>
			</div>

			<div class="ibox-content" id="addImgDiv">
				<c:forEach items="${obj.imgList }" var="img" varStatus="imgStatus">
					<img updateImgTag="img_src_${imgStatus.index }"
						onclick="updateImg(${imgStatus.index })" width="100px"
						height="100px" src="${img.imgUrl }" />
					<input type="hidden" name="imgList[${imgStatus.index }].imageId"
						value="${img.imageId }" />
					<input updateImgTag="img_src_input_${imgStatus.index }"
						type="hidden" name="imgList[${imgStatus.index }].imgUrl"
						value="${img.imgUrl }" />
					<input type="hidden" class="goodsImg"
						name="imgList[${imgStatus.index }].sortOrder"
						value="${img.imgUrl }" />
				</c:forEach>
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
						maxlength="20" required="" value="${obj.phone }" />
				</div>
				<label class="col-sm-2 control-label">昵称：</label>
				<div class="col-sm-4">
					<input type="text" name="nickName" id="nickName"
						class="form-control" maxlength="20" required="" value="${obj.nickName }"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">年龄：</label>
				<div class="col-sm-4">
					<input type="text" name="age" id="age" class="form-control"
						maxlength="20" required="" value="${obj.age }" />
				</div>
				<label class="col-sm-2 control-label">性别：</label>
				<div class="col-sm-4">
					<select id="sex" name="sex" class="form-control">
						<option value="1" <c:if test="${obj.sex==1 }">selected='selected'</c:if>>男</option>
						<option value="2" <c:if test="${obj.sex==2 }">selected='selected'</c:if>>女</option>
					</select>

				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">所属城市：</label>
				<div class="col-sm-4">
					<select id="cityId" name="cityId" class="form-control" onchange="showPosition()">
						<c:forEach var="item" items="${listPro}">
							<option value="${item.provinceId}" <c:if test="${obj.cityId==item.provinceId }">selected='selected'</c:if>>${item.name}</option>
						</c:forEach>

					</select>
				</div>
				<label class="col-sm-2 control-label">体重：</label>
				<div class="col-sm-4">
					<input type="text" name="weight" id="weight" class="form-control"
						maxlength="20" required="" value="${obj.weight}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">身高：</label>
				<div class="col-sm-4">
					<input type="text" name="height" id="height" class="form-control"
						required="" value="${obj.height}" />
				</div>
				<label class="col-sm-2 control-label">体型：</label>
				<div class="col-sm-4">
					<select id="shape" name="shape" class="form-control">
						<option value="瘦弱" <c:if test="${obj.shape=='瘦弱' }">selected='selected'</c:if>>瘦弱</option>
						<option value="匀称" <c:if test="${obj.shape=='匀称' }">selected='selected'</c:if>>匀称</option>
						<option value="运动" <c:if test="${obj.shape=='运动' }">selected='selected'</c:if>>运动</option>
						<option value="肌肉" <c:if test="${obj.shape=='肌肉' }">selected='selected'</c:if>>肌肉</option>
						<option value="微胖" <c:if test="${obj.shape=='微胖' }">selected='selected'</c:if>>微胖</option>
						<option value="超重" <c:if test="${obj.shape=='超重' }">selected='selected'</c:if>>超重</option>
					</select>


				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">性取向：</label>
				<div class="col-sm-4">
					<select id="sexuat" name="sexuat" class="form-control">
						<option value="异性恋" <c:if test="${obj.sexuat=='异性恋' }">selected='selected'</c:if>>异性恋</option>
						<option value="同性恋" <c:if test="${obj.sexuat=='同性恋' }">selected='selected'</c:if>>同性恋</option>
						<option value="双性恋" <c:if test="${obj.sexuat=='双性恋' }">selected='selected'</c:if>>双性恋</option>
						<option value="泛性"  <c:if test="${obj.sexuat=='泛性' }">selected='selected'</c:if>>泛性</option>
						<option value="不确定" <c:if test="${obj.sexuat=='不确定' }">selected='selected'</c:if>>不确定</option>
					</select>

				</div>
				<label class="col-sm-2 control-label">个性签名：</label>
				<div class="col-sm-4">
					<input type="text" name="sign" id="sign" class="form-control"
						required="" value="${obj.sign}"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">设备类型：</label>
				<div class="col-sm-4">
					<select id="deviceType" name="deviceType" class="form-control">
						<option value="1" <c:if test="${obj.deviceType==1 }">selected='selected'</c:if>>IOS</option>
						<option value="2"  <c:if test="${obj.deviceType==2 }">selected='selected'</c:if>>Android</option>
					</select>
				</div>
				<c:if test="${obj.userId<=0 }">
					<label class="col-sm-2 control-label">邀请码：</label>
					<div class="col-sm-4">
						<input type="text" name="registerCode" id="registerCode"
							class="form-control" required="" />
					</div>
				</c:if>
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
function showPosition() {
	var address=$("#cityId").find("option:selected").text();  //获取Select选择的Text
	$("#cityName").attr("value",address);
   
}
</script>

