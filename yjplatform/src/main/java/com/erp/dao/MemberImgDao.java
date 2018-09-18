package com.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.service.bean.UserImg;

@Mapper
public interface MemberImgDao {

	/**
	 * 批量更新
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserImgList(List<UserImg> userImgs);

	/**
	 * 更新图片无效
	 * 
	 * @param userId
	 * @return
	 */
	public int updateUserImgStatus(int userId);

	/**
	 * 根据用户ID查询
	 * 
	 * @param user
	 * @return
	 */
	public List<UserImg> selectImgtByUserId(int userId);

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int insertUserImg(List<UserImg> userImgs);

}
