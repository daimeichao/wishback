package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Xm;
import com.jiading.modules.back.service.XmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/xm")
public class XmController {

    @Autowired
    private XmService xmService;

    @GetMapping("/allList")
    public ResultMap getAllList() {
        List<Xm> list = xmService.list();
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

    //    @PostMapping("/list")
//    public ResultMap getList(@RequestBody Xm jsjg) {
//        return xmService.pageList(jsjg);
//    }
//
    @GetMapping("/getWithShzt2")
    public ResultMap getXmInfoByIdWithShzt2(@RequestParam("pid") Integer pid, @RequestParam(value = "yhid", required = false) Integer yhid) {
//        Xm jsjg = xmService.getById(pid);
//        ResultMap resultMap = new ResultMap();
//        resultMap.put("data", jsjg);

        return xmService.getXmInfoByIdWithShzt2(pid, yhid);
    }

    @GetMapping("/getWithShztall")
    public ResultMap getXmInfoByIdWithShztall(@RequestParam("pid") Integer pid, @RequestParam(value = "yhid", required = false) Integer yhid) {
//        Xm jsjg = xmService.getById(pid);
//        ResultMap resultMap = new ResultMap();
//        resultMap.put("data", jsjg);

        return xmService.getXmInfoByIdWithShztall(pid, yhid);
    }

    @PostMapping("/get")
    public ResultMap getXmById(@RequestBody Map<String, Object> params) {
        return xmService.getXmById(params);
    }

    //
//
//    @GetMapping("/delete")
//    public ResultMap deleteXm(@RequestParam("pid") Integer pid) {
//        if (xmService.removeById(pid)) {
//            return new ResultMap();
//        } else {
//            return ResultMap.error(999, "删除失败");
//        }
//    }
//
//    @PostMapping("/update")
//    public ResultMap updateXm(@RequestBody Xm jsjg) {
//        return xmService.updateXm(jsjg);
//    }
//
//    @PostMapping("/add")
//    public ResultMap addXm(@RequestBody Xm jsjg) {
//       return xmService.addXm(jsjg);
//    }
    @GetMapping("/getShzt2AllList")
    public ResultMap getShzt2AllList() {
        return xmService.getShzt2AllList();
    }

}
