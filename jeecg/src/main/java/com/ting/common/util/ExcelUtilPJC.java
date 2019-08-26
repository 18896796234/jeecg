/*package com.ting.common.util;
  
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.krisk.app.jeecg.core.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;  
  
public class ExcelUtilPJC {  
    // 文件编码  
    public static final String ENCODE = "UTF-8";  
  
    //获取excel第一个sheet页的所有行
 	public static Iterator<Row> getRows(MultipartFile file){
		try {
			InputStream input = file.getInputStream(); // 建立输入流
			String fieldName = file.getOriginalFilename();
			boolean isE2007 = false; // 判断是否是excel2007格式
			if (fieldName.endsWith("xlsx")) {
				isE2007 = true;
			}
			Workbook wb = null;
			// 根据文件格式(2003或者2007)来初始化
			if (isE2007){
				wb = new XSSFWorkbook(input);
			}else{
				wb = new HSSFWorkbook(input);
			}
		    Sheet sheet = wb.getSheetAt(0); // 获得第一个表单
		    Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
		    return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
 	} 
    
    //取出一行的所有数据
 	public static List<String> getRowNameList(Row row){
 		List<String> rowNameList = new ArrayList<String>();
 		int columnNum=row.getPhysicalNumberOfCells();
		for(int i=0;i<columnNum;i++){
			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
			String cell = row.getCell(i).getStringCellValue();
			rowNameList.add(cell);
		}
		return rowNameList;
 	}
 	
 	//从第一行取出所有列名
 	public static Map<String, Object> getMap(Row row,List<String> rowNameList){
 		Map<String, Object> map = new HashMap<String, Object>();
 		for(int i=0;i<rowNameList.size();i++){
			Cell cell = row.getCell(i);
			Object cellValue = null;
			if(cell!=null){
				if(cell.getCellType()==0
						&&HSSFDateUtil.isCellDateFormatted(cell)){
					cellValue = DateUtil.date2String(
							cell.getDateCellValue(),"yyyy-MM-dd");
				}else{
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = cell.getStringCellValue();
				}
				
			}
			map.put(rowNameList.get(i), cellValue);
		}
 		return map;
 	}
    
 	//判断单元格不为空
 	public static boolean isNotNull(Cell cell){
 		if(cell!=null){
 			cell.setCellType(Cell.CELL_TYPE_STRING);
 			return StringUtilPJC.isNotNull(cell.getStringCellValue());
 		}
 		return false;
 	}
    
 	
    
    
}  */