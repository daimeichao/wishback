package com.jiading.common.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/xcx/data/")
public class WxTool {

    //pc端单文件上传
    @RequestMapping("/fileupload")
    @ResponseBody
    public Map AttachUpload(@RequestParam Map<String, String> paramsMap,
                            HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "file") MultipartFile file, HttpSession session){

        String err = "";
        Map<String, String> outmap = new HashMap<String, String>();

        String tempName = file.getOriginalFilename();//获取文件名加后缀
        if (tempName!=null&&!tempName.equals("")){
            String ext = tempName.substring(tempName.lastIndexOf(".")+1).toLowerCase();
            if(ext.equals("jpg")
                    || ext.equals("png")
                    || ext.equals("jpeg")
                    || ext.equals("bmp")
                    || ext.equals("gif")
                    || ext.equals("ico")
                    || ext.equals("mp3")
                    || ext.equals("mp4")
            ){
                String pathparam = "wx_xcx";
                String path =session.getServletContext().getRealPath("/upload/"+pathparam+"/") ;//文件存储路径

                File desfilepath = new File(path);
                if (!desfilepath.exists()) {//如果没有该路径
                    desfilepath.mkdirs();
                }
                String newfilename = Config.getAutoKey("f")+"."+ext;
                File targetFile = new File(path, newfilename);//文件路径和文件名
                String c_type = "";
                if(ext.equals("jpg")
                        || ext.equals("png")
                        || ext.equals("jpeg")
                        || ext.equals("bmp")
                        || ext.equals("gif")
                        || ext.equals("ico")){
                    c_type = "1";
                }
                if( ext.equals("mp3")|| ext.equals("mp4")){
                    c_type = "2";
                }

                try {
                    file.transferTo(targetFile);
                    outmap.put("c_url", "/upload/"+pathparam+"/"+newfilename);
                    outmap.put("c_type", c_type);
                    err="success";
                } catch (Exception e) {
                    e.printStackTrace();
                    err="err";
                }
            }else{
                err="不支持该文件格式";
            }
        }
        outmap.put("err", err);
        return outmap;

    }


}
