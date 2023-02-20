package com.jiading.modules.xcx.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.xcx.service.XcxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/xcx/my/")
@CrossOrigin
public class MyController1 {
    @Autowired
    private XcxService xcxService;
//    获取到我的商品兑换记录
    @PostMapping("getmyjf")
    public ResultMap getmydh(@RequestBody Map<String,Object> params) {
        return xcxService.getmyjf(params);
    }
//获取到我的志愿者申请信息
@PostMapping("getmyzyz")
public ResultMap getmyzyz(@RequestBody Map<String,Object> params) {
    return xcxService.getmyzyz(params);
}
}


