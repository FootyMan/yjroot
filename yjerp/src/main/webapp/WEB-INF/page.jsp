<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" id="page.pageSize" name="page.pageSize"
	value="${page.pageSize==null?0:page.pageSize}" />
<input type="hidden" id="page.pageNo" name="page.pageNo" value="1" />
<input type="hidden" id="currentNo" name="currentNo"
	value="${page.pageNo==null?0:page.pageNo }" />
<script type="text/javascript">
	function getForm() {
		var e = document.getElementById("page.pageNo");
		while (e = e.parentNode) {
			if (e.tagName == "FORM") {
				return e;
			}
			if (e.tagName == "BODY") {
				return null;
			}
		}

	}

	function openPage(pagerNo) {

		var pageNo = document.getElementById("page.pageNo");
		var form = getForm();
		pageNo.value = pagerNo;
		if (form != null) {
			form.submit();
		}
	}
	function checkNumber(pager) {
		var num = $("#Number1").val();
		var max = pager;

		if (num != 0 && num <= max) {
			openPage(num);
		} else {
			if (num != 0) {
				alert("输入页数不正确！");

			} else {
				alert("请输入有效数字");

			}

			var num = $("#Number1").val("");
		}

	}
	function changePageSize(pageSize) {
		var page_size = document.getElementById("page.pageSize");
		var pageNo = document.getElementById("page.pageNo");
		pageNo.value = 1;
		var form = getForm();
		page_size.value = pageSize;
		if (form != null) {
			form.submit();
		}
	}
</script>
第${page.pageNo}页 /${page.totalPage }页
<strong>共<font color="#66CC33" style="font-weight: normal">${page.totalCount }</font>条
</strong>
<c:if test="${page.pageNo!=1}">
&nbsp;&nbsp; <a href="javascript:openPage(1);">首页</a>&nbsp;&nbsp;
</c:if>
<c:choose>

	<c:when test="${page.pageNo==1}">
		<c:if test="${page.pageNo!=1}">
			<a href="#">上一页</a>
		</c:if>
	</c:when>
	<c:otherwise>
		<a href="javascript:openPage(${page.pageNo-1});">上一页</a>
	</c:otherwise>
</c:choose>
<c:if test="${page.pageNo>5}">
	<c:forEach begin="${page.pageNo-5}"
		end="${page.pageNo+5>=page.totalPage?page.totalPage:page.pageNo+2}"
		var="index">
	&nbsp;
	<c:choose>

			<c:when test='${page.pageNo==index }'>
				<a href="javascript:openPage(${index});">${index}</a>
			</c:when>

			<c:otherwise>
				<a href="javascript:openPage(${index});">[${index}]</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

</c:if>
<c:if test="${page.pageNo<=5&&page.totalPage>1}">
	<c:forEach begin="1" end="${page.totalPage>=8?8:page.totalPage}"
		var="index">
&nbsp;
	<c:choose>

			<c:when test='${page.pageNo==index }'>
				<a href="javascript:openPage(${index});">${index}</a>
			</c:when>

			<c:otherwise>
				<a href="javascript:openPage(${index});">[${index}]</a>
			</c:otherwise>

		</c:choose>

	</c:forEach>

</c:if>
<c:choose>

	<c:when test='${page.pageNo==page.totalPage }'>
		<c:if test="${page.pageNo!=page.totalPage }">
			<a href="#">下一页</a>
		</c:if>
	</c:when>

	<c:otherwise>
		<a href="javascript:openPage(${page.pageNo+1});">下一页</a>
	</c:otherwise>

</c:choose>
<c:if test="${page.pageNo!=page.totalPage }">

&nbsp;&nbsp; <a href="javascript:openPage(${page.totalPage});">末页</a>&nbsp;
</c:if>

<%-- <c:if test="${page.pageNo!=page.totalPage}">
 跳转到
 <input onkeyup="this.value=this.value.replace(/[^\d]/g,'') "  type="text" id="Number1"   style="width:20px;" />页
<input type="button"  value="跳转"  onclick="checkNumber(${page.totalPage })"/>
</c:if>
<c:if test="${page.pageNo!=1}">

<c:if test="${page.pageNo==page.totalPage}">
 跳转到
 <input onkeyup="this.value=this.value.replace(/[^\d]/g,'') "  type="text" id="Number1"   style="width:20px;" />页
<input type="button"  value="跳转"  onclick="checkNumber(${page.totalPage })"/>
</c:if>
</c:if> --%>