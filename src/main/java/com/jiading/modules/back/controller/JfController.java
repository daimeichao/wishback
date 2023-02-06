package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.service.JfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/jf")
public class JfController {
    @Autowired
  private   JfService jfService;
    @PostMapping("/jflist")
    public ResultMap getList(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap = jfService.getListByMap(params);
        return ResultMap.ok().put("outmap", outmap);
    }
    //    删除志愿者
    @PostMapping("/delById")
    public ResultMap delzyz(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap =jfService.delzyzById(params);
        return ResultMap.ok().put("outmap", outmap);
    }
    //志愿者详情
    @PostMapping("/getById")
    public ResultMap getById(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap =jfService.getById(params);
        return ResultMap.ok().put("outmap", outmap);
    }
}
