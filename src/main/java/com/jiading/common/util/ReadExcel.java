package com.jiading.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;



public class ReadExcel {



	  HSSFFormulaEvaluator hssfevaluator = null;
	    XSSFFormulaEvaluator xssffevaluator = null;

	    /**
	     * 读取excel中的数据
	     * @param path,i代表列数
	     * @return List<StudentBean>
	     * @author zhang 2015-08-18 00:08
	     */
	    public List<Map> readExcel(String path,int i) {

	        if (path != null && !path.equals("")) {
	            String ext = getExt(path);
	            if (ext!=null && !ext.equals("")) {
	                if (ext.equals("xls")) {
	                    return readXls(path,i);
	                } else if (ext.equals("xlsx")) {
	                    return readXlsx(path,i);
	                }
	            }
	        }
	        return new ArrayList<Map>();
	    }

	    /**
	     * 读取后缀为xls的excel文件的数据
	     * @param path
	     * @return List<StudentBean>
	     * @author zhang 2015-08-18 00:10
	     */
	    private List<Map> readXls(String path,int col) {

	        HSSFWorkbook hssfWorkbook = null;
	        try {
	            InputStream is = new FileInputStream(path);
	            hssfWorkbook = new HSSFWorkbook(is);
	            new HSSFFormulaEvaluator(hssfWorkbook);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        List<Map> list = new ArrayList<Map>();
	        if (hssfWorkbook != null) {
	            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	                if (hssfSheet == null) {
	                    continue;
	                }
	                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                    if (hssfRow != null) {
	                    	  Map map = new HashMap<String, Object>();
	                    	  for (int i = 0; i < col; i++) {
	                    		  map.put(i+"", getValue(hssfRow.getCell(i)).trim());
							  }
	                         list.add(map);
	                    }
	                }
	            }
	        }
	        return list;
	    }

	    /**
	     * 读取后缀为xlsx的excel文件的数据
	     * @param path
	     * @return List<StudentBean>
	     * @author zhang 2015-08-18 00:08
	     */
	    private List<Map> readXlsx(String path,int col) {

	        XSSFWorkbook xssfWorkbook = null;
	        try {
	            InputStream is = new FileInputStream(path);
	            xssfWorkbook = new XSSFWorkbook(is);
	            xssffevaluator = new XSSFFormulaEvaluator(xssfWorkbook);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        List<Map> list = new ArrayList<Map>();
	        if(xssfWorkbook!=null){
	            // Read the Sheet
	            for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
	                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
	                if (xssfSheet == null) {
	                    continue;
	                }
	                // Read the Row
	                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	                    if (xssfRow != null) {
	                    	Map studentBean = new HashMap<String, Object>();
	                        for (int i = 0; i < col; i++) {
	                        	XSSFCell  cell = xssfRow.getCell(i);
	                        	studentBean.put(i+"", getValue(cell).trim());
							}
	                        list.add(studentBean);
	                    }
	                }
	            }
	        }
	        return list;
	    }

	    /**
	     * 获取文件扩展名
	     * @param path
	     * @return String
	     * @author zhang 2015-08-17 23:26
	     */
	    private String getExt(String path) {
	        if (path == null || path.equals("") || !path.contains(".")) {
	            return null;
	        } else {
	            return path.substring(path.lastIndexOf(".") + 1, path.length());
	        }
	    }


	    /**
	     * 判断后缀为xlsx的excel文件的数据类型
	     * @param xssfCell
	     * @return String
	     * @author zhang 2015-08-18 00:12
	     */
	    @SuppressWarnings("static-access")
	    private String getValue(XSSFCell xssfCell) {
	    	if (xssfCell != null) {
	    		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
	 	            return String.valueOf(xssfCell.getBooleanCellValue());
	 	        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
           		if(HSSFDateUtil.isCellDateFormatted(xssfCell)){//用于转化为日期格式
	             		Date d = xssfCell.getDateCellValue();
	             		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	             		return formater.format(d);
           		}else{
           			//DecimalFormat df = new DecimalFormat("0.0000");
  	 	        	//String whatYourWant = df.format(xssfCell.getNumericCellValue());
					String nf = String.valueOf(xssfCell.getNumericCellValue());
					if(nf.endsWith(".0")){
						nf = nf.substring(0,nf.length()-2);
					}
  	 	            return nf;
					//return String.valueOf(xssfCell.getStringCellValue());
           		}
	 	        }else if (xssfCell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {//有公式的Excel单元格
					//这样对于字符串cell.getStringCellValue()方法即可取得其值，如果公式生成的是数值，使用cell.getStringCellValue()方法会抛出IllegalStateException异常，在异常处理中使用cell.getNumericCellValue();即可。
					try {  
						return String.valueOf(xssfCell.getStringCellValue());  
					} catch (IllegalStateException e) {  
						CellValue tempCellValue = xssffevaluator.evaluate(xssfCell);
						double iCellValue = tempCellValue.getNumberValue();
						return String.valueOf(iCellValue);   
					}
				} else {
	 	            return String.valueOf(xssfCell.getStringCellValue());
	 	        }
	    	}else{
	    		return "";
	    	}
	    }

	    /**
	     * 判断后缀为xls的excel文件的数据类型
	     * @param hssfCell
	     * @return String
	     * @author zhang 2015-08-18 00:12
	     */
	    @SuppressWarnings("static-access")
	    private String getValue(HSSFCell hssfCell) {
	    	if (hssfCell != null) {
	    		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
		            return String.valueOf(hssfCell.getBooleanCellValue());
		        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
		        	/*DecimalFormat df = new DecimalFormat("0.0000");
		        	String whatYourWant = df.format(hssfCell.getNumericCellValue());  
		            return whatYourWant;*/
					String nf = String.valueOf(hssfCell.getNumericCellValue());
					if(nf.endsWith(".0")){
						nf = nf.substring(0,nf.length()-2);
					}
					return nf;
		        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {//有公式的Excel单元格
					//这样对于字符串cell.getStringCellValue()方法即可取得其值，如果公式生成的是数值，使用cell.getStringCellValue()方法会抛出IllegalStateException异常，在异常处理中使用cell.getNumericCellValue();即可。
					try {  
						return String.valueOf(hssfCell.getStringCellValue());  
					} catch (IllegalStateException e) {  
						CellValue tempCellValue = hssfevaluator.evaluate(hssfCell);
						double iCellValue = tempCellValue.getNumberValue();
						return String.valueOf(iCellValue);  
					}
				} else {
		            return String.valueOf(hssfCell.getStringCellValue());
		        }
			}else{
				return "";
			}
	        
	    }
	
}
