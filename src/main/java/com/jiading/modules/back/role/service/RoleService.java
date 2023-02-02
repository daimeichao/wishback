package com.jiading.modules.back.role.service;

import com.jiading.common.util.FenYe;
import com.jiading.modules.back.role.dao.RoleDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService {

    @Resource
    private RoleDao roleDao;

    /**
     * 查询角色列表
     * @param params rolename、pageindex、pagesize
     * @return list、count
     */
    public Map getJSlist(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

            // 取值
            List<Map> list = roleDao.getJSlist(params);
            int count = roleDao.getJSlistCount(params);
            int pagecount = FenYe.pagecount(count, pagesize);

            // 返回
            outmap.put("pagecount", pagecount);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 12);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }

    /**
     * 获取菜单树
     * @param params
     * @return
     */
    public Map getTree(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            // 获取一级菜单
            List<Map> onelist = roleDao.getOneList(params);
            if (onelist.size()!=0){
                for (Map m:onelist){
                    if (m!=null){
                        // 获取二级菜单
                        List<Map> twolist = roleDao.getTwoList(m);
                        m.put("children",twolist);
                    }
                }
            }
            outmap.put("list",onelist);
            System.out.println(onelist.toString());
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("list",new ArrayList());
        }
        return outmap;
    }

    /**
     * 新增角色
     * @param params rolename
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Object insJS(Map<String, Object> params)  throws Exception{
        Map outmap = new HashMap();
        // 验证是否重名
        int count = roleDao.getJSCount(params);
        if (count!=0) {
            outmap.put("result","已存在该角色，请勿重复新增");
            return outmap;
        }
        // 根据rolename remark添加角色
        roleDao.insJS(params);
        int max = roleDao.seleMax();
        List<Map> arr = (List<Map>) params.get("cdArr"); // 添加的菜单列表
        if (arr.size()!=0){
            for (Map m:arr){
                m.put("JSID",max);
                m.put("menudid",m.get("id"));
                roleDao.insJSMENU(m);
            }
        }
        outmap.put("result","success");
        return outmap;
    }

    /**
     * 根据id获取角色详情
     * @param params roleid
     * @return
     */
    public Object getJSXQ(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            // 获取角色信息
            outmap = roleDao.getJSMap(params);
            // 获取角色菜单列表
            List<Map> menulist = roleDao.getMenuListBYJSID(params);
            List list = new ArrayList();
            if (menulist.size()!=0){
                for (Map m:menulist){
                    list.add(m.get("menudid"));
                }
            }
            outmap.put("menulist",list);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("menulist",new ArrayList());
        }
        return outmap;
    }

    /**
     * 修改角色
     * @param params
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Object updJS(Map<String, Object> params) throws Exception{
        Map outmap = new HashMap();
        //验证是否重名  #{rolename}
        System.out.println(params.toString());
        int count = roleDao.getJSCount(params);
        if (count!=0) {
            Map map = roleDao.getJSMapBYName(params);
            if (!String.valueOf(params.get("pid")).equals(String.valueOf(map.get("JSID")))){
                outmap.put("result","已存在该角色，请勿重复新增");
                return outmap;
            }
        }
        //修改角色名称 rolename
        roleDao.upd(params);
        //删除角色菜单 roleid
        roleDao.delRoleMenu(params);
        //重新插入
        List<Map> arr = (List<Map>) params.get("cdArr");
        if (arr.size()!=0){
            for (Map m:arr){
                System.out.println(m.toString());
                m.put("JSID",params.get("JSID"));
                m.put("menudid",m.get("id"));
                roleDao.insJSMENU(m);
            }
        }
        outmap.put("result","success");
        return outmap;
    }

    /**
     * 获取角色绑定的用户列表
     * @param params
     * @return
     */
    public Object getLSlist(Map<String, Object> params) {
        System.out.println(params);
        Map outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("page"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

            // 取值
            List<Map> list = roleDao.getLSlist(params);
            int count = roleDao.getLSlistCount(params);
            int pagecount = FenYe.pagecount(count, pagesize);

            // 返回
            outmap.put("pagecount", pagecount);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 12);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }

    @Transactional(rollbackFor = Exception.class)
    public Object insJSLSS(Map<String, Object> params) throws Exception{
        Map outmap = new HashMap();
        List<String> list = (List) params.get("LSIDS");
        if (list.size()>0){
            for (String s:list){
                String[] arr = s.split("\\|");
                params.put("LSID",arr[0]);
                params.put("LSMC",arr[1]);

                int count = roleDao.getJSLSCount(params);
                if (count!=0){
                    outmap.put("err",params.put("LSMC",arr[1]).toString()+"已邀请，请勿重复邀请!");
                    return outmap;
                }

                roleDao.insJSLSS(params);
            }
        }
        outmap.put("err","success");
        return outmap;
    }

    @Transactional(rollbackFor = Exception.class)
    public Object delJSLS(Map<String, Object> params)  throws Exception{
        System.out.println("[delJSLS]" + params.toString());
        Map outmap = new HashMap();
        roleDao.delJSLS(params);
        outmap.put("err","success");
        return outmap;
    }

    @Transactional(rollbackFor = Exception.class)
    public Object delJS(Map<String, Object> params) throws Exception{
        Map outmap = new HashMap();
        //删除绑定用户
        roleDao.delJSLSbyJSID(params);
        //删除菜单
        roleDao.delRoleMenu(params);
        //删除角色
        roleDao.delJS(params);
        outmap.put("err","success");
        return outmap;
    }

    public Object getMenuListByLSID(Map<String, Object> params) {
        System.out.println(params);
        Map outmap = new HashMap();
        try {
            // 取值
            List<Map> list = roleDao.getMenuListByLSID(params);
            List<Map> rolelist = roleDao.getJsById(params);
            outmap.put("ifadmin", '0');
            for(int i = 0;i < rolelist.size();i++){
                if("1".equals(rolelist.get(i).get("JSID").toString())){
                    outmap.put("ifadmin", '1');
                }
            }
            List<Map> pList = new ArrayList<>();
            for (Map m : list) {
                if ("x1".equals(String.valueOf(m.get("SPARENT")))) {
                    pList.add(m);
                }
            }
            for (Map p : pList) {
                List<Map> chList = new ArrayList<>();
                for (Map s : list) {
                    if (String.valueOf(p.get("sid")).equals(String.valueOf(s.get("SPARENT")))) {
                        chList.add(s);
                    }
                }
                p.put("chlist", chList);
            }
            outmap.put("list", pList);
            System.out.println("[pList] " + pList.toString());
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("list", new ArrayList<Map<String, Object>>());
        }
        return outmap;
    }

    public Map<String, Object> getAllTeacher(Map<String, Object> paramsMap) {

        Map<String, Object> outmap = new HashMap<String, Object>();
        System.out.println("getAllTeacher" + paramsMap);
        try {
            List<String> ids = roleDao.getBdLsIdsList(paramsMap);
            System.out.println("getAllTeacher---ids:" + ids);
            if (ids.size() != 0) {
                paramsMap.put("ids", ids);
            }
            List<Map<String, Object>> list = roleDao.getWBDLsList(paramsMap);
            outmap.put("list", list);
            outmap.put("search", paramsMap);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", paramsMap);
        }

        return outmap;
    }

    public Map<String, Object> getUserInfoByUserId(Map<String, Object> paramsMap) {
        Map<String, Object> outmap = new HashMap<String, Object>();
        try {
            //   System.out.println(paramsMap);

            Map<String, Object> userMap = roleDao.getUserInfoByUserId(paramsMap);

//            List<Map> map2=roleDao.ifXzls(paramsMap);


//            if(map2!=null){
//                userMap.put("ifxzls","1");
//            }else{
//                userMap.put("ifxzls","0");
//            }

            outmap.put("userMap", userMap);
            outmap.put("result", 1);

        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", 0);
        }
        return outmap;
    }

    public Map<String, Object> getStudentsByTeacherCourse(Map<String, Object> paramsMap) {
        Map<String, Object> outmap = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> userList = roleDao.getStudentsByTeacherCourse(paramsMap);
            outmap.put("userList", userList);
            outmap.put("result", 1);

        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", 0);
        }
        return outmap;
    }

    public Map<String, Object> getAllDeptTree(Map<String, Object> paramsMap) {
        Map<String, Object> outmap = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> allDeptList = roleDao.getAllDepartment();
            List<Map<String, Object>> allMajorList = roleDao.getAllMajors();
            List<Map<String, Object>> allCourseList = roleDao.getAllCourse();
            List<Map<String, Object>> allStudentList = roleDao.getAllStudents();
            List<Map<String, Object>> allClassList = roleDao.getAllClasses();
            //获取所有院系
            for(int i = 0;i < allDeptList.size();i++){
                List<Map<String, Object>> majorList = new ArrayList<>();
                //获取所有专业
                for(int j = 0;j <allMajorList.size();j++){
                    //将专业与院系对应
                    if(String.valueOf(allDeptList.get(i).get("id")).equals(String.valueOf(allMajorList.get(j).get("p_id")))){
                        List<Map<String, Object>> classList = new ArrayList<>();
                        //将班级与专业对应
                        for(int k = 0;k < allClassList.size();k++){
                            if(String.valueOf(allMajorList.get(j).get("id")).equals(String.valueOf(allClassList.get(k).get("p_id")))){
                                //将课程与班级对应
                                List<Map<String, Object>> courseList = new ArrayList<>();
                                for(int t = 0;t < allCourseList.size();t++){
                                    if(String.valueOf(allClassList.get(k).get("id")).equals(String.valueOf(allCourseList.get(t).get("classId")))){
                                        courseList.add(allCourseList.get(t));
                                    }
                                }
//                                allClassList.get(k).put("courseList",courseList);
                                allClassList.get(k).put("childList",courseList);
                                //将课程与班级对应
                                List<Map<String, Object>> studentList = new ArrayList<>();
                                for(int z = 0;z < allStudentList.size();z++){
                                    if(String.valueOf(allStudentList.get(z).get("classId")).equals(String.valueOf(allClassList.get(k).get("id")))){
                                        studentList.add(allStudentList.get(z));
                                    }
                                }
//                                allClassList.get(k).put("studentList",studentList);
                                allClassList.get(k).put("childList",studentList);
//                                allClassList.get(k).put("courseList",courseList);
                                allClassList.get(k).put("childList",courseList);

                                classList.add(allClassList.get(k));
                            }
                        }
//                        allMajorList.get(j).put("classList",classList);
                        allMajorList.get(j).put("childList",classList);
                        majorList.add(allMajorList.get(j));
                    }
                }
//                allDeptList.get(i).put("majorList",majorList);
                allDeptList.get(i).put("childList",majorList);
            }
            outmap.put("deptList", allDeptList);
            outmap.put("result", 1);

        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", 0);
        }
        return outmap;
    }

    public Map<String, Object> getAllDeptTree2(Map<String, Object> paramsMap) {
        Map<String, Object> outmap = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> allDeptList = roleDao.getAllDepartment();
            List<Map<String, Object>> allMajorList = roleDao.getAllMajors();
//            List<Map<String, Object>> allCourseList = roleDao.getAllCourse();
            List<Map<String, Object>> allStudentList = roleDao.getAllStudents();
            List<Map<String, Object>> allClassList = roleDao.getAllClasses();
            //获取所有院系
            for(int i = 0;i < allDeptList.size();i++){
                List<Map<String, Object>> majorList = new ArrayList<>();
                //获取所有专业
                for(int j = 0;j <allMajorList.size();j++){
                    //将专业与院系对应
                    if(String.valueOf(allDeptList.get(i).get("id")).equals(String.valueOf(allMajorList.get(j).get("p_id")))){
                        List<Map<String, Object>> classList = new ArrayList<>();
                        //将班级与专业对应
                        for(int k = 0;k < allClassList.size();k++){
                            if(String.valueOf(allMajorList.get(j).get("id")).equals(String.valueOf(allClassList.get(k).get("p_id")))){
                                //将学生与班级对应
                                List<Map<String, Object>> studentList = new ArrayList<>();
                                for(int z = 0;z < allStudentList.size();z++){
                                    if(String.valueOf(allStudentList.get(z).get("classId")).equals(String.valueOf(allClassList.get(k).get("id")))){
                                        studentList.add(allStudentList.get(z));
                                    }
                                }
//                                allClassList.get(k).put("studentList",studentList);
                                allClassList.get(k).put("childList",studentList);

                                classList.add(allClassList.get(k));
                            }
                        }
//                        allMajorList.get(j).put("classList",classList);
                        allMajorList.get(j).put("childList",classList);
                        majorList.add(allMajorList.get(j));
                    }
                }
//                allDeptList.get(i).put("majorList",majorList);
                allDeptList.get(i).put("childList",majorList);
            }
            outmap.put("deptList", allDeptList);
            outmap.put("result", 1);

        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", 0);
        }
        return outmap;
    }

    public Map<String, Object> getExaminationRooms(Map<String, Object> paramsMap) {
        Map<String, Object> outmap = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> roomList = roleDao.getExaminationRooms();
            outmap.put("roomList", roomList);
            outmap.put("result", 1);

        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", 0);
        }
        return outmap;
    }

    public Map<String, Object> getCourseTreeByLSID(Map<String, Object> paramsMap) {
        Map<String, Object> outmap = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> courseList = roleDao.getAllCourseByLSID(paramsMap);
            List<Map<String, Object>> classList = roleDao.getAllClasses2();
            List<Map<String, Object>> courseClassRelations = roleDao.getCourseClassRelation();
            List<Map<String, Object>> studentClassRelations = roleDao.getAllStudents();
            List<Map<String, Object>> classes = new ArrayList<Map<String,Object>>();
            int g = 0;
            for(int i = 0;i < courseList.size();i++){
                for(int j = 0;j < courseClassRelations.size();j++){
                    if(String.valueOf(courseList.get(i).get("courseId")).equals(String.valueOf(courseClassRelations.get(j).get("courseId")))){
                        for(int k = 0;k < classList.size();k++){
                            if(String.valueOf(courseClassRelations.get(j).get("classId")).equals(String.valueOf(classList.get(k).get("classId")))){
                                List<Map<String, Object>> students = new ArrayList<Map<String,Object>>();
                                for(int z = 0;z < studentClassRelations.size();z++){
                                    if(String.valueOf(classList.get(k).get("classId")).equals(String.valueOf(studentClassRelations.get(z).get("classId")))){
                                        students.add(studentClassRelations.get(z));
                                        g++;
                                        System.out.println(g);
                                    }
                                }
                                classList.get(k).put("childList",students);
                                classes.add(classList.get(k));
                            }
                        }
                    }
                }
                courseList.get(i).put("childList",classes);
            }
            outmap.put("courseList", courseList);
            outmap.put("result", 1);

        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", 0);
        }
        return outmap;
    }
}
