package com.jiading.modules.back.service;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.UserJieb;
import com.jiading.modules.back.domain.Xm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 */
public interface XmService extends IService<Xm> {

    ResultMap getXmInfoByIdWithShzt2(Integer pid,Integer yhid);

    ResultMap getXmInfoByIdWithShztall(Integer pid,Integer yhid);

    ResultMap getXmById(Map<String, Object> params);

    ResultMap getShzt2AllList();
}
