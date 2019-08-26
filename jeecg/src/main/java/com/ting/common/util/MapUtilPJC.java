package com.ting.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MapUtilPJC {

	/**
	 * 缓存参考数据【缓存】
	 */
	public static Map<String, Map<String, Object>> referenceMap = null;
	
	/**
	 * 缓存ric映射【缓存】
	 */
	public static Map<String, List<String>> ricMap = null;
	
	//获取String,-blank-
	public static String getString(Map<String, Object> map,String key,String blank) {
		String value = (map.get(key) == null ? blank : map.get(key).toString());
		if(key.contains("Date")&&!value.equals(blank)){
			Date date = (Date)map.get(key);
			value = DateUtilPJC.date2String(date);
		}
		return value;
	}
	
	//获取Date
	public static Date getDate(Map<String, Object> map,String key) {
		Date value = (Date)map.get(key);
		return value;
	}
	
	//获取后缀名
	public static Boolean isNotNull(Map<String, Object> map,String key) {
		Object str = map.get(key);
		return str!=null&&!str.toString().equals("");
	}
	
	/**
	 * 获取map的key
	 * 
	 * @return
	 */
	public String getKeysByMap(Map<String, Object> map){
		String keys = "";
		for(String key : map.keySet()){
			keys += key + ",";
        }
		if(keys.length()!=0){
			keys = keys.substring(0,keys.length()-1);
		}
		return keys;
	}
	
	
	//比较两个map中值是否相等
	public static Boolean valueEqual(Map<String, Object> map1,Map<String, Object> map2,
			String key) {
		return map1.get(key).toString().equals(map2.get(key).toString());
	}
	//添加，杨丹丹20180208,增加对数值为空的判断
		public static Boolean valueEqual1(Map<String, Object> map1,Map<String, Object> map2,
				String key) {
			boolean flag=false;
			if(null!=map1.get(key)&&null!=map2.get(key))
			{
				if(map1.get(key).toString().equals(map2.get(key).toString()))
					flag=true;
			}
			else if(null==map1.get(key)&&null==map2.get(key))
				flag=true;
			return flag;
		}
		
	//比较两个map中值是否相等
	public static Boolean valueEqual(Map<String, Object> map1,Map<String, Object> map2,
			String key1,String key2) {
		return map1.get(key1).toString().equals(map2.get(key2).toString());
	}
	
	
	//获取Double,0
	public static double getDouble(Map<String, Object> map,String key) {
		Object valueO = map.get(key);
		double value = (valueO == null ? 0 : Double.valueOf(valueO.toString()));
		return value;
	}
	
	//获取Double,0
	public static Integer getInteger(Map<String, Object> map,String key) {
		Object valueO = map.get(key);
		Integer value = (valueO == null ? null : Integer.valueOf(valueO.toString()));
		return value;
	}
}
