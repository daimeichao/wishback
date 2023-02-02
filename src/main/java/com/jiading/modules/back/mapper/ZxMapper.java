package com.jiading.modules.back.mapper;

import com.jiading.modules.back.domain.Zx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.jiading.modules.back.domain.Zx
 */
@Mapper
public interface ZxMapper extends BaseMapper<Zx> {

    List<Map<String,Object>> getZxList(Map<String, Object> map);

    Integer getZxListCount(Map<String, Object> map);

    int updateZx(Map<String, Object> prarms);

    int updateDzd(Map<String, Object> prarms);

    List<Map<String, Object>> getZxBypid(Map<String, Object> params);

    int delZx(Map<String, Object> prarms);
}




