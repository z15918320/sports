import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

 


public class TestMain {

	private static String url = "http://127.0.0.1:8080/zmh/user/RegistSms.do";  
	private String charset = "utf-8";  
	private HttpClientUtil httpClientUtil = null;  

	public TestMain(){  
		httpClientUtil = new HttpClientUtil();  
	}  
	
//	 public boolean uploadFileToBos(String objId, File fileToPush, AsyncHttpResponseHandler responseHandler) {
//	        // authStringPrefix = bce-auth-v1/{accessKeyId}/{timestamp}/{expirationPeriodInSeconds}
//	        // SigningKey = HMAC-SHA256-HEX(sk, authStringPrefix)。
//	        // CanonicalRequest = HTTP Method + "\n" + CanonicalURI + "\n" + CanonicalQueryString + "\n" + CanonicalHeaders
//	        // Signature = HMAC-SHA256-HEX(SigningKey, CanonicalRequest)
//	        // Authorization = bce-auth-v1/{accessKeyId}/{timestamp}/{expirationPeriodInSeconds}/{signedHeaders}/{signature}
//	        // RequestParams reqParams = new RequestParams();
//	        String uuid = objId;
//	        String url = Cst.getBosUrl(uuid);
//	        Date now = new Date();
//	        TimeZone tzUTC = TimeZone.getTimeZone("GMT");
//	        SimpleDateFormat greenwichDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
//	        greenwichDate.setTimeZone(tzUTC);
//	        String date = greenwichDate.format(now);
//
//	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
//	        sdf.setTimeZone(tzUTC);
//	        String timeStamp = sdf.format(now);
//
//	        StringBuilder authStringPerfix = new StringBuilder("bce-auth-v1/");
//	        authStringPerfix.append(Cst.BOS_AK).append("/");
//	        authStringPerfix.append(timeStamp).append("/");
//	        authStringPerfix.append("1800");
//	        String signingKey = sha256Hex(Cst.BOS_SK, authStringPerfix.toString());
//
//	        StringBuilder canonicalRequest = new StringBuilder("PUT\n");
//	        canonicalRequest.append(uriEncodeExceptSlash(url)).append("\n");
//	        canonicalRequest.append("\n");
//	        canonicalRequest.append(URLEncoder.encode("host")).append(":").append(URLEncoder.encode(Cst.BOS_HOST));
//	        String signature = sha256Hex(signingKey, canonicalRequest.toString());
//	        StringBuilder auth = new StringBuilder("bce-auth-v1/");
//	        auth.append(Cst.BOS_AK).append("/");
//	        auth.append(timeStamp).append("/").append("1800/");
//	        auth.append("host/");
//	        auth.append(signature);
//
//	        FileEntity entity = new FileEntity(fileToPush, "application/octet-stream");
//
//	        Header[] headers = {
//	                new BasicHeader("Date", date),
//	                new BasicHeader("Authorization", auth.toString())
//	        };
//	        BOS_CLIENT.setTimeout(30 * 1000);
//	        BOS_CLIENT.put(mContext, "http://" + Cst.BOS_HOST + url, headers, entity, "application/octet-stream",
//	                responseHandler);
//	        return true;
//	    }


	public void test() throws FileNotFoundException{  
//	    String ACCESS_KEY_ID = "459ca22b254644c0aa795d18de104a8f";                   // 用户的Access Key ID
//	    String SECRET_ACCESS_KEY = "471064b9b9bf4c8fa2cf319f9a617dca";           // 用户的Secret Access Key
//	    String endpoint = "http://bj.bcebos.com";
//	    BosClientConfiguration config = new BosClientConfiguration();
//	    config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
//	    config.setEndpoint(endpoint);
//	    BosClient client = new BosClient(config);
//	    // 获取指定文件
//	    File file = new File("E:/112.png");
//	    // 以文件形式上传Object
//	    PutObjectResponse putObjectFromFileResponse = client.putObject("miaotu", "112.png", file);
//	    // 打印ETag
//	    System.out.println(putObjectFromFileResponse.getETag());
	    
//		String httpOrgCreateTest = url;
//		Map<String,String> headMap = new HashMap<String,String>();  
//		headMap.put("Content-Type", "application/text");
//		Map<String,String> createMap = new HashMap<String,String>();  
//		createMap.put("phone","18670747057");  
 
//		String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,headMap,charset);  
//		System.out.println("result:"+httpOrgCreateTestRtn);  
	}  

	public static void main(String[] args){  
		TestMain main = new TestMain();  
		try {
			main.test();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Map<String,String> createMap = new HashMap<String,String>();  
//		createMap.put("phone","18670747057");  
//		List<NameValuePair> list = new ArrayList<NameValuePair>();  
//		Iterator iterator = createMap.entrySet().iterator();  
//		while(iterator.hasNext()){  
//			Entry<String,String> elem = (Entry<String, String>) iterator.next();  
//			list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
//		}  
//	 
//		try {
//			main.postTableData(url, list);
//		} catch (ConnectTimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
	}  
	public static String postTableData(String url, List<NameValuePair> params) throws ConnectTimeoutException,ClientProtocolException,
			IOException {
		String result = null;
		HttpClient httpclient = new DefaultHttpClient();  ;// 创建一个HttpClient
		HttpPost httppost = new HttpPost(url);// 创建一个POST请求
		httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));// 添加请求参数到请求对象
		//httppost.setHeader("User-Agent", str_agent);//在报文头部添加一些字符串标识
		httppost.setHeader("Content-Type", "application/text");
		HttpResponse response = httpclient.execute(httppost);//发送请求
		HttpEntity resEntity = response.getEntity();//从响应中获取消息实体
		if (resEntity != null) {
			result = EntityUtils.toString(resEntity);
		}
		httpclient.getConnectionManager().shutdown();//关闭连接
		return result;
	}
	
	
}
