<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%-- <%@ taglib  uri="/WEB-INF/func.tld"  prefix="func"%> --%>
<jsp:include page="/inc/web/header.jsp" />
<div  class="gray-bg dashbard-1">
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<div class="row">
				<h5><a href="#">商品管理</a>><a href="/ope/goods/list">商品列表</a>><a href="#">商品发布</a></h5>
			</div>
		</div>
	</div>
	<form id="addGoodsForm" name="addGoodsForm" class="form-horizontal" method="get" >
	    <input type="hidden" id="goodsIdInput" name="id" value="${obj.id}"/>
	    <input type="hidden" id="goodsStateInput" name="goodsState" value="${obj.goodsState}"/>
	    <input type="hidden" id="usageIdInput" name="usageId"  value="${obj.usageId}${goodsCheckupObj.olddegreeId}"/>
		<input type="hidden" name="gloveId"  value="${obj.gloveId}"/>
		<input type="hidden" name="source"  value="${obj.source}"/>
		<input type="hidden" name="sourceId"  value="${obj.sourceId}"/>
	    <input type="hidden" id="stockNum" name="stockNum" value="1"  />
		<input type="hidden" id="subCateIdInput"  value="${obj.subCateId}"/>
		<input type="hidden" id="brandSeriesInput"  value="${obj.brandSeriesId}"/>
		 <input type="hidden" id="remarksInput" name="remarks" value="${obj.remarks }"/>
         <input type="hidden" name="pbizType" id="pbizType" value="2"/>
         <input type="hidden" id="goodsNameInputStr"  value="${obj.goodsName }${goodsCheckupObj.goodsTitle}" >
         <input type="hidden" id="bizTypeInput" value="${obj.bizType }"/>
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<h3>商品图片</h3>
					<img id="addImgDef1" src="../img/add_img.png" />
				</div>
			</div>
			
			<div class="ibox-content" id="addImgDiv">
<%-- 				 <c:forEach items="${obj.imgList }" var="goodsImg" varStatus="goodsImgStatus" > --%>
<%-- 					<img updateImgTag="img_src_${goodsImgStatus.index }" onclick="updateImg(${goodsImgStatus.index })" --%>
<%-- 						 width="100px" height="100px" src="${goodsImg.longUrl }"/> --%>
<%-- 					<input type="hidden"  name="imgList[${goodsImgStatus.index }].id" value="${goodsImg.id }"/> --%>
<%-- 					<input updateImgTag="img_src_input_${goodsImgStatus.index }" type="hidden"  name="imgList[${goodsImgStatus.index }].imgUrl" value="${goodsImg.imgUrl }"/> --%>
<%-- 					<input type="hidden" class="goodsImg"  name="imgList[${goodsImgStatus.index }].sortOrder" value="${goodsImg.sortOrder }"/> --%>
<%-- 				</c:forEach>  --%>
				<input id="uploadFileInput" isUpdate="-1" onchange="uploadFileFun()"  multiple="true" name="uploadFileInput" style="display: none;" type="file" />
				 
				<img id="addImgDef" src="../img/add_img.png" onclick='javascript:$("#uploadFileInput").attr("isUpdate","-1");$("#uploadFileInput").click();return false;'/>
			</div>
		</div>
		 
	</div>
</div>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script src="/js/goods/goods.js?v=20170216"></script>
<script type="text/javascript">
<!--

//-->

$(document).ready(function() {
	 
	initSubCate();
	document.getElementById("descChild").style.display="none";
	
}
)




function brandIdSelectChange(){
	/* var selectValue = document.addGoodsForm.brandIdSelect.options[document.addGoodsForm.brandIdSelect.selectedIndex].text; 
	alert(selectValue) */
	var state = $("#goodsStateInput").val();
	 var bizType = $("#bizTypeInput").val();
	 if(bizType==20||bizType==5||bizType==10||bizType==""){
		 //alert(state)
		if(state==3||state==7||state==""){
		var brandIdSelect = $("#brandIdSelect").val();
			$.ajax({
				type:'get',
				async:false,
				url:'/ope/brand/list.brandById?id='+brandIdSelect,
				success:function(data){
						var sortName = JSON.parse(data).sortName;
						var name = JSON.parse(data).name;
						var goodsName = $("#goodsNameInputStr").val();
					if(state==7){
						var goods = goodsName.split(" ");
						if(goods.length>1){
							goodsName = goods[1];
						}
					}
					if(sortName!=null){
						$("#goodsNameInput").empty();
						$("#goodsNameInput").val(sortName+" "+goodsName);
					}else{
						$("#goodsNameInput").val(name+" "+goodsName);
					}
				}
			});
	 	}
	}
}
//根据父类加载子类
$("#goodsCategorySelect").change(function(){
		$("#descChild").empty();
		var cateID= $("#goodsCategorySelect").val();
		var subCateId = $("#subCateIdInput").val();
		 $.ajax({
			type:'get',
			async:false,
			url:'/ope/goodsCheck/list.getSellerGoodsDescListByPid?pid='+cateID+'&key=seller_goods_desc',
			success:function(data){
				$("#desccheckbox").empty();
				var list = $.parseJSON(data);
				for(var i=0;i<list.length;i++){
					if(list[i].state>=0){
							$("#desccheckbox").prepend("<label><input "+" name='checkedDesc' "+" type='checkbox' "+" onclick='checkedDesc1(this)' "+" value='"+list[i].name+ "' />"+list[i].name+"</label><br/>");
					}
				}
			}
		});	
	});

