package com.myErp.dao;

import com.myErp.manager.bean.UserPosition;

public abstract interface  UserPositionMapper {
	/**
	 * 添加
	 * @param position
	 * @return
	 */
	public abstract int insertPosition(UserPosition position);
	/**
	 * 更新
	 * @param position
	 * @return
	 */
	public abstract int updatePosition(UserPosition position); 
	/**
	 * 表中是否存储
	 * @param position
	 * @return
	 */
	public abstract int selectCountByUserId(Integer userid); 
}
