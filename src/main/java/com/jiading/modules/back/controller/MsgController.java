package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Msg;
import com.jiading.modules.back.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @PostMapping("/list")
    public ResultMap getList(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap = msgService.getListByMap(params);
        return ResultMap.ok().put("outmap", outmap);
    }

    @GetMapping("/get")
    public ResultMap getXmById(@RequestParam("pid") Integer pid) {
        return msgService.getMsgById(pid);
    }


    @GetMapping("/delete")
    public ResultMap deleteMsg(@RequestParam("pid") Integer pid) {
       return msgService.deleteMsg(pid);
    }

    @PostMapping("/update")
    public ResultMap updateMsg(@RequestBody Msg msg) {
        return msgService.updateMsg(msg);
    }

    @PostMapping("/add")
    public ResultMap addMsg(@RequestBody Msg msg) {
        return msgService.addMsg(msg);
    }


}
