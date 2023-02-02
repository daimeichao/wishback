package com.jiading.modules.back.role.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleDao {
    List<Map> getJSlist(Map<String, Object> params);

    int getJSlistCount(Map<String, Object> params);

    /**
     * 获取一级菜单
     */
    @Select("SELECT pid id,menuname label,parent SPARENT from t_menu where del = 0 AND parent = 'x1' order by sort")
    List<Map> getOneList(Map<String, Object> params);

    /**
     * 获取二级菜单
     * @param params
     * @return
     */
    @Select("SELECT pid id,menuname label,parent SPARENT from t_menu where del = 0 AND parent = #{id} order by sort")
    List<Map> getTwoList(Map<String, Object> params);

    /**
     * 添加角色
     * @param params
     */
    void insJS(Map<String, Object> params);

    @Select("select max(pid) from t_role")
    int seleMax();
    /**
     * 新增角色菜单
     * @param params
     */
    @Insert("insert into t_rolemenu(roleid,menudid) value(#{JSID},#{menudid})")
    void insJSMENU(Map<String, Object> params);

    /**
     * 验证是否重名
     *
     * @param params
     * @return
     */
    @Select("SELECT count(*) FROM t_role WHERE del = 0 AND rolename = #{JSMC}")
    int getJSCount(Map<String, Object> params);

    /**
     * 获取角色详情
     */
    @Select("SELECT pid JSID,rolename JSMC, remark BZ FROM t_role WHERE del = 0 AND pid = #{JSID}")
    Map getJSMap(Map<String, Object> params);

    /**
     * 根据角色id获取menuid
     * @param params
     * @return
     */
    @Select("SELECT menudid FROM t_rolemenu WHERE roleid=#{JSID}")
    List<Map> getMenuListBYJSID(Map<String, Object> params);

    @Update("update t_role set rolename=#{JSMC}, remark=#{BZ} WHERE del = 0 AND pid = #{JSID}")
    void upd(Map<String, Object> params);

    @Delete("delete from t_rolemenu where roleid = #{JSID}")
    void delRoleMenu(Map<String, Object> params);

    @Select("SELECT * FROM t_role WHERE del = 0 AND rolename =  #{JSMC} ")
    Map getJSMapBYName(Map<String, Object> params);

    List<Map> getLSlist(Map<String, Object> params);

    int getLSlistCount(Map<String, Object> params);

    @Select("SELECT count(*) from t_user_role where roleid=#{JSID} AND userid=#{LSID}")
    int getJSLSCount(Map<String, Object> params);

    //角色新增老师
    @Insert("insert into t_user_role(roleid,userid) value(#{JSID},#{LSID})")
    void insJSLSS(Map<String, Object> params);

    @Delete("delete from t_user_role where roleid=#{JSID} AND userid=#{LSID}")
    void delJSLS(Map<String, Object> params);

    @Delete("delete from t_user_role where roleid= #{JSID}")
    void delJSLSbyJSID(Map<String, Object> params);

    @Update("UPDATE t_role SET del = 1 WHERE pid = #{JSID}")
    void delJS(Map<String, Object> params);

    @Select("SELECT menu.pid sid,menu.menuname sname,menu.link slink,menu.desc sdesc,menu.parent SPARENT FROM t_menu menu " +
            "where menu.del = 0 and pid in " +
            "(select rm.menudid FROM t_rolemenu rm where rm.roleid in " +
            "(SELECT ur.roleid FROM t_user_role ur where ur.userid =  #{LSID} ))" +
            " ORDER BY menu.sort")
    List<Map> getMenuListByLSID(Map<String, Object> params);

    @Select("SELECT ur.roleid JSID FROM t_user_role ur where ur.userid =  #{LSID}")
    List<Map> getJsById(Map<String, Object> params);

    List<Map<String, Object>> getAllTeacher(Map<String, Object> paramsMap);

    List<String> getBdLsIdsList(Map<String, Object> params);

    List<Map<String, Object>> getWBDLsList(Map<String, Object> params);

    Map<String, Object> getUserInfoByUserId(Map<String, Object> paramsMap);

    List<Map> ifXzls(Map<String,Object> paramsMap);

    List<Map<String, Object>> getStudentsByTeacherCourse(Map<String, Object> params);

    List<Map<String, Object>> getAllDepartment();

    List<Map<String, Object>> getAllMajors();

    List<Map<String, Object>> getAllClasses();

    List<Map<String, Object>> getAllClasses2();

    List<Map<String, Object>> getAllCourse();

    List<Map<String, Object>> getAllCourseByLSID(Map<String,Object> params);

    List<Map<String, Object>> getCourseClassRelation();

    List<Map<String, Object>> getAllStudents();

    List<Map<String, Object>> getExaminationRooms();
}
