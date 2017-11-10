package com.service.api.impl;

import com.service.api.dao.InformMapper;
import com.service.bean.Inform;
import com.service.utils.DBContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ReportServiceImpl")
public class ReportServiceImpl {
	@Autowired
	private InformMapper reportMapper;

	/**
	 * 添加
	 * @param report
	 * @return
	 */
	public int insertReport(Inform report) {
		DBContextHolder.setDBType("siteRead");
		return reportMapper.insertReport(report);
	}

}
