import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;
 
public class FileUpload {
 
    String multipart_form_data = "multipart/form-data";   
    String twoHyphens = "--";   
    String boundary = "****************fD4fH3gL0hK7aI6";    // 数据分隔符    
    String lineEnd = "\r\n";    // The value is "\r\n" in Windows.    
       
    /**
     * 发送请求
     * 
     * @param url
     *            请求地址
     * @param filePath
     *            文件在服务器保存路径（这里是为了自己测试方便而写，可以将该参数去掉）
     * @return
     * @throws IOException
     */
    public String send(String url, String filePath) throws IOException {
 
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
 
        /**
         * 第一部分
         */
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        BufferedReader input = null;   
        /**
         * 设置关键值
         */
        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false); // post方式不能使用缓存
 
        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
 
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
                + BOUNDARY);
 
        // 请求正文信息
 
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // ////////必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
 
        byte[] head = sb.toString().getBytes("utf-8");
 
        // 获得输出流
 
        OutputStream out = new DataOutputStream(con.getOutputStream());
        out.write(head);
 
        // 文件正文部分
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
 
        
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
 
        out.write(foot);
 
        out.flush();
        out.close();
 
        /**
         * 读取服务器响应，必须读取,否则提交不成功
         */
 
        input = new BufferedReader(new InputStreamReader(con.getInputStream()));   
        StringBuilder response = new StringBuilder();   
        String oneLine;   
        while((oneLine = input.readLine()) != null) {   
            response.append(oneLine + "\r\n");   
        }   
           
        return response.toString();   
 
        /**
         * 下面的方式读取也是可以的
         */
 
        // try {
        // // 定义BufferedReader输入流来读取URL的响应
        // BufferedReader reader = new BufferedReader(new InputStreamReader(
        // con.getInputStream()));
        // String line = null;
        // while ((line = reader.readLine()) != null) {
        // System.out.println(line);
        // }
        // } catch (Exception e) {
        // System.out.println("发送POST请求出现异常！" + e);
        // e.printStackTrace();
        // }
 
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
            output.writeBytes(sb.toString());// 发送表单字段数据    
        } catch (IOException e) {   
            throw new RuntimeException(e);   
        }   
    }   
    public static void main(String[] args) throws IOException {
        FileUpload up = new FileUpload();
       // http://127.0.0.1:8080/zmh/user/
        System.out.println(up.send("http://127.0.0.1:8080/zmh/upload/uploadfile.do",
                "E:/112.png"));
        ;
    }
}