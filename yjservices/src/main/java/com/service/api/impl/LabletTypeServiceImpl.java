package com.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.LabletTypeMapper;
import com.service.bean.LabletType;
import com.service.utils.DBContextHolder;

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
	public LabletType selectlabletByName(String name) {
		DBContextHolder.setDBType("siteRead");
		return labletTypeMapper.selectlabletByName(name);
	}
}
