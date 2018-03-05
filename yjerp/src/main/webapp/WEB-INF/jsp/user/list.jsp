<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/WEB-INF/func.tld" prefix="func"%>
<jsp:include page="/inc/web/header.jsp" />
<div class="container-fluid">

	<form action="../user/list" method="get">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<h3>用户管理></h3>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<input type="text" name="userNo" id="userNo" class="form-control"
								maxlength="20" placeholder="用户id">
						</div>
						<div class="col-sm-2">
							<input type="text" name="phone" id="phone" class="form-control"
								maxlength="20" placeholder="手机号">
						</div>
						<div class="col-sm-2">
							<input type="text" name="nickName" id="nickName"
								class="form-control" maxlength="20" placeholder="昵称">
						</div>
						<div class="col-sm-2">
							<select name="isImport" id="isImport" class="form-control">
								<option value="">全部</option>
								<option value="0">注册用户</option>
								<option value="1">花蛇用户</option>
							</select>
						</div>
						<div class="col-sm-2">
							<select name="userLevel" id="userLevel" class="form-control">
								<option value="">全部</option>
								<option value="1">普通会员</option>
								<option value="2">月度会员</option>
								<option value="3">半年会员</option>
								<option value="4">年度会员</option>
							</select>
						</div>
						<div class="col-sm-2">
							<select name="sex" id="sex" class="form-control">
								<option value="">全部</option>
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
						</div>
						<div class="col-sm-2">
							<input type="submit" class="btn btn-sm btn-primary" value="搜索" />
						</div>
						<div class="col-sm-2"">
							<a href="../user/add" class="btn btn-sm btn-primary">新增用户</a>
						</div>
						<div class="col-sm-2"">
							<a href="../user/easeMessage" class="btn btn-sm btn-primary">发送消息</a>
						</div>
					</div>
				</div>
			</div>
			<div class="ibox-content">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>用户ID</th>
							<th>昵称</th>
							<th>头像</th>
							<th>手机号</th>
							<th>级别</th>
							<th>性别</th>
							<th>年龄</th>
							<th>城市</th>
							<th>性取向</th>
							<th>设备类型</th>
							<th>用户状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="emp" items="${listUser}">
							<tr>
								<td>${emp.userNo}</td>
								<td>${emp.nickName}</td>
								<td><img widht=30 height=30 src=${emp.headImage}></img></td>
								<td>${emp.phone}</td>
								<td>${emp.userLevel}</td>
								<td>${emp.sexName}</td>
								<td>${emp.age}</td>
								<td>${emp.cityName}</td>
								<td>${emp.sexuat}</td>
								<td>${emp.deviceTypeName}</td>
								<td>${emp.isDisable}</td>
								<td><a class="btn btn-white btn-sm"
									href="../user/add?userId=${emp.userId}"> 编辑 </a> <c:if
										test="${emp.isHomeUser==0}">
										<a class="btn btn-white btn-sm" href="javascript:void(0)"
											onclick="SetHomeUser(${emp.userId},1)"> <!-- 											<i -->
											<!-- 											class="fa fa-pencil"></i>  --> 设到首页
										</a>
									</c:if> <c:if test="${emp.userLevelId==1}">
										<a class="btn btn-white btn-sm" href="javascript:void(0)"
											onclick="SetUserLevel(${emp.userId},2)"> <!-- 											<i -->
											<!-- 											class="fa fa-pencil"></i> --> 设为会员
										</a>
									</c:if> <c:if test="${emp.userLevelId>1}">
										<a class="btn btn-white btn-sm" href="javascript:void(0)"
											onclick="SetUserLevel(${emp.userId},1)"> <!-- 											<i -->
											<!-- 											class="fa fa-pencil"></i>  --> 取消会员
										</a>
									</c:if></td>


							</tr>
						</c:forEach>
					</tbody>
					<tr>
						<td colspan="9"><jsp:include page="/WEB-INF/page.jsp" /></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</div>
<script src="../js/goods/goods.js"></script>