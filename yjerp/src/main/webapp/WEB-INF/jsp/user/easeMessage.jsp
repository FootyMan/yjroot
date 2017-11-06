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
					<a href="../user/list">用户管理</a>><a href="#">发送消息</a>
				</h5>
			</div>
		</div>
	</div>
	<form id="deptForm" method="POST"
		class="form-horizontal">
		 
			
			<div class="form-group">
			 
				<label class="col-sm-2 control-label">个性签名：</label>
				<div class="col-sm-4">
					<input type="text" name="message" id="message" class="form-control"
						required="" />
				</div>
			</div>
			
			 
		</div>
		<div class="form-group">
			<div class="col-sm-4 col-sm-offset-3">
				<button type="button" onclick="SendMessage()" class="btn btn-primary">提交</button>
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
 
 function SendMessage(){
	 var message=$("#message").val();
		var httpUrl = '../user/sendMsg?message='+message;
		$.ajax({
			type:'get',
			async:false,
			url:httpUrl,
			success:function(data){
				if(data>=1){
					alert("发送成功！");
					// $(".goodsID"+goodsId).remove();
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

