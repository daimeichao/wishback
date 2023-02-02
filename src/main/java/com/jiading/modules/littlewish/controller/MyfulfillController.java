package com.jiading.modules.littlewish.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.littlewish.domain.Wish;
import com.jiading.modules.littlewish.domain.wishClaimant;
import com.jiading.modules.littlewish.service.TwishClaimantService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/wish/myfulfill")
@CrossOrigin
public class MyfulfillController {
    @Autowired
    private TwishClaimantService service;
    @RequestMapping("/myfulfill")
    public ResultMap myfulfill(@Param("pid") Integer pid) {
        List<wishClaimant> list = service.getmywishClaimant(pid);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

    @RequestMapping("/myfulfilllist")
    public ResultMap myfulfilllist(@Param("pid") Integer pid) {
        List<wishClaimant> list = service.myfulfilllist(pid);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }
}
