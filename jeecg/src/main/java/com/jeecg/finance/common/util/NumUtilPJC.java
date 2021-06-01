package com.jeecg.finance.common.util;

import java.util.Random;

public class NumUtilPJC {

	
	//两个数字相加
	public static String add(String num1,String num2) {
		Double sum = Double.valueOf(num1).doubleValue()+
				Double.valueOf(num2).doubleValue();
		java.text.DecimalFormat to= new java.text.DecimalFormat("#0.00");
		return to.format(sum);
	}
	
	//生成随机数
	public static String getNum() {
		String result = "";
        int max=100;
        int min=0;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        
        int max2=99;
        int min2=0;
        Random random2 = new Random();
        int s2 = random2.nextInt(max2)%(max2-min2+1) + min2;
        result = s+"."+s2;
        System.out.println(result);
        
        if(s2<10){
        	result = result + "0";
        }
        
        if(s2>50){
        	result = "-" + result;
        }
		
		return result;
	}
	
	//string转Double
	public static Object getDouble(Object num) {
		try{
			Double d = Double.valueOf(num.toString()).doubleValue();
			//java.text.DecimalFormat to= new java.text.DecimalFormat("#.#");
			//return to.format(d);
			return d;
		}catch (Exception e) {
		}
		return num;
	}
	
	//string转Double
	public static String getStringReplaceZero(double num) {
		try{
			if(num==0){
				return "null";
			}else{
				return "" + num;
			}
		}catch (Exception e) {
		}
		return null;
	}
	
	
	//string转Double
	public static Double add(Double num1,Double num2) {
		try{
			if(num1==null){
				num1 = (double) 0;
			}
			if(num2==null){
				num1 = (double) 0;
			}
		}catch (Exception e) {
		}
		return num1 + num2;
	}
	
	//根据条件把数字设为nul
	public static Double getNull(Double num1,String symbol,Double num2) {
		try{
			if(symbol.equals("<")){
				if(num1<num2){
					return null;
				}
			}else if(symbol.equals(">=")){
				if(num1>=num2){
					return null;
				}
			}
		}catch (Exception e) {
		}
		return num1;
	}
  

}
