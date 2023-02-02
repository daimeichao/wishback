package com.jiading.modules.back.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.common.util.FenYe;
import com.jiading.common.util.Md5;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.*;
import com.jiading.modules.back.domain.Msg;
import com.jiading.modules.back.mapper.DzdMapper;
import com.jiading.modules.back.mapper.MsgMapper;
import com.jiading.modules.back.mapper.UserMsgMapper;
import com.jiading.modules.back.mapper.XmMapper;
import com.jiading.modules.back.service.MsgService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements MsgService {
    @Autowired
    private DzdMapper dzdMapper;

    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private UserMsgMapper userMsgMapper;

    @Autowired
    private XmMapper xmmapper;

    @Override
    public ResultMap getMsgById(Integer pid) {
        if (pid == null) {
            return ResultMap.error("pid不能为空");
        }
//        Msg msg = getOneByPidAndSczkAndJyzk(pid, jyzk);
        Msg msg = getById(pid);

        ResultMap resultMap = new ResultMap();
        if (msg == null) {
            resultMap.put("data", new Msg());
            return resultMap;
        }
//        List<User> jsrList = userMsgMapper.getAllBySczkAndXxid(msg.getPid());
//        msg.setJsrList(jsrList); 

        List<String> jsrList = userMsgMapper.getAllIdBySczkAndXxid(msg.getPid());
        msg.setJsridListStr(jsrList.toString());
        System.out.println("jsrList = " + jsrList);
//
        Xm xm = xmmapper.selectById(msg.getXmid());
        msg.setXm(xm);

        resultMap.put("data", msg);
        return resultMap;
    }

    @Override
    public Map<String, Object> getListByMap(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

            //试卷列表
            List<Map<String, Object>> list = msgMapper.getListByMap(params);
            int count = msgMapper.getListCountByMap(params);
            int pagecount = FenYe.pagecount(count, pagesize);


            //消息id
            List<String> xxidList = list.stream().map(m -> m.get("pid").toString()).collect(Collectors.toList());

            if (!ObjectUtils.isEmpty(xxidList)) {
                List<Map<String, Object>> userList = msgMapper.getJsrListByMsgIds(xxidList);
//                System.out.println("userList = " + userList);
                for (Map<String, Object> xxMap : list) {
                    StringBuilder sb = new StringBuilder();
                    for (Map<String, Object> map : userList) {
                        if (xxMap.get("pid").toString().equals(map.get("xxid").toString())) {
                            sb.append(map.get("xm").toString()).append(",");
                        }
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    xxMap.put("jsrxmList", sb);
                }
            }


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

    @Override
    public ResultMap addMsg(Msg msg) {
        System.out.println("msg = " + msg);

        String jsridListStr = msg.getJsridListStr();
        if (ObjectUtils.isEmpty(jsridListStr)) {
            return ResultMap.error("请选择接收人");
        }
//        if (ObjectUtils.isEmpty(msg.getXmid())) {
//            return ResultMap.error("请选择项目");
//        }


        if (!save(msg)) {
            return ResultMap.error("添加失败");
        }


        List<UserMsg> userMsgs = new ArrayList<>();
        String[] jsridList = jsridListStr.split(",");
        for (String jsridStr : jsridList) {
            UserMsg userMsg = new UserMsg(Integer.valueOf(jsridStr), msg.getPid());
            userMsgs.add(userMsg);
        }
        int i = userMsgMapper.insertBatch(userMsgs);
        if (i == 0) {
            return ResultMap.error("添加失败");
        }

        return ResultMap.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultMap deleteMsg(Integer pid) {
        userMsgMapper.deleteByXxid(pid);
        if (removeById(pid)) {
            return new ResultMap();
        } else {
            throw new RuntimeException("删除失败！");
        }
    }

    @Override
    public ResultMap updateMsg(Msg msg) {

        String jsridListStr = msg.getJsridListStr();
        if (ObjectUtils.isEmpty(jsridListStr)) {
            return ResultMap.error("请选择接收人");
        }
//        if (ObjectUtils.isEmpty(msg.getXmid())) {
//            return ResultMap.error("请选择项目");
//        }


        if (!updateById(msg)) {
            return ResultMap.error("保存失败");
        }

        Integer pid = msg.getPid();
        userMsgMapper.deleteByXxid(pid);
        List<UserMsg> userMsgs = new ArrayList<>();
        String[] jsridList = jsridListStr.split(",");
        for (String jsridStr : jsridList) {
            UserMsg userMsg = new UserMsg(Integer.valueOf(jsridStr), msg.getPid());
            userMsgs.add(userMsg);
        }
        int i = userMsgMapper.insertBatch(userMsgs);
        if (i == 0) {
            return ResultMap.error("保存失败");
        }

        return ResultMap.ok();
    }
}
