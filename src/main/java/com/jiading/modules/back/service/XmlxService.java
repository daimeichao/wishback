package com.jiading.modules.back.service;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Xm;
import com.jiading.modules.back.domain.Xmlx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface XmlxService extends IService<Xmlx> {

    Map<String,Object> getXmlxList(Map<String,Object> map);

    Map<String, Object> updateXmlx(Map<String, Object> prarms);

    Map<String, Object> addXmlx(Map<String, Object> prarms);

    Map<String,Object> getAllXmlx(Map<String,Object> map);

    Map<String, Object> getRylbList(Map<String, Object> prarms);

    Map<String, Object> updateRylb(Map<String, Object> prarms);

    Map<String, Object> addRylb(Map<String, Object> prarms);

    Map<String,Object> getXmlxTree(Map<String,Object> map);

    Map<String, Object> delJbxmbyYhid(Map<String, Object> prarms);

    ResultMap importXmlx(Map<String, Object> prarms);
}
