package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.User;
import com.jiading.modules.back.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有的角色（不分页）
     * @return
     */
    @GetMapping("/allList")
    public ResultMap getAllList() {
        List<User> list = userService.list();
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

    /**
     * 获取所有的角色（分页）
     * @param params
     * @return
     */
    @PostMapping("/list")
    public ResultMap getList(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap = userService.getListByMap(params);
        return ResultMap.ok().put("outmap", outmap);
    }

    /**
     * 角色详情
     * @param pid
     * @param jyzk
     * @return
     */
    @GetMapping("/get")
    public ResultMap getXmById(@RequestParam("pid") Integer pid,@RequestParam(value = "jyzk",required = false) String jyzk) {
        return userService.getUserById(pid,jyzk);
    }

    @PostMapping("/get2")
    public ResultMap getXmById2(@RequestBody Map<String, Object> params) {
        return userService.getUserById2(params);
    }

    @GetMapping("/delete")
    public ResultMap deleteUser(@RequestParam("pid") Integer pid) {
        return userService.removeById(pid);
    }

    @PostMapping("/update")
    public ResultMap updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/add")
    public ResultMap addUser(@RequestBody User user) {
       return userService.addUser(user);
    }

    @PostMapping("/addUserFromAdmin")
    public ResultMap addUserFromAdmin(@RequestBody User user) {
       return userService.addUserFromAdmin(user);
    }
    /**
     * 更改当前角色密码
     * @return
     */
    @PostMapping("/updateNowPassword")
    public ResultMap updatePassword(@RequestBody Map<String, Object> params) {
        System.out.println("ok");
        return userService.updateNowPassword(params);
    }

    @RequestMapping(value = "/getZwInfo", method = RequestMethod.POST)
    public ResultMap getInfo(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", userService.getInfo(params));
    }

    @RequestMapping(value = "/getSchoolInfo", method = RequestMethod.POST)
    public ResultMap getSchoolInfo(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", userService.getSchoolInfo(params));
    }

    @RequestMapping(value = "/getInfo1", method = RequestMethod.POST)
    public ResultMap getInfo1(@RequestBody Map<String, Object> params) {
        return userService.getUserInfo1(params);
    }

    @PostMapping("/changeType")
    public ResultMap changeType(@RequestBody User user) {
        return userService.changeType(user);
    }

    @PostMapping("/updatePassword")
    public ResultMap updatePassword(@RequestBody User user) {
        return userService.updatePassword(user);
    }

    /**
     * 授权（分配用户角色）
     * @param yhid roleid
     * @param jsid userid
     * @return
     */
    @GetMapping("/grantAuthorize")
    public ResultMap grantAuthorize(@RequestParam("yhid") Integer yhid, @RequestParam("jsid")String jsid) {
        return userService.grantAuthorize(yhid,jsid);
    }

    /**
     * 获取用户角色列表
     * @param pid
     * @return
     */
    @GetMapping("/getUserRoleList")
    public ResultMap getUserRoleList(@RequestParam("pid") Integer pid) {
        return userService.getUserRoleList(pid);
    }

}
