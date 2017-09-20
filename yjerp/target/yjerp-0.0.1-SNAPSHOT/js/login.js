$(function(){
	
	//读取物理设备，高度给body添加高度
	var nWinHeight=$(window).height();
	$(window).resize(function(){
		var nWinHeight=$(window).height();
		var nWinHeight=$(window).height();
	});
	$('body').css({
		height:nWinHeight
	});
});
