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

	<form action="<%=path%>/user/list" method="get">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<h3>用户管理></h3>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<input type="text" name="empNo" id="empNo" class="form-control"
								maxlength="20" placeholder="编号">
						</div>
						<div class="col-sm-2">
							<input type="text" name="empName" id="empName"
								class="form-control" maxlength="20" placeholder="姓名">
						</div>
						<div class="col-sm-2">
							<select name="roleId" id="roleId" class="form-control">
								<option value="">请选择角色</option>
								<%-- 								<c:forEach var="item" items="${roleMap}"> --%>
								<%-- 									<option value="${item.key}">${item.value}</option> --%>
								<%-- 								</c:forEach> --%>
							</select>
						</div>

						<div class="col-sm-2">
							<input type="submit" class="btn btn-sm btn-primary" value="搜索" />
						</div>
						<div class="ibox-tools">
							<a href="<%=path%>/hr/employee/add"
								class="btn btn-sm btn-primary">新增员工</a>
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
							<th>手机号</th>
							<th>性别</th>
							<th>年龄</th>
							<th>城市</th>
							<th>性取向</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="emp" items="${listUser}">
							<tr>
								<td>${emp.Id}</td>
								<td>${emp.nickName}</td>
								<td>${emp.phone}</td>
								<td>${emp.sex}</td>
								<td>${emp.age}</td>
								<td>${emp.cityName}</td>
								<td>${emp.sexuat}</td>
								<td><a class="btn btn-white btn-sm"
									href="<%=path%>/hr/employee//list.detail?id=${emp.id}"><i
										class="fa fa-pencil"></i> 详情 </a> <a class="btn btn-white btn-sm"
									href="/hr/employee/update?id=${emp.id}"><i
										class="fa fa-pencil"></i> 编辑 </a></td>
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
