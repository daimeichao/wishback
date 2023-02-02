package com.jiading.modules.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiading.modules.back.domain.Msg;
import com.jiading.modules.back.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface MsgMapper extends BaseMapper<Msg> {

    List<Map<String, Object>> getListByMap(Map<String, Object> params);

    int getListCountByMap(Map<String, Object> params);

    List<Map<String,Object>>  getJsrListByMsgIds(List<String> xxidList);
}
