package com.jiading.modules.back.service;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Login;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface LoginService {

  ResultMap login(Login params);
}