package com.jiading.modules.littlewish.mapper;


import com.jiading.modules.littlewish.domain.TWishFile;
import com.jiading.modules.littlewish.domain.Wish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TwishMapper {
    List<Wish> getwishById(Integer pid);

    List<Wish> getwishByPid(Integer pid);

    Integer editwishbyid(String name, String content, String adder,Integer pid);

    List<Wish> getfullwishbyid(Integer pid);

    List<Wish> getfullwishbypid(Integer pid);

    List<TWishFile> geturlbypid(Integer pid);
    List<Wish> getListById(Integer pid);
}
