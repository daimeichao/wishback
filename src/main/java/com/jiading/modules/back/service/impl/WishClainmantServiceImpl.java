package com.jiading.modules.back.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.common.util.FenYe;
import com.jiading.modules.back.domain.Wish;
import com.jiading.modules.back.domain.WishClainmant;
import com.jiading.modules.back.mapper.WishClainmantMapper;
import com.jiading.modules.back.mapper.WishMapper;
import com.jiading.modules.back.service.WishClainmantService;
import com.jiading.modules.back.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//实现心愿
@Service
public class WishClainmantServiceImpl extends ServiceImpl<WishClainmantMapper, WishClainmant> implements WishClainmantService {
    @Autowired
    private  WishClainmantMapper wishClainmantMapper;
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
            list = wishClainmantMapper.getListByMap(params); // 返回当前页的展示列表
            count = wishClainmantMapper.getListCountByMap(params); // 计算真实数据总数
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
    /**
     * 获得心愿审核列表 getSHList，更改审核状态 认领审核状态 0：待审核 1：审核通过 2：审核不通过*/
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
            String claimant_audit_state = String.valueOf(params.get("claimant_audit_state"));
            if("3".equals(claimant_audit_state) || "4".equals(claimant_audit_state) ||"5".equals(claimant_audit_state) ){
                params.put("claimant_audit_state","");
            }
            list = wishClainmantMapper.getSHList(params); // 返回当前页的展示列表
            count = wishClainmantMapper.getSXCount(params); // 计算真实数据总数
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
    /**
     * 根据id查询人员信息详情
     * @param params id
     * @return
     */
    @Override
    public Map getWish(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
           Map map=wishClainmantMapper.getdetail(params);
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
     * 根据人员id删除人员信息
     * @param params id
     * @return
     */
    @Override
    public Map deleteWishById(Map<String, Object> params){
        Map outmap = new HashMap();
        try {
            wishClainmantMapper.deleteWishById(Integer.parseInt(String.valueOf( params.get("pid") )));
//            将心愿状态改为待认领
            wishClainmantMapper.upddrl(params);
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
        System.out.println(params.get("pid"));
        try {
            wishClainmantMapper.updateWishById3(params);
            wishClainmantMapper.updateWishById(params);
            wishClainmantMapper.updateFJ(params);

            outmap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result","err");
        }
        return outmap;
    }

    @Override
    public Map updateWishById1(Map<String, Object> params){
        Map outmap = new HashMap();

        try {
//            心愿表心愿状态随实现表审核状态改变
            wishClainmantMapper.upShstatu(params);
//            当认领审核通过时，将心愿状态改为已完成
            if("1".equals (String.valueOf (params.get("claimant_audit_state")))){
                params.put("wish_state","2");
            }
//            当审核不通过时，将心愿状态改为待审核xxx
            else if (("2".equals (String.valueOf (params.get("claimant_audit_state"))))){
//                params.put("wish_audit_state","0");
                params.put("wish_state","0");
//                并将认领表和心愿表相连的wishid改为null
//                wishClainmantMapper.updnull(params);
                //将心愿表对应的实现表的数据删除
//                wishClainmantMapper.updateSxb(params);
            }
            else {
               params.put("wish_state","0");
            }
//            心愿表——>心愿状态修改
            wishClainmantMapper.upXybShstatu(params);//走完了
            outmap.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result","err");
        }
        return outmap;
    }
    /**
     * 添加单条人员信息数据
//     * @param params
     * @return
     */
    @Override
    public Map insertWish(WishClainmant wishClainmant){
        Map outmap = new HashMap();
        try {
//            WishClainmant wishClainmant = JSONObject.parseObject(JSONObject.toJSONString(params), WishClainmant.class);
//            int id = companyDao.selectOne(new QueryWrapper<SysCompany>().eq("name",personnel.getCompany())).getId();
//            if (id != 0 ){
//                personnel.setCompanyId(id);
//                int num = sysPersonnelDao.insert(personnel);
//                if (num >= 1) {
//                    outmap.put("result","success");
//                } else {
//                    outmap.put("result","err");
//                }
//            } else {
//                outmap.put("result","err");
//            }
            int num = wishClainmantMapper.insert(wishClainmant);
            if (num >= 1) {
                outmap.put("result","success");
            } else {
                outmap.put("result","err");
            }
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result","err");
        }
        System.out.println("outmap : " + outmap.get("result"));
        return outmap;
    }
//    public Map insertWish(Map<String, Object> params){
//        Map outmap = new HashMap();
//        try {
//           WishClainmant wishClainmant = JSONObject.parseObject(JSONObject.toJSONString(params), WishClainmant.class);
////            int id = companyDao.selectOne(new QueryWrapper<SysCompany>().eq("name",personnel.getCompany())).getId();
////            if (id != 0 ){
////                personnel.setCompanyId(id);
////                int num = sysPersonnelDao.insert(personnel);
////                if (num >= 1) {
////                    outmap.put("result","success");
////                } else {
////                    outmap.put("result","err");
////                }
////            } else {
////                outmap.put("result","err");
////            }
//            int num = wishClainmantMapper.insert(wishClainmant);
//            if (num >= 1) {
//                outmap.put("result","success");
//            } else {
//                outmap.put("result","err");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            outmap.put("result","err");
//        }
//        System.out.println("outmap : " + outmap.get("result"));
//        return outmap;
//    }
}
//
