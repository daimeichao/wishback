package com.jiading.modules.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.common.util.FenYe;
import com.jiading.common.util.Md5;
import com.jiading.common.util.PhoneUtil;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.*;
import com.jiading.modules.back.mapper.DzdMapper;
import com.jiading.modules.back.role.dao.RoleDao;
import com.jiading.modules.back.service.UserBqService;
import com.jiading.modules.back.service.UserService;
import com.jiading.modules.back.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleDao roleDao;

    /**
     * 角色详情
     * @param pid
     * @param jyzk
     * @return
     */
    @Override
    public ResultMap getUserById(Integer pid, String jyzk) {
        if (pid == null) {
            return ResultMap.error("pid不能为空");
        }
        User user = getById(pid);

        ResultMap resultMap = new ResultMap();
        if (user == null) {
            resultMap.put("data", new User());
            return resultMap;
        }
        resultMap.put("data", user);
        return resultMap;
    }

    @Override
    public ResultMap getUserById2(Map<String, Object> params) {
        if (params.get("pid") == null || "".equals(params.get("pid").toString())) {
            return ResultMap.error("pid不能为空");
        }
        User user = getById(params.get("pid").toString());

        ResultMap resultMap = new ResultMap();
        if (user == null) {
            resultMap.put("data", new User());
            return resultMap;
        }
        resultMap.put("data", user);
        return resultMap;
    }

    /**
     * 获取所有的角色（分页）
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> getListByMap(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

            //用户列表
            List<Map<String, Object>> list = userMapper.getListByMap(params);
            int count = userMapper.getListCountByMap(params);
            int pagecount = FenYe.pagecount(count, pagesize);

            // 返回
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("pagecount", pagecount);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("count", 0);
            outmap.put("list", Collections.emptyList());
            outmap.put("curpage", 1);
            outmap.put("pagesize", 10);
        }
        return outmap;
    }

    /**
     * 根据id删除
     * @param pid
     * @return
     */
    @Override
    public ResultMap removeById(Integer pid) {
        int s = userMapper.removeById(pid);
        if (s <= 0) {
            return ResultMap.error("删除失败！");
        }
        userMapper.deleteRoleByYhid(pid);
        return ResultMap.ok();
    }

    @Override
    public ResultMap updateUser(User user) {
//        String phone = user.getPhone();
//        System.out.println("phone = " + phone);
//        if (phone != null && userMapper.getOneByPhoneAndPid(user) != null) {
//            return ResultMap.error("该手机号码已注册");
//        }

//        String lb = user.getType();
//        if ("2".equals(lb)) {
//            String mm = user.getPassword();
//            if (StringUtils.isBlank(mm)) {
//                return ResultMap.error("请输入密码");
//            }
//            String md5Password = Md5.MD5Encode(mm);
//            user.setPassword(md5Password);
//        }

        if (!updateById(user)) {
            return ResultMap.error("更新失败！");
        }
        return ResultMap.ok();
    }

    //根据id获取未删除未禁用的用户
    @Override
    public User getOneByPidAndSczkAndJyzk(Integer pid, String jyzk) {
        String a = null == jyzk ? "0" : jyzk;
        return userMapper.getOneByPidAndSczkAndJyzk(pid, "0", a);
    }

    @Override
    public ResultMap addUser(User user) {
//        if (StringUtils.isBlank(user.getXm())) {
//            return ResultMap.error("姓名不能为空");
//        }
//        if (!PhoneUtil.isLegalMobileNumber(user.getPhone())) {
//            return ResultMap.error("手机号码格式不合法");
//        }
//        if (userMapper.getOneByPhone(user.getPhone()) != null) {
//            return ResultMap.error("该手机号码已注册");
//        }
        if (StringUtils.isBlank(user.getOpenid())) {
            return ResultMap.error("Openid为空");
        }

//        String lb = user.getLb();
//        if ("1".equals(lb)) {
            String mm = user.getPassword();
            if (StringUtils.isBlank(mm)) {
                return ResultMap.error("请输入密码");
            }
            String md5Password = Md5.MD5Encode(mm);
            user.setPassword(md5Password);
//        }
        if (!save(user)) {
            return ResultMap.error("添加失败");
        }
        return ResultMap.ok();
    }

    @Override
    public ResultMap addUserFromAdmin(User user) {
        User user123 = userMapper.getOneByPhoneOrXm(user.getAccount(), user.getAccount());
        if (user123 != null) {
            return ResultMap.error("用户名已被注册！");
        }
        if (StringUtils.isBlank(user.getName())) {
            return ResultMap.error("姓名不能为空");
        }
//        if (!PhoneUtil.isLegalMobileNumber(user.getPhone())) {
//            return ResultMap.error("手机号码格式不合法");
//        }
//        if (userMapper.getOneByPhone(user.getPhone()) != null) {
//            return ResultMap.error("该手机号码已注册");
//        }

//        String lb = user.getLb();
//        if ("1".equals(lb)) {
            String mm = user.getPassword();
            if (StringUtils.isBlank(mm)) {
                return ResultMap.error("请输入密码");
            }
            String md5Password = Md5.MD5Encode(mm);
            user.setPassword(md5Password);
//        }
        if (!save(user)) {
            return ResultMap.error("添加失败");
        }

        return ResultMap.ok();
    }

    public Map getInfo(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            List<Map> list = userMapper.getInfo(params);
            List<Map> sellist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);

                Map<String, Object> map1 = new HashMap();
                map1.put("id", map.get("pid"));
                map1.put("text", map.get("zwmc"));
                sellist.add(map1);
            }

            outmap.put("list", sellist);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }

    public Map getSchoolInfo(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            List<Map> list = userMapper.getSchoolInfo(params);
            List shoollist = new ArrayList<>();
            List shoolidlist = new ArrayList<>();
            shoollist.add("请选择毕业院校");
            shoolidlist.add(-1);
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                shoollist.add(map.get("mc"));
                shoolidlist.add(map.get("pid"));
            }
            Map outmap1 = new HashMap();
            outmap1.put("shoollist",shoollist);
            outmap1.put("shoolidlist",shoolidlist);

            outmap.put("list", outmap1);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }

    @Override
    public ResultMap getUserInfo1(Map<String, Object> params) {
        QueryWrapper<User> queryWrapperOne = new QueryWrapper();
        queryWrapperOne.eq("openid", params.get("openid"));
        User user = baseMapper.selectOne(queryWrapperOne);
        return ResultMap.ok().put("data", user);
    }

    @Override
    public ResultMap changeType(User user) {
        Integer pid = user.getPid();
        if (ObjectUtils.isEmpty(pid) || pid < 0) {
            return ResultMap.error("请选择要更改类型的用户");
        }
        String lb = user.getType();
        if (StringUtils.isBlank(lb)) {
            return ResultMap.error("请选择类别");
        }
        if ("2".equals(lb)) {
            String mm = user.getPassword();
            if (StringUtils.isBlank(mm)) {
                return ResultMap.error("请输入密码");
            }
            String md5Password = Md5.MD5Encode(mm);
            int i = userMapper.updateLbAndMmByPid(lb, md5Password, pid);
            if (i != 1) {
                return ResultMap.error("保存失败");
            }
        } else if ("1".equals(lb)) {
            int i = userMapper.updateLbAndMmByPid(lb, null, pid);
            if (i != 1) {
                return ResultMap.error("保存失败");
            }
        }
        return ResultMap.ok();
    }

    @Override
    public ResultMap updatePassword(User user) {
        Integer pid = user.getPid();
        if (ObjectUtils.isEmpty(pid) || pid < 0) {
            return ResultMap.error("请选择要更改类型的用户");
        }
//        String lb = user.getLb();
//        if (!"1".equals(lb)) {
//            return ResultMap.error("不是工作人员无法重置密码");
//        }
        String mm = user.getPassword();
        System.out.println(mm);
        if (StringUtils.isBlank(mm)) {
            return ResultMap.error("请输入新的密码");
        }

        String md5Password = Md5.MD5Encode(mm);
        int i = userMapper.updatePasswordAndLb(md5Password, pid);
        if (i != 1) {
            return ResultMap.error("保存失败");
        }
        return ResultMap.ok();
    }
    @Override
    public ResultMap updateNowPassword(Map<String, Object> params) {
        String pid = (String)params.get("pid");
        if (pid == null || "".equals(pid)) {
            return ResultMap.error("请选择要更改的用户");
        }
//        String lb = user.getLb();
//        if (!"1".equals(lb)) {
//            return ResultMap.error("不是工作人员无法重置密码");
//        }
        String old_password = (String)params.get("old_password");
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("pid",params.get("pid"))
                .eq("del","0")
        );
        if (user == null) {
            return ResultMap.error("用户不存在");
        }

