package com.sportsexp.activities.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sportsexp.common.service.SendSMSService;
import com.sportsexp.common.util.DateUtil;
import com.sportsexp.smssendlog.pojo.SmsSendDetailParams;
import com.sportsexp.smssendlog.service.SMSSendMsgLogService;
import com.sportsexp.smssendlog.vo.SmsSendLog;
import com.sportsexp.user.service.UserService;
import com.sportsexp.user.vo.UserInfo;

/**
 * * <p>Title:TimeTask </p>
 * <p>Description: 定时任务类</p>
 * <p>Company: sportsexp </p> 
 * @author zhijiang.zhang
 * @date 2016年6月19日 下午5:14:52
 */
@Component
@Lazy(false)
public class TimeTask {
	@Autowired
	private SendSMSService sendSMSService;
	@Autowired
	private SMSSendMsgLogService smsLogService;
	@Autowired
	private UserService userService;
	
	 
	/**  
     * 心跳更新。启动时执行一次，之后每隔30秒执行一次  
     * 从日志中获取今天待发送短信进行发送
	 * @throws Exception 
     **/    
	//@Scheduled(cron = "0/30 * * * * ?")
    public void timeSendSMS() throws Exception{  
		System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm")+"进入定时发送待发送短信任务~~~~~~~~~~~~");
		List<SmsSendLog> logList = smsLogService.getLoopEntity("0");
		for (SmsSendLog smsSendLog : logList) {
			sendSMS(smsSendLog.getSMS_SEND_ID());
		}
    }
	
	private void sendSMS(String logId) throws Exception {
		String returnStatus="";
		Map<String,String> msgInfo = new HashMap();
		  SmsSendLog log = smsLogService.getEntityListFroID(logId);
		  //判断用户是否存在
		  UserInfo userinfo = userService.getUserInfoFroIdActivities(log.getUSER_INFO_ID());
		  if(null!=userinfo){
			  msgInfo.put(log.getSEND_MOBILE(), log.getSMS_SEND_CONTENT());
			  returnStatus = this.sendSMSService.sendSMS(msgInfo);
			//记录日志
			  SmsSendDetailParams ssd = new SmsSendDetailParams();
			  if("SUCCESS".equals(returnStatus)){
				  ssd.setSend_status("1"); 
			  }else{
				  ssd.setSend_status("2"); 
			  }
			  ssd.setSend_time(new Date());
			  ssd.setSms_send_detail_id(log.getSMS_SEND_DETAIL_ID());
			  smsLogService.editEntity(ssd);
		  }else{
			  returnStatus="不存在的用户"; 
		  }
	}
}