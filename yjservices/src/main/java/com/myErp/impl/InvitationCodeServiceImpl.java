package com.myErp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.InvitationCodeMapper;
import com.myErp.manager.bean.InvitationCode;
import com.myErp.utils.DBContextHolder;

@Service("InvitationCodeService")
public class InvitationCodeServiceImpl {

	@Autowired
	private InvitationCodeMapper invitationCodeMapper;

	public int insertCode(InvitationCode code) {
		DBContextHolder.setDBType("siteRead");
		return invitationCodeMapper.insertCode(code);
	}

	public InvitationCode selectCode(String code) {
		DBContextHolder.setDBType("siteRead");
		return invitationCodeMapper.selectCode(code);
	}

	/**
	 * 获取有效的邀请码
	 * 
	 * @return
	 */
	public InvitationCode selectCodeByvalid() {
		DBContextHolder.setDBType("siteRead");
		InvitationCode code = invitationCodeMapper.selectCodeByvalid();
		/**
		 * 开启线程去更新
		 */
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				invitationCodeMapper.updateCode(code.getCode());

			}
		});
		thread.start();
		return code;
	}

	public int updateCode(String code) {
		DBContextHolder.setDBType("siteRead");
		return invitationCodeMapper.updateCode(code);
	}

}
