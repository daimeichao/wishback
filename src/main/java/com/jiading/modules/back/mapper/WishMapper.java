package com.jiading.modules.back.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiading.modules.back.domain.Wish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface WishMapper extends BaseMapper<Wish> {
    List<Map<String, Object>> getListByMap(Map<String, Object> params);
    List<Map<String, Object>> getSHList(Map<String, Object> params);
    List<Map<String, Object>> wishList(Map<String, Object> params);
    Map getListById(Map<String, Object> params);
    int getListCountByMap(Map<String, Object> params);
    int   getSXCount(Map<String, Object> params);
    int deleteWishById(int id);
    int deleteWishById1(int id);
    int myUpdateById(Map<String, Object> params);
    int UpdSx(Map<String, Object> params);
    int myDjeUpdateById(Map<String, Object> params);
    int myUpdateById1(Map<String, Object> params);
    int  myUpdateById2(Map<String, Object> params);
    List<Map> getname(Map<String, Object> params);
    Map getdetail(Map<String, Object> params);
    int addTu1(Map<String, Object> params);
    int addTu(Map<String, Object> params);
    int addfb(Map<String, Object> params);
    int updatesort(Map<String, Object> params);
    int updCliamant(Map<String, Object> params);
    int updrl(Map<String, Object> params);
    int delfj(Map<String, Object> params);
    int delsxb(Map<String, Object> params);
}