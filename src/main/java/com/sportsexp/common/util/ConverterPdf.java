package com.sportsexp.common.util;


import java.awt.Insets;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;

import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

/**
 * * <p>Title:ConverterPdf </p>
 * <p>Description:html转PDF </p>
 * <p>Company: sportsexp </p> 
 * @author zhijiang.zhang
 * @date 2016年6月23日 上午9:39:34
 */
public class ConverterPdf {
	protected int topValue = 10;
    protected int leftValue = 20;
    protected int rightValue = 10;
    protected int bottomValue = 10;
    protected int userSpaceWidth = 1300;
    
    public static void main(String[] args) throws InvalidParameterException, MalformedURLException, IOException {
    	ConverterPdf pdfUtils = new ConverterPdf();
        pdfUtils.doConversion("http://10.20.10.88:8080/htmlUrl/20160622/1466575038335.html", "d:/test/pd4ml.pdf");//网络地址
//        String html = readFile("g:/test/confirm.html", "UTF-8");  //文件地址
//        pdfUtils.doConversion2(html, "g:/test/pd4ml2.pdf");  
    }
    
    /**将文件转换成pdf,源文件为http://开头的网络地址*/
    public void doConversion(String url, String outputPath)
            throws InvalidParameterException, MalformedURLException,
            IOException {
        File output = new File(outputPath);
        FileOutputStream fos = new FileOutputStream(output);
        PD4ML pd4ml = new PD4ML();
        pd4ml.setPageInsets(new Insets(5, 20, 20, 20));
        pd4ml.setHtmlWidth(1000);
        pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
        pd4ml.useTTF("java:fonts", true);
        pd4ml.setDefaultTTFs("SimHei", "Arial", "Courier New");
        pd4ml.enableDebugInfo();
        pd4ml.render(new URL(url), fos); 
        fos.close();
        System.out.println(outputPath + "\ndone.");
    }
    
    public void doConversion2( String htmlDocument, String outputPath )   
            throws InvalidParameterException, MalformedURLException, IOException {  
        PD4ML pd4ml = new PD4ML();  
        pd4ml.setHtmlWidth(userSpaceWidth); 
        // 选择目标文件的格式
        pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));   
        // 设置边距  
        pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));   
        //原来的html文档也有边距，可以通过这个方式压缩 
        pd4ml.addStyle("BODY {margin: 0}", true);  
        // 如果内置的基本pdf字体不够用，可以设置成non-Latin，TTF能够做到这一点
        pd4ml.useTTF("D:/test/fonts", true);  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        pd4ml.render(new StringReader(htmlDocument), baos);   
        baos.close();  
        File output = new File(outputPath);  
        FileOutputStream fos = new FileOutputStream(output);  
        fos.write( baos.toByteArray() );  
        fos.close();  
        System.out.println( outputPath + "\ndone." );  
    }  
  
    private final static String readFile( String path, String encoding ) throws IOException {  
        File f = new File( path );  
        FileInputStream is = new FileInputStream(f);  
        BufferedInputStream bis = new BufferedInputStream(is);  
        ByteArrayOutputStream fos = new ByteArrayOutputStream();  
        byte buffer[] = new byte[2048];  
        int read;  
        do {  
            read = is.read(buffer, 0, buffer.length);  
            if (read > 0) {   
                fos.write(buffer, 0, read);   
            }  
        } while (read > -1);  
        fos.close();  
        bis.close();  
        is.close();  
        return fos.toString(encoding);  
    }  
	
}
