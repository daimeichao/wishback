package com.jiading.modules.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Bq;
import com.jiading.modules.back.domain.User;
import com.jiading.modules.back.domain.UserBq;
import com.jiading.modules.back.mapper.UserBqMapper;
import com.jiading.modules.back.service.BqService;
import com.jiading.modules.back.mapper.BqMapper;
import com.jiading.modules.back.service.UserBqService;
import com.jiading.modules.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class BqServiceImpl extends ServiceImpl<BqMapper, Bq>
        implements BqService {

    @Autowired
    private BqMapper bqMapper;

    @Autowired
    private UserBqMapper userBqMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserBqService userBqService;

    @Override
    public ResultMap addUserBq(Map<String, Object> params) {

        if (ObjectUtils.isEmpty(params.get("userid"))) {
            return ResultMap.error("userid不能为空");
        }
        if (ObjectUtils.isEmpty(params.get("bqmc"))) {
            return ResultMap.error("bqmc不能为空");
        }
        Integer userid = Integer.valueOf(params.get("userid").toString());

        User user = userService.getOneByPidAndSczkAndJyzk(userid, "0");
        if (user == null) {
            return ResultMap.error("该用户不存在！");
        }

        String bqmc = params.get("bqmc").toString();
        Bq bq = new Bq(bqmc);
        boolean save1 = save(bq);
        if (!save1) {
            return ResultMap.error("添加失败！");
        }

        UserBq userBq = new UserBq(userid, bq.getPid());
        boolean save = userBqService.save(userBq);
        if (!save) {
            return ResultMap.error("添加失败！");
        }

        return ResultMap.ok();
    }


//    @Override
//    public ResultMap pageList(Bq bq) {
//
//        ResultMap resultMap = new ResultMap();
//
//        QueryWrapper<Bq> queryWrapper = new QueryWrapper<>();
//
//
//////        IPage<Jsjg> jsjgIPage = new Page<>(Bq.getCurpage(), jsjg.getPagesize());
////        IPage<Jsjg> page = jsjgMapper.selectPage(jsjgIPage, queryWrapper);
////
////        resultMap.put("data", page);
//        return resultMap;
//    }
}




