package com.jiading.modules.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiading.modules.back.domain.WishClainmant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface WishClainmantMapper  extends BaseMapper<WishClainmant> {
        List<Map<String, Object>> getListByMap(Map<String, Object> params);
        List<Map<String, Object>> getSHList(Map<String, Object> params);
        int getListCountByMap(Map<String, Object> params);
        int getSXCount(Map<String, Object> params);
        int deleteWishById(int id);
        int updateWishById(Map<String, Object> params);
        int updateWishById3(Map<String, Object> params);
        int updateFJ(Map<String, Object> params);
        Map getdetail(Map<String, Object> params);
        int upShstatu(Map<String, Object> params);
        int upXybShstatu(Map<String, Object> params);
        List<Map<String,Object>>geturl(Map<String, Object> params);
        int upddrl(Map<String, Object> params);
}

