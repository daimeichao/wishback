package com.jiading.modules.xcx.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.xcx.service.XmsgService;
import com.jiading.modules.xcx.service.ZxdtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yanchao
 * @date 2022/8/26 15:31
 */

@RestController
@RequestMapping("/xcx/msg/")
@CrossOrigin
public class XmsgController {

    @Autowired
    private XmsgService xmsgService;


    @PostMapping("getXmsgList")
    public ResultMap getZxdtList(@RequestBody Map<String,Object> params) {
        return xmsgService.getXmsgList(params);
    }

    @PostMapping("/updateyd")
    public ResultMap updateyd(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = xmsgService.updateyd(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("getXmsgByid")
    public ResultMap getXmsgByid(@RequestBody Map<String,Object> params) {
        return xmsgService.getXmsgByid(params);
    }

}
