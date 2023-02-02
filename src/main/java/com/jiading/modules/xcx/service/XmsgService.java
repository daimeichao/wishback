package com.jiading.modules.xcx.service;

import com.jiading.common.util.FenYe;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.xcx.dao.XmsgDao;
import com.jiading.modules.xcx.dao.ZxdtDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanchao
 * @date 2022/8/26 15:34
 */

@Service
public class XmsgService {

    @Autowired
    private XmsgDao xmsgDao;

    public ResultMap getXmsgList(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();

        try {
            List<Map> list = xmsgDao.getXmsgByid(params);
            resultMap.put("code",1);
            resultMap.put("list",list);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code",0);
            resultMap.put("list",new ArrayList<Map<String, Object>>());
        }
        int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
        int pagesize = FenYe.pagesize(params.get("pagesize"));
        params.put("pagesize", pagesize);
        params.put("pageindex", pageindex);
        List<Map> list = xmsgDao.getXmsgList(params);
        int j = 1;
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            map.put("index",j);
            j++;
            if(j==4){j=1;}
        }
        int count = xmsgDao.getXmsgListCount(params);
        int pagecount = FenYe.pagecount(count, pagesize);
        resultMap.put("pagecount", pagecount);
        resultMap.put("count", count);
        resultMap.put("list",list);
        return resultMap;
    }

    public Map<String, Object> updateyd(Map<String, Object> prarms) {
        Map<String, Object> outmap = new HashMap();
        try {
            int i = xmsgDao.updateyd(prarms);
            // 返回
            outmap.put("code", i);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("code", 0);
        }
        return outmap;
    }

    public ResultMap getXmsgByid(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();
        try {
            List<Map> list = xmsgDao.getXmsgByid(params);
            resultMap.put("code",1);
            resultMap.put("list",list);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code",0);
            resultMap.put("list",new ArrayList<Map<String, Object>>());
        }


        return resultMap;
    }
}
