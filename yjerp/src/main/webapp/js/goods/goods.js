//$(function(){
//	//#############商品列表页
//	//选择商品id  商品名称
//	$(".selectGoods").bind("click",function(){
//		var thisSelect = $(this).html();
//		$("#selectGoodsButton").html(thisSelect+'<span class="caret"></span>');
//		$("#selectGoodsInput").attr("name",$(this).attr("val"));
//		$("#selectGoodsInput").val('');
//	});
//	//选择用户id  用户名称
//	$(".selectSeller").bind("click",function(){
//		var thisSelect = $(this).html();
//		$("#selectSellerButton").html(thisSelect+'<span class="caret"></span>');
//		$("#selectSellerInput").attr("name",$(this).attr("val"));
//		$("#selectSellerInput").val();
//	});
//	//根据 商品 状态搜索
//	$(".selectGoodsState").bind("click",function(){
//		var stateVal = $(this).attr("stateVal");
//		$("#goodsState").val(stateVal);
//		$("#goodsForm").submit();
//	});
//	//根据 商品分类搜索
//	$(".selectGoodsCateId").bind("click",function(){
//		var stateVal = $(this).attr("cateIdVal");
//		$("#cateId").val(stateVal);
//		$("#goodsForm").submit();
//	});
//	// 点击提交表单
//	$(".goodsFormSub").bind("click",function(){
//		$(this).attr("disabled","disabled");
//		$("#goodsForm").submit();
//	});
//	//审核
//	$(".goodsUpButton").bind("click",function(){
//		var goodsId = $(this).attr("goodsId");
//		var goodsState = $(this).attr("goodsState");
//		$.ajax({
//			type:'get',
//			async:false,
//			url:'/ope/goodsCheck/updateGoodsState?id='+goodsId+"&goodsState="+goodsState,
//			success:function(data){
//				if(data=="1"){
//					alert("操作成功！");
//					$("#goodsForm").submit();
//				}
//				else if(data=="-1"){					 
//					alert("此产品存在关闭的订单，请先取消此产品的订单再上架！商品ID:"+goodsId);
//				}
//				else{
//					alert("操作失败，请重新操作！商品ID:"+goodsId);
//				}
//			},
//			error:function(XMLHttpRequest, textStatus, errorThrown){
////				alert(XMLHttpRequest.status);
////				alert(XMLHttpRequest.readyState);
////				alert(textStatus);
//				alert("网络异常，正在重新提交！商品ID:"+goodsId);
//				$(".goodsUpButton").click();
//			}
//		});
//	});
//	//############新增商品
//	//商品类型切换
//	$(".goodsTypeTab").bind("click",function(){
//		$(".goodsTypeTab").css('background-color','transparent');
//		$(this).css('background-color','#1ab394');
//		$("#isGloveInput").val($(this).attr('goodsType'));
//	});
//	//选择是否关联历史商品
//	$("#isOldGood").bind("click",function(){
//		 if($(this).hasClass('btn-default')){
//			 $(this).removeClass('btn-default');
//			 $(this).addClass('btn-info');
//			 $("#oldGoodsIdInputDiv").html(' <input type="text" id="oldGoodsIdInput" name="oldGoodsId" class="form-control"  placeholder="请输入关联商品的商品ID">');
//			 $("#oldGoodsIdImportDiv").html(' <button id="importOldGoddsById" class="btn btn-primary btn-sm" type="button">导入</button>');
//		 }else{
//			 $(this).removeClass('btn-info');
//			 $(this).addClass('btn-default');
//			 $("#oldGoodsIdInputDiv").html('');
//			 $("#oldGoodsIdImportDiv").html('');
//		 }
//	});
//	// 商品品牌  系列二级菜单联动
//	$("#brandIdSelect").change(function(){
//		var brandSeriesId = $("#brandSeriesInput").val();
//		$.ajax({
//			type:'get',
//			async:false,
//			url:'/ope/brandSeries/selectBrandSeriesById?brandId='+$(this).val(),
//			success:function(data){
//				$("#brandSeriesIdSelect").empty();
//				var list = $.parseJSON(data);
//				if(list.length==0){
//					//alert("当前品牌无品牌系列，请重新选择！");
//				}
//				for(var i=0;i<list.length;i++){
//					var optionSelectedStr ="";
//					if(brandSeriesId==list[i].seriesId){
//						optionSelectedStr="selected='selected'"
//					}
//					$("#brandSeriesIdSelect").prepend("<option "+optionSelectedStr+" value='"+list[i].seriesId+"'>"+list[i].seriesName+"</option>");	
//				}
//			}
//		});
//	});
//	
//	// 选择商品品牌   后  联动商品系列
//	$("#goodsCategorySelect").change(function(){
//		var subCateId = $("#subCateIdInput").val();
//		$.ajax({
//			type:'get',
//			async:false,
//			url:'/ope/goodsCategory/getListByPid?pid='+$(this).val(),
//			success:function(data){
//				$("#PgoodsCategorySelect").empty();
//				$("#PgoodsCategorySelect").prepend('<option value="0">请先选择一级类目</option>');
//				var list = $.parseJSON(data);
//				for(var i=0;i<list.length;i++){
//					var optionSelectedStr ="";
//					if(list[i].state>=0){
//						if(subCateId==list[i].id){
//							optionSelectedStr="selected='selected'"
//						}
//						$("#PgoodsCategorySelect").prepend("<option "+optionSelectedStr+" value='"+list[i].id+"'>"+list[i].name+"</option>");	
//					}
//				}
//			}
//		});
//	});
//	
//	//选择成色
//	$(".usageIdSelect").bind("click",function(){
//		$(".usageIdSelect").removeClass('active');
//		$(this).addClass('active');
//		$("#usageIdInput").val($(this).attr('usageState'));
//	});
//	//选择劵
//	$(".bonusButton").bind("click",function(){
//		if($(this).hasClass('btn-info')){
//			$(this).removeClass('btn-info');
//			$(this).addClass('btn-default');
//			$("#bonusInput").val('');
//		}else{
//			$(".bonusButton").removeClass('btn-info');
//			$(this).removeClass('btn-default');
//			$(this).addClass('btn-info');
//			$("#bonusInput").val($(this).attr('val'));
//		}
//	});
//	//选择配件
//	$(".goodsAttachmentSelect").bind("click",function(){
//		var goodsAttachment = $("#goodsAttachmentInput").val();
//		if($(this).hasClass('btn-info')){
//			$(this).removeClass('btn-info');
//			$(this).addClass('btn-default');
//			$("#goodsAttachmentInput").val(goodsAttachment.replace($(this).attr("val")+"|",""));
//		}else{
//			$(this).removeClass('btn-default');
//			$(this).addClass('btn-info');
//			goodsAttachment+=$(this).attr("val")+"|";
//			$("#goodsAttachmentInput").val(goodsAttachment);
//		}
//	});
//	
//	//选择标签
//	$(".goodsLableSelect").bind("click",function(){
// 
//		var lableValue= $(this).attr("val");
//		
//		if($(this).hasClass('btn-info')){
//			if(lableValue=="10"||lableValue=="11"||lableValue=="12")
//			{		
//			   alert("系统默认标签，不能去除");
//			   return;			
//			}	
//			$(this).removeClass('btn-info');
//			$(this).addClass('btn-default');
//		}else{
//			if(lableValue=="10"||lableValue=="11"||lableValue=="12")
//			{ 
//				 alert("系统默认标签，不能选择");
//				 return;			
//			}	
//			$(this).removeClass('btn-default');
//			$(this).addClass('btn-info');
//		}
//	});
//	//导入历史商品
//	$("#oldGoodsIdImportDiv").bind("click",function(){
//		$.ajax({
//			type:'get',
//			async:false,
//			url:'/ope/goods/importOldGoods?goodsId='+$("#oldGoodsIdInput").val(),
//			success:function(data){
//				var goods = $.parseJSON(data);
//				$("#goodsNameInput").val(goods.goodsName);
//				$("#salePriceInput").val(goods.salePrice);
//				$("#purchasePriceInput").val(goods.purchasePrice);
//				$("#brandIdSelect [value='"+goods.brandId+"']").attr("selected","selected");
//				$("#goodsCategorySelect [value='"+goods.cateId+"']").attr("selected","selected");
//				$("#goodsCategorySelect").change();
//				$("#PgoodsCategorySelect [value='"+goods.subCateId+"']").attr("selected","selected");
//				$("[usageState='"+goods.usageId+"']").addClass("active")
//				
//				$("#colorInput").val(goods.attrList[0].attrValue);
//				$("#sizeInput").val(goods.attrList[1].attrValue);
//				$("#bonusInput").val(goods.attrList[2].attrValue);
//
//				$(".bonusButton[val="+goods.attrList[2].attrValue+"]").removeClass('btn-default');
//				$(".bonusButton[val="+goods.attrList[2].attrValue+"]").addClass('btn-info');
//				$("#goodsAttachmentInput").val(goods.attrList[3].attrValue);
//				var goodsAttachmentSelects=$(".goodsAttachmentSelect");
//				for (var int = 0; int < goodsAttachmentSelects.length; int++) {
//					if(goods.attrList[3].attrValue.indexOf($(goodsAttachmentSelects[int]).attr('val'))>=0){
//						$(goodsAttachmentSelects[int]).addClass('btn-info');
//						$(goodsAttachmentSelects[int]).removeClass('btn-default');
//					}
//				}
//			}
//		});
//	});
//	//商品新增 修改 提交
//	$(".addGoodsFormSubmitButton").bind("click",function(){
//		var otherRemark = $("#divRemarksInput").val();
//		if(otherRemark!=null && otherRemark!=''){
//			$("#remarksInput").val(otherRemark);
//		}
//		
//		 var  goodsLable=$('.goodsLableSelect.btn-info').map(function(){
//				return $(this).attr("val");
//			}).get().join(",");
//		$("#goodsLable").val(goodsLable);
//		
//		$(this).attr("disabled","disabled");
//		$("#goodsStateInput").val($(this).attr("goodsState"));
//		if($("#goodsIdInput").val()==''){
//			$("#addGoodsForm").attr("action","/ope/goods/add.do");
//		}else{
//			$("#addGoodsForm").attr("action","/ope/goods/update.do");
//		}
// 
//		var pbizType=  $("#pbizType").val();
//		if(pbizType==2)
//		{//闲置才验证
//			var resultVal = formValidate();
//			if(resultVal!=1){
//				alert(resultVal);
//				$(this).removeAttr('disabled');
//				return false;
//			}
//		}
//		//$("#addGoodsForm").submit();
//		 $('#addGoodsForm').ajaxSubmit({
//			 dataType:"text",
//			 error: function(XmlHttpRequest, textStatus, errorThrown){  
//                 alert( "error");  
//             } , 
//			 success: function (data) {
//				 if(data.indexOf('/ope/goods/list')>=0){
//					 window.location.href="/ope/goods/list"
//				 }else if (data.indexOf('/ope/goodsCheck/list')>=0){
//					 window.location.href="/ope/goodsCheck/list"
//				 }else if (data.indexOf('/ope/goodsCheck/list')>=0){
//					 window.location.href="/ope/goodsCheck/list"
//				 }else{
//					 alert(data);
//					  alert("系统错误，请稍后再试！");  
//					  $('[disabled]').removeAttr('disabled');
//				 }
//			 	
//		     }
//		 });
//	});
//	//################商品审核######################################
//	// 点击提交表单
//	$(".goodsCheckFormSub").bind("click",function(){
//		$("#goodsCheckForm").submit();
//	});
//	//切换tab
//	$(".selectCheckTab").bind("click",function(){
//		$("#tabTypeInput").val($(this).attr('tabType'));
//		$("#goodsCheckForm").submit();
//	});
//	//审核
//	$(".goodsCheckButton").bind("click",function(){
//		var goodsId = $(this).attr("goodsId");
//		var goodsState = $(this).attr("goodsState");
//		updateGoodsState(goodsId,goodsState,null,null,null);
//		
//		$(".goodsID"+goodsId).remove();
//		 
//		//$("#goodsCheckForm").submit();
//	});
//	//审核驳回弹框
//	$(".goodsRejectReasonButton").bind("click",function(){
//		var goodsId = $(this).attr("goodsId");
//		var userId = $(this).attr("userId");
//		$("#userIdInput").val(userId);
//		var goodsState = $(this).attr("goodsState");
//		var goodsTitle = $(this).attr("goodsTitle");
//		$("#rejectGoodsId").val(goodsId);	
//		$("#rejectGoodsState").val(goodsState);	
//		$("#rejectGoodsTitle").val(goodsTitle);
//	});
//	//选择驳回理由
//	$("#reasonSelect").bind("change",function(){
//		if($(this).val()=='-1'){
//			$("#otherReasonDiv").css("display","block");
//		}else{
//			$("#otherReasonDiv").css("display","none");
//		}
//	});
//	//提交驳回理由
//	$("#saveGoodsRejectReason").bind("click",function(){
//		$("#goodsRejectReason").css("display","none");
//		var goodsId = $("#rejectGoodsId").val();	
//		var userId = $("#userIdInput").val();
//		var goodsState = $("#rejectGoodsState").val();	
//		var goodsTitle = $("#rejectGoodsTitle").val();	
//		var rejectReason;
//		if($("#reasonSelect").val()=='-1'){
//			rejectReason=$("#otherRejectReasonInput").val();
//		}else{
//			rejectReason=$("#reasonSelect").val();
//		}
//		updateGoodsState(goodsId,goodsState,rejectReason,userId,goodsTitle);
//	});
//	
//	$("#reasonSelectToRemarks").bind("change",function(){
//		var reasonInput  = $(this).val();
//		$("#remarksInput").val(reasonInput);
//		if($(this).val()=='-1'){
//			$("#otherReasonDiv").css("display","block");
//		}else{
//			$("#otherReasonDiv").css("display","none");
//		}
//	});
//	
//	//根据 商品分类搜索
//	$(".selectGoodsCateId").bind("click",function(){
//		var stateVal = $(this).attr("cateIdVal");
//		$("#cateId").val(stateVal);
//		$("#goodsCheckForm").submit();
//	});
//	//######白手套审核##############
//	//修改鉴定价格弹框
//	$(".gloveCheckPriceButton").bind("click",function(){
//		var barCode = $(this).attr("glovecheckbarcode");
//		$("#gloveCheckBarCodeInput").val(barCode);
//	});
//	//修改鉴定价格保存
//	$("#checkPriceSaveButton").bind("click",function(){
//		$("#gloveCheckPrice").css("display","none");
//		var barCode = $("#gloveCheckBarCodeInput").val();
//		var checkPrice=$("#checkPriceInput").val();
//		$("#checkPriceInput").val("");
//		$.ajax({
//			type:'post',
//			async:false,
//			url:'/scm/goodsCheckup/updateCheckPrice?barCode='+barCode+"&checkUpPrice="+checkPrice,
//			success:function(data){
//				if(data=="1"){
//					alert("修改成功！");
//					location.reload();
//				}else{
//					alert("修改失败，请重新修改！商品barCode:"+barCode);
//				}
//			},
//			error:function(XMLHttpRequest, textStatus, errorThrown){
////				alert(XMLHttpRequest.status);
////				alert(XMLHttpRequest.readyState);
////				alert(textStatus);
//				alert("网络异常，正在重新提交！商品barCode:"+barCode);
//				$("#checkPriceSaveButton").click();
//			}
//		});
//	});
//	
//	$("#testButton").bind("click",function(){
//		var img = document.createElement("img");
//		img.setAttribute("width", "100px");
//    	img.setAttribute("height", "100px");
//		img.src = "/tmp/goods/14634875939874559.png";
//		$("#uploadFileInput").after(img);
//	});
//})
//设置首页用户
function SetHomeUser(userId,type){
	var httpUrl = '../user/setHome?userId='+userId+'&type='+type;
	$.ajax({
		type:'get',
		async:false,
		url:httpUrl,
		success:function(data){
			if(data>=1){
				alert("设置成功！");
				// $(".goodsID"+goodsId).remove();
				location.reload();
			}else if(data==-1){
				alert("改用户首页已存在");
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

//上传图片
function uploadFileFun() {
	$.ajaxFileUpload({
				url : '../image/upload?controlName=uploadFileInput', // 需要链接到服务器地址
				secureuri : false,
				fileElementId : 'uploadFileInput', // 文件选择框的id属性
				dataType : 'text', // 服务器返回的格式，可以是json
				success : function(data, status) {// 相当于java中try语句块的用法

					var imgSrc = data;// eval('('+data+')');
					var imgSort = $("#uploadFileInput").attr('isUpdate');
					if (imgSort != "-1") {// 图片新增
						$("[updateImgTag='img_src_" + imgSort + "']").attr('src', imgSrc);
						$("[updateImgTag='img_src_input_" + imgSort + "']").val(imgSrc);
						$("#uploadFileInput").attr("isUpdate", "-1");
					} else {
						var img = document.createElement("img");
						img.setAttribute("width", "100px");
						img.setAttribute("height", "100px");
						img.setAttribute("updateImgTag", "img_src_"+ $('.goodsImg').length);// 修改图片
						img.setAttribute("onclick", "updateImg("+ $('.goodsImg').length + ")");
						img.src = imgSrc;
						$("#addImgDef").before(img);
						$("#addImgDef").before('<input type="hidden"  name="imgList['+ $('.goodsImg').length+ '].sortOrder" value="'+ ($('.goodsImg').length + 1) + '"/>');
						$("#addImgDef").before('<input type="hidden" updateImgTag="img_src_input_'+ $('.goodsImg').length+ '" class="goodsImg"  name="imgList['+ $('.goodsImg').length+ '].imgUrl" value="' + imgSrc + '"/>');
					}
				},
				error : function(data, status, e) { // 相当于java中catch语句块的用法
					alert('上传图片失败');
				}
			});
}
// 修改图片
function updateImg(imgSort) {
	$("#uploadFileInput").attr("isUpdate", imgSort);
	$("#uploadFileInput").click();
}

// 上传头像
function uploadFileFunHead() {
	$.ajaxFileUpload({
				url : '../image/upload?controlName=uploadFileInputHead', // 需要链接到服务器地址
				secureuri : false,
				fileElementId : 'uploadFileInputHead', // 文件选择框的id属性
				dataType : 'text', // 服务器返回的格式，可以是json
				success : function(data, status) {// 相当于java中try语句块的用法
					var imgSrc = data;// eval('('+data+')');
					$("#addImgDefHead").attr("src",imgSrc);
					var imgSort = $("#uploadFileInputHead").attr('isUpdate');
					$("#headImage").attr("value",imgSrc);
//					if (imgSort != "-1") {// 图片新增
//						$("[updateImgTag='img_src_" + imgSort + "']").attr('src', imgSrc);
//						$("[updateImgTag='img_src_input_" + imgSort + "']").val(imgSrc);
//						$("#uploadFileInputHead").attr("isUpdate", "-1");
//					} else {
//						var img = document.createElement("img");
//						img.setAttribute("width", "100px");
//						img.setAttribute("height", "100px");
//						img.setAttribute("updateImgTag", "img_src_"+ $('.goodsImg').length);// 修改图片
//						img.setAttribute("onclick", "updateImgHead("+ $('.goodsImg').length + ")");
//						img.src = imgSrc;
//					}
				},
				error : function(data, status, e) { // 相当于java中catch语句块的用法
					alert('上传图片失败');
				}
			});
}
// 修改图片
function updateImgHead(imgSort) {
	$("#uploadFileInputHead").attr("isUpdate", imgSort);
	$("#uploadFileInputHead").click();
}
// //商品修改时 页面完成加载 自动联动二级 类目
// function pageLoadFun(){
// if($("#goodsIdInput").val()!=""){
// /*alert(1);*/
// $("#goodsCategorySelect").change();
// $("#brandIdSelect").change();
// }
// }
// window.onload=pageLoadFun;
