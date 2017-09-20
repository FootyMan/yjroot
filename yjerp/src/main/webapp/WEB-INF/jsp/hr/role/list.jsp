<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/inc/web/header.jsp" />

<div class="gray-bg dashbard-1">
	<jsp:include page="/inc/web/pagenav.jsp" />
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<h5>角色管理></h5>
			<div class="ibox-tools">

				<a class="btn btn-primary btn-xs" href="<%=path %>/hr/role/add">新增角色</a>

			</div>

		</div>
		<div class="ibox-content">
			<table class="table table-striped">

				<thead>
					<tr>
						<th>角色编号</th>
						<th>角色名称</th>
						<th>角色所属部门</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="role" items="${roleReslist}">
						<tr>
							<td>${role.roleId}</td>
							<td>${role.roleName}</td>
							<td>${depMap[role.depId]}</td>
							<td><a class="btn btn-white btn-sm"
								href="/hr/dept/update?code=${role.roleId}"><i
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
</div>
</body>
</html>
