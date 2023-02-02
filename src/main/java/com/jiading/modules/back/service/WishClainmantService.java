package com.jiading.modules.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiading.modules.back.domain.Wish;
import com.jiading.modules.back.domain.WishClainmant;

import java.util.Map;

public interface WishClainmantService extends IService<WishClainmant> {
    Map<String, Object> getListByMap(Map<String, Object> params);
    Map<String, Object> getWish(Map<String, Object> params);
    //    Map<String, Object> addWish(Map<String, Object> params);
    Map<String, Object> deleteWishById(Map<String, Object> params);
    Map<String, Object> updateWishById(Map<String, Object> params);
    Map<String, Object> updateWishById1(Map<String, Object> params);
//    Map<String, Object> insertWish(Map<String, Object> params);
Map<String, Object>  insertWish(WishClainmant wishClainmant);
    Map<String, Object> getSHList(Map<String, Object> params);//获得实现心愿的审核列表
}
//
