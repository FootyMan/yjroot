package com.api.utils;

import com.myErp.manager.bean.RangeParameter;

public class PageParameter {

	/**
	 * 附近的人分页设置
	 * 
	 * @param pageIndex
	 * @param userId
	 * @return
	 */
	public static RangeParameter GetRangePage(int pageIndex, int userId) {
		RangeParameter rangeParameter = new RangeParameter();
		rangeParameter.setPageIndex((pageIndex - 1) * PageUtils.PageSize.getValue());
		rangeParameter.setPageSize(PageUtils.PageSize.getValue());
		rangeParameter.setUserId(userId);
		return rangeParameter;
	}
}
