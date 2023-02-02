package com.jiading.modules.back.jbgsgl.service;

import com.jiading.common.util.FenYe;
import com.jiading.modules.back.jbgsgl.dao.JbgsDao;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileService {

    public Map upload(MultipartFile uploadFile, HttpServletRequest req){
        Map<String,Object> outmap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        //设置上传文件的保存路径为项目运行目录下的uploadFile文件夹
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        //获取当前日期对所上传的文件进行归类保存
        String format = sdf.format(new Date());
        File folder = new File(realPath+format);
        if (!folder.isDirectory()){//判断是否为目录
            folder.mkdirs();//不是目录就创建目录
        }
        //给上传的文件重命名
        String oldName = uploadFile.getOriginalFilename();//获取文件名称
        String newName = UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());//设置新文件名
        try{
            //文件保存操作
            uploadFile.transferTo(new File(folder,newName));
            //生成文件访问路径
            String filePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/uploadFile/"+format+newName;
            outmap.put("url",filePath);
            outmap.put("result","ok");
            return outmap;
        }catch (Exception e){
            e.printStackTrace();
        }
        outmap.put("result","err");
        return outmap;

    }

    /**
     * 导出
     */
    public Map ExportExcel(Map<String, Object> params) {
        Map outmap = new HashMap<String, Object>();
        //ppath:E:\wokespace\myeclipse2014workspace\cswl\WebRoot\
        String path = "temple/t.xlsx";// 文件模板路径
        File file = new File(path);
        String realPath = "upload/temple";   // 保存文件的路径
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
                String[] colnamearr = params.get("colnames").toString().split(",");
                String[] coltitlearr = params.get("coltitles").toString().split(",");
                Row row = sheet.createRow(0); //第一个sheet的第一行
                Cell cell =null;
                //生成标题
                for (int i = 0; i < colnamearr.length; i++) {//表格的第一行是标题
                    cell = row.createCell(i);
                    cell.setCellValue(coltitlearr[i]);
                }
                List<Map<String,Object>> list = (List)params.get("list");
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
        outmap.put("result","ok");
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
}
