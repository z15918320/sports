package com.sportsexp.activities.service;

import com.sportsexp.activities.pojo.LoginParams;
import com.sportsexp.smssendlog.pojo.SmsSendLogParams;
import com.sportsexp.user.vo.UserInfo;

public interface ActivitiesService {

	public UserInfo loginActivities(LoginParams loginInfo);
	
	public boolean addSMSSendMsgLog(SmsSendLogParams sendLogPar);
}
