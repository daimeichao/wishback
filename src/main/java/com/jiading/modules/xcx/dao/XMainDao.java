package com.jiading.modules.xcx.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiading.modules.back.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XMainDao extends BaseMapper<User> {
}
