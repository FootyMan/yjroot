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

	<form action="../profit/wdList" method="get">
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
							<select name="operateStatus" id="operateStatus" class="form-control">
								<option value="0">全部</option>
								<option value="1">未结算</option>
								<option value="2">已结算</option>
							</select>
						</div>

						<div class="col-sm-2">
							<input type="submit" class="btn btn-sm btn-primary" value="搜索" />
						</div>
						<div class="ibox-tools">
							<a href="../user/add" class="btn btn-sm btn-primary">新增用户</a>
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
							<th>提现金额</th>
							<th>收款账号</th>
							<th>收款人</th>
							<th>账户剩余金额</th>
							<th>提现单号</th>
							<th>提现日期</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="emp" items="${wdList}">
							<tr>
								<td>${emp.userNo}</td>
								<td>${emp.nickName}</td>
								<td>${emp.financialMoney}</td>
								<td>${emp.payAccount}</td>
								<td>${emp.payRealName}</td>
								<td>${emp.totalMoney}</td>
								<td>${emp.sourceNumber}</td>
								<td>${emp.operateDate}</td>
								<td>${emp.operateStatusName}</td>
								<td>
								<c:if test="${emp.operateStatus==1}">
								<a class="btn btn-white btn-sm"
									href="javascript:void(0)" onclick="SetOperateState(${emp.recordID})"> <i
										class="fa fa-pencil"></i> 标记已打款
								</a> 
								<a class="btn btn-white btn-sm" href="javascript:void(0)"
									onclick="SetHomeUser(${emp.recordID},1)"> 
									<i class="fafa-pencil"></i> 去支付</a>
								</c:if>
								</td>


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
<script>
//设置首页用户
function SetOperateState(recordId){
	var httpUrl = '../profit/setOperate?recordId='+recordId;
	$.ajax({
		type:'get',
		async:false,
		url:httpUrl,
		success:function(data){
			if(data>=1){
				alert("设置成功！");
				// $(".goodsID"+goodsId).remove();
				location.reload();
			} 
			else
			{
				alert("设置失败")
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert("网络异常");
			
		}
	});
}

</script>