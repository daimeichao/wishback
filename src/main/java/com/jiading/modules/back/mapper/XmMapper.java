package com.jiading.modules.back.mapper;
import org.apache.ibatis.annotations.Param;

import com.jiading.modules.back.domain.Xm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.jiading.modules.back.domain.Xm
 */
@Mapper
public interface XmMapper extends BaseMapper<Xm> {

    Map<String, Object> getXmInfo(Map outmap);

    Integer getJbrsByPidAndSczk(Integer xmid);
}




