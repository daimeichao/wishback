package com.jiading.modules.littlewish.service.Impl;


import com.jiading.modules.littlewish.domain.TWishFile;
import com.jiading.modules.littlewish.mapper.TwishMapper;
import com.jiading.modules.littlewish.domain.Wish;
import com.jiading.modules.littlewish.service.TwishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwishServiceImpl implements TwishService {
    @Autowired
    private TwishMapper TwishMapper;

    @Override
    public List getwishById(Integer pid) {
        return TwishMapper.getwishById(pid);
    }

    @Override
    public List getwishByPid(Integer pid) {
        return TwishMapper.getwishByPid(pid);
    }

    @Override
    public Integer editwishbyid(String name, String content, String adder,Integer pid) {
        return TwishMapper.editwishbyid(name,content,adder,pid);
    }

    @Override
    public List<Wish> getfullwishbyid(Integer pid) {
        return TwishMapper.getfullwishbyid(pid);
    }

    @Override
    public List<Wish> getfullwishbypid(Integer pid) {
        return TwishMapper.getfullwishbypid(pid);
    }

    @Override
    public List<TWishFile> geturlbypid(Integer pid) {
        return TwishMapper.geturlbypid(pid);
    }
}
