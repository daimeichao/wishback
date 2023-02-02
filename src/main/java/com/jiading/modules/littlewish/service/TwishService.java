package com.jiading.modules.littlewish.service;

import com.jiading.modules.littlewish.domain.TWishFile;
import com.jiading.modules.littlewish.domain.Wish;

import java.util.List;



public interface TwishService {
    List getwishById(Integer pid);
    List getwishByPid(Integer pid);

    Integer editwishbyid(String name, String content, String adder, Integer pid);

    List<Wish> getfullwishbyid(Integer pid);

    List<Wish> getfullwishbypid(Integer pid);

    List<TWishFile> geturlbypid(Integer pid);
}

