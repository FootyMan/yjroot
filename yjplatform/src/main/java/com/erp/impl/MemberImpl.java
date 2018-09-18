package com.erp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.bean.User;
import com.erp.dao.MemberDao;
import com.erp.service.MemberService;
import com.service.parameter.bean.UserListSearchParameter;
//@Transactional
@Service
public class MemberImpl implements MemberService {

	@Autowired
	private MemberDao userMapperERP;

	@Override
	public List<User> GetUserList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapperERP.UserList(map);
	}

	@Override
	public List<User> GetHomeUserList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapperERP.HomeUserList(map);
	}

	@Override
	public int GetHomeUserCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapperERP.HomeUserCount(map);
	}

	@Override
	public int InsertUserErp(User user) {
		// TODO Auto-generated method stub
		return userMapperERP.InsertUserErp(user);
	}

	@Override
	public List<User> selectDetalsERP(int userId) {
		// TODO Auto-generated method stub
		return userMapperERP.selectDetalsERP(userId);
	}

	@Override
	public int selectUserByNickName(String nickName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> selectEaseIdAll() {
		// TODO Auto-generated method stub
		return userMapperERP.selectEaseIdAll();
	}

	@Override
	public List<User> ImportUser() {
		// TODO Auto-generated method stub
		return userMapperERP.ImportUser();
	}

	@Override
	public int setUserIsEnable(User user) {
		// TODO Auto-generated method stub
		return userMapperERP.setUserIsEnable(user);
	}

	@Override
	public int GetUserListCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapperERP.UserListCount(map);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userMapperERP.updateUser(user);
	}

}
