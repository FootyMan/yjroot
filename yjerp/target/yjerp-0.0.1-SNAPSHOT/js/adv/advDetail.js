$(function(){
	//广告位上下架
	$(".advButton").bind("click",function(){
		var advId = $(this).attr("advId");
		var advState = $(this).attr("advState");
		if(advState=='2'){
			var r=confirm("确认删除？")
		    if (r==false){
		 	     return false;
		    }
		}
		$.ajax({
			type:'get',
			async:false,
			url:'/ope/advDetail/updateAdvState?status='+advState+"&id="+advId,
			success:function(data){
				if(data=="1"){
					alert("操作成功！");
					$("#advDetailForm").submit();
				}else{
					alert("操作失败，请重新操作！");
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
	});
	//选择广告  打开方式
	$("#advOpenType").bind("change",function(){
		var advOpenType= $(this).val();
		$("#advLinkInput").val($("#advOpenType_"+advOpenType).attr('protocalUrl'));
		$("#advLinkTypeIdInput").attr("placeholder",$("#advOpenType_"+advOpenType).attr('promptStr'));
	});
	$("#advFormSubmitButton").bind("click",function(){
		$(this).attr("disabled","disabled");
		if($("#advDetailIdInput").val()==''){
			$("#advDetailForm").attr("action","/ope/advDetail/add.do");
		}else{
			$("#advDetailForm").attr("action","/ope/advDetail/update.do");
		}
		$("#advLinkInput").val($("#advLinkInput").val()+$("#advLinkTypeIdInput").val());
//		if(isAdvTimeClash()){
//			alert("广告时间冲突，请重新选择");
//			$(this).removeAttr("disabled");
//		}else{
//			$("#advDetailForm").submit();
//		}
		$("#advDetailForm").submit();
	});
})

function clearSeach(){
	$("#startDateStr").val('');
	$('#endDateStr').val('');
	
}
//上传图片
function uploadFileFun(){
	 $.ajaxFileUpload({  
         url:'/comm/image/upload?imgType=adv',             //需要链接到服务器地址  
         secureuri:false,  
         fileElementId:'uploadFileInput',                         //文件选择框的id属性  
         dataType: 'text',                                     //服务器返回的格式，可以是json  
         success: function (data, status){//相当于java中try语句块的用法
        	 var imgSrc= eval('('+data+')');
        	 $("#advImgUrl").val(imgSrc);
        	 $("#addImgDef").attr('src',imgSrc);
         },  
         error: function (data, status, e){             //相当于java中catch语句块的用法  
         	alert('上传图片失败');  
         }  
     });  
}
//上传分享图片
function uploadShraeFileFun(){
	 $.ajaxFileUpload({  
         url:'/comm/image/upload?imgType=adv',             //需要链接到服务器地址  
         secureuri:false,  
         fileElementId:'uploadShareFileInput',                         //文件选择框的id属性  
         dataType: 'text',                                     //服务器返回的格式，可以是json  
         success: function (data, status){//相当于java中try语句块的用法
        	 var imgSrc= eval('('+data+')');
        	 $("#shareAdvImgUrl").val(imgSrc);
        	 $("#addShareImgDef").attr('src',imgSrc);
         },  
         error: function (data, status, e){             //相当于java中catch语句块的用法  
         	alert('上传图片失败');  
         }  
     });  
}
//广告时间是否冲突
function isAdvTimeClash(){
	var positionIdSelect = $("#positionIdSelect").val();
	var startDateStrInput = $("#startDateStrInput").val();
	var endDateStrInput = $("#endDateStrInput").val();
	var isClash=false;
	$.ajax({
		type:'get',
		async:false,
		url:'/ope/advDetail/isAdvTimeClash?positionId='+positionIdSelect+"&startDateStr="+startDateStrInput+"&endDateStr="+endDateStrInput,
		success:function(data){
			if(data!="0"){
				isClash=true;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			isClash=true;
			alert("网络忙，请重新提交");
//			alert(XMLHttpRequest.status);
//			alert(XMLHttpRequest.readyState);
//			alert(textStatus);
		}
	});
	return isClash;
}

function pageLoadFun(){
	var advLinkVal = $("#advLinkInput").val();
	if(advLinkVal!=""){
		var advOpenType=$("#advOpenType").val();
		var protocalUrl=$("#advOpenType_"+advOpenType+"").attr("protocalUrl");
		var oId = advLinkVal.replace(protocalUrl,'');
		$("#advLinkInput").val(protocalUrl);
		$("#advLinkTypeIdInput").val(oId);
	}
}
window.onload=pageLoadFun;