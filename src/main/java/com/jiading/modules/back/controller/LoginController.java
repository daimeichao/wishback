package com.jiading.modules.back.controller;

import com.jiading.common.util.CaptchaUtil;
import com.jiading.common.util.MD5Utils;
import com.jiading.common.util.RedisUtil;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Login;
import com.jiading.modules.back.domain.User;
import com.jiading.modules.back.mapper.UserMapper;
import com.jiading.modules.back.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/jbgs/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public ResultMap login(@RequestBody Login login) {

        return loginService.login(login);
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取验证码
     * @return
     */
    @GetMapping("/getCaptcha/{key}")
    @ResponseBody
    public ResultMap getCaptcha(@PathVariable("key") String key) {
        //画图工具类
        CaptchaUtil imageCode = new CaptchaUtil();
        // 获取验证码对应的 base64  编码
        String base64 = CaptchaUtil.getBase64(imageCode.getImage());
        // 获取对应的 验证码 code
        String code = imageCode.getCode();
        System.out.println(" [ 当前登录验证码为： ] "+code);
//        // 存入数据库
//        User user = new User();
//        user.setYzm(code);
//        userMapper.insert(user);
        //存入redis
        String lowerCaseCode = code.toLowerCase();
        String realKey = MD5Utils.encode(lowerCaseCode + key);
        redisUtil.set(realKey,lowerCaseCode,60);
        // 将封装好的验证码对象响应给前端
        ResultMap resultMap = new ResultMap();
//        resultMap.put("pid",user.getPid());
        resultMap.put("imgBase",base64);
        return resultMap;
    }
}
