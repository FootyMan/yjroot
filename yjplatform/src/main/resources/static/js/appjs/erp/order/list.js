var prefix = "/erp/order"
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
						userNo : $('#userNo').val(),
						orderState: $('#orderState').val(),
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
						field : 'orderNumber', // 列字段名
						title : '订单号', // 列标题
						align : 'center',
					},
					{
						field : 'orderStateName',
						title : '订单状态',
						align : 'center',
					},
					{
						field : 'orderTime', // 列字段名
						title : '下单时间', // 列标题
						align : 'center',
					},
					{
						field : 'orderSourceName',
						title : '订单来源',
						align : 'center',
					},
					{
						field : 'payTypeName',
						title : '支付方式',
						align : 'center',
					},
					{
						field : 'orderPrice',
						title : '支付金额',
						align : 'center',
					},
					{
						field : 'nickName',
						title : '昵称',
						align : 'center',
					},
					{
						field : 'productDesc',
						title : '购买类型',
						align : 'center',
					},
					{
						field : 'title',
						title : '会员类型',
						align : 'center',
					},
					]
			});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}