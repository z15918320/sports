import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*  
 * 修改历史：  
 */   
public class UploadImage {   
	String multipart_form_data = "multipart/form-data";   
	String twoHyphens = "--";   
	String boundary = "****************fD4fH3gL0hK7aI6";    // 数据分隔符    
	String lineEnd = "\r\n";    // The value is "\r\n" in Windows.    

	/*  
	 * 上传图片内容，格式请参考HTTP 协议格式。  
	 * 人人网Photos.upload中的”程序调用“http://wiki.dev.renren.com/wiki/Photos.upload#.E7.A8.8B.E5.BA.8F.E8.B0.83.E7.94.A8  
	 * 对其格式解释的非常清晰。  
	 * 格式如下所示：  
	 * --****************fD4fH3hK7aI6  
	 * Content-Disposition: form-data; name="upload_file"; filename="apple.jpg"  
	 * Content-Type: image/jpeg  
	 *  
	 * 这儿是文件的内容，二进制流的形式  
	 */   
	private void addImageContent(File[] files, DataOutputStream output) {   
		for(File file : files) {   
			StringBuilder split = new StringBuilder();   
			split.append(twoHyphens + boundary + lineEnd);   
			split.append("Content-Disposition: form-data; name=\"" + file.getName() + "\"; filename=\"" + file.getName() + "\"" + lineEnd);   
			split.append("Content-Type: " + "Content-Type:application/octet-stream" + lineEnd);   
			split.append(lineEnd);   
			try {   
				// 发送图片数据    
				output.writeBytes(split.toString());   
				System.out.println("   path  :  "+file.getPath()+"    length   :  "+File2byte(file.getPath()).length);
				output.write(File2byte(file.getPath()), 0, File2byte(file.getPath()).length);   
				output.writeBytes(lineEnd);   
			} catch (IOException e) {   
				throw new RuntimeException(e);   
			}   
		}   
	}   


	public static byte[] File2byte(String filePath)  
	{  
		byte[] buffer = null;  
		try  
		{  
			File file = new File(filePath);  
			FileInputStream fis = new FileInputStream(file);  
			ByteArrayOutputStream bos = new ByteArrayOutputStream();  
			byte[] b = new byte[1024];  
			int n;  
			while ((n = fis.read(b)) != -1)  
			{  
				bos.write(b, 0, n);  
			}  
			fis.close();  
			bos.close();  
			buffer = bos.toByteArray();  
		}  
		catch (FileNotFoundException e)  
		{  
			e.printStackTrace();  
		}  
		catch (IOException e)  
		{  
			e.printStackTrace();  
		}  
		return buffer;  
	}  
	/*  
	 * 构建表单字段内容，格式请参考HTTP 协议格式（用FireBug可以抓取到相关数据）。(以便上传表单相对应的参数值)  
	 * 格式如下所示：  
	 * --****************fD4fH3hK7aI6  
	 * Content-Disposition: form-data; name="action"  
	 * // 一空行，必须有  
	 * upload  
	 */   
	private void addFormField(Set<Map.Entry<Object,Object>> params, DataOutputStream output) {   
		StringBuilder sb = new StringBuilder();   
		for(Map.Entry<Object, Object> param : params) {   
			sb.append(twoHyphens + boundary + lineEnd);   
			sb.append("Content-Disposition: form-data; name=\"" + param.getKey() + "\"" + lineEnd);   
			sb.append(lineEnd);   
			sb.append(param.getValue() + lineEnd);   
		}   
		try {   
			byte [] string  = sb.toString().getBytes("utf-8") ;
			output.write(string,0,string.length);// 发送表单字段数据    
		} catch (IOException e) {   
			throw new RuntimeException(e);   
		}   
	}   

