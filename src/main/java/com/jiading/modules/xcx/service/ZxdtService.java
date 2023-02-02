package com.jiading.modules.xcx.service;

import com.jiading.common.util.FenYe;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.mapper.UserMapper;
import com.jiading.modules.xcx.dao.JbrfcDao;
import com.jiading.modules.xcx.dao.ZxdtDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ZxdtService {

    @Autowired
    private ZxdtDao zxdtDao;



    public ResultMap getZxdtList(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();
        int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
        int pagesize = FenYe.pagesize(params.get("pagesize"));
        params.put("pagesize", pagesize);
        params.put("pageindex", pageindex);
        List<Map> list = zxdtDao.getZxdtList(params);
        int count = zxdtDao.getZxdtListCount(params);
        int pagecount = FenYe.pagecount(count, pagesize);
        resultMap.put("pagecount", pagecount);
        resultMap.put("count", count);
        resultMap.put("list",list);
        return resultMap;
    }
}
