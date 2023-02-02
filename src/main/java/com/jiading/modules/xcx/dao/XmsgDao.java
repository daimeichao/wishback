package com.jiading.modules.xcx.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author yanchao
 * @date 2022/8/26 15:31
 */
@Mapper
public interface XmsgDao {

    List<Map> getXmsgList(Map<String, Object> params);

    int getXmsgListCount(Map<String, Object> params);

    int updateyd(Map<String, Object> prarms);

    List<Map>  getXmsgByid(Map<String, Object> params);
}
