package com.jiading.modules.xcx.controller;


import com.alibaba.fastjson.JSONArray;
import com.jiading.common.util.Config;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.xcx.service.XcxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/xcx/api/")
@CrossOrigin
public class XcxController {
    @Value("${file.address}")
    private String localpath; // 对外暴露访问路径

    @Autowired
    private XcxService xcxService;


    @PostMapping("getUserByOpenId")
    public ResultMap getUserByOpenId(@RequestBody Map<String,Object> params) {
        return xcxService.getUserByOpenId(params);
    }
    @PostMapping("addUser")
    public ResultMap addUser(@RequestBody Map<String,Object> params) {
        return xcxService.addUser(params);
    }


    @PostMapping("getBannerList")
    public ResultMap getBannerList(@RequestBody Map<String,Object> params) {
        return xcxService.getBannerList();
    }

    @PostMapping("getWishList")
    public ResultMap getWishList(@RequestBody Map<String,Object> params) {
        return xcxService.getWishList(params);
    }

//获取积分排行榜
@PostMapping("getphb")
public ResultMap getphb(@RequestBody Map<String,Object> params) {
    return xcxService.getphb(params);
}

    @PostMapping("getWishDetail")
    public ResultMap getWishDetail(@RequestBody Map<String,Object> params) {
        return xcxService.getWishDetail(params);
    }


    @PostMapping("realizationWish")
    public ResultMap realizationWish(@RequestBody Map<String,Object> params) {
        return xcxService.realizationWish(params);
    }


    @PostMapping("addWish")
    public ResultMap addWish(@RequestBody Map<String,Object> params) {
        return xcxService.addWish(params);
    }

    @PostMapping("getBannerDetail")
    public ResultMap getBannerDetail(@RequestBody Map<String,Object> params) {
        return xcxService.getBannerDetail(params);
    }




    @RequestMapping("/fileupload")
    @ResponseBody
    public JSONArray fileupload(HttpServletRequest request, @RequestParam(value = "file") MultipartFile[] file, @RequestParam(value = "path") String pathparam, HttpSession session) {
        JSONArray json = new JSONArray();

        String wishid = request.getParameter("wishid");
        String name  = request.getParameter("name");


        for (int i = 0; i < file.length; i++) {
            MultipartFile ff = file[i];
            String tempName = ff.getOriginalFilename();
            // 后缀
            String suffix = ff.getOriginalFilename().substring(ff.getOriginalFilename().lastIndexOf("."));
            String paths = localpath+ pathparam + "/";
            File dir = new File(paths);
            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs();
            }
            String filename= Config.getAutoKey(pathparam)+suffix; // 随机生成文件名称
            File targetFile = new File(paths, filename);
            try {
//                ff.transferTo(targetFile);
                FileCopyUtils.copy(ff.getBytes(), targetFile);



                Map map = new HashMap();
                map.put("name", tempName);
                map.put("url", "/upload/" + pathparam + "/" + filename);
                json.add(map);

                Map params =new HashMap();
                params.put("wishid",wishid);
                params.put("name",name);
                params.put("url", "/upload/" + pathparam + "/" + filename);
                xcxService.addFile(params);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return json;
    }

}
