import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;


public class DomTest {
	public static String xmlContent = "<CSubmitState xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://tempuri.org/'>"
			+"<State>1032</State>"
			+"<MsgID>0</MsgID>"
			+"<MsgState>自由签名的产品101281814,签名格式不正确</MsgState>"
			+"<Reserve>0</Reserve>"
			+"</CSubmitState>";

	public static HashMap<String,String> ValidateMap = new HashMap<String,String>(); 
	public static <T> T converyToJavaBean(String xml, Class<T> c) {  
		T t = null;  
		try {  
			JAXBContext context = JAXBContext.newInstance(c);  
			Unmarshaller unmarshaller = context.createUnmarshaller();  
			t = (T) unmarshaller.unmarshal(new StringReader(xml));  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  

		return t;  
	}  

	public static void main(String[] args) throws Exception  
	{  
		//		DomTest domTest  = new DomTest();
		//		ResponseBean responseBean =new ResponseBean();
		//		CSubmitState  cSubmitState = DomTest.converyToJavaBean(xmlContent,CSubmitState.class);
		//		System.out.println(cSubmitState.toString());
		//		// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）  
		//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		//		DocumentBuilder db = dbf.newDocumentBuilder();  
		//        InputStream  inIos  = new ByteArrayInputStream(xmlContent.getBytes("UTF-8"));
		//		Document document = db.parse(inIos);  
		//		NodeList list = document.getElementsByTagName("CSubmitState");  
		//
		//		for(int i = 0; i < list.getLength(); i++)  
		//		{  
		//			Element element = (Element)list.item(i);  
		//
		//			String content = element.getElementsByTagName("State").item(0).getFirstChild().getNodeValue();  
		//
		//			System.out.println("State:" + content);  
		//
		//			content = element.getElementsByTagName("MsgID").item(0).getFirstChild().getNodeValue();  
		//
		//			System.out.println("MsgID:" + content);  
		//
		//			content = element.getElementsByTagName("MsgState").item(0).getFirstChild().getNodeValue();  
		//
		//			System.out.println("MsgState:" + content);  
		//
		//			content = element.getElementsByTagName("Reserve").item(0).getFirstChild().getNodeValue();  
		//
		//			System.out.println("Reserve:" + content);  
		//		}  
		ValidateMap.put("11", "a");
		ValidateMap.put("12", "a");
		Iterator<Map.Entry<String, String>> it = ValidateMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
	}
}
