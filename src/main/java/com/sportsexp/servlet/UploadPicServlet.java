package com.sportsexp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsexp.util.AliyunUtil;

/**
 * 
 * @author Name:shengxianming
 * @version 创建时间：2016/3/10日 下午4:14:02
 * @intro 图片上传服务
 */
public class UploadPicServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		// 上传文件
		PrintWriter out = null;
		Map<String,String> resMap = new HashMap<String,String>();
		File file = null;
		try {
			request.setCharacterEncoding("UTF-8");
			Integer tag = new Integer(request.getParameter("tag"));
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			String fileName = this.getServletContext().getRealPath("/")+System.currentTimeMillis()+".jpg";
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (!item.isFormField()){
			    	 if (item.getName() != null && !item.getName().equals("")) {
			    		 file = new File(fileName);
			    		 if(!file.exists()){
			    			 file.createNewFile();
			    			 
			    		 }
			    		 item.write(file);
			    	 }
			    }
			}
			InputStream is = new FileInputStream(file);
			out =  response.getWriter();
			String key = AliyunUtil.uploadFile(is,tag);
			String url="";
			if(tag==1){
				url = "http://8yyq8.com/" + key;
			}else{
				url = "/" + key;
			}
			is.close();
			response.setContentType("multipart/form-data;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			resMap.put("STATUS", "1");
			resMap.put("MSG", "上传图片成功");
			resMap.put("url",url);		
		} catch (Exception e) {						
			e.printStackTrace();
		} finally {
			ObjectMapper mapper = new ObjectMapper();
			String errorStr;
			try {
				errorStr = mapper.writeValueAsString(resMap);
				out.write(errorStr);
				if (out != null) {
					out.flush();
					out.close();
				}
				if(null!=file){
					file.delete();
				}
			} catch (JsonProcessingException e) {			
				e.printStackTrace();
			}
			
		}

	}

}