import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;


public class AliyunTest {

	public static void main(String args[])
	{
		String endpoint = "https://oss-cn-beijing.aliyuncs.com";
		String accessKeyId = "IaXh2fPzhJbK5d3b";
		String accessKeySecret = "3Fj6E8ZuvfTHsUPm8DlST01CzIXNlK";
		String bucketName = "miaotu1";
		String objectName = "android-2/";
		// Create a new client configuration instance
		ClientConfiguration conf = new ClientConfiguration();
		conf.setSupportCname(true);

		// Set the maximum number of allowed open HTTP connections
		conf.setMaxConnections(100);

		// Set the amount of time to wait (in milliseconds) when initially establishing 
		// a connection before giving up and timing out
		conf.setConnectionTimeout(5000);

		// Set the maximum number of retry attempts for failed retryable requests
		conf.setMaxErrorRetry(3);

		// Set the amount of time to wait (in milliseconds) for data to betransfered over 
		// an established connection before the connection times out and is closed
		conf.setSocketTimeout(2000);
		// Create a new OSSClient instance with CNAME support 
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);

//		StringBuffer tmpContent = new StringBuffer();
//	      tmpContent.append("<html><head>");
//	      tmpContent.append("<meta charset=\"gb2313\">");
//	      tmpContent.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//	      tmpContent.append("<meta name=\"robots\" content=\"index, follow\">");
//	      tmpContent.append("<title>苗途</title>");
//	      tmpContent.append("</head>");
//	      tmpContent.append("<body style=\"width:100%;padding:0px;margin:0px\" class=\"page\">");
//	      tmpContent.append("<p>嬲哒你一屋</p>");
//	      tmpContent.append("</body></html>");
//	      InputStream is = new ByteArrayInputStream(tmpContent.toString().getBytes());
	      String imgFile = "E:/222.jpg";
	      InputStream fin = null;
	    	try {
				fin = new FileInputStream(imgFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		//client.putObject(new PutObjectRequest(bucketName, "header/header48415487515421",createFile()));
		 ObjectMetadata metadata = new ObjectMetadata();
		 metadata.setContentType("image/jpeg");
		
		 client.putObject(new PutObjectRequest(bucketName, "444.jpg", fin, metadata));

		//client.createBucket(bucketName);
		// Do some operations with the instance...

		// Shutdown the instance to release any allocated resources
		//client.shutdown();
	}
    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("0123456789011234567890\n");
        writer.close();

        return file;
    }
    private static File createFile()
    {
    	File file = new File("E:/113.png");  
    	try {
			FileInputStream fin = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return file;
    }
}
