package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.WishClainmant;
import com.jiading.modules.back.mapper.WishClainmantMapper;
import com.jiading.modules.back.service.WishClainmantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/Sxwish")
//实现心愿
public class WishClainmantController {
    @Autowired
    private WishClainmantService wishClainmantService;
    /**
     * 获取待审核未删除的列表*/
    @PostMapping("/list")
    public ResultMap getList(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap = wishClainmantService.getListByMap(params);
        return ResultMap.ok().put("outmap", outmap);
    }

    @PostMapping("/getwish")
    public ResultMap getwish(@RequestBody Map<String, Object> params) {
        return ResultMap.ok(wishClainmantService.getWish(params));
    }

    @PostMapping("/delwish")
    public ResultMap deleteWishById(@RequestBody Map<String, Object> params){
        return ResultMap.ok(wishClainmantService.deleteWishById(params));
    }

    @PostMapping("/updatewish")
    public ResultMap updateWishById(@RequestBody Map<String, Object> params){
        System.out.println("---------[updateRyxx]");
        return ResultMap.ok(wishClainmantService.updateWishById(params));
    }
//    实现心愿审核
    @PostMapping("/updatewish1")
    public ResultMap updateWishById1(@RequestBody Map<String, Object> params){

        return ResultMap.ok(wishClainmantService.updateWishById1(params));
    }

    @PostMapping("/addwish")
    public ResultMap addWish(@RequestBody WishClainmant wishClainmant) {
        System.out.println("--------- [/addRyxx]");
        return ResultMap.ok(wishClainmantService.insertWish(wishClainmant));
    }
    @RequestMapping("/shenHe")
    public ResultMap getSHList(@RequestBody  Map<String, Object> params) {
        System.out.println("--------- [/addRyxx]");
        return ResultMap.ok(wishClainmantService.getSHList(params));
    }

}
//
