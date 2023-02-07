package com.jiading.modules.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Fjb;
import com.jiading.modules.back.domain.TJf;

import java.util.Map;

public interface JfService extends IService<TJf> {
    Map<String, Object> getListByMap(Map<String, Object> params);
    Map<String, Object> delzyzById(Map<String, Object> params);

    Map<String, Object> getById(Map<String, Object> params);

    Map<String, Object>  addsp(Map<String, Object> params);
    Map<String, Object>  updsp(Map<String, Object> params);

    Map<String, Object> delsp(Map<String, Object> params);

    Map<String, Object> splist(Map<String, Object> params);

    Map<String, Object> spById(Map<String, Object> params);
}
