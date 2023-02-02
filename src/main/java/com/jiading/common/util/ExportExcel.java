package com.jiading.common.util;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
@RequestMapping("/ExportExcel")
@SuppressWarnings("all")
public class ExportExcel {

	@Value("${file.address}")
	private String localpath; // 对外暴露访问路径
		
	
	/**
	 * 导出
	 * @param ppath		
	 * @param list
	 * @param colnames
	 * @param coltitles
	 * @return
	 */
	public Map ExportExcel(String ppath,List<Map> list,String colnames,String coltitles) {
		 Map outmap = new HashMap<String, Object>();
		 	//ppath:E:\wokespace\myeclipse2014workspace\cswl\WebRoot\
	        String path = ppath + "temple/t.xlsx";// 文件模板路径  
	        File file = new File(path);  
	        String realPath = ppath + "upload/temple";   // 保存文件的路径   
	        String newFileName = System.currentTimeMillis() + ".xlsx";// 新的文件名  
	        // 判断路径是否存在  
	        File dir = new File(realPath);  
	        if (!dir.exists()) {//路径不存则创建路径  
	            dir.mkdirs();  
	        }  
	        // 写入到新的excel  (路径，文件名)
	        File newFile = new File(realPath, newFileName);  
	        try {  
	            newFile.createNewFile();  
	            //(模板,新文件)
	            fileChannelCopy(file, newFile); // 复制模板到新文件  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	   
	       InputStream is = null;  
	       SXSSFWorkbook workbook = null;
	       Sheet sheet = null;  
	        try {  
	            is = new FileInputStream(newFile);// 将excel文件转为输入流  
	            XSSFWorkbook workbook1 = new XSSFWorkbook(is);
	            workbook = new SXSSFWorkbook(workbook1, 100);
	            sheet = workbook.getSheetAt(0); // 获取第一个sheet 
	        } catch (Exception e1) {  
	            e1.printStackTrace();  
	        }
	        if (sheet != null) {  
	            try {  
	                // 写数据 
	                FileOutputStream fos = new FileOutputStream(newFile); 
	                String[] colnamearr = colnames.split(",");
	                String[] coltitlearr = coltitles.split(",");
	                Row row = sheet.createRow(0); //第一个sheet的第一行
	                Cell cell =null;
	                //生成标题
	                for (int i = 0; i < colnamearr.length; i++) {//表格的第一行是标题
	                	cell = row.createCell(i);   
	  	                cell.setCellValue(coltitlearr[i]);
					}
	                //生成数据
	                for(int i=0;i<list.size();i++){
	                	Map map = list.get(i);//每个对象就是一行
	                	row = sheet.createRow(i+1); //因为第一行是标题，所以从第二行开始
	                	for (int j = 0; j < colnamearr.length; j++) {
                			cell = row.createCell(j);  
	                		cell.setCellValue(String.valueOf(map.get(colnamearr[j])==null?"":map.get(colnamearr[j])));
	                	}
	                }
	              
	                workbook.write(fos);  
	                fos.flush();  
	                fos.close(); 
	  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            } finally {  
	                try {  
	                    if (null != is) {  
	                        is.close();  
	                    }  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        } 
	        //文件删除
	        outmap.put("url","upload/temple/"+newFileName);
	        return outmap;
	}
	
	//(模板,新文件)
	 public void fileChannelCopy(File s, File t) {  
	        try {  
	            InputStream in = null;  
	            OutputStream out = null;  
	            try {  
	                in = new BufferedInputStream(new FileInputStream(s), 1024);  
	                out = new BufferedOutputStream(new FileOutputStream(t), 1024);  
	                byte[] buffer = new byte[1024];  
	                int len;  
	                while ((len = in.read(buffer)) != -1) {  
	                    out.write(buffer, 0, len);  
	                }  
	            } finally {  
	                if (null != in) {  
	                    in.close();  
	                }  
	                if (null != out) {  
	                    out.close();  
	                }  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
    }
	/**
	 * 导出Excel
	 *
	 * @param sheetName sheet名称
	 * @param title     表格第一行标题
	 * @param values    表格内容内容
	 * @return
	 */
	public static XSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, XSSFWorkbook xb) {

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if (xb == null) {
			xb = new XSSFWorkbook();
		}

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = xb.createSheet(sheetName);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		XSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		XSSFCellStyle style = xb.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		// 声明列对象
		XSSFCell cell = null;

		// 创建标题
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}

		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(2, 8000);
		sheet.setColumnWidth(3, 8000);
		sheet.setColumnWidth(4, 8500);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);

		// 创建内容
		for (int i = 0; i < values.length; i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < values[i].length; j++) {
				// 将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values[i][j]);
			}
		}
		return xb;
	}

	public HSSFWorkbook getHSSFWorkbook(String sheetName,String[] title,String[][] values,HSSFWorkbook workbook){
		//创建一个HSSFWorkbook,对应一个Excel文件
		if(workbook == null){
			workbook = new HSSFWorkbook();
		}
		//在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = workbook.createSheet(sheetName);
		//在sheet中添加表头第0行
		HSSFRow row = sheet.createRow(0);
		//创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//声明列对象
		HSSFCell cell = null;
		//创建标题
		for(int i = 0;i < title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(cellStyle);
		}
		//创建内容
		for(int i = 0;i < values.length;i++){
			row = sheet.createRow(i+1);
			for(int j = 0;j < values[i].length;j++){
				//将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values[i][j]);
			}
		}
		return workbook;
	}

	public void setResponseHeader(HttpServletResponse response,String fileName){
		try{
			fileName = new String(fileName.getBytes(),"ISO8859-1");
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition","attachment;filename="+fileName);
			response.addHeader("Pargam","no-cache");
			response.addHeader("Cache-Control","no-cache");
			response.setContentType("application/vnd.ms-excel;charset=gb2312");

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(HttpServletResponse response){
		String[][] content = new String[2][2];
		content[0][0]="1";
		content[0][1]="1";
		content[1][0]="1";
		content[1][1]="1";
		//Excel标题
		String[] title={"姓名","联系方式"};
		//Excel文件名
		String fileName = "揭榜人名单.xls";
		//sheet名
		String sheetName="揭榜人名单";
		//导出excel
		try{
			HSSFWorkbook hssfWorkbook = getHSSFWorkbook(sheetName,title,content, (HSSFWorkbook) null);
			OutputStream outputStream = response.getOutputStream();
			hssfWorkbook.write(outputStream);
			setResponseHeader(response,fileName);
			outputStream.flush();
			outputStream.close();
		}catch (Exception e){

		}
	}

	
}
