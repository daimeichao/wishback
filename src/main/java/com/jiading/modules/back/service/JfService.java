package com.jiading.modules.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiading.modules.back.domain.Fjb;
import com.jiading.modules.back.domain.TJf;

import java.util.Map;

public interface JfService extends IService<TJf> {
    Map<String, Object> getListByMap(Map<String, Object> params);
    Map<String, Object> delzyzById(Map<String, Object> params);

    Map<String, Object> getById(Map<String, Object> params);
}
