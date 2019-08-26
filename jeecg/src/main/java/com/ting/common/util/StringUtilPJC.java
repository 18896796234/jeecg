package com.ting.common.util;


public class StringUtilPJC{

	
	//打印String
	public static void print(String str) {
		System.out.print(str);
	}
	
	//打印String,换行
	public static void println(String str) {
		System.out.println(str);
	}
	
	//包含不为空
	public static boolean contains(String str1,String str2) {
		return str1!=null&&str1.contains(str2);
	}
	
	//相等不为空
	public static boolean equals(String str1,String str2) {
		return str1!=null&&str1.equals(str2);
	}
	
	//数组字符插入单引号
	public static String arrayStrInsert(String arrayStr) {
		String[] strs = arrayStr.split(",");
		String newArrayStr = "";
		for(String str:strs){
			newArrayStr += "'" + str + "',";
		}
		if(strs.length>0){
			newArrayStr = newArrayStr.substring(0,newArrayStr.length()-1);
		}
		return newArrayStr;
	}
	
	//去掉最后一个字符
	public static String removeLastChar(String str) {
		if(str.length()>0){
			return str.substring(0,str.length()-1);
		}
		return str;
	}
	
	//获取后缀名
	public static String getEndAddress(String str,String point) {
		int endAddress = str.lastIndexOf(point);
		String endStr = str.substring(endAddress+point.length(), str.length());//后缀名
		return endStr;
	}

	//非空
	public static Boolean isNotNull(String str) {
		return str!=null&&!str.equals("");
	}
	
	//非空
	public static Boolean isNotNull(Object obj) {
		return obj!=null&&!obj.toString().equals("");
	}
	
	//string空值替换
	public static String replaceNull(String str1,String str2) {
		return str1 == null ? str2 : str1;
	}
	
	//string空值替换
	public static String getString(Object str) {
		return str == null ? "" : str.toString();
	}

	//string空值替换
	public static String replaceNull(Object str1,String str2) {
		return str1 == null ? str2 : str1.toString();
	}
	
	//去掉后缀名
	public static String removeEndAddress(String str,String point) {
		int endAddress = str.lastIndexOf(point);
		String newStr;
		
		if(endAddress>0){
			newStr = str.substring(0, endAddress);//后缀名
		}else{
			newStr = str;
		}
		
		return newStr;
	}
	
}
