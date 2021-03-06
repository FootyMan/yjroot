package com.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.UserLableMappingMapper;
import com.service.bean.UserLableMapping;
import com.service.utils.DBContextHolder;

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

	/**
	 * 删除
	 * 
	 * @param userLableMappingMapper
	 * @return
	 */
	public int deleteLableByUserId(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userLableMappingMapper.deleteLableByUserId(userId);

	}
}
