package com.jiading.modules.back.role.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.role.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/jbgs/back/qxgl")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询角色列表
     * @param params rolename、pageindex、pagesize
     * @return list、count
     */
    @ApiOperation(value = "查询角色列表")
    @RequestMapping(value = "/getJSlist", method = RequestMethod.POST)
    public ResultMap getJSlist(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", roleService.getJSlist(params));
    }

    /**
     * 获取菜单树
     * @param params
     * @return
     */
    @ApiOperation(value = "获取菜单树")
    @RequestMapping(value = "/getTree", method = RequestMethod.POST)
    public ResultMap getTree(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", roleService.getTree(params));
    }

    /**
     * 新增角色
     * @param params
     * @return
     */
    @ApiOperation(value = "新增角色")
    @RequestMapping(value = "/insJS", method = RequestMethod.POST)
    public ResultMap insJS(@RequestBody Map<String, Object> params) {
        System.out.println("ok");
        try {
            return ResultMap.ok().put("outmap", roleService.insJS(params));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.ok().put("outmap", new HashMap().put("result","新增失败"));
        }
    }

    /**
     * 角色详情
     * @param params
     * @return
     */
    @ApiOperation(value = "角色详情")
    @RequestMapping(value = "/getJSXQ", method = RequestMethod.POST)
    public ResultMap getJSXQ(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", roleService.getJSXQ(params));
    }

    /**
     * 修改角色
     * @param params
     * @return
     */
    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "/updJS", method = RequestMethod.POST)
    public ResultMap updJS(@RequestBody Map<String, Object> params) {
        try {
            return ResultMap.ok().put("outmap", roleService.updJS(params));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.ok().put("outmap", new HashMap().put("result","修改失败"));
        }
    }

    /**
     * 通过角色id获取绑定用户列表
     * @param params
     * @return
     */
    @ApiOperation(value = "通过角色id获取绑定用户列表")
    @RequestMapping(value = "/getLSlist", method = RequestMethod.POST)
    public ResultMap getLSlist(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", roleService.getLSlist(params));
    }

    /**
     * 角色绑定老师
     * @param params
     * @return
     */
    @ApiOperation(value = "角色绑定老师")
    @RequestMapping(value = "/insJSLSS", method = RequestMethod.POST)
    public ResultMap insJSLSS(@RequestBody Map<String, Object> params) {
        try {
            return ResultMap.ok().put("outmap", roleService.insJSLSS(params));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.ok().put("outmap", new HashMap().put("result","绑定失败"));
        }
    }

    @ApiOperation(value = "角色删除老师")
    @RequestMapping(value = "/delJSLS", method = RequestMethod.POST)
    public ResultMap delJSLS(@RequestBody Map<String, Object> params) {
        try {
            return ResultMap.ok().put("outmap", roleService.delJSLS(params));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.ok().put("outmap", new HashMap().put("result","删除失败"));
        }
    }

    @ApiOperation(value = "角色删除")
    @RequestMapping(value = "/delJS", method = RequestMethod.POST)
    public ResultMap delJS(@RequestBody Map<String, Object> params) {
        try {
            return ResultMap.ok().put("outmap", roleService.delJS(params));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.ok().put("outmap", new HashMap().put("result","删除失败"));
        }
    }

    @ApiOperation(value = "通过用户id获取菜单")
    @RequestMapping(value = "/getMenuListByLSID", method = RequestMethod.POST)
    public ResultMap getMenuListByLSID(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", roleService.getMenuListByLSID(params));
    }

    @ApiOperation(value = "查询老师列表")
    @RequestMapping(value = "/getAllTeacher", method = RequestMethod.POST)
    public ResultMap getAllTeacher(@RequestBody Map<String, Object> params) {

        Map<String, Object> outmap = roleService.getAllTeacher(params);

        return ResultMap.ok().put("outmap", outmap);
    }

    @RequestMapping(value="/getUserInfoByUserId",method=RequestMethod.POST)
    public ResultMap getUserInfoByUserId(@RequestBody  Map<String, Object> params){

        Map<String, Object> outmap = roleService.getUserInfoByUserId(params);

        return ResultMap.ok().put("outmap",outmap);
    }

    @RequestMapping(value="/getStudentsByTeacherCourse",method=RequestMethod.POST)
    public ResultMap getStudentsByTeacherCourse(@RequestBody  Map<String, Object> params){
        Map<String, Object> outmap = roleService.getStudentsByTeacherCourse(params);
        return ResultMap.ok().put("outmap",outmap);
    }

    @RequestMapping(value="/getAllDeptTree",method=RequestMethod.POST)
    public ResultMap getAllDeptTree(@RequestBody  Map<String, Object> params){
        Map<String, Object> outmap = roleService.getAllDeptTree2(params);
        return ResultMap.ok().put("outmap",outmap);
    }

    @RequestMapping(value="/getExaminationRooms",method=RequestMethod.POST)
    public ResultMap getExaminationRooms(@RequestBody  Map<String, Object> params){
        Map<String, Object> outmap = roleService.getExaminationRooms(params);
        return ResultMap.ok().put("outmap",outmap);
    }

    @RequestMapping(value="/getCourseTreeByLSID",method=RequestMethod.POST)
    public ResultMap getCourseTreeByLSID(@RequestBody  Map<String, Object> params){
        Map<String, Object> outmap = roleService.getCourseTreeByLSID(params);
        return ResultMap.ok().put("outmap",outmap);
    }
}
