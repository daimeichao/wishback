package com.jiading.modules.xcx.service;

import com.jiading.common.util.MD5Utils;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.mapper.JfMapper;
import com.jiading.modules.xcx.dao.XcxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XcxService {

    @Autowired
    private XcxDao xcxDao;
    @Autowired
    private JfMapper jfMapper;


    public ResultMap getBannerList(){

        List<Map> bannerList = xcxDao.getBannerList();

        if(bannerList!= null && bannerList.size()>0){
            return  ResultMap.ok().put("bannerLilst",bannerList);
        }else{
            return  ResultMap.ok().put("bannerLilst",new ArrayList<>());
        }

    }

    public ResultMap getWishList(Map map){
        Integer pageindex = Integer.parseInt(map.get("pageindex")+"");
        Integer pagesize = Integer.parseInt(map.get("pagesize")+"");

        map.put("pageindex",pageindex-1);

        List<Map> wishList = xcxDao.getWishList(map);
        Integer total = xcxDao.getWishCount(map);
        BigDecimal totalPage = new BigDecimal(total);
        totalPage =totalPage.divide(new BigDecimal(pagesize),0,BigDecimal.ROUND_UP);

        if(wishList!= null && wishList.size()>0){
            return  ResultMap.ok().put("wishList",wishList).put("total",totalPage);
        }else{
            return  ResultMap.ok().put("wishList",new ArrayList<>()).put("total",0);
        }

    }


    public ResultMap getphb(Map map){
        Integer pageindex = Integer.parseInt(map.get("pageindex")+"");
        Integer pagesize = Integer.parseInt(map.get("pagesize")+"");

        map.put("pageindex",pageindex-1);

        List<Map> wishList = xcxDao.getphb(map);
        Integer total = xcxDao.getphbCount(map);
        BigDecimal totalPage = new BigDecimal(total);
        totalPage =totalPage.divide(new BigDecimal(pagesize),0,BigDecimal.ROUND_UP);

        if(wishList!= null && wishList.size()>0){
            return  ResultMap.ok().put("wishList",wishList).put("total",totalPage);
        }else{
            return  ResultMap.ok().put("wishList",new ArrayList<>()).put("total",0);
        }

    }

    public ResultMap getgood(Map map){
        Integer pageindex = Integer.parseInt(map.get("pageindex")+"");
        Integer pagesize = Integer.parseInt(map.get("pagesize")+"");

        map.put("pageindex",pageindex-1);
        List<Map> wishList =  jfMapper.splist(map); // 返回当前页的展示列表
        Integer total = jfMapper.spcount(map); // 计算真实数据总数
        BigDecimal totalPage = new BigDecimal(total);
        totalPage =totalPage.divide(new BigDecimal(pagesize),0,BigDecimal.ROUND_UP);

        if(wishList!= null && wishList.size()>0){
            return  ResultMap.ok().put("wishList",wishList).put("total",totalPage);
        }else{
            return  ResultMap.ok().put("wishList",new ArrayList<>()).put("total",0);
        }

    }


    public ResultMap getUserByOpenId(Map map){
        String openId = map.get("openId")+"";
        Map userInfo = xcxDao.getUserByOpenId(openId);
        if(userInfo!=null){
            return  ResultMap.ok().put("userInfo",userInfo).put("isUser",true);
        }else{
            return  ResultMap.ok().put("userInfo",new HashMap<>()).put("isUser",false);
        }
    }


    public ResultMap addUser(Map map){
        String code = xcxDao.getCode();
        map.put("password", MD5Utils.encode("Aa123456"));
        map.put("account", code);
        xcxDao.addUser(map);
        //update code
        Integer size= 8;
        Integer topIndex = 0;
        for(int i = 0; i<code.length();i++) {				//遍历字符串的每个字符
            if(!String.valueOf(code.charAt(i)).equals("0")){
                topIndex =i;
                break;
            }
        }
        Integer num  = Integer.parseInt(code.substring(topIndex,code.length()));
        num = num+1;
        String resultCode  =num +"";
        while(resultCode.length()<size){
            resultCode = "0" + resultCode;
        }
        xcxDao.updateCode(resultCode);

        map.put("password","");

        return  ResultMap.ok().put("userInfo",map);
    }


    public ResultMap getWishDetail(Map map){
        Map wishDetail = xcxDao.getWishDetail(map);
        if(wishDetail!=null ){
            return ResultMap.ok().put("detail",wishDetail);
        }else{
            return ResultMap.ok().put("detail",new HashMap<>());
        }
    }

    public ResultMap realizationWish(Map map){
        xcxDao.changeWishState(map);
        xcxDao.realizationWish(map);
        return ResultMap.ok().put("wishClaimant",map);
    }



    public ResultMap addWish(Map map){
        xcxDao.addWish(map);
        return ResultMap.ok();
    }

    public ResultMap addFile(Map map){
        xcxDao.addFile(map);
        return ResultMap.ok();
    }

    public ResultMap getBannerDetail(Map map){
        String id = map.get("id")+"";
        Map bannerDetail = xcxDao.getBannerDetail(id);
        return ResultMap.ok().put("bannerDetail",bannerDetail);
    }
    public ResultMap getgooddetail(Map map){
        Map wishDetail = jfMapper.spdetail(map);
        if(wishDetail!=null ){
            return ResultMap.ok().put("detail",wishDetail);
        }else{
            return ResultMap.ok().put("detail",new HashMap<>());
        }

}

    public ResultMap buysp(Map<String, Object> map) {
//        新增积分变化数据，消费多少积分（spprice）
        xcxDao.dejf(map);
//        减少库存
        xcxDao.updkc(map);

        return ResultMap.ok();
    }
    public ResultMap getmydh(Map map){
        Integer pageindex = Integer.parseInt(map.get("pageindex")+"");
        Integer pagesize = Integer.parseInt(map.get("pagesize")+"");

        map.put("pageindex",pageindex-1);

        List<Map> wishList = xcxDao.getmydh(map);
        Integer total = xcxDao.getdhCount(map);
        BigDecimal totalPage = new BigDecimal(total);
        totalPage =totalPage.divide(new BigDecimal(pagesize),0,BigDecimal.ROUND_UP);

        if(wishList!= null && wishList.size()>0){
            return  ResultMap.ok().put("wishList",wishList).put("total",totalPage);
        }else{
            return  ResultMap.ok().put("wishList",new ArrayList<>()).put("total",0);
        }

    }
}
