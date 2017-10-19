package com.api.utils;

import com.service.bean.AppHomePagePaging;
import com.service.bean.RangeParameter;

public class PageParameter {

	/**
	 * 附近的人分页设置
	 * 
	 * @param pageIndex
	 * @param userId
	 * @return
	 */
	public static RangeParameter GetRangePage(int pageIndex, int userId) {
		pageIndex = pageIndex == 0 ? 1 : pageIndex;
		RangeParameter rangeParameter = new RangeParameter();
		rangeParameter.setPageIndex((pageIndex - 1) * PageUtils.PageSize.getValue());
		rangeParameter.setPageSize(PageUtils.PageSize.getValue());
		rangeParameter.setUserId(userId);
		return rangeParameter;
	}

	/**
	 * 首页分页设置
	 * 
	 * @param pageIndex
	 * @param sex
	 * @return
	 */
	public static AppHomePagePaging GetHomePage(int pageIndex, int sex) {
		pageIndex = pageIndex == 0 ? 1 : pageIndex;
		AppHomePagePaging pagePaging = new AppHomePagePaging();
		pagePaging.setPageIndex((pageIndex - 1) * PageUtils.PageSize.getValue());
		pagePaging.setPageSize(PageUtils.PageSize.getValue());
		pagePaging.setGender(sex);
		return pagePaging;
	}

	/**
	 * 获取页数
	 * 
	 * @param dataCount
	 * @param pagesize
	 * @return
	 */
	public static int GetTotalPage(int dataCount, int pagesize) {
		int totalPage = 0;
		if (dataCount % pagesize == 0) {
			totalPage = dataCount / pagesize;
		} else {
			totalPage = dataCount / pagesize + 1;
		}
		return totalPage;
	}
}
