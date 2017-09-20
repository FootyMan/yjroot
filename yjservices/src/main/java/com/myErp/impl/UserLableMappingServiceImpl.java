package com.myErp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.UserLableMappingMapper;
import com.myErp.manager.bean.UserLableMapping;
import com.myErp.utils.DBContextHolder;

@Service("userLableMappingService")
public class UserLableMappingServiceImpl {
	@Autowired
	private UserLableMappingMapper userLableMappingMapper;

	/**
	 * 添加
	 * 
	 * @param userLableMappingMapper
	 * @return
	 */
	public int insertlabletMapping(List<UserLableMapping> userLableMappings) {
		DBContextHolder.setDBType("siteRead");
		return userLableMappingMapper.insertlabletMapping(userLableMappings);
	}

	/**
	 * 查询
	 * 
	 * @param userLableMappingMapper
	 * @return
	 */
	public List<UserLableMapping> selectlabletByUserId(UserLableMapping userLableMapping) {
		DBContextHolder.setDBType("siteRead");
		return userLableMappingMapper.selectlabletByUserId(userLableMapping);
		
	}
}
