package com.jiading.modules.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.common.util.FenYe;
import com.jiading.common.util.Md5;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Dzd;
import com.jiading.modules.back.domain.User;
import com.jiading.modules.back.domain.Zx;
import com.jiading.modules.back.mapper.DzdMapper;
import com.jiading.modules.back.mapper.XmlxMapper;
import com.jiading.modules.back.service.ZxService;
import com.jiading.modules.back.mapper.ZxMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class ZxServiceImpl extends ServiceImpl<ZxMapper, Zx>  implements ZxService{
    @Autowired
    private ZxMapper zxMapper;

    @Autowired
    private DzdMapper dzdMapper;

    @Override
    public Map<String, Object> getZxList(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize",pagesize);
            params.put("pageindex",pageindex);


            List<Map<String, Object>> list = zxMapper.getZxList(params);
            int count = zxMapper.getZxListCount(params);

            // 返回
            outmap.put("count", count);
            outmap.put("list", list);
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
    public Map<String, Object> getZxBypid(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            List<Map<String, Object>> list = zxMapper.getZxBypid(params);
            outmap.put("list", list);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("list", Collections.emptyList());
        }
        return outmap;
    }

    @Override
    public Map<String, Object> updateZx(Map<String, Object> prarms) {
        Map<String, Object> outmap = new HashMap();
        try {
            int i = zxMapper.updateZx(prarms);
            int j = zxMapper.updateDzd(prarms);
            // 返回
            outmap.put("code", i);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("code", 0);
        }
        return outmap;
    }

    @Override
    public Map<String, Object> delZx(Map<String, Object> prarms) {
        Map<String, Object> outmap = new HashMap();
        try {
            int i = zxMapper.delZx(prarms);
            outmap.put("code", i);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("code", 0);
        }
        return outmap;
    }


    @Override
    public ResultMap addZx(Zx zx) {

            if (!StringUtils.isBlank(zx.getZxnr())) {
                Dzd dzd = new Dzd(zx.getZxnr());
                int insert = dzdMapper.insert(dzd);
                if(insert==0){
                    return ResultMap.error("添加失败");
                }else{
                    zx.setZxnrid(dzd.getPid());
                }
            }

            if (!save(zx)) {
                return ResultMap.error("添加失败");
            }
            return ResultMap.ok();
    }


}




