package com.jiading.modules.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.*;
import com.jiading.modules.back.mapper.*;
import com.jiading.modules.back.service.XmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class XmServiceImpl extends ServiceImpl<XmMapper, Xm>
        implements XmService {

    @Autowired
    private DzdMapper dzdMapper;

    @Autowired
    private XmMapper xmMapper;

    @Autowired
    private FjbMapper fjbMapper;

    @Autowired
    private UserJiebMapper userJiebMapper;

    @Autowired
    private XmlxMapper xmlxMapper;

    @Override
    public ResultMap getXmInfoByIdWithShzt2(Integer pid, Integer yhid) {
        ResultMap resultMap = new ResultMap();

        QueryWrapper<Xm> xmQueryWrapper = new QueryWrapper<>();
        xmQueryWrapper.eq("shzt", "2");
        xmQueryWrapper.eq("pid", pid);
        Xm xm = getOne(xmQueryWrapper);

        Dzd dzd = dzdMapper.selectById(xm.getXmnrid());
        xm.setXmnr(dzd.getVt());

        //附件
        QueryWrapper<Fjb> fjbQueryWrapper = new QueryWrapper<Fjb>();

        //项目id
        Integer xmid = xm.getPid();

        fjbQueryWrapper.eq("ssxmid", xmid);
        List<Fjb> fjbs = fjbMapper.selectList(fjbQueryWrapper);
        xm.setFjList(fjbs);

        //揭榜人

        List<User> jbrList = userJiebMapper.getXmJbrListWithDealName(xmid);

        for (User user : jbrList) {
            String username = user.getName();
//            int length = username.length();

            user.setName(username.substring(0, 1) + "**");
        }

        xm.setJbrList(jbrList);

        //数量
        int jbrCount = userJiebMapper.getUserCountByXmid2(xmid);

        resultMap.put("jbrCount", jbrCount);

        HashMap<String, Object> map = new HashMap<>();
        map.put("pid", xm.getPid());
        List<Xmlx> subXmlxList = xmlxMapper.getAllXmlxByXmid(map);


        boolean hasJieBang = false;

        if (yhid != null) {
            //是否揭榜过
            UserJieb oneByYhidAndXmid = userJiebMapper.getOneByYhidAndXmid(yhid, xmid);
            if (oneByYhidAndXmid != null) {
                hasJieBang = true;
            }
        }


        //判断时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime kssj = xm.getKssj();
        LocalDateTime jzsj = xm.getJzsj();

        if (jzsj != null && now.isAfter(jzsj)) {
            hasJieBang = true;
        }

        if (kssj != null && now.isBefore(kssj)) {
            hasJieBang = true;
        }

        if ("1".equals(xm.getSfxj())) {
            hasJieBang = true;
        }
        if (!"1".equals(xm.getSftj())) {
            hasJieBang = true;
        }
        if (!"0".equals(xm.getJazt())) {
            hasJieBang = true;
        }

        /* 超过人数限制 */

        int size = jbrList == null ? 0 : jbrList.size();
//        System.out.println("size = " + size);
        Integer jbrs = xmMapper.getJbrsByPidAndSczk(xmid);
//        System.out.println("jbrs = " + jbrs);
        jbrs = jbrs == null ? 0 : jbrs;
        if (jbrs <= size) {
            hasJieBang = true;
        }

        xm.setXmlxList(subXmlxList);
        resultMap.put("data", xm);
        resultMap.put("hasJieBang", hasJieBang);

        return resultMap;
    }

    @Override
    public ResultMap getXmInfoByIdWithShztall(Integer pid, Integer yhid) {
        ResultMap resultMap = new ResultMap();

        QueryWrapper<Xm> xmQueryWrapper = new QueryWrapper<>();
        xmQueryWrapper.eq("pid", pid);
        Xm xm = getOne(xmQueryWrapper);

        Dzd dzd = dzdMapper.selectById(xm.getXmnrid());
        xm.setXmnr(dzd.getVt());

        //附件
        QueryWrapper<Fjb> fjbQueryWrapper = new QueryWrapper<Fjb>();

        //项目id
        Integer xmid = xm.getPid();

        fjbQueryWrapper.eq("ssxmid", xmid);
        List<Fjb> fjbs = fjbMapper.selectList(fjbQueryWrapper);
        xm.setFjList(fjbs);

        //揭榜人

        List<User> jbrList = userJiebMapper.getXmJbrListWithDealName(xmid);

        for (User user : jbrList) {
            String username = user.getName();
//            int length = username.length();

            user.setName(username.substring(0, 1) + "**");
        }

        xm.setJbrList(jbrList);

        //数量
        int jbrCount = userJiebMapper.getUserCountByXmid2(xmid);

        resultMap.put("jbrCount", jbrCount);

        HashMap<String, Object> map = new HashMap<>();
        map.put("pid", xm.getPid());
        List<Xmlx> subXmlxList = xmlxMapper.getAllXmlxByXmid(map);


        boolean hasJieBang = false;

        if (yhid != null) {
            //是否揭榜过
            UserJieb oneByYhidAndXmid = userJiebMapper.getOneByYhidAndXmid(yhid, xmid);
            if (oneByYhidAndXmid != null) {
                hasJieBang = true;
            }
        }

        //判断时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime kssj = xm.getKssj();
        LocalDateTime jzsj = xm.getJzsj();

        if (jzsj != null && now.isAfter(jzsj)) {
            hasJieBang = true;
        }

        if (kssj != null && now.isBefore(kssj)) {
            hasJieBang = true;
        }

        xm.setXmlxList(subXmlxList);
        resultMap.put("data", xm);
        resultMap.put("hasJieBang", hasJieBang);

        return resultMap;
    }

    @Override
    public ResultMap getXmById(Map<String, Object> params) {
        if (params.get("pid") == null || "".equals(params.get("pid").toString())) {
            return ResultMap.error("pid不能为空");
        }
//        User user = getOneByPidAndSczkAndJyzk(pid, jyzk);
        Map<String, Object> xm = xmMapper.getXmInfo(params);
        List<Xmlx> xmlxList = xmlxMapper.getAllXmlxByXmid(xm);
        List<Map<String, Object>> fjList = fjbMapper.getFjByXmid(xm);
        xm.put("xmlxList", xmlxList);
        xm.put("fjList", fjList);
        ResultMap resultMap = new ResultMap();
        if (xm == null) {
            resultMap.put("data", new User());
            return resultMap;
        }
//        //获取标签
//        List<Bq> bqList = userMapper.getUserBqById(pid);
//        user.setBqList(bqList);
        resultMap.put("data", xm);
        return resultMap;
    }

    @Override
    public ResultMap getShzt2AllList() {
        QueryWrapper<Xm> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Xm> eq = queryWrapper.eq("sczk", "0").eq("shzt", "2");

        List<Xm> list = xmMapper.selectList(eq);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }
}




