package com.jiading.modules.littlewish.service;
import java.util.List;


public interface TwishUserService {
    List getinfoById(String openid);

    Integer editinfo(String name,Integer pid);
}
