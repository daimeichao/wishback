package com.jiading.modules.back.service;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 */
public interface BannerService extends IService<Banner> {

    ResultMap addBanner(Banner banner);

    ResultMap updateBanner(Banner banner);

    ResultMap getBannerById(Integer pid);

    ResultMap pageList(Banner banner);

    Map<String, Object> getListByMap(Map<String, Object> params);
}