//        if (!"1".equals(user.getLb())) {
//            return ResultMap.error("该账号不是工作人员");
//        }

        if (!user.getPassword().equals(Md5.MD5Encode(old_password))) {
            return ResultMap.error("密码错误");
        }


        String mm = (String)params.get("new_password");
        if (StringUtils.isBlank(mm)) {
            return ResultMap.error("请输入新的密码");
        }

        String md5Password = Md5.MD5Encode(mm);
        System.out.println("[md5Password]: " + md5Password);
//        int i = userMapper.updatePassword(md5Password, pid);
        int i = userMapper.update(null, new UpdateWrapper<User>().eq("pid",params.get("pid") ).set("password",md5Password));
        if (i != 1) {
            return ResultMap.error("修改失败");
        }
        return ResultMap.ok();
    }

    @Override
    public ResultMap grantAuthorize(Integer yhid, String jsids) {
        System.out.println("jsidList = " + jsids);
        if (ObjectUtils.isEmpty(yhid) || yhid < 0) {
            return ResultMap.error("请选择要授权的用户");
        }
//        if (ObjectUtils.isEmpty(jsids)) {
//            return ResultMap.error("请选择要授权的角色");
//        }
        // 删除用户现有的角色列表
        userMapper.deleteRoleByYhid(yhid);
        if (ObjectUtils.isEmpty(jsids)) {
            return ResultMap.ok();
        }

        String[] split = jsids.split(",");

        for (String jsid : split) {
            //循环给用户授权（添加角色列表）
            int i = userMapper.insertRole(yhid, jsid);
        }


//        int count = userMapper.getRoleByJsidAndYhid(yhid, jsid);
//        if (count > 0) {
//            return ResultMap.error("该角色已经授权过该用户");
//        }

//        if (i != 1) {
//            return ResultMap.error("授权失败");
//        }
        return ResultMap.ok();
    }

    @Override
    public ResultMap getUserRoleList(Integer pid) {
        List<Integer> list = userMapper.getUserRoleListByPid(pid);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

    @Override
    public ResultMap getShzt2AllList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> eq = queryWrapper.eq("del", "0").eq("shzt", "2");

        List<User> list = userMapper.selectList(eq);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

}




