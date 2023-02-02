package com.jiading.modules.back.mapper;

import java.util.Collection;
import java.util.List;

import com.jiading.modules.back.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jiading.modules.back.domain.UserMsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.jiading.modules.back.domain.UserMsg
 */
@Mapper
public interface UserMsgMapper extends BaseMapper<UserMsg> {
    List<User> getAllBySczkAndXxid(@Param("xxid") Integer xxid);

    List<String> getAllIdBySczkAndXxid(@Param("xxid") Integer xxid);

    int insertBatch(@Param("userMsgCollection") Collection<UserMsg> userMsgCollection);

    int deleteByXxid(@Param("xxid") Integer xxid);
}




