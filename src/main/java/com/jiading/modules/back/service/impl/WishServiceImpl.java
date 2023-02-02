package com.jiading.modules.back.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.common.util.FenYe;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Wish;
import com.jiading.modules.back.mapper.WishClainmantMapper;
import com.jiading.modules.back.mapper.WishMapper;
import com.jiading.modules.back.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class WishServiceImpl extends ServiceImpl<WishMapper, Wish> implements WishService {
    @Autowired
    private WishMapper wishMapper;
    @Autowired
    private WishClainmantMapper wishClainmantMapper;
    @Override
//    发布心愿列表
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
            list = wishMapper.getListByMap(params); // 返回当前页的展示列表
            count = wishMapper.getListCountByMap(params); // 计算真实数据总数
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
    //心愿列表wishList
    @Override
    public Map<String, Object> wishList(Map<String, Object> params) {
        Map outmap = new HashMap();
        List<Map<String, Object>> list = new ArrayList<>(); //当前页的展示列表
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            int count = 0;
            int pagecount = 0;
            params.put("pagesize", pagesize); // 计算从第几个开始显示
            params.put("pageindex", pageindex); // 每页显示个数
            String claimant_audit_state = String.valueOf(params.get("claimant_audit_state"));
            if("3".equals(claimant_audit_state) || "4".equals(claimant_audit_state) ||"5".equals(claimant_audit_state) ){
                params.put("claimant_audit_state","");
            }
            list = wishMapper.wishList(params); // 返回当前页的展示列表
            count = wishMapper.getListCountByMap(params); // 计算真实数据总数
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
    //心愿待审核表
    @Override
    public Map<String, Object> getSHList(Map<String, Object> params) {
        Map outmap = new HashMap();
        List<Map<String, Object>> list = new ArrayList<>(); //当前页的展示列表
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            int count = 0;
            int pagecount = 0;
            params.put("pagesize", pagesize); // 计算从第几个开始显示
            params.put("pageindex", pageindex); // 每页显示个数
            list = wishMapper.getSHList(params); // 返回当前页的展示列表
            count = wishMapper.getSXCount(params); // 计算真实数据总数
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
    public Map getListById(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            Map map = wishMapper.getListById(params);
        List<Map<String,Object>> urlList=wishClainmantMapper.geturl(params);
        outmap.put("urlList",urlList);
        System.out.println("urlList"+urlList);
        outmap.put("map",map);
    } catch (Exception e) {
        e.printStackTrace();
        outmap.put("map", new HashMap<>());
    }
        return outmap;
}
    /**
     * 根据id查询人员信息详情
     * @param params id
     * @return
     */
    @Override
    public Map getWish(Map<String, Object> params){
        Map outmap = new HashMap();

        try {
            Map  map= wishMapper.getdetail(params);
            outmap.put("map", map);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("wish", new Wish());
        }
        return outmap;
    }

    /**
     * 根据人员id删除人员信息
     * @param params id
     * @return
     */
    @Override
    public Map deleteWishById(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            wishMapper.deleteWishById(Integer.parseInt(String.valueOf( params.get("id") )));
            outmap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result","err");
        }
        return outmap;
    }
//    实现删除
    @Override
    public Map deleteWishById1(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            wishMapper.deleteWishById1(Integer.parseInt(String.valueOf( params.get("pid") )));
            outmap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result","err");
        }
        return outmap;
    }

    /**
     * 根据人员id修改人员信息
     * @param params
     * @return
     */
    @Override
    public Map updateWishById(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            //删除之前的附件
            wishMapper.delfj(params);
            String s = params.get("ifdje")==null?"0":params.get("ifdje").toString();
            if("1".equals(s)){
                //大金额
                wishMapper.myDjeUpdateById(params);
            }else {
                //编辑的修改
//              如果心愿审核状态为审核不通过,将状态改为待审核
                if("2".equals(params.get("wish_audit_state"))){
                    params.put("wish_audit_state", 0);
                }
                wishMapper.myUpdateById(params);
            }
            //  修改认领人信息
            wishMapper.updrl(params);
            //            新增附件
            if(params.get("url")!=null){
                List<Map> urlList=(List<Map>) params.get("url");
                for (int i = 0; i < urlList.size(); i++) {
                    params.put("fjname",String.valueOf(urlList.get(i).get("name")));
                    params.put("fjurl",String.valueOf(urlList.get(i).get("url")));
                    wishMapper.addTu(params);
                }
            }


            outmap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result","err");
        }
        return outmap;
    }
//    实现心愿
@Override
public Map updSX(Map<String, Object> params){
    Map outmap = new HashMap();
    try {
            //编辑的修改
            wishMapper.UpdSx(params);
//            新增附件
        if(params.get("url")!=null){
            List<Map> urlList=(List<Map>) params.get("url");
            for (int i = 0; i < urlList.size(); i++) {
                params.put("fjname",String.valueOf(urlList.get(i).get("name")));
                params.put("fjurl",String.valueOf(urlList.get(i).get("url")));
                wishMapper.addTu1(params);
            }
        }
            wishMapper.updCliamant(params);
           outmap.put("result","success");
    } catch (Exception e) {
        e.printStackTrace();
        outmap.put("result","err");
    }
        return outmap;
}

    @Override
    public Map  updatesort(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
//当方法触发后将实现审核状态改为0，审核备注改为空
            wishMapper.updatesort(params);
            outmap.put("status","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("status","err");
        }
        return outmap;
    }
    @Override
    public Map updateWishById1(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
//当方法触发后将实现审核状态改为0，审核备注改为空
            wishMapper.myUpdateById2(params);
            outmap.put("status","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("status","err");
        }
        return outmap;
    }
    @Override
    public Map addWish(Map<String, Object> params){
            Map outmap = new HashMap();
        try {
            List<Map> list= wishMapper.getname(params);
            outmap.put("result","success");

            outmap.put("name",list);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", "err");
        }
        return outmap;
    }
    public Map addWish1(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            wishMapper.addfb(params);
            outmap.put("status","success");
        }catch (Exception e) {
            e.printStackTrace();
            outmap.put("status","false");
        }
        return outmap;
    }
}
