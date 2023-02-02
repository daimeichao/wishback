package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Xm;
import com.jiading.modules.back.service.XmService;
import com.jiading.modules.back.service.XmlxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author chhStart
 * @create 2022-08-22 15:22
 */
@RestController
@CrossOrigin
@RequestMapping("/xmlx")
public class XmlxController {
    @Autowired
    private XmlxService xmlxService;

    @PostMapping("/getXmlxList")
    public ResultMap getXmlxList(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = xmlxService.getXmlxList(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("/updateXmlx")
    public ResultMap updateXmlx(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = xmlxService.updateXmlx(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("/addXmlx")
    public ResultMap addXmlx(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = xmlxService.addXmlx(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("/getAllXmlx")
    public ResultMap getAllXmlx(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = xmlxService.getAllXmlx(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }
    @PostMapping("/getRylbList")
    public ResultMap getRylbList(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = xmlxService.getRylbList(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("/updateRylb")
    public ResultMap updateRylb(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = xmlxService.updateRylb(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("/addRylb")
    public ResultMap addRylb(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = xmlxService.addRylb(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("/getXmlxTree")
    public ResultMap getXmlxTree(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = xmlxService.getXmlxTree(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    @PostMapping("/delJbxmbyYhid")
    public ResultMap delJbxmbyYhid(@RequestBody Map<String,Object> prarms) {
        Map<String, Object> map = xmlxService.delJbxmbyYhid(prarms);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", map);
        return resultMap;
    }

    /* excel导入的项目 二级类型在驳回理由 匹配类型 插入t_xm_xmlx表 */
    @GetMapping("/importXmlx")
    public ResultMap importXmlx(@RequestBody Map<String,Object> prarms) {
        return xmlxService.importXmlx(prarms);
    }
}
