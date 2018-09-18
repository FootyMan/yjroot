package com.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.bean.UserImg;
import com.erp.dao.MemberImgDao;

@Service
public class MemberImgImpl {
	@Autowired
	MemberImgDao userImgMapperERP;

	/**
	 * 批量更新
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserImgList(List<UserImg> userImgs) {
		return userImgMapperERP.updateUserImgList(userImgs);
	}
	/**
	 * 更新无效
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserImgStatus(int userId) {
		return userImgMapperERP.updateUserImgStatus(userId);
	}
	/**
	 * 根据用户ID查询
	 * 
	 * @param user
	 * @return
	 */
	public List<UserImg> selectImgtByUserId(int userId)
	{
		return userImgMapperERP.selectImgtByUserId(userId);
	}
	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int insertUserImg(List<UserImg> userImgs)
	{
		return userImgMapperERP.insertUserImg(userImgs);
	}
}
