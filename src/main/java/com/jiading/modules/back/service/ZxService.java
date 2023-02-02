package com.jiading.modules.back.service;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Zx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 */
public interface ZxService extends IService<Zx> {

    Map<String,Object> getZxList(Map<String,Object> map);

    Map<String,Object> getZxBypid(Map<String,Object> map);

    Map<String, Object> updateZx(Map<String, Object> prarms);

    Map<String, Object> delZx(Map<String, Object> prarms);

    ResultMap addZx(Zx zx);

}
