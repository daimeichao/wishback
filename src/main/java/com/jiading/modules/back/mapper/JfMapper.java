package com.jiading.modules.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiading.modules.back.domain.TJf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JfMapper extends BaseMapper<TJf> {
    List<Map<String, Object>> getListByMap(Map<String, Object> params);
    int getListCountByMap(Map<String, Object> params);
    int delById(Map<String, Object> params);
    int delphb(Map<String, Object> params);
    Map getdetail(Map<String, Object> params);

    int addsp(Map<String, Object> params);

    int updsp(Map<String, Object> params);

    int delsp(Map<String, Object> params);

    List<Map<String, Object>> splist(Map<String, Object> params);

    int spcount(Map<String, Object> params);

    Map spdetail(Map<String, Object> params);

//实现他人愿望新增积分
    int addjf(Map<String, Object> params);

    int czjf(Map<String, Object> params);
}
