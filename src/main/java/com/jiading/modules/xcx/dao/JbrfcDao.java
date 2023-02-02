package com.jiading.modules.xcx.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JbrfcDao {
    List<Map> getJbrList(Map<String,Object> params);

    int getJbrListCount(Map<String,Object> params);

    List<Map> getJbjlList(Map<String,Object> params);
}
