package com.jiading.modules.back.mapper;
import org.apache.ibatis.annotations.Param;

import com.jiading.modules.back.domain.User;
import com.jiading.modules.back.domain.UserJieb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.jiading.modules.back.domain.UserJieb
 */
@Mapper
public interface UserJiebMapper extends BaseMapper<UserJieb> {


   List<User> getUserInfoByXmid(Map map);

   int getUserCountByXmid(Map map);

   int getUserCountByXmid2(Integer pid);

    List<User> getUserInfoByXmidWithJyzk(Map map);

    int getUserCountByXmidWithJyzk(Map map);

    List<User> getXmJbrList(Integer xmid);

    List<User> getXmJbrListWithDealName(Integer xmid);

    List<UserJieb> getIfJieBangWithXmidAndYhid(Map map);
    List<Map<String,Integer>> getIfJieBangWithXmid(Map map);

    UserJieb getOneByYhidAndXmid(@Param("yhid") Integer yhid, @Param("xmid") Integer xmid);

    int deleteByXmidAndYhid( @Param("xmid") Integer xmid, @Param("yhid") Integer yhid);

    int getJieBangCountByYhid(Integer yhid);
}




