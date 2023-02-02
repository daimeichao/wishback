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
        /**
         * 根据人员id删除单位信息
         * @param id
         * @return
         */
        int deleteWishById(int id);

        /**
         * 根据人员id修改单位信息
         * @param params
         * @return
         */
        int updateWishById(Map<String, Object> params);
        int updateWishById3(Map<String, Object> params);
        int updateFJ(Map<String, Object> params);

        /**
         * 添加单条人员信息数据
         * @param params
         * @return
         */
        int insertWish(Map params);
//        Map getdetail(Map<String, Object> params);
        Map getdetail(Map<String, Object> params);

        int upShstatu(Map<String, Object> params);

        int upXybShstatu(Map<String, Object> params);

        List<Map<String,Object>>geturl(Map<String, Object> params);

        int updnull(Map<String, Object> params);

        int updateSxb(Map<String, Object> params);
}
//
//