	/**  
	 * 直接通过 HTTP 协议提交数据到服务器，实现表单提交功能。  
	 * @param actionUrl 上传路径  
	 * @param params 请求参数key为参数名，value为参数值  
	 * @param files 上传文件信息  
	 * @return 返回请求结果  
	 */   
	public String post(String actionUrl, Set<Map.Entry<Object,Object>> params, File[] files) {   
		HttpURLConnection conn = null;   
		DataOutputStream output = null;   
		BufferedReader input = null;   
		try {   
			URL url = new URL(actionUrl);   
			conn = (HttpURLConnection) url.openConnection();   
			conn.setConnectTimeout(120000);   
			conn.setDoInput(true);        // 允许输入    
			conn.setDoOutput(true);        // 允许输出    
			conn.setUseCaches(false);    // 不使用Cache    
			conn.setRequestMethod("POST");   
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Connection", "keep-alive");   
			conn.setRequestProperty("Content-Type", multipart_form_data + "; boundary=" + boundary);   
		 
			conn.connect();   
			output = new DataOutputStream(conn.getOutputStream());   

			addImageContent(files, output);    // 添加图片内容    

			addFormField(params, output);    // 添加表单字段内容    

			output.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);// 数据结束标志    
			output.flush();   

			int code = conn.getResponseCode();   
			if(code != 200) {   
				throw new RuntimeException("请求‘" + actionUrl +"’失败！");   
			}   

			input = new BufferedReader(new InputStreamReader(conn.getInputStream()));   
			StringBuilder response = new StringBuilder();   
			String oneLine;   
			while((oneLine = input.readLine()) != null) {   
				response.append(oneLine + lineEnd);   
			}   

			return response.toString();   
		} catch (IOException e) {   
			throw new RuntimeException(e);   
		} finally {   
			// 统一释放资源    
			try {   
				if(output != null) {   
					output.close();   
				}   
				if(input != null) {   
					input.close();   
				}   
			} catch (IOException e) {   
				throw new RuntimeException(e);   
			}   

			if(conn != null) {   
				conn.disconnect();   
			}   
		}   
	}   

	public static void main(String[] args) throws IOException {   
		String response = "";   

		//            BufferedReader in = new BufferedReader(new FileReader("config/actionUrl.properties"));   
		//            String actionUrl = in.readLine();   

		// 读取表单对应的字段名称及其值    
		//            Properties formDataParams = new Properties();   
		//            formDataParams.load(new FileInputStream(new File("config/formDataParams.properties")));   
		//            Set<Map.Entry<Object,Object>> params = formDataParams.entrySet();   

		//            // 读取图片所对应的表单字段名称及图片路径    
		//            Properties imageParams = new Properties();   
		//            imageParams.load(new FileInputStream(new File("config/imageParams.properties")));   
		Map<Object,Object> paramsMap = new HashMap<Object,Object>();   
		//		jsonMap.put("password","1234");
		UserInfo userInfo = new UserInfo();
		userInfo.setAddress("长沙市雨花区");
		userInfo.setPassword("12345");
		userInfo.setAuthentication(1);
		userInfo.setBusiness_direction("苗木");
		userInfo.setCompany_name("羊圈科技");
		userInfo.setMobile("18722001700");
		userInfo.setLandline("888741442");
		userInfo.setNickname("小小");
		userInfo.setUser_name("18722003000");
		userInfo.setEmail("66202558@qq.com");
		try {
			paramsMap = convertBean(userInfo);
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		paramsMap.put("commodity_id", 1);
		//		paramsMap.put("topic_id", 2);
		//		paramsMap.put("campaign_id", 3);
		//            Set<Entry<String, Integer>> images = map.entrySet();   
		File[] files = new File[1];   
		File file = new File("E:/113.png");   
		files[0] = file; 
//		File file1 = new File("E:/113.png");   
//		files[1] = file1; 
		//            int i = 0;   
		//            for(Map.Entry<Object,Object> image : images) {   
		//                File file = new File(image.getValue().toString());   
		//                files[i++] = file;   
		//            }   
		//            Image file = new Image("images/apple.jpg", "upload_file");    
		//            Image[] files = new Image[0];    
		//            files[0] = file;    

		response = new UploadImage().post("http://127.0.0.1:8080/zmh/user/updateUser.do", paramsMap.entrySet(), files);   
		System.out.println("返回结果：" + response);   
	}   
	
	 @SuppressWarnings("rawtypes")  
	    public static Object convertMap(Class type, Map map)  
	            throws IntrospectionException, IllegalAccessException,  
	            InstantiationException, InvocationTargetException {  
	        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性  
	        Object obj = type.newInstance(); // 创建 JavaBean 对象  
	  
	        // 给 JavaBean 对象的属性赋值  
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
	        for (int i = 0; i< propertyDescriptors.length; i++) {  
	            PropertyDescriptor descriptor = propertyDescriptors[i];  
	            String propertyName = descriptor.getName();  
	  
	            if (map.containsKey(propertyName)) {  
	                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  
	                Object value = map.get(propertyName);  
	  
	                Object[] args = new Object[1];  
	                args[0] = value;  
	  
	                descriptor.getWriteMethod().invoke(obj, args);  
	            }  
	        }  
	        return obj;  
	    }  
	 
	   @SuppressWarnings({ "rawtypes", "unchecked" })  
	    public static Map convertBean(Object bean)  
	            throws IntrospectionException, IllegalAccessException, InvocationTargetException {  
	        Class type = bean.getClass();  
	        Map returnMap = new HashMap();  
	        BeanInfo beanInfo = Introspector.getBeanInfo(type);  
	  
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
	        for (int i = 0; i< propertyDescriptors.length; i++) {  
	            PropertyDescriptor descriptor = propertyDescriptors[i];  
	            String propertyName = descriptor.getName();  
	            if (!propertyName.equals("class")) {  
	                Method readMethod = descriptor.getReadMethod();  
	                Object result = readMethod.invoke(bean, new Object[0]);  
	                if (result != null) {  
	                    returnMap.put(propertyName, result);  
	                } else {  
	                    returnMap.put(propertyName, "");  
	                }  
	            }  
	        }  
	        return returnMap;  
	    }  
} 