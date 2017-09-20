<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">
    <title>我的erp</title>
    <meta name="keywords" content="屹品文汇">
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/css/animate.css"  rel="stylesheet">
    <link href="<%=path%>/css/style.css"  rel="stylesheet">
 
	<script src="<%=path%>/js/jquery.min.js?v=2.1.4"></script>
	<script src="<%=path%>/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="<%=path%>/js/jquery.ocupload-1.1.2.js"></script>
     <script src="<%=path%>/js/plugins/validate/jquery.validate.min.js"></script>
	<link href="<%=path%>/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
     <script src="<%=path%>/js/plugins/metisMenu/jquery.metisMenu.js" ></script>
    <link href="<%=path%>/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <script src="<%=path%>/js/plugins/toastr/toastr.min.js"></script>   
<!--     日期区间选择插件 Data picker
    <link href="/css/plugins/datapicker/datepicker3.css" rel="stylesheet"/>
    <script src="/js/plugins/datapicker/bootstrap-datepicker.js"></script>
 -->
    <!-- 时间日期插件 -->
    <link href="<%=path%>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="<%=path%>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script>
        $(document).ready(function () {
        	 $('.form_datetime').datetimepicker({
        	        language:  'zh-CN',
        	        weekStart: 1,
        	        todayBtn:  1,
        			autoclose: 1,
        			todayHighlight: 1,
        			startView: 2,
        			forceParse: 0,
        	        showMeridian: 1
        	    });
        		$('.form_date').datetimepicker({
        	        language:  'zh-CN',
        	        weekStart: 1,
        	        todayBtn:  1,
        			autoclose: 1,
        			todayHighlight: 1,
        			startView: 2,
        			minView: 2,
        			forceParse: 0
        	    });
        	
        	/* 
            $('.input-daterange').datepicker({
                keyboardNavigation: false,
                forceParse: false,
                format: 'yyyy-mm-dd',
                autoclose: true
            });
 */
            	toastr.options = {
            			  "closeButton": true,
            			  "debug": true,
            			  "progressBar": false,
            			  "positionClass": "toast-top-right",
            			  "showDuration": "400",
            			  "hideDuration": "1000",
            			  "timeOut": "7000",
            			  "extendedTimeOut": "1000",
            			  "showEasing": "swing",
            			  "hideEasing": "linear",
            			  "showMethod": "fadeIn",
            			  "hideMethod": "fadeOut"
            			};
        });
        
        
        function OpenNewTab(url,title){
            var p = '<a href="javascript:;" class="active J_menuTab" data-id="' + url + '">'+title+'<i class="fa fa-times-circle"></i></a>';
            $(".J_menuTab",window.parent.document).removeClass("active");
           var n = '<iframe class="J_iframe"  width="100%" height="100%" src="' + url + '" frameborder="0" data-id="' + url + '" seamless></iframe>';
         $(".J_mainContent",window.parent.document).find("iframe.J_iframe").hide().parents(".J_mainContent").append(n);
            $(".page-tabs .page-tabs-content",window.parent.document).append(p);
        }

    </script>
    <style>
    body{
    background:#fff;}
    </style>
</head>
<body>
