$().ready(function() {
	var obj = document.getElementById('signupForm');
	obj.onsubmit = function(){
		save();
	}
//	obj.submit();

});

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

function SetUserLevel(userId,type){
	var httpUrl = '../user/setLevel?userId='+userId+'&type='+type;
	$.ajax({
		type:'get',
		async:false,
		url:httpUrl,
		success:function(data){
			if(data>=1){
				alert("设置成功！");
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

//上传图片
function uploadFileFun() {
	$.ajaxFileUpload({
				url : '/image/upload?controlName=uploadFileInput', // 需要链接到服务器地址
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
				url : '/image/upload?controlName=uploadFileInputHead', // 需要链接到服务器地址
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
 


//禁用-启用
function SetisDisable(userId,type){
	var httpUrl = '../user/setisDisable?userId='+userId+'&type='+type;
	$.ajax({
		type:'get',
		async:false,
		url:httpUrl,
		success:function(data){
			if(data>=1){
				alert("设置成功！");
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

function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/erp/member/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
