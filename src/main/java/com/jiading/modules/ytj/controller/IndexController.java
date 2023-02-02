package com.jiading.modules.ytj.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Banner;
import com.jiading.modules.ytj.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/ytj")
public class IndexController {

    @Autowired
    private IndexService indexService;

    //获取首页已完成心愿列表
    @PostMapping("/getCompleteWishList")
    public ResultMap getList(@RequestBody Map<String, Object> Params) {
        return ResultMap.ok().put("data",indexService.getCompleteWishList());
    }

    //获取首页待认领心愿列表
    @PostMapping("/getClaimWishList")
    public ResultMap getClaimWishList(@RequestBody Map<String, Object> Params) {
        return ResultMap.ok().put("data",indexService.getClaimWishList());
    }
    //获取列表页心愿列表
    @PostMapping("/getWishList")
    public ResultMap getWishList(@RequestBody Map<String, Object> Params) {
        return ResultMap.ok().put("data",indexService.getWishList(Params));
    }

    //获取心愿详情
    @PostMapping("/getDeatail")
    public ResultMap getDeatail(@RequestBody Map<String, Object> Params) {
        return ResultMap.ok().put("data",indexService.getDeatail(Params));
    }
    //添加我的心愿
    @PostMapping("/addWish")
    public ResultMap addWish(@RequestBody Map<String, Object> Params) {
        return ResultMap.ok().put("data",indexService.addWish(Params));
    }
    //添加认领心愿
    @PostMapping("/addWishClaimant")
    public ResultMap addWishClaimant(@RequestBody Map<String, Object> Params) {
        return ResultMap.ok().put("data",indexService.addWishClaimant(Params));
    }
}
