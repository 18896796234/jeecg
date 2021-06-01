/*package com.ting.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.krisk.app.jeecg.web.cgform.entity.config.CgFormFieldEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;



public class JsonUtilPJC {

    // 文件编码  
    public static final String ENCODE = "UTF-8";  
    
    
    public static JSONObject getJsonObject(InputStream input) throws Exception  {  
        InputStreamReader isw = new InputStreamReader(input, ENCODE);  
        BufferedReader br = new BufferedReader(isw);  
        String jsonStr = "";
        try {  
            String sname = null;  
            while ((sname = br.readLine()) != null) {  
                jsonStr += sname;
            }  
            JSONObject jsonObject = new JSONObject(jsonStr);
            return jsonObject;
        } catch (IOException e1) {  
            e1.printStackTrace();  
        }  
        return null;
    }     
    
    public static Set<String> getValueSet(JSONArray jsonArr,String key) throws Exception  {  
    	Set<String> set = new HashSet<String>(); 
        try {  
        	for(int i=0;i<jsonArr.length();i++){
        		JSONObject json = jsonArr.getJSONObject(i);
 				String value = json.getString(key);
 				value = value.replaceAll("_", "");
 				set.add(value);
 			}
        } catch (Exception e) {
        	e.printStackTrace();
		}
        return set;
    }     
    
    
	//拼table_json
	public static JSONObject getTableJson(String th0,String[] ths,
			List<String> td0List,Map<String, List<String>> ListMap) {
		
		JSONObject result = new JSONObject();
		JSONArray trsArray = new JSONArray();//表格内容
		JSONArray title = new JSONArray();//标题行
		title.put(th0);
		for (int i = 0; i < ths.length; i++) {
			title.put(ths[i]);
		}
		trsArray.put(title);
		for (String td0 : td0List) {
			JSONArray tr = new JSONArray();//内容行
			tr.put(td0);
			List<String> list = ListMap.get(td0);
			for (String td : list) {
				tr.put(td);
			}
			trsArray.put(tr);
		}
		result.put("result", trsArray);
			
		return result;
	}
	
	
	//将Bean --> Json 
    public static JSONObject getJsonByBean(Object obj) {  
    	JSONObject result = new JSONObject();
        if(obj == null){  
            return result;  
        }          
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
                    result.put(key, value);
                }  
            }  
        } catch (Exception e) {  
        	e.printStackTrace();
        }  
        return result;  
    }  
    
    
    public static boolean ArrayContainJson(JSONArray array,String key,String value) {  
    	for(int i=0;i<array.length();i++){
    		JSONObject json = array.getJSONObject(i);
    		if(json.get(key).equals(value)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public static JSONObject getJsonFromArrayByKey(JSONArray array,String key,String value) {  
    	for(int i=0;i<array.length();i++){
    		JSONObject json = array.getJSONObject(i);
    		if(json.get(key).equals(value)){
    			return json;
    		}
    	}
    	return new JSONObject();
    }
    
    public static JSONArray getArrayFromJsonByKey(JSONObject json,String key) {
    	return (json.isNull(key) ? new JSONArray() : json.getJSONArray(key));
    }
    
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		json.put("text", "11");

		json.put("childs", array);
		

		JSONObject json2 = new JSONObject();
		json2.put("text", "22");
		array.put(json2);
		
		StringUtilPJC.println(json.toString());
	}
	
    
	
}
*/