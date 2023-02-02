package com.jiading.common.util;


import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/upload")
@SuppressWarnings("all")
public class FC {
    @Value("${file.address}")
    private String localpath; // 对外暴露访问路径

    @RequestMapping("/fileupload")
    @ResponseBody
    public JSONArray fileupload2(@RequestParam(value = "file") MultipartFile[] file, @RequestParam(value = "path") String pathparam, HttpSession session) {
        JSONArray json = new JSONArray();
        for (int i = 0; i < file.length; i++) {
            MultipartFile ff = file[i];
            String tempName = ff.getOriginalFilename();
            // 后缀
            String suffix = ff.getOriginalFilename().substring(ff.getOriginalFilename().lastIndexOf("."));
            //ApplicationHome h = new ApplicationHome(getClass());
            //File jarF = h.getSource();
            //String paths = jarF.getParentFile().toString() + "/upload/" + path;
            //String paths = session.getServletContext().getRealPath("/upload/" + pathparam + "/");

            String paths = localpath+ pathparam + "/";

            File dir = new File(paths);
            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs();
            }
            String filename=Config.getAutoKey(pathparam)+suffix; // 随机生成文件名称
            File targetFile = new File(paths, filename);
            try {
                ff.transferTo(targetFile);
                Map map = new HashMap();
                map.put("name", tempName);
                map.put("url", "/upload/" + pathparam + "/" + filename);
                json.add(map);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return json;
    }

    @RequestMapping("/fileupload1")
    @ResponseBody
    public JSONObject fileupload2(@RequestParam(value = "file") MultipartFile[] file, HttpSession session) {
        //JSONArray json = new JSONArray();
        JSONObject json=new JSONObject();
        for (int i = 0; i < file.length; i++) {
            MultipartFile ff = file[i];
            String tempName = ff.getOriginalFilename();
            // 后缀
            String suffix = ff.getOriginalFilename().substring(ff.getOriginalFilename().lastIndexOf("."));
            //ApplicationHome h = new ApplicationHome(getClass());
            //File jarF = h.getSource();
            //String paths = jarF.getParentFile().toString() + "/upload/" + path;
            //String paths = session.getServletContext().getRealPath("/upload/" + pathparam + "/");

            String paths = localpath+ "file" + "/";

            File dir = new File(paths);
            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs();
            }
            String filename=Config.getAutoKey("file")+suffix; // 随机生成文件名称
            File targetFile = new File(paths, filename);
            try {
                ff.transferTo(targetFile);

                json.put("codeEnum", "SUCCESS");
                json.put("code", 1);
                json.put("msg", "上传成功");
                json.put("success", true);
                String [] url = {"/upload/" + "file" + "/" + filename};
                json.put("data",url);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return json;
    }

    @RequestMapping("/fileupload3")
    @ResponseBody
    public JSONObject fileupload2(@RequestParam(value = "file") MultipartFile file, HttpServletResponse resp, HttpSession session, @RequestParam String callBackPath) {
        //JSONArray json = new JSONArray();
        JSONObject json=new JSONObject();

//            MultipartFile ff = file[i];
            String tempName = file.getOriginalFilename();
            // 后缀
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //ApplicationHome h = newfile ApplicationHome(getClass());
            //File jarF = h.getSource();
            //String paths = jarF.getParentFile().toString() + "/upload/" + path;
            //String paths = session.getServletContext().getRealPath("/upload/" + pathparam + "/");

            String paths = localpath + "/";

            File dir = new File(paths);
            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs();
            }
            String filename=Config.getAutoKey("file")+suffix; // 随机生成文件名称
            File targetFile = new File(paths, filename);
            try {

                file.transferTo(targetFile);

//                json.put("codeEnum", "SUCCESS");
//                json.put("code", 1);
//                json.put("msg", "上传成功");
//                json.put("success", true);
                String  url = "/upload/" + filename;
                json.put("url",url);

                resp.sendRedirect(callBackPath+"#/redirect?error=0&url="+url);

            } catch (Exception e) {

                e.printStackTrace();
            }


        return null;
    }
}
