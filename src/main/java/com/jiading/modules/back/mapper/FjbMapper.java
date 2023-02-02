package com.jiading.modules.back.mapper;

import com.jiading.modules.back.domain.Fjb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.jiading.modules.back.domain.Fjb
 */
@Mapper
public interface FjbMapper extends BaseMapper<Fjb> {

    List<Map<String, Object>> getFjByXmid(Map outmap);

    int delFjByXmid(Map outmap);
}




