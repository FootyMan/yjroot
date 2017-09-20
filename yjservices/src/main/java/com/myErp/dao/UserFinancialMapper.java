package com.myErp.dao;

import com.myErp.manager.bean.UserFinancial;

/**
 * 用户收入主表
 * @author HCY
 *
 */
public abstract interface UserFinancialMapper {
	
	/**
	 * 插入
	 * @param userFinacial
	 * @return
	 */
	public abstract int insertFinancial(UserFinancial userFinacial);
	/**
	 * 更新
	 * @param userFinacial
	 * @return
	 */
	public abstract int updateFinancial(UserFinancial userFinacial);
	/**
	 * 查询
	 * @param userId
	 * @return
	 */
	public abstract UserFinancial selectFinancial(int userId);

}
