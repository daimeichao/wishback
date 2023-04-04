package com.jiading.modules.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiading.modules.back.domain.TZyz;
import com.jiading.modules.back.domain.WishClainmant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ZyzMapper  extends BaseMapper<TZyz> {
    int shcount(Map<String, Object> params);

    List<Map<String, Object>> shlb(Map<String, Object> params);

    List<Map<String, Object>> zyzlb(Map<String, Object> params);

    int zyzcount(Map<String, Object> params);

    int delshById(Map<String, Object> params);

    Map getdetail(Map<String, Object> params);

    int shById(Map<String, Object> params);

    int addzyz(Map<String, Object> params);

    int updById(Map<String, Object> params);

    int updzt(Map<String, Object> params);
}
