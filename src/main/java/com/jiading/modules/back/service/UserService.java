package com.jiading.modules.back.service;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface UserService extends IService<User> {

    ResultMap getUserById(Integer pid, String jyzk);

    ResultMap getUserById2(Map<String,Object> params);

    Map<String, Object> getListByMap(Map<String, Object> params);

    ResultMap removeById(Integer pid);

    ResultMap updateUser(User user);

    User getOneByPidAndSczkAndJyzk(Integer pid, String jyzk);

    ResultMap addUser(User user);

    Map getInfo(Map<String, Object> params);

    ResultMap getUserInfo1(Map<String, Object> params);

    ResultMap changeType(User user);

    ResultMap updatePassword(User user);
    ResultMap updateNowPassword(Map<String, Object> params);
    ResultMap addUserFromAdmin(User user);

    ResultMap grantAuthorize(Integer yhid, String jsid) ;

    ResultMap getUserRoleList(Integer pid);

    ResultMap getShzt2AllList();

    Map getSchoolInfo(Map<String, Object> params);
}
