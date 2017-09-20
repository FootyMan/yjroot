package com.myErp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.LabletTypeMapper;
import com.myErp.manager.bean.LabletType;
import com.myErp.utils.DBContextHolder;

@Service("labletTypeService")
public class LabletTypeServiceImpl {

	@Autowired
	private LabletTypeMapper labletTypeMapper;

	public int insertlabletType(LabletType labletType) {
		DBContextHolder.setDBType("siteRead");
		return labletTypeMapper.insertlabletType(labletType);
	}

	public List<LabletType> selectlabletTypeAll() {
		DBContextHolder.setDBType("siteRead");
		return labletTypeMapper.selectlabletTypeAll();
	}
}
