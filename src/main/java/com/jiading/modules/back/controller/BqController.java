package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Bq;
import com.jiading.modules.back.service.BqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/bq")
public class BqController {

    @Autowired
    private BqService bqService;

//    @GetMapping("/allList")
//    public ResultMap getAllList() {
//        List<Bq> list = bqService.list();
//        ResultMap resultMap = new ResultMap();
//        resultMap.put("data", list);
//        return resultMap;
//    }

//    @PostMapping("/list")
//    public ResultMap getList(@RequestBody Map<String, Object> params) {
//        Map<String, Object> outmap = bqService.getListByMap(params);
//        return ResultMap.ok().put("outmap", outmap);
//    }
//
//    @GetMapping("/get")
//    public ResultMap getXmById(@RequestParam("pid") Integer pid,@RequestParam(value = "jyzk",required = false) String jyzk) {
//        return bqService.getBqById(pid,jyzk);
//    }
//
//
//    @GetMapping("/delete")
//    public ResultMap deleteXm(@RequestParam("pid") Integer pid) {
//        if (bqService.removeById(pid)) {
//            return new ResultMap();
//        } else {
//            return ResultMap.error(999, "删除失败");
//        }
//    }
//
//    @PostMapping("/update")
//    public ResultMap updateXm(@RequestBody Bq bq) {
//        return bqService.updateBq(bq);
//    }
//
    @PostMapping("/add")
    public ResultMap addUserBq(@RequestBody Map<String,Object> params) {
       return bqService.addUserBq(params);
    }

}
