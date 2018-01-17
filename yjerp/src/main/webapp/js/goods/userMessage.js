/**
 * 用户短信
 */


	 
	


/**
 * 设置显示不显示手机号
 * @returns
 */
function SetSelect(){
	var type=$("#userType").find("option:selected").val();  //获取Select选择的Text
	 alert(type);
	if(type=="1")
	{
		$("#divPhone").show();
	}
	else
	{
		$("#divPhone").hide();
	}
 
}

//发送短信
function SendMessage(){
	
	var content=$("#msgContent").val();
	var phone=$("#phone").val();
	if(content=="")
	{
		alert("短信内容为空");
		return;
	}
	var type=$("#userType").find("option:selected").val();  //获取Select选择的Text
	var httpUrl = '../user/sendMessage?phone='+phone+'&type='+type+'&msg='+content;
	$.ajax({
		type:'get',
		async:false,
		url:httpUrl,
		success:function(data){
			if(data>=1){
				alert("发送成功！");
			} 
			else
			{
				alert("发送失败")
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert("网络异常");
			
		}
	});
}

  
