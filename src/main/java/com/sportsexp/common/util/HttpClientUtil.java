package com.sportsexp.common.util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/* 
 * 利用HttpClient进行post请求的工具类 
 */  
public class HttpClientUtil {  
	public static synchronized String doPost(String url,Map<String,String> contentmap,Map<String,String> headmap,String charset){  
		HttpClient httpClient = null;  
		HttpPost httpPost = null;  
		String result = null;  
		try{  
			httpClient = new DefaultHttpClient();  
			httpPost = new HttpPost(url);  

			if (headmap != null) 
			{
				for (Entry<?, ?> e : headmap.entrySet()) 
				{
					httpPost.addHeader(e.getKey().toString(),e.getValue().toString());
				}
			}
			//设置参数  
			List<NameValuePair> list = new ArrayList<NameValuePair>();  
			Iterator<Entry<String, String>> iterator = contentmap.entrySet().iterator();  
			while(iterator.hasNext()){  
				Entry<String,String> elem = (Entry<String, String>) iterator.next();  
				list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
			}  
			if(list.size() > 0){  
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset); 
				httpPost.setEntity(entity);  
			}

			HttpResponse response = httpClient.execute(httpPost);  
			if(response != null){  
				HttpEntity resEntity = response.getEntity();  
				if(resEntity != null){  
					result = EntityUtils.toString(resEntity,charset);  
				}  
			}  
		}catch(Exception ex){  
			ex.printStackTrace();  
		}  
		return result;  
	}  
}  