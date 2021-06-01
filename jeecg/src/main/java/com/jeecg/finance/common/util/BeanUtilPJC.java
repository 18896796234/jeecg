package com.jeecg.finance.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author  pjc
 * @version 1.0
 */

public class BeanUtilPJC {
	
    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
    public static Map<String, Object> transBean2Map(Object obj,Map<String, Object> mapRun) {  
        if(obj == null){  
            return null;  
        }          
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName(); 
                Object value;
                // 过滤class属性  
                if (!key.equals("class")&&!key.equals("tradeDataCenter")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    //System.out.println("----key:"+key);
                    if(!key.equals("ref")&&!key.equals("refMap")){
                    	value = getter.invoke(obj);  
                    	mapRun.put(key, value);
                    }else{
                    	value="-blank-";
                    }
                    //System.out.println("----value:"+value);
                    if(key.equals("tradeDataCenter")&&value!=null){
                    	mapRun.putAll(transBean2Map(value, mapRun));
                    }
                    if(key.equals("buyCurrencyCode")){
                    	mapRun.put("ccy1", value);
                    }
                    if(key.equals("buyCurrencyAmount")
                    		||key.equals("buyAmount")){
                    	mapRun.put("amount1", value);
                    }
                    if(key.equals("sellCurrencyCode")){
                    	mapRun.put("ccy2", value);
                    }
                    if(key.equals("sellCurrencyAmount")
                    		||key.equals("sellAmount")){
                    	mapRun.put("amount2", value);
                    }
                    if(key.equals("optionPremiumCurrency")){
                    	mapRun.put("ccy3", value);
                    }
                    if(key.equals("optionPremium")){
                    	mapRun.put("amount3", value);
                    }
                    
                    if(key.equals("valueDate")){
                    	//String date = DateUtilPJC.date2String((Date) value,"yyyy-MM-dd");
                    	mapRun.put("validDate", value);
                    }
                    
                    
                    
                }  
            }  
        } catch (Exception e) {  
        	e.printStackTrace();
            System.out.println("transBean2Map Error " + e);  
        }  
        return mapRun;  
    }  
    
    // Bean --> Map List
    public static List<Map<String, Object>> addBeanList2MapList(List<Object> objList,
    		List<Map<String, Object>> mapList) {  
    	
    	for(Object object:objList){
            if(object != null){  
            	Map<String, Object> map = new HashMap<String, Object>();
            	map = transBean2Map(object, map);
            	mapList.add(map);
            } 
    	}
    	return mapList;
    } 
}
