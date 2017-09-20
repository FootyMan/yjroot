package com.myErp.utils;

import java.util.List;

public class Pagination extends BasePage
{
  private static final long serialVersionUID = 5729482538591310351L;
  public static ThreadLocal<Pagination> threadLocal = new ThreadLocal();
  private List<?> list;

  public Pagination()
  {
  }

  public Pagination(int pageNo, int pageSize, int totalCount)
  {
    super(pageNo, pageSize, totalCount);
  }

  public Pagination(int pageNo, int pageSize, int totalCount, List<?> list)
  {
    super(pageNo, pageSize, totalCount);
    this.list = list;
  }

  public List<?> getList()
  {
    return this.list;
  }

  public void setList(List<?> list)
  {
    this.list = list;
  }
}