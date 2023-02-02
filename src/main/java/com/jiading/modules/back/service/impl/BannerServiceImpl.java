package com.jiading.modules.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.common.util.FenYe;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Banner;
import com.jiading.modules.back.domain.Dzd;
import com.jiading.modules.back.mapper.DzdMapper;
import com.jiading.modules.back.service.BannerService;
import com.jiading.modules.back.mapper.BannerMapper;
import com.jiading.modules.back.service.DzdService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.EncKrbPrivPart;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner>
        implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public ResultMap addBanner(Banner banner) {
        boolean save = save(banner);
        if (!save) {
            return ResultMap.error("添加失败");
        }
        return ResultMap.ok();
    }

    @Override
    public ResultMap updateBanner(Banner banner) {
        boolean save = updateById(banner);
        if (!save) {
            return ResultMap.error("更新失败");
        }
        return ResultMap.ok();
    }

    @Override
    public ResultMap getBannerById(Integer pid) {
        ResultMap resultMap = new ResultMap();
        Banner banner = bannerMapper.selectById(pid);
        resultMap.put("data", banner);
        return resultMap;
    }

    @Override
    public ResultMap pageList(Banner banner) {
        ResultMap resultMap = new ResultMap();

        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();

        IPage<Banner> jsjgIPage = new Page<>(banner.getCurpage(), banner.getPagesize());
        IPage<Banner> page = bannerMapper.selectPage(jsjgIPage, queryWrapper);

        resultMap.put("data", page);
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
            List<Map<String, Object>> list = bannerMapper.getListByMap(params);
            for (Map<String, Object> l:list) {
                if(l.get("type") != null && l.get("type") != ""){
                    if( "0".equals(l.get("type")) ) {
                        l.put("typeName","文本内容");
                    }
                    if( "1".equals(l.get("type")) ) {
                        l.put("typeName","链接地址");
                    }
                }
            }
            int count = bannerMapper.getListCountByMap(params);
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
}




