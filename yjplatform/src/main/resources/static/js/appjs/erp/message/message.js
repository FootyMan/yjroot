$().ready(function() {
	var obj = document.getElementById('signupForm');
	obj.onsubmit = function(){
		send();
	}
//	obj.submit();

});

 

function send() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/erp/message/send",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
