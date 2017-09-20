package com.myErp.utils;

import java.io.Serializable;

public abstract class BasePage
  implements Serializable, IPage
{
  private static final long serialVersionUID = 2286258500479017476L;
  public static final int DEF_COUNT = 10;
  private int pageSize = 10;
  private int currentResult;
  private int totalPage;
  private int pageNo = 1;
  private int totalCount;

  public void setPageNo(int pageNo)
  {
    this.pageNo = pageNo;
  }

  public BasePage(int pageNo, int pageSize, int totalCountt)
  {
    this.pageNo = pageNo;
    this.pageSize = pageSize;
    this.totalCount = totalCountt;
  }

  public int getTotalCount()
  {
    return this.totalCount;
  }

  public void setTotalCount(int totalCount)
  {
    if (totalCount < 0)
      this.totalCount = 0;
    else
      this.totalCount = totalCount;
  }

  public BasePage()
  {
  }

  public int getFirstResult()
  {
    return (this.pageNo - 1) * this.pageSize;
  }

  public void setPageSize(int pageSize)
  {
    if (pageSize < 1)
      this.pageSize = 10;
    else
      this.pageSize = pageSize;
  }

  public int getTotalPage()
  {
    if (this.totalPage <= 0) {
      this.totalPage = (this.totalCount / this.pageSize);
      if ((this.totalPage == 0) || (this.totalCount % this.pageSize != 0)) {
        this.totalPage += 1;
      }
    }
    return this.totalPage;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public int getPageNo()
  {
    return this.pageNo;
  }

  public boolean isFirstPage()
  {
    return this.pageNo <= 1;
  }

  public boolean isLastPage()
  {
    return this.pageNo >= getTotalPage();
  }

  public int getNextPage()
  {
    if (isLastPage()) {
      return this.pageNo;
    }
    return this.pageNo + 1;
  }

  public int getCurrentResult()
  {
    this.currentResult = ((getPageNo() - 1) * getPageSize());
    if (this.currentResult < 0)
      this.currentResult = 0;
    return this.currentResult;
  }

  public int getPrePage() {
    if (isFirstPage()) {
      return this.pageNo;
    }
    return this.pageNo - 1;
  }
}