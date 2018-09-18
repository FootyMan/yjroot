package com.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.service.bean.HomeUser;
@Mapper
public interface HomeMemberDao {

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int insertHomeUser(HomeUser home);

	/**
	 * 删除
	 * 
	 * @param user
	 * @return
	 */
	public int deleteHomeUser(int[] userIds);

	/**
	 * 查询
	 * 
	 * @param user
	 * @return
	 */
	public HomeUser selectHomeUserByUserId(int userId);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<HomeUser> selectHomeUserList();

}
