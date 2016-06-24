package com.sportsexp.util;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;


public class AliyunUtil {
	public static final String endpoint = "img-cn-beijing.aliyuncs.com";
	public static final String accessKeyId = "IaXh2fPzhJbK5d3b";
	public static final String accessKeySecret = "3Fj6E8ZuvfTHsUPm8DlST01CzIXNlK";
	public static final String bucketName = "sportsexp1";
	public static  ClientConfiguration cfg= null;
	
	static{
		cfg= new ClientConfiguration();
		cfg.setSupportCname(true);
		// Set the maximum number of allowed open HTTP connections
		cfg.setMaxConnections(100);
		// Set the amount of time to wait (in milliseconds) when initially establishing 
		// a connection before giving up and timing out
		cfg.setConnectionTimeout(5000);
		// Set the maximum number of retry attempts for failed retryable requests
		cfg.setMaxErrorRetry(3);
		// Set the amount of time to wait (in milliseconds) for data to betransfered over 
		// an established connection before the connection times out and is closed
		cfg.setSocketTimeout(2000);
		cfg.setSupportCname(true);
		
	}
	
	/**
	 * 活动图片上传阿里云
	 * @param is 图片流
	 * @return
	 */
//	public static String uploadFileActivities(InputStream is,int tag){
//		String key="";
//		try {
//			byte[] data = readInputStream(is);
//			String fileName = System.getProperty("user.dir")+System.currentTimeMillis()+".jpg";
//			File imageFile = new File(fileName);  
//			//创建输出流  
//	        FileOutputStream outStream = new FileOutputStream(imageFile);  
//	        //写入数据  
//	        outStream.write(data);  
//	        //关闭输出流  
//	        outStream.close(); 
//	        key = AliyunUtil.uploadFile(fileName, tag);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//		
//		
//        /*ObjectMetadata meta = new ObjectMetadata();
//		meta.setContentType("image/jpeg");
//		String key="activities/"+System.currentTimeMillis()+".jpg";
//		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret, cfg);
////		client.putObject(bucketName, key, is,meta);
//		PutObjectResult aa = client.putObject(new PutObjectRequest(bucketName, key, is, meta));
//		aa.toString();*/
//	   return key;
//	}
	
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  
	
	/**
	 * 图片上传阿里云
	 * @param is 图片流
	 * @param tag 1:活动 2:头像图片 3:供应,求购,话题图片,4:资讯一张大图,5:资讯一张图,6:资讯三张小图,7:主题,8:资讯
	 * @return
	 */
	public static String uploadFile(InputStream is,int tag){
		
//		int width=1024;
//		int height=1024;
//		InputStream is = null;
//		File destFile = null;
//    	try {
//    		ImgCompress imgCom = new ImgCompress(fileUrl);
//    		//根据不同业务设置不同的长宽高
//    		if(1==tag){
//    			width = 750;
//    			height = 440;
//    		}
//    		if(tag==1||tag==2||tag==3){
//    			destFile = imgCom.resizeFix(width, height,System.currentTimeMillis()+"");
//    		}else{
//    			destFile = new File(fileUrl);
//    		}
//    		
//    		is = new FileInputStream(destFile);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			if(null!=destFile){
//				destFile.delete();
//			}
//		}
    	
        ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType("image/jpeg");
		String key="";
		if(2==tag){ //头像图片
			key= "header/header_"+System.currentTimeMillis()+".jpg";
		}else if(3==tag){ //供应,求购,话题图片
			key= "content/content_"+System.currentTimeMillis()+".jpg";
		}else{
			key= "activities/"+System.currentTimeMillis()+".jpg";
		}
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret, cfg);
		PutObjectResult aa = client.putObject(new PutObjectRequest(bucketName, key, is, meta));
//		destFile.delete();
	   return key;
	}

}
