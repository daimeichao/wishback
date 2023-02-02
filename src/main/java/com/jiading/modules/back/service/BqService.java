package com.jiading.modules.back.service;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Bq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 */
public interface BqService extends IService<Bq> {

    ResultMap addUserBq(Map<String, Object> params);
}
