package com.jiading.modules.littlewish.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.littlewish.domain.TWishFile;
import com.jiading.modules.littlewish.domain.Wish;
import com.jiading.modules.littlewish.service.TwishService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/wish/mywish")
@CrossOrigin
public class MywishController {
    @Autowired
    private TwishService service;

    @RequestMapping("/getmywish")
    public ResultMap getmywish(@Param("pid") Integer pid) {
        List<Wish> list = service.getwishById(pid);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

    @RequestMapping("/getwishbyid")
    public ResultMap getwishbyid(@Param("pid") Integer pid) {
        List<Wish> list = service.getwishByPid(pid);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }
    @RequestMapping("/getfullwishbyid")
    public ResultMap getfullwishbyid(@Param("pid") Integer pid) {
        List<Wish> list = service.getfullwishbyid(pid);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

    @RequestMapping("/getfullwishbypid")
    public ResultMap getfullwishbypid(@Param("pid") Integer pid) {
        List<Wish> list = service.getfullwishbypid(pid);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

    @RequestMapping("/geturlbypid")
    public ResultMap geturlbypid(@Param("pid") Integer pid) {
        List<TWishFile> list = service.geturlbypid(pid);
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

    @RequestMapping("/editwishbyid")
    public Integer editwishbyid(@Param("name")String name, @Param("content")String content, @Param("adder")String adder,@Param("pid")Integer pid) {
        return service.editwishbyid(name,content,adder,pid);
    }

}
