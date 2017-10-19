package com.service.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.service.bean.Employee;
import com.service.bean.Role;
import com.service.bean.SessionEmp;

@Service
public class SessionEmpManager
{
  private SessionEmp sessionEmp;

  public static SessionEmp getsessionEmp(HttpServletRequest request)
  {
    return (SessionEmp)request.getSession().getAttribute("xsUserSession");
  }

  public static void setSessionEmp(HttpServletRequest request, Employee user, Role role) {
    request.getSession().removeAttribute("xsUserSession");
    request.getSession().setAttribute("xsUserSession", new SessionEmp(user, role));
  }

  public static void updateSessionEmp(HttpServletRequest request, Employee user, Role role) {
    request.getSession().setAttribute("xsUserSession", new SessionEmp(user, role));
  }
  public static void removeSessionEmp(HttpServletRequest request) {
    request.getSession().removeAttribute("xsUserSession");
    request.getSession().invalidate();
  }
}