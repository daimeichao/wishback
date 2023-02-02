package com.jiading.modules.xcx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiading.modules.back.domain.User;

import java.util.Map;

public interface XMainService extends IService<User> {
    Map ifExistUser(Map paramsMap);
}
