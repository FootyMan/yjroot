<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <% SessionEmp sEmp=(SessionEmp)session.getAttribute(GlobalsConstant.EMP_SESSION_KEY); %> --%>
<body>
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="/img/profile_small.jpg" />
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#" >
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${sessionScope.xsUserSession.employee.empName}</strong>
                             </span>  <span class="text-muted text-xs block">${sessionScope.xsUserSession.role.roleName} <b class="caret"></b></span> </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                             <li><a href="/self/uppass" >修改密码</a>
                                </li>
                                 <!--   <li><a href="profile.html" >个人资料</a>
                                </li> -->
                                <li class="divider"></li>
                                <li><a href="/accounts/logout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">	心上  </div>

                    </li>
<%--                     <% --%>
//                       int roleId=sEmp.getRole().getId();
<%--                     if(GlobalsConstant.SUPER_ROLE==roleId){ %> --%>
                    <li>
                        <a href="/main#" ><i class="fa fa-th-large"></i> <span class="nav-label">人力管理</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="/sys/role/list" >员工管理</a>
                            </li>
                            <li><a href="/sys/roleFunc/list" >角色权限</a>
                            </li>
                            <li><a href="/sys/func/list" >函数</a>
                            </li>
                            <li><a href="/sys/config/list" >系统配置</a>
                            </li>
                              <li><a href="/sys/log/list" >系统日志</a>
                            </li>
                        </ul>
                    </li>
<%--                     <%} %> --%>
<%--                     <% --%>
               
<!--                     HashMap<String,List<SysRoleFunc>> menuMap=GlobalsKey.roleAuthMap.get(roleId); -->
<!--                       for(String bizCode:menuMap.keySet()){ -->
<!--                     %> -->
                      <li>
                        <a href="/main#" ><i class="fa fa-th-large"></i><span class="nav-label">
<%--                         <%=GlobalsKey.bizCode.get(bizCode)%> --%>
                        </span> <span class="fa arrow"></span></a>
                    	 <ul class="nav nav-second-level">
<%--                     	<%  --%>
//                     	List<SysRoleFunc> subMenuList=menuMap.get(bizCode);
//                     	  for(SysRoleFunc func: subMenuList) {
//                     		 if(func.getIsMenu()!=null&&func.getIsMenu()==1){
<%--                     	%> --%>
<%--                     	<li><a href="<%=func.getFuncUrl()%>"><%=func.getFuncName()%></a> </li> --%>
<%--                     	<%}  --%>
<%--                     		 }%> --%>
                    	</ul>
                      </li>
<%--                     <%} %> --%>
                
                    </ul>
				</div>
                </nav>
            </div>