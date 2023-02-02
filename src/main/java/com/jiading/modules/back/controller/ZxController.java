package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.User;
import com.jiading.modules.back.domain.Zx;
import com.jiading.modules.back.service.XmlxService;
import com.jiading.modules.back.service.ZxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yanchao
 * @date 2022/8/25 9:56
 */

@RestController
@CrossOrigin
@RequestMapping("/zx")
public class ZxController {

    @Autowired
    private ZxService zxService;

    @PostMapping("/getZxList")
    public ResultMap getXmlxList(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = zxService.getZxList(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("/getZxBypid")
    public ResultMap getZxBypid(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = zxService.getZxBypid(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("/updateZx")
    public ResultMap updateXmlx(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = zxService.updateZx(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("/addZx")
    public ResultMap addXmlx(@RequestBody Zx zx) {
        return zxService.addZx(zx);
    }

    @PostMapping("/delZx")
    public ResultMap delZx(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = zxService.delZx(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }




}
