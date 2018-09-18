var prefix = "/erp/financia"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
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
						operateStatus : $('#operateStatus').val(),
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
						field : 'userNo',
						title : '用户No',
						align : 'center',
					},
					{
						field : 'nickName',
						title : '昵称',
						align : 'center',
					},
					{
						field : 'financialMoney',
						title : '提现金额',
						align : 'center',
					},
					{
						field : 'payAccount',
						title : '收款账号',
						align : 'center',
					},
					{
						field : 'payRealName',
						title : '收款人',
						align : 'center',
					},
					{
						field : 'totalMoney',
						title : '账户剩余金额',
						align : 'center',
					},
					{
						field : 'operateDate',
						title : '提现日期',
						align : 'center',
					},
					{
						field : 'operateStatusName',
						title : '状态',
						align : 'center',
					},
					{
						 title : '操作',
						 field : 'id',
						 align : 'center',
						 formatter : function(value, row, index) {
							 var e="";
							 var d="";
							 if(row.operateStatus==1)
							 {
								 d='<a  class="btn btn-primary btn-sm" href="https://auth.alipay.com/login/index.htm"  title="去打款" )" target="_Blank"><i class="fa fa-jpy"></i></a> ';
								 
								 e= '<a  class="btn btn-warning btn-sm ' + s_set_h + '" href="#" mce_href="#" title="标记已打款" onclick="set(\''
								 + row.recordID
								 + '\')"><i class="fa fa-money"></i></a> ';
								 return e+d;
							}
						 
					}}
					]
			});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}


//设置已打款
function set(id){
	var httpUrl = prefix+'/set/'+id;
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

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
}