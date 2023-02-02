package com.jiading.modules.littlewish.mapper;

import com.jiading.modules.littlewish.domain.WishUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TUserMapper {
    List<WishUser> getUserById(String openid);

    Integer editinfo(String name,Integer pid);
}
