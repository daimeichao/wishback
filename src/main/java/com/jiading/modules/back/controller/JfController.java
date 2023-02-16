package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.service.JfService;
import com.jiading.modules.xcx.service.XcxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/jf")
public class JfController {
    @Autowired
  private   JfService jfService;
    @Autowired
    private XcxService xcxService;
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
    //积分详情
    @PostMapping("/getById")
    public ResultMap getById(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap =jfService.getById(params);
        return ResultMap.ok().put("outmap", outmap);
    }
//    积分排行榜
@PostMapping("getphb")
public ResultMap getphb(@RequestBody Map<String, Object> params) {
    Map<String, Object> outmap = jfService.getphb(params);
    return ResultMap.ok().put("outmap", outmap);
}
    //    删除排行榜
    @PostMapping("/delphb")
    public ResultMap delphb(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap =jfService.delphb(params);
        return ResultMap.ok().put("outmap", outmap);
    }
    //    新增商品
@PostMapping("addsp")
public Map<String, Object> addsp(@RequestBody Map<String,Object> params) {
    return jfService.addsp(params);
}
    //    编辑商品
    @PostMapping("updsp")
    public Map<String, Object> updsp(@RequestBody Map<String,Object> params) {
        return jfService.updsp(params);
    }
//    删除商品
@PostMapping("delsp")
public Map<String, Object> delsp(@RequestBody Map<String,Object> params) {
    return jfService.delsp(params);
}
//商品列表
@PostMapping("/splist")
public ResultMap splist(@RequestBody Map<String, Object> params) {
    Map<String, Object> outmap = jfService.splist(params);
    return ResultMap.ok().put("outmap", outmap);
}
    //商品详情
    @PostMapping("/spById")
    public ResultMap spById(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap =jfService.spById(params);
        return ResultMap.ok().put("outmap", outmap);
    }
}
