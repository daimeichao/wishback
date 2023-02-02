package com.jiading.modules.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiading.common.util.MD5Utils;
import com.jiading.common.util.Md5;
import com.jiading.common.util.RedisUtil;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Login;
import com.jiading.modules.back.domain.User;
import com.jiading.modules.back.mapper.UserMapper;
import com.jiading.modules.back.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultMap login(Login login) {

        String yhm = login.getYhm();
        String pwd = login.getPwd();
        String yzm = login.getYzm();
        String yzmid = login.getYzmid();
        System.out.println(login.toString());

        // 判断验证码
//        User tYzm = userMapper.selectOne(new QueryWrapper<User>().eq("pid", yzmid));
//        if ( tYzm == null) {
//            return ResultMap.error("验证码错误！");
//        }
//        if ( !tYzm.getYzm().equals(yzm.toUpperCase()) ) {
//            return ResultMap.error("验证码错误！");
//        }
        if (yzm == null || "".equals(yzm)){
            return ResultMap.error("验证码不能为空");
        }
        String catage = yzm.toLowerCase();
        String value = MD5Utils.encode(catage + login.getKey());
        Object checkCode = redisUtil.get(value);
        if(checkCode==null || !checkCode.toString().equals(catage)) {
            return ResultMap.error("验证码错误");
        }

        // 判断用户
        User user = userMapper.getOneByPhoneOrXm(yhm, yhm);
        if (user == null) {
            return ResultMap.error("用户不存在");
        }
        if ("1".equals(user.getJyzk())) {
            return ResultMap.error("该账号已被禁用");
        }
        if (!"2".equals(user.getType())) {
            return ResultMap.error("该账号不是系统用户");
        }

        System.out.println(user.toString());

        if (!user.getPassword().equals(Md5.MD5Encode(pwd))) {
            System.out.println(user.getPassword());
            System.out.println(Md5.MD5Encode(pwd));
            return ResultMap.error("密码错误");
        }

        //有没有角色
        List<Integer> userRoleListByPid = userMapper.getUserRoleListByPid(user.getPid());
        if(ObjectUtils.isEmpty(userRoleListByPid)){
            return ResultMap.error("该账号没有权限");
        }

        // 登录成功，删除验证码并返回
        userMapper.deleteById(yzmid);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data",user);
        return resultMap;
    }
}
