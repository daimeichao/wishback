package com.jiading.modules.xcx.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcxDao {


    // 得到轮播
    @Select({
            "select pid,content,url,link,type,add_time from t_banner where del=0"
    })
    List<Map> getBannerList();
    // 愿望列表
    List<Map> getWishList(Map map);
//    积分排行榜
//    List<Map> getphb(Map map);
    Integer getWishCount(Map map);
    List<Map<String, Object>> getphb(Map<String, Object> params);
    List<Map<String, Object>> xcxphb(Map<String, Object> params);
    // 愿望详情
    Map getWishDetail(Map map);

    // 实现愿望
    Integer realizationWish(Map map);

    // 更改愿望状态
    @Update({
            "update t_wish set wish_state=1 where pid=#{pid}"
    })
    Integer changeWishState(Map map);

    // 添加愿望
    Integer addWish(Map map);


    // 添加附件
    Integer addFile(Map map);



    Map getUserById(String pid);


    Integer addUser(Map map);


    @Select({
            "select code from t_code"
    })
    String getCode();


    @Update({
            "update t_code set code=#{code}"
    })
    Integer updateCode(String code);



    @Select({
            "select * from t_banner where pid = #{id}"
    })
    Map getBannerDetail(String id);
    int getphbCount(Map map);
    int dejf(Map<String, Object> map);
    int updkc(Map<String, Object> map);
    List<Map<String, Object>> getmyjf(Map<String, Object> map);
    int getdhCount(Map map);
    Map zyzdetail(Map map);
    int upduser(Map<String, Object> params);
    int zhuce(Map<String, Object> params);
    int getZh(Map<String, Object> params);
    Map getUser(Map<String, Object> params);
    int getjfnum(Map map);
    List<String> getidlist(Map<String, Object> params);
    Map getjf(Map<String, Object> params);
    int addjf(Map<String, Object> params);
    int cutjf(Map<String, Object> params);
}
