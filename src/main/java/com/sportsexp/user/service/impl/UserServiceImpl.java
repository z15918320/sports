package com.sportsexp.user.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.sportsexp.common.service.impl.BaseServiceImpl;
import com.sportsexp.user.pojo.UserParams;
import com.sportsexp.user.service.UserService;
import com.sportsexp.user.vo.UserInfo;
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService{	
	private static final String DELETE_USERINFO_ID="userDao.deleteUser";

	private static final String SELECT_USERINFO_COUNT = "userDao.selectUserCount";
	
	private static final String SELECT_USERINFO_ID = "userDao.selectUserByUserId";
	
	private static final String ADD_USERINFO_ID = "userDao.addUserActivities";
	
	private static final String EDIT_USERINFO_ID = "userDao.editUserActivities";
	
	private static final String SELECT_USERINFO_PAR = "userDao.selectUserByUserPar";
	
	@Override
	public boolean deleteUserActivities(String userId) {
		int count = baseDao.delete(DELETE_USERINFO_ID, userId);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Integer getCountUserInfoActivities(UserParams userPars) {
		// TODO Auto-generated method stub
		return this.baseDao.get(SELECT_USERINFO_COUNT, userPars);
	}

	@Override
	public boolean addUserActivities(UserParams userPar) {
		int count = baseDao.save(ADD_USERINFO_ID, userPar);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean editUserActivities(UserParams userPar) {
		int count = baseDao.update(EDIT_USERINFO_ID, userPar);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public UserInfo getUserInfoFroIdActivities(String userId) {
		return baseDao.get(SELECT_USERINFO_ID, userId);
	}

	@Override
	public List<UserInfo> getUserInfoFroParActivities(UserParams userPar) {
		// TODO Auto-generated method stub
		List<UserInfo> userInfoList = baseDao.getList(SELECT_USERINFO_PAR, userPar);
		return userInfoList;
	}
}
