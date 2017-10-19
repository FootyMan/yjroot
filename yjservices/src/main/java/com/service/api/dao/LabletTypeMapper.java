package com.service.api.dao;

import java.util.List;

import com.service.bean.LabletType;

public abstract interface LabletTypeMapper {

	public abstract int insertlabletType(LabletType labletType);

	public abstract List<LabletType> selectlabletTypeAll();
}
