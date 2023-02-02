package com.jiading.modules.ytj.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IndexDao {

    List<Map<String,Object>> getCompleteWishList();

    List<Map<String,Object>> getClaimWishList();

    void addWish(Map<String, Object> params);

    List<Map<String,Object>> getWishList(Map<String, Object> params);

    void addWishClaimant(Map<String, Object> params);

    Integer confirmWished(Map<String, Object> params);

    void updateWished(Map<String, Object> params);

    List<Map<String,Object>> getCompleteWishFileList(Map<String, Object> item);

    Map<String, Object> getWishDeatail(Map<String, Object> params);

    Map<String, Object> getWishclaimant(Map<String, Object> params);
}
