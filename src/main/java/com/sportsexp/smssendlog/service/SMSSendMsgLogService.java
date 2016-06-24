package com.sportsexp.smssendlog.service;


import java.util.List;

import com.sportsexp.smssendlog.pojo.SmsSendDetailParams;
import com.sportsexp.smssendlog.pojo.SmsSendLogParams;
import com.sportsexp.smssendlog.pojo.SmsSendParams;
import com.sportsexp.smssendlog.vo.SmsSendLog;

public interface SMSSendMsgLogService {

	
	public SmsSendParams addSmsSend(SmsSendParams ss);
	
	public boolean addSmsSendDetail(SmsSendDetailParams ssd);
	/**
	 * 修改日志
	 * @param entityPar
	 * @return
	 */
	public boolean editEntity(SmsSendDetailParams ssd);
	
	/**
	 * 查询总数
	 * @param entityPar
	 * @return
	 */
	public Integer getCountEntity(SmsSendLogParams entityPar);
	
	/**
	 * 根据查询条件查询发送日志记录
	 * @param entityPar
	 * @return
	 */
	public List<SmsSendLog> getEntityListFroPar(SmsSendLogParams entityPar);
	
	/**
	 * 获得轮询的记录(当天)
	 * @return
	 */
	public List<SmsSendLog> getLoopEntity(String status);
	
	/**
	 * 根据查询条件查询发送日志记录
	 * @param sendLogPar
	 * @return
	 */
	public SmsSendLog getEntityListFroID(String entityId);
}
