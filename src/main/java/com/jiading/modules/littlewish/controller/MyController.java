package com.jiading.modules.littlewish.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.littlewish.domain.WishUser;
import com.jiading.modules.littlewish.service.TwishUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/wish/my")
@CrossOrigin
public class MyController {
    @Autowired
    private TwishUserService TwishUserService;
    @RequestMapping("/info")
    public ResultMap getMyinfo(@Param("openid") String openid) {
        List<WishUser> list = TwishUserService.getinfoById(openid);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

    @RequestMapping("/editinfo")
    public Integer editinfo(@Param("name")String name,@Param("pid")Integer pid) {
        return TwishUserService.editinfo(name,pid);
    }


}
