package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Rylb;
import com.jiading.modules.back.service.RylbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/rylb")
public class RylbController {

    @Autowired
    private RylbService rylbService;

    @GetMapping("/allList")
    public ResultMap getAllList() {
        List<Rylb> list = rylbService.list();
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

//    @PostMapping("/pageList")
//    public ResultMap getList(@RequestBody Rylb rylb) {
//        return rylbService.pageList(rylb);
//    }
//
//    @PostMapping("/list")
//    public ResultMap getList(@RequestBody Map<String, Object> params) {
//        Map<String, Object> outmap = rylbService.getListByMap(params);
//        return ResultMap.ok().put("outmap", outmap);
//    }
////
//    @GetMapping("/get")
//    public ResultMap getRylbById(@RequestParam("pid") Integer pid) {
//
//        return rylbService.getRylbById(pid);
//    }
////
////
//    @GetMapping("/delete")
//    public ResultMap deleteRylb(@RequestParam("pid") Integer pid) {
//        if (rylbService.removeById(pid)) {
//            return new ResultMap();
//        } else {
//            return ResultMap.error(999, "删除失败");
//        }
//    }
////
//    @PostMapping("/update")
//    public ResultMap updateRylb(@RequestBody Rylb rylb) {
//        return rylbService.updateRylb(rylb);
//    }
////
//    @PostMapping("/add")
//    public ResultMap addRylb(@RequestBody Rylb rylb) {
//       return rylbService.addRylb(rylb);
//    }

}
