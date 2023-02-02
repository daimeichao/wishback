package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.School;
import com.jiading.modules.back.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/allList")
    public ResultMap getAllList() {
        ResultMap resultMap = new ResultMap();
        List<School> list = schoolService.list(null);
        resultMap.put("data",list);
        return resultMap;
    }






}
