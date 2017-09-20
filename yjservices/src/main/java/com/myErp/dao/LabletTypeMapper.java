package com.myErp.dao;

import java.util.List;

import com.myErp.manager.bean.LabletType;

public abstract interface LabletTypeMapper {

	public abstract int insertlabletType(LabletType labletType);

	public abstract List<LabletType> selectlabletTypeAll();
}
