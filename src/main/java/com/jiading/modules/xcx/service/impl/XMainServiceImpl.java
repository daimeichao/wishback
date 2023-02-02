package com.jiading.modules.xcx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.modules.back.domain.Dzd;
import com.jiading.modules.back.domain.User;
import com.jiading.modules.back.mapper.DzdMapper;
import com.jiading.modules.xcx.dao.XMainDao;
import com.jiading.modules.xcx.service.XMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @date 2022/8/19 17:09
 */
@Service
public class XMainServiceImpl extends ServiceImpl<XMainDao, User> implements XMainService {
    @Autowired
    private DzdMapper dzdMapper;

    @Override
    public Map ifExistUser(Map paramsMap) {
        Map outMap = new HashMap();
        QueryWrapper<User> queryWrapperOne = new QueryWrapper();
        queryWrapperOne.eq("openid", paramsMap.get("c_openid"));
        User user = baseMapper.selectOne(queryWrapperOne);
//        if (user != null) {
//            if(user.getCgid()!=null){
//                Dzd dzd = dzdMapper.selectById(user.getCgid());
//                user.setCgnr(dzd.getVt());
//            }
            outMap.put("user", user);
//        } else {
//            outMap.put("user", null);
//        }

        return outMap;
    }
}
