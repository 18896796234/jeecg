/*package com.ting.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class BatchDateUtils {
	
//	private final static String batchPath = "/Users/admin/Desktop/cfg/batchdate.txt";
	
	public static void main(String[] args) {
		
		String batchdate = "20160812";
		try {
            System.out.println( getSpecifiedDayAfter(batchdate));
           
        } catch(Exception px) {
            px.printStackTrace();
        }
	}
	
	
	public static String getBatchDate(String batchPath) {
		
		String batchDate = "";
		
		String filePath = batchPath+"/batchdate.txt";
		
		try {
			
			InputStreamReader reader = new InputStreamReader(  
			        new FileInputStream(filePath));
			BufferedReader br = new BufferedReader(reader); 	// 建立一个对象，它把文件内容转成计算机能读懂的语言  
	        String line = "";  
	        line = br.readLine(); 
	        
	        if(isFormat(line)) {
	        		return line;
	        }
	        
	        br.close();
	        reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 			
        
        
		return batchDate;
	}
	

	
	*//**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 *//*
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		return dayAfter;
	}
	
	*//**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 *//*
	public static String getSpecifiedDayBefore(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}

	public static void cutoffDate(String folder) {
		
		String batchdate = getBatchDate(folder);
		  写入date文件   
		try {
			File writename = new File(folder + "/batchdate.txt");
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write(getSpecifiedDayAfter(batchdate));
			out.flush(); // 把缓存区内容压入文件
			out.close(); // 最后记得关闭文件

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
*/