package com.jiading.modules.littlewish.service.Impl;
import com.jiading.modules.littlewish.mapper.TUserMapper;
import com.jiading.modules.littlewish.service.TwishUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class twishUserServiceImpl implements TwishUserService {
    @Autowired
    private TUserMapper TUserMapper;
    @Override
    public List getinfoById(String openid) {
        return TUserMapper.getUserById(openid);
    }

    @Override
    public Integer editinfo(String name,Integer pid) {
        return TUserMapper.editinfo(name,pid);
    }
}
