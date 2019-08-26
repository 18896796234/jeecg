package com.ting.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtilPJC {

	
	//转字符串
	public static String toString(List<String> list) {
		String result = "";
		for(String s:list){
			result += s + ",";
		}
		if(result.length()>0){
			result = result.substring(0,result.length()-1);
		}
		return result;
	}
	
	//求和
	public static List<String> addListByNum(List<String> list,int i,String num) {
		String sum = list.get(i);
		sum = NumUtilPJC.add(sum, num);
		list.set(i, sum);
		return list;
	}
	
	//String装List
	public static List<String> getListByString(String str){
		List<String> list = new ArrayList<String>();
		if(StringUtilPJC.isNotNull(str)){
			String[] strArray = str.split(",");
			for(String s:strArray){
				list.add(s);
			}
		}
		return list;
	}
	
	//String装List
	public static List<String> getListByArray(String[] array){
		List<String> list = new ArrayList<String>();
		for(String str:array){
			list.add(str);
		}
		return list;
	}
	
	

}
