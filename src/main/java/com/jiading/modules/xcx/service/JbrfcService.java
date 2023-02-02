package com.jiading.modules.xcx.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiading.common.util.FenYe;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Xmlx;
import com.jiading.modules.back.mapper.UserMapper;
import com.jiading.modules.back.mapper.XmlxMapper;
import com.jiading.modules.xcx.dao.HomeMapper;
import com.jiading.modules.xcx.dao.JbrfcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JbrfcService {

    @Autowired
    private JbrfcDao jbrfcDao;



    public ResultMap getJbrList(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();
        int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
        int pagesize = FenYe.pagesize(params.get("pagesize"));
        params.put("pagesize", pagesize);
        params.put("pageindex", pageindex);
        List<Map> list = jbrfcDao.getJbrList(params);
        int count = jbrfcDao.getJbrListCount(params);
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i).get("hdjx"));
            if(list.get(i).get("hdjx") != null && !"".equals(list.get(i).get("hdjx").toString())){
                String[] strs = list.get(i).get("hdjx").toString().split(",");
                System.out.println(strs.toString());
                list.get(i).put("hdjxList",strs);
            }
            params.put("yhid",list.get(i).get("pid"));
            List<Map> jblist = jbrfcDao.getJbjlList(params);
            list.get(i).put("jbList",jblist);
        }
        int pagecount = FenYe.pagecount(count, pagesize);
        resultMap.put("pagecount", pagecount);
        resultMap.put("count", count);
        resultMap.put("list",list);
        return resultMap;
    }
}