//根据父类加载子类
function initSubCate()
{
	var cateID= $("#goodsCategorySelect").val();
	var subCateId = $("#subCateIdInput").val();
	$.ajax({
		type:'get',
		async:false,
		url:'/ope/goodsCategory/getListByPid?pid='+cateID,
		success:function(data){
			$("#PgoodsCategorySelect").empty();
			$("#PgoodsCategorySelect").prepend('<option value="0">请先选择一级类目</option>');
			var list = $.parseJSON(data);
			for(var i=0;i<list.length;i++){
				var optionSelectedStr ="";
				if(list[i].state>=0){
					if(subCateId==list[i].id){
						optionSelectedStr="selected='selected'"
					}
					$("#PgoodsCategorySelect").prepend("<option "+optionSelectedStr+" value='"+list[i].id+"'>"+list[i].name+"</option>");	
				}
			}
		}
	});	
	
	 $.ajax({
		type:'get',
		async:false,
		url:'/ope/goodsCheck/list.getSellerGoodsDescListByPid?pid='+cateID+'&key=seller_goods_desc',
		success:function(data){
			$("#desccheckbox").empty();
			var list = $.parseJSON(data);
			for(var i=0;i<list.length;i++){
				if(list[i].state>=0){
					if(subCateId==list[i].id){
					}
					$("#desccheckbox").prepend("<label><input "+" name='checkedDesc' "+" type='checkbox' "+" onclick='checkedDesc1(this)' "+" value='"+list[i].name+ "' />"+list[i].name+"</label><br/>");
				}
			}
		}
	});		
	 var state = $("#goodsStateInput").val();
	 var bizType = $("#bizTypeInput").val();
	 if(bizType==20||bizType==5||bizType==10||bizType==""){//20是自营、5是C2C、10是白手套、""是白手套待复审
		//alert(state)
		if(state==3||state==7||state==""){//3是待审核、7是待上架、""是白手套待复审、
		var brandIdSelect = $("#brandIdSelect").val();
			$.ajax({
				type:'get',
				async:false,
				url:'/ope/brand/list.brandById?id='+brandIdSelect,
				success:function(data){
						var sortName = JSON.parse(data).sortName;
						var name = JSON.parse(data).name;
						var goodsName = $("#goodsNameInputStr").val();
					if(state==7){
						var goods = goodsName.split(" ");
						if(goods.length>1){
							goodsName = goods[1];
						}
					}
					if(sortName!=null){
						$("#goodsNameInput").empty();
						$("#goodsNameInput").val(sortName+" "+goodsName);
					}else{
						$("#goodsNameInput").val(name+" "+goodsName);
					}
				}
			});
		}
	}
}
function checkedDesc2(obj){
	 if (obj.checked == true){
			var desc= obj.value;
			if(desc==''){
				return;
			}
				
			var sellerDescInput= $("#sellerDescInput").val();
		    if(sellerDescInput!=''){
		       $("#sellerDescInput").val(sellerDescInput+' '+desc+'、');
			}
		    else{
			    $("#sellerDescInput").val(desc);
			}
	 }
}
	
 function checkedDesc1(obj){
	 if (obj.checked == true){
		var desc= obj.value;
		if(desc==''){
			return;
		}
			
		var sellerDescInput= $("#sellerDescInput").val();
	    if(sellerDescInput!=''){
	       $("#sellerDescInput").val(sellerDescInput+'\n'+desc);
		}
	    else{
		    $("#sellerDescInput").val(desc);
		}
	    
	    var goodsCategorySelect = $("#goodsCategorySelect").val();
	    if(desc=="属性:"){
	    	$("#descChild").empty();
	    	document.getElementById("descChild").style.display="";
	    	var v1 = "男";
		 	var v2 = "女";
 	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label>"); 
	    }else if(desc=="表径:"){
	    	$("#descChild").empty();
	    	document.getElementById("descChild").style.display="";
	    	var v1 = "36mm以下";
		 	var v2 = "36-39mm";
		 	var v3 = "39-42mm";
		 	var v4 = "42-45mm";
		 	var v5 = "45mm以上";
		 	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v4+ "' />"+v4+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v5+ "' />"+v5+"</label><br/>");
	    }else if(desc=="机芯:"){
	    	$("#descChild").empty();
	    	document.getElementById("descChild").style.display="";
	    	var v1 = "自动机械";
		 	var v2 = "手动机械";
		 	var v3 = "石英";
		 	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
	    }else if(desc=="材质:"){
	    	$("#descChild").empty();
	    	document.getElementById("descChild").style.display="";
	    	var v1 = "钢";
		 	var v2 = "金";
		 	var v3 = "间金";
		 	var v4 = "其他";
		 	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v4+ "' />"+v4+"</label><br/>");
	    }else if(desc=="功能:"){
	    	$("#descChild").empty();
	    	document.getElementById("descChild").style.display="";
	    	var v1 = "计时";
		 	var v2 = "防磁";
		 	var v3 = "动储";
		 	var v4 = "三问";
		 	var v5 = "月相";
		 	var v6 = "双时区";
		 	var v7 = "世界时";
		 	var v8 = "万年历";
		 	var v9 = "陀飞轮";
		 	var v10 = "日期显示";
		 	var v11 = "星期显示";
		 	var v12 = "月份显示";
		 	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v4+ "' />"+v4+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v5+ "' />"+v5+"</label><br/>"); 
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v6+ "' />"+v6+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v7+ "' />"+v7+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v8+ "' />"+v8+"</label><br/>"); 
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v9+ "' />"+v9+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v10+ "' />"+v10+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v11+ "' />"+v11+"</label><br/>"); 
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v12+ "' />"+v12+"</label><br/>");
	    }else if(desc=="表盘颜色:"){
	    	$("#descChild").empty();
	    	document.getElementById("descChild").style.display="";
	    	var v1 = "白色";
		 	var v2 = "黑色";
		 	var v3 = "金色";
		 	var v4 = "图案";
		 	var v5 = "镶钻";
		 	var v6 = "其他";
		 	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v4+ "' />"+v4+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v5+ "' />"+v5+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v6+ "' />"+v6+"</label><br/>");
	    }else if(desc=="表盘形状:"){
	    	$("#descChild").empty();
	    	document.getElementById("descChild").style.display="";
	    	var v1 = "圆形";
		 	var v2 = "方形";
		 	var v3 = "椭圆形";
		 	var v4 = "酒桶形";
		 	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
	    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='radio' "+" onclick='checkedDesc2(this)' "+"  value='"+v4+ "' />"+v4+"</label><br/>");
	    }else if(desc=="附件:"){
	    	if(goodsCategorySelect==88){
		    	$("#descChild").empty();
		    	document.getElementById("descChild").style.display="";
		    	var v1 = "原盒";
			 	var v2 = "表卡";
			 	var v3 = "发票";
			 	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
	    	}else if(goodsCategorySelect==83){
		    	$("#descChild").empty();
		    	document.getElementById("descChild").style.display="";
		    	var v1 = "身份卡";
			 	var v2 = "原包装盒";
			 	var v3 = "原防尘袋";
			 	var v4 = "无任何包装";
			 	var v5 = "非原厂包装";
			 	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v4+ "' />"+v4+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v5+ "' />"+v5+"</label><br/>");
		    }else if(goodsCategorySelect==87){
		    	$("#descChild").empty();
		    	document.getElementById("descChild").style.display="";
		    	var v1 = "原包装盒";
			 	var v2 = "原防尘袋";
			 	var v3 = "无任何包装";
			 	var v4 = "非原厂包装";
			 	var v5 = "说明书";
			 	var v6 = "保证卡";
			 	var v7 = "其他金属附件";
			 	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v4+ "' />"+v4+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v5+ "' />"+v5+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v6+ "' />"+v6+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v7+ "' />"+v7+"</label><br/>");
		    }else if(goodsCategorySelect==84||goodsCategorySelect==85||goodsCategorySelect==86){
		    	$("#descChild").empty();
		    	document.getElementById("descChild").style.display="";
		    	var v1 = "吊牌";
			 	var v2 = "原包装盒";
			 	var v3 = "原防尘袋";
			 	var v4 = "无任何包装";
			 	var v5 = "非原厂包装";
			 	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v1+ "' />"+v1+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v4+ "' />"+v4+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v5+ "' />"+v5+"</label><br/>");
		    }else if(goodsCategorySelect==89){
		    	$("#descChild").empty();
		    	document.getElementById("descChild").style.display="";
			 	var v2 = "原包装盒";
			 	var v3 = "原防尘袋";
			 	var v4 = "无任何包装";
			 	var v5 = "非原厂包装";
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v2+ "' />"+v2+"</label><br/>"); 
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v3+ "' />"+v3+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v4+ "' />"+v4+"</label><br/>");
		    	$("#descChild").prepend("<label><input "+" name='descChildSex' "+" type='checkbox' "+" onclick='checkedDesc2(this)' "+"  value='"+v5+ "' />"+v5+"</label><br/>");
		    }
	    }
	 }else{
		 var desc= obj.value;
		 if(desc=="附件:"){
			 $("#descChild").empty();
			return;
		}
			
	 }
	 
	 

} 
 

</script>

