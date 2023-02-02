package com.jiading.modules.back.mapper;

import com.jiading.modules.back.domain.Banner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.jiading.modules.back.domain.Banner
 */
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

    List<Map<String, Object>> getListByMap(Map<String, Object> params);

    int getListCountByMap(Map<String, Object> params);
}




