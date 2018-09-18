var prefix = "/erp/member"
$(function() {
	selectLoad();
	load();
});

function load() {
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : prefix + "/list", // 服务器数据的加载地址
		// showRefresh : true,
		// showToggle : true,
		// showColumns : true,
		iconSize : 'outline',
		toolbar : '#exampleToolbar',
		striped : true, // 设置为true会有隔行变色效果
		dataType : "json", // 服务器返回的数据类型
		pagination : true, // 设置为true会在底部显示分页条
		// queryParamsType : "limit",
		// //设置为limit则会发送符合RESTFull格式的参数
		singleSelect : false, // 设置为true将禁止多选
		// contentType : "application/x-www-form-urlencoded",
		// //发送到服务器的数据编码类型
		pageSize : 10, // 如果设置了分页，每页数据条数
		pageNumber : 1, // 如果设置了分布，首页页码
		// search : true, // 是否显示搜索框
		showColumns : false, // 是否显示内容下拉框（选择显示的列）
		sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
		// "server"
		queryParams : function(params) {
			return {
				// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
				limit : params.limit,
				offset : params.offset,
				userNo : $('#userNo').val(),
				phone : $('#phone').val(),
				nickName : $('#nickName').val(),
				gender : $('#gender').val()
			};
		},
		// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
		// queryParamsType = 'limit' ,返回参数必须包含
		// limit, offset, search, sort, order 否则, 需要包含:
		// pageSize, pageNumber, searchText, sortName,
		// sortOrder.
		// 返回false将会终止请求

		columns : [ 
			{
				checkbox : true
			},
			{
				field : 'userNo', // 列字段名
				title : '用户Id',// 列标题
				align : 'center'
			},
			{
				field : 'nickName',
				title : '昵称',
				align : 'center'
			},
			{
				field : 'headImage',
				title : '头像',
				align : 'center',
				formatter : function(value, row, index) {
					return '<img widht="30" height="30" src='+value+'>';
				}
			}, 
			{
				field : 'phone',
				title : '手机号',
				align : 'center'
		}, {
			field : 'userLevel',
			title : '级别',
			align : 'center'
		}, {
			field : 'sex',
			title : '性别',
			align : 'center',
			formatter : function(value, row, index) {
				if (value == '1') {
					return '<span class="label label-danger">男</span>';
				} else if (value == '2') {
					return '<span class="label label-primary">女</span>';
				}
			}
		}, {
			field : 'sexuat',
			title : '性取向',
			align : 'center'
		}, {
			field : 'isDisable',
			title : '状态',
			align : 'center',
			formatter : function(value, row, index) {
				if (value == '1') {
					return '<span class="label label-primary">正常</span>';
				} else {
					return '<span class="label label-danger">禁用</span>';
				}
			}
		}, {
			field : 'deviceTypeName',
			title : '设备类型',
			align : 'center'
		},
		{
			field : 'registerTime',
			title : '注册时间',
			align : 'center'
		},
		 {
		 title : '操作',
		 field : 'id',
		 align : 'center',
		 formatter : function(value, row, index) {
			var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
				+ row.userId
				+ '\')"><i class="fa fa-edit "></i></a> ';
			var f = '<a class="btn btn-success btn-sm ' + s_setmember_h + '" href="#" title="设置会员"  mce_href="#" onclick="setUserLevel(\''
				+ row.userId
				+ '\',1)"><i class="fa fa-user-plus"></i></a> ';
			if(row.userLevelId>1)
			{
				//取消会员
				f = '<a class="btn  btn-danger btn-sm ' + s_setmember_h + '" href="#" title="取消会员"  mce_href="#" onclick="setUserLevel(\''
				+ row.userId
				+ '\',2)"><i class="fa fa-user-times"></i></a> ';
			}
			if(row.isDisable=='1')
			{
				var g = '<a class="btn btn-warning btn-sm ' + s_disable_h + '" href="#" title="禁用"  mce_href="#" onclick="setisDisable(\''
				+ row.userId
				+ '\',0)"><i class="fa fa-times-circle-o"></i></a> ';
			}
			else{
				g = '<a class="btn btn-primary btn-sm ' + s_disable_h + '" href="#" title="启用"  mce_href="#" onclick="setisDisable(\''
				+ row.userId
				+ '\',1)"><i class="fa fa-check-circle"></i></a> ';
			}
			

			var h = '<a class="btn btn-success btn-sm ' + s_info_h + '" href="#" title="会员信息"  mce_href="#" onclick="info(\''
			+ row.userId
			+ '\')"><i class="fa fa-male"></i></a> ';
			return e+f+g+h;
		 }
		 }
		]
	});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['userId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {
	});
}


function selectLoad() {
	var html = "";
			// 加载数据
// for (var i = 0; i < data.length; i++) {
// html += '<option value="' + data[i].type + '">' + data[i].description +
// '</option>'
// }
			html += '<option value="1">普通会员</option>';
			html += '<option value="2">月度会员</option>';
			html += '<option value="3">半年会员</option>';
			html += '<option value="4">年度会员</option>';
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			// 点击事件
			$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						userLevel : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
}

//编辑用户初始化
function edit(id) {
	layer.open({
		type : 2,
		title : '用户修改',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
//设置首页用户
function setHome() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要添加首页的用户");
		return;
	}
	layer.confirm("确认要将'" + rows.length + "'条数据添加首页?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['userId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/sethome',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}


//设置用户级别
function setUserLevel(userId,type){
	var httpUrl = prefix+'/setmember/'+userId+'/'+type;
	$.ajax({
		type:'get',
		url:httpUrl,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				reLoad();
//				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
//				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
}

//禁用-启用
function setisDisable(userId,type){
	var httpUrl = prefix+'/disable/'+userId+'/'+type;
	$.ajax({
		type:'get',
		async:false,
		url:httpUrl,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				reLoad();
//				parent.reLoad();
//				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
//				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
}

//编辑用户初始化
function info(id) {
	layer.open({
		type : 2,
		title : '用户详情',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/info/' + id // iframe的url
	});
}
//function memberLevelName(level)
//{
//	var levelName="普通会员";
//	switch(level)
//	{
//		case 1:
//			levelName="普通会员";
//			break;
//		case 2:
//			levelName="包月会员";
//			break;
//		case 3:
//			levelName="半年会员";
//			break;
//		case 4:
//			levelName="年费会员";
//			break;
//		default:
//			levelName="普通会员";
//	}
//	return levelName;
//}