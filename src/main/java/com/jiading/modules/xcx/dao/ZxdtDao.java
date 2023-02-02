package com.jiading.modules.xcx.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZxdtDao {
    List<Map> getZxdtList(Map<String,Object> params);

    int getZxdtListCount(Map<String,Object> params);
}
