package com.sportsexp.activities.service.impl;

import org.springframework.stereotype.Service;

import com.sportsexp.activities.pojo.LoginParams;
import com.sportsexp.activities.service.ActivitiesService;
import com.sportsexp.common.service.impl.BaseServiceImpl;
import com.sportsexp.smssendlog.pojo.SmsSendLogParams;
import com.sportsexp.user.vo.UserInfo;

@Service
public class ActivitiesServiceImpl extends BaseServiceImpl implements ActivitiesService {

	private static final String SELECT_USERINFO = "activitiesDao.selectUserByNamePassword";
	
	private static final String ADD_SMS_SEND_LOG = "activitiesDao.selectUserByNamePassword";
	/**
	 * 获取登录用户信息
	 */
	@Override
	public UserInfo loginActivities(LoginParams loginInfo) {
		// TODO Auto-generated method stub
		UserInfo userInfo = baseDao.get(SELECT_USERINFO, loginInfo);
		return userInfo;
	}

	@Override
	public boolean addSMSSendMsgLog(SmsSendLogParams sendLogPar) {
		// TODO Auto-generated method stub
		int count = baseDao.save(ADD_SMS_SEND_LOG, sendLogPar);
		if (count > 0) {
			return true;
		}
		return false;
	}
	
}
