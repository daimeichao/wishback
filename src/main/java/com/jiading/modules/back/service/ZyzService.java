package com.jiading.modules.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiading.modules.back.domain.TZyz;

import java.util.Map;

public interface ZyzService extends IService<TZyz> {
    Map<String, Object> shList(Map<String, Object> params);

    Map<String, Object> zyzList(Map<String, Object> params);

    Map<String, Object> delzyzById(Map<String, Object> params);

    Map<String, Object> getById(Map<String, Object> params);

    Map<String, Object> shById(Map<String, Object> params);
}
