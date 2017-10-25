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

	<form action="../order/list" method="get">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<h3>订单管理></h3>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<input type="text" name="userNo" id="userNo" class="form-control"
								maxlength="20" placeholder="用户id">
						</div>
						<div class="col-sm-2">
							<input type="text" name="phone" id="phone"
								class="form-control" maxlength="20" placeholder="手机号">
						</div>
						<div class="col-sm-2">
							<select name="orderState" id="orderState" class="form-control">
								<option value="-1">全部</option>
								<option value="0">待付款</option>
								<option value="10">已付款</option>
							</select>
						</div>

						<div class="col-sm-2">
							<input type="submit" class="btn btn-sm btn-primary" value="搜索" />
						</div>
			
					</div>
				</div>
			</div>
			<div class="ibox-content">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>订单号</th>
							<th>订单状态</th>
							<th>下单时间</th>
							<th>订单来源</th>
							<th>支付方式</th>
							<th>支付金额</th>
							<th>用户Id</th>
							<th>用户昵称</th>
							<th>产品类型</th>
							<th>会员类型</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="emp" items="${orderList}">
							<tr>
								<td>${emp.orderNumber}</td>
								<td>${emp.orderStateName}</td>
								<td>${emp.orderTime}</td>
								<td>${emp.orderSourceName}</td>
								<td>${emp.payTypeName}</td>
								<td>${emp.orderPrice}</td>
								<td>${emp.userNo}</td>
								<td>${emp.nickName}</td>
								<td>${emp.productDesc}</td>
								<td>${emp.title}</td>
							
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