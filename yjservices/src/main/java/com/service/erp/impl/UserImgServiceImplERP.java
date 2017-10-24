package com.service.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.bean.UserImg;
import com.service.erp.dao.UserImgMapperERP;
import com.service.utils.DBContextHolder;

@Service("UserImgServiceImplERP")
public class UserImgServiceImplERP {
	@Autowired
	UserImgMapperERP userImgMapperERP;

	/**
	 * 批量更新
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserImgList(List<UserImg> userImgs) {
		DBContextHolder.setDBType("siteRead");
		return userImgMapperERP.updateUserImgList(userImgs);
	}
	/**
	 * 更新无效
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserImgStatus(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userImgMapperERP.updateUserImgStatus(userId);
	}
}
