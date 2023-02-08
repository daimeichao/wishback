package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.service.ZyzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/zyz")
public class zyzController {
    @Autowired
    private ZyzService zyzService;
//    查询志愿者审核列表
@PostMapping("/shlist")
public ResultMap shList(@RequestBody Map<String, Object> params) {
    Map<String, Object> outmap =zyzService.shList(params);
    return ResultMap.ok().put("outmap", outmap);
}
    //    查询志愿者列表
    @PostMapping("/zyzlist")
    public ResultMap zyzList(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap =zyzService.zyzList(params);
        return ResultMap.ok().put("outmap", outmap);
    }
//    删除志愿者
   @PostMapping("/delById")
   public ResultMap delzyz(@RequestBody Map<String, Object> params) {
    Map<String, Object> outmap =zyzService.delzyzById(params);
    return ResultMap.ok().put("outmap", outmap);
}
//志愿者详情
@PostMapping("/getById")
public ResultMap getById(@RequestBody Map<String, Object> params) {
    Map<String, Object> outmap =zyzService.getById(params);
    return ResultMap.ok().put("outmap", outmap);
}
//志愿者审核
@PostMapping("/shById")
public ResultMap shById(@RequestBody Map<String, Object> params) {
    Map<String, Object> outmap =zyzService.shById(params);
    return ResultMap.ok().put("outmap", outmap);
}
//志愿者列表编辑
@PostMapping("/updById")
public ResultMap updById(@RequestBody Map<String, Object> params) {
    Map<String, Object> outmap =zyzService.updById(params);
    return ResultMap.ok().put("outmap", outmap);
}
//新增志愿者
@PostMapping("addzyz")
public Map<String, Object> addzyz(@RequestBody Map<String,Object> params) {
    return zyzService.addzyz(params);
}
}
