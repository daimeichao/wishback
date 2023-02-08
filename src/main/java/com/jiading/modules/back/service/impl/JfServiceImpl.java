package com.jiading.modules.back.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.common.util.FenYe;
import com.jiading.modules.back.domain.TJf;
import com.jiading.modules.back.mapper.JfMapper;
import com.jiading.modules.back.service.JfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JfServiceImpl extends ServiceImpl<JfMapper, TJf> implements JfService {
    @Autowired
 private    JfMapper jfMapper;
//    查询积分列表所有
    @Override
    public Map<String, Object> getListByMap(Map<String, Object> params) {
        Map outmap = new HashMap();
        List<Map<String, Object>> list = new ArrayList<>(); //当前页的展示列表
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            int count = 0;
            int pagecount = 0;
            params.put("pagesize", pagesize); // 计算从第几个开始显示
            params.put("pageindex", pageindex); // 每页显示个数
            list = jfMapper.getListByMap(params); // 返回当前页的展示列表
            count = jfMapper.getListCountByMap(params); // 计算真实数据总数
            pagecount = FenYe.pagecount(count, pagesize);
            // 返回
            outmap.put("pagecount", pagecount); // 返回总页数
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 10);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }
    @Override
    public Map delzyzById(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            jfMapper.delById(params);
            outmap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result","err");
        }
        return outmap;
    }
    @Override
    public Map getById(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            Map map=jfMapper.getdetail(params);
            outmap.put("map",map);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("map", new HashMap<>());
        }
        return outmap;
    }
    @Override
    public Map addsp(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            jfMapper.addsp(params);
            outmap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", "err");
        }
        return outmap;
    }
    @Override
    public Map updsp(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            jfMapper.updsp(params);
            outmap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", "err");
        }
        return outmap;
    }
    @Override
    public Map delsp(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            jfMapper.delsp(params);
            outmap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", "err");
        }
        return outmap;
    }
    //    查询积分列表所有
    @Override
    public Map<String, Object> splist(Map<String, Object> params) {
        Map outmap = new HashMap();
        List<Map<String, Object>> list = new ArrayList<>(); //当前页的展示列表
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            int count = 0;
            int pagecount = 0;
            params.put("pagesize", pagesize); // 计算从第几个开始显示
            params.put("pageindex", pageindex); // 每页显示个数
            list = jfMapper.splist(params); // 返回当前页的展示列表
            count = jfMapper.spcount(params); // 计算真实数据总数
            pagecount = FenYe.pagecount(count, pagesize);
            // 返回
            outmap.put("pagecount", pagecount); // 返回总页数
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
            outmap.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 10);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
            outmap.put("result", "err");
        }
        return outmap;
    }
    @Override
    public Map spById(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            Map map=jfMapper.spdetail(params);
            outmap.put("map",map);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("map", new HashMap<>());
        }
        return outmap;
    }
}
