package com.jiading.modules.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Msg;
import com.jiading.modules.back.domain.Wish;

import java.util.List;
import java.util.Map;

public interface WishService extends IService<Wish> {
    Map<String, Object> getListByMap(Map<String, Object> params);
    Map<String, Object> getWish(Map<String, Object> params);
    Map<String, Object>   wishList(Map<String, Object> params);
    Map<String, Object> getListById (Map<String, Object> params);
    Map<String, Object> getSHList(Map<String, Object> params);
    Map<String, Object> deleteWishById(Map<String, Object> params);
    Map<String, Object> deleteWishById1(Map<String, Object> params);
    Map<String, Object> updateWishById(Map<String, Object> params);
    Map<String, Object> updateWishById1(Map<String, Object> params);
    Map<String, Object> updSX(Map<String, Object> params);
   Map<String, Object> addWish(Map<String, Object> params);
   Map<String, Object> addWish1(Map<String, Object> params);

    Map<String, Object> updatesort(Map<String, Object> params);
}
