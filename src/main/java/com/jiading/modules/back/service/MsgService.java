package com.jiading.modules.back.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Msg;

import java.util.Map;


public interface MsgService extends IService<Msg> {
    Map<String, Object> getListByMap(Map<String, Object> params);

    ResultMap getMsgById(Integer pid);

    ResultMap updateMsg(Msg msg);

    ResultMap addMsg(Msg msg);

    ResultMap deleteMsg(Integer pid);
}
