package com.sportsexp.user.service;
import java.util.List;

import com.sportsexp.user.pojo.UserParams;
import com.sportsexp.user.vo.UserInfo;

public interface UserService {
	
	public boolean deleteUserActivities(String userId);
	
	public Integer getCountUserInfoActivities(UserParams userPars);
	
	public boolean addUserActivities(UserParams userPar);
	
	public boolean editUserActivities(UserParams userPar);
	
	public UserInfo getUserInfoFroIdActivities(String user_id);
	
	public List<UserInfo> getUserInfoFroParActivities(UserParams userPar);
}
