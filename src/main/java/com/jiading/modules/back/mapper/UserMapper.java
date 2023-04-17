package com.jiading.modules.back.mapper;

import com.jiading.modules.back.domain.Rylb;
import org.apache.ibatis.annotations.*;

import com.jiading.modules.back.domain.Bq;
import com.jiading.modules.back.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

/**
 * @Entity com.jiading.modules.back.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Bq> getUserBqById(Integer pid);
//    List<Rylb> getUserRylbById(Integer pid);

    User getOneByPidAndSczkAndJyzk(@Param("pid") Integer pid, @Param("sczk") String sczk, @Param("jyzk") String jyzk);

    List<Map<String, Object>> getListByMap(Map<String, Object> params);

    @Update("update t_user set del='1' where pid = #{pid} ")
    int removeById(Integer pid);

    int getListCountByMap(Map<String, Object> params);

    User getOneByPhone(@Param("phone") String phone);

    User getOneByPhoneAndPid(User user);

    List<Map> getInfo(Map<String, Object> params);

    int updateLbAndMmByPid(@Param("lb") String lb, @Param("mm") String mm, @Param("pid") Integer pid);

    int updatePassword(String mm, Integer pid);

    int updatePasswordAndLb(String mm, Integer pid);

    User getOneByPhoneOrXm(@Param("phone") String phone, @Param("xm") String xm);

    int insertRole(Integer yhid, String jsid);

    int getRoleByJsidAndYhid(Integer yhid, Integer jsid);

    List<Integer> getUserRoleListByPid(Integer pid);

    @Delete("delete from t_user_role where userid = #{yhid}")
    void deleteRoleByYhid(Integer yhid);

    @Select("select * from t_user where del ='0' ")
    List<User> selectAllList();

    List<Map> getSchoolInfo(Map<String, Object> params);

    List<User> selectListWithSchool(List<Integer> yhidList);
    @Select("SELECT password FROM t_user where pid=#{pid}")
    String getmm(Map<String, Object> params);
    @Update("UPDATE t_user SET password = #{password} where pid=#{pid}")
    void updxcxmm(Map<String, Object> params);
}




