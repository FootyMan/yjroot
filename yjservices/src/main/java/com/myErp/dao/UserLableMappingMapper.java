package com.myErp.dao;

import java.util.List;

import com.myErp.manager.bean.UserLableMapping;

public abstract interface UserLableMappingMapper {

	/**
	 * 添加
	 * @param userLableMappingMapper
	 * @return
	 */
	public abstract int insertlabletMapping(List<UserLableMapping> userLableMappings);
	/**
	 * 查询
	 * @param userLableMappingMapper
	 * @return
	 */
	public abstract List<UserLableMapping> selectlabletByUserId(UserLableMapping userLableMapping);

}
