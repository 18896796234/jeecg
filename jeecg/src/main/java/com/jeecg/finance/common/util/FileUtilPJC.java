/*package com.ting.common.util;
  
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;  
import java.util.ArrayList;
import java.util.Properties;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;  
  
public class FileUtilPJC {  
    // CSV文件编码  
    public static final String ENCODE = "UTF-8";  
  
     
    public static JSONObject getFile(String filename) throws Exception {  
    	
    	String rootPath = FileUtilPJC.class.getResource("/").getPath();
    	System.out.println(rootPath);
    	//String filePath = rootPath + filename;
    	String filePath = rootPath + filename;
    	System.out.println(filePath);
    	File file = new File(filePath); 
        InputStream in = new FileInputStream(file);
        try { 
        	JSONObject json = JsonUtilPJC.getJsonObject(in);
        	System.out.println(json);
        	return json;
        } catch (IOException e) { 
            e.printStackTrace(); 
        }  
        return null;
    }
    
    public static void main(String args[]){ 
    	try {
			getFile("");
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    
    
}  */