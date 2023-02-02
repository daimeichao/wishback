package com.jiading.modules.back.jbgsgl.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface JbgsDao {
    List<Map> getXmlist(Map<String, Object> params);

    int getXmlistCount(Map<String, Object> params);

    int addXm(Map<String, Object> params);

    int editXm(Map<String, Object> params);

    List<Map> getJbrlist(Map<String, Object> params);

    int getJbrlistCount(Map<String, Object> params);

    List<Map> getXmlxlist(Map<String, Object> params);

    int getXmlxlistCount(Map<String, Object> params);

    int addXmlx(Map<String, Object> params);

    int editXmlx(Map<String, Object> params);

    List<Map> getXmlistOnlyDate(Map<String, Object> params);

    List<Map> getXmlistbyyhid(Map<String, Object> params);

    int getXmlistbyyhidCount(Map<String, Object> params);

    List<Map> getFbXmlistByuid(Map<String, Object> params);

    int getFbXmlistByuidCount(Map<String, Object> params);

    List<Map> getCountByCity(Map<String, Object> params);

    List<Map> getCountByCity1(Map<String, Object> params);

    List<Map> getCountByCity2(Map<String, Object> params);

    int addXmXmlx(Map<String, Object> params);

    List<Map> getTjhzCount(Map<String, Object> params);

    List<Map> getLbCount(Map<String, Object> params);

    int getXmCount(Map<String, Object> params);

    void deleteXM(@Param("oldoid") Integer oldoid);
}
