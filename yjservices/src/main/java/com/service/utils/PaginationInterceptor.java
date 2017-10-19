package com.service.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PaginationInterceptor extends HandlerInterceptorAdapter
{
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception
  {
    String pageSize = request.getParameter("page.pageSize");
    String pageNo = request.getParameter("page.pageNo");
    Pagination pagination = new Pagination();

    if ("0".equals(pageNo)) {
      pagination.setPageNo(0);
    }
    if (pageSize != null) {
      pagination.setPageSize(Integer.parseInt(pageSize));
      pagination.setPageNo(Integer.parseInt(pageNo));
    }
    Pagination.threadLocal.set(pagination);
    return true;
  }

  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    throws Exception
  {
    super.postHandle(request, response, handler, modelAndView);
    Pagination pagination = (Pagination)Pagination.threadLocal.get();
    if (pagination != null) {
      request.setAttribute("page", pagination);

      Pagination.threadLocal.remove();
    }
  }
}