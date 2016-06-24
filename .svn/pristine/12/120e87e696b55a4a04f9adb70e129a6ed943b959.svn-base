import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;

public class HttpSenderTest {
	public static void main(String[] args) {
		String url = "http://222.73.117.158/msg/HttpBatchSendSM";// 应用地址
		String account = "HNYJ05";// 账号
		String pswd = "Tch451516";// 密码
		String mobile = "18670747057";// 手机号码，多个号码使用","分割
		String msg = "亲爱的用户，您的验证码是123456，5分钟内有效。";// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String product = null;// 产品ID
		String extno = null;// 扩展码
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		Map  contentmap  = new HashMap();
		contentmap.put("account", account);
		contentmap.put("pswd", pswd);
		contentmap.put("mobile", mobile);
		contentmap.put("needstatus", String.valueOf(needstatus)); 
		contentmap.put("msg", msg);
		contentmap.put("product", product);
		contentmap.put("extno", extno); 
	 
		String returnString = httpClientUtil.doPost(url, contentmap, null, "UTF-8");
		System.out.println(returnString);
//		try {
//			String returnString = HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus, product, extno);
//			System.out.println(returnString);
//			// TODO 处理返回值,参见HTTP协议文档
//		} catch (Exception e) {
//			// TODO 处理异常
//			e.printStackTrace();
//		}
	}
}
