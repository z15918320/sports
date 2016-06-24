package com.sportsexp.smssendlog.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.sportsexp.common.service.impl.BaseServiceImpl;
import com.sportsexp.smssendlog.pojo.SmsSendDetailParams;
import com.sportsexp.smssendlog.pojo.SmsSendLogParams;
import com.sportsexp.smssendlog.pojo.SmsSendParams;
import com.sportsexp.smssendlog.service.SMSSendMsgLogService;
import com.sportsexp.smssendlog.vo.SmsSendLog;

@Service
public class SMSSendMsgLogServiceImpl extends BaseServiceImpl implements SMSSendMsgLogService {
	private static final String SELECT_ENTITY_FOR_ID = "smsSendLog.selectEntityForId";
	
	private static final String ADD_SMSSEND = "smsSendLog.addSmsSend";
	
	private static final String ADD_SMSSENDDETAIL = "smsSendLog.addSmsSendDetail";
	
	private static final String EDIT_ENTITY = "smsSendLog.editEntity";
	
	private static final String SELECT_ENTITY_FOR_PAR = "smsSendLog.selectEntityForPar";
	
	private static final String SELECT_COUNT_ENTITY = "smsSendLog.selectCountEntity";
	
	private static final String SELECT_LOOP_ENTITY = "smsSendLog.selectLoopEntity";


	@Override
	public Integer getCountEntity(SmsSendLogParams entityPar) {
		// TODO Auto-generated method stub
		return this.baseDao.get(SELECT_COUNT_ENTITY, entityPar);
	}


	@Override
	public List<SmsSendLog> getEntityListFroPar(SmsSendLogParams entityPar) {
		// TODO Auto-generated method stub
		List<SmsSendLog> list = baseDao.getList(SELECT_ENTITY_FOR_PAR,entityPar);
		return list;
	}


	@Override
	public SmsSendLog getEntityListFroID(String entityId) {
		
		return baseDao.get(SELECT_ENTITY_FOR_ID, entityId);
	}


	@Override
	public boolean editEntity(SmsSendDetailParams ssd) {
		// TODO Auto-generated method stub
		int count = baseDao.update(EDIT_ENTITY, ssd);
		if (count > 0) {
			return true;
		}
		return false;
	}


	@Override
	public SmsSendParams addSmsSend(SmsSendParams ss) {
		baseDao.save(ADD_SMSSEND, ss);
		return ss;
	}


	@Override
	public boolean addSmsSendDetail(SmsSendDetailParams ssd) {
		// TODO Auto-generated method stub
		int count = baseDao.save(ADD_SMSSENDDETAIL, ssd);
		if (count > 0) {
			return true;
		}
		return false;
	}


	@Override
	public List<SmsSendLog> getLoopEntity(String status) {
		// TODO Auto-generated method stub
		return baseDao.getList(SELECT_LOOP_ENTITY,status);
	}
	
}
