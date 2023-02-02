package com.jiading.modules.back.jbgsgl.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.jbgsgl.service.JbgsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/jbgs/back/jbgsgl")
public class JbgsController {

    @Autowired
    private JbgsService jbgsService;

    @ApiOperation(value = "查询项目列表")
    @RequestMapping(value = "/getXmlist", method = RequestMethod.POST)
    public ResultMap getXmlist(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.getXmlist(params));
    }


    @ApiOperation(value = "查询项目列表")
    @RequestMapping(value = "/getXmlistWithJbrInfo", method = RequestMethod.POST)
    public ResultMap getXmlistWithJbrInfo(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.getXmlistWithJbrInfo(params));
    }

    @ApiOperation(value = "修改项目信息")
    @RequestMapping(value = "/updXmInfo", method = RequestMethod.POST)
    public ResultMap updXmInfo(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.updXmInfo(params));
    }

    @ApiOperation(value = "添加项目")
    @RequestMapping(value = "/addXm", method = RequestMethod.POST)
    public ResultMap addXm(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.addXm(params));
    }

    @ApiOperation(value = "编辑项目")
    @RequestMapping(value = "/editXm", method = RequestMethod.POST)
    public ResultMap editXm(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.editXm(params));
    }

    @ApiOperation(value = "查询揭榜人列表")
    @RequestMapping(value = "/getJbrlist", method = RequestMethod.POST)
    public ResultMap getJbrlist(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.getJbrlist(params));
    }

    @ApiOperation(value = "查询项目类型列表")
    @RequestMapping(value = "/getXmlxlist", method = RequestMethod.POST)
    public ResultMap getXmlxlist(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.getXmlxlist(params));
    }

    @ApiOperation(value = "修改项目类型信息")
    @RequestMapping(value = "/updXmlxInfo", method = RequestMethod.POST)
    public ResultMap updXmlxInfo(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.updXmlxInfo(params));
    }

    @ApiOperation(value = "添加项目类型")
    @RequestMapping(value = "/addXmlx", method = RequestMethod.POST)
    public ResultMap addXmlx(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.addXmlx(params));
    }

    @ApiOperation(value = "根据用户id查询我的揭榜项目列表")
    @RequestMapping(value = "/getJbrXmlistInfo", method = RequestMethod.POST)
    public ResultMap getJbrXmlistInfo(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.getJbrXmlistInfo(params));
    }

    @ApiOperation(value = "根据用户id查询我的发榜项目列表")
    @RequestMapping(value = "/getFbrXmlistInfo", method = RequestMethod.POST)
    public ResultMap getFbrXmlistInfo(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.getFbrXmlistInfo(params));
    }

    @ApiOperation(value = "根据项目id查询揭榜人列表")
    @RequestMapping(value = "/getJbrListByXmid", method = RequestMethod.POST)
    public ResultMap getJbrListByXmid(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.getJbrListByXmid(params));
    }

    @ApiOperation(value = "按照地区统计数量")
    @RequestMapping(value = "/getCountByCity", method = RequestMethod.POST)
    public ResultMap getCountByCity(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.getCountByCity(params));
    }

    @ApiOperation(value = "按照地区统计履职数量")
    @RequestMapping(value = "/getLzCountByCity", method = RequestMethod.POST)
    public ResultMap getLzCountByCity(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.getlzCountByCity(params));
    }

    @ApiOperation(value = "按照地区统计履职数量")
    @RequestMapping(value = "/getTjhzCount", method = RequestMethod.POST)
    public ResultMap getTjhzCount(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", jbgsService.getTjhzCount(params));
    }
}
