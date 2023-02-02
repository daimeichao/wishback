package com.jiading.modules.back.jbgsgl.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.jbgsgl.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/jbgs/back/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultMap upload(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpSession session) {
        return ResultMap.ok().put("outmap", fileService.upload(file,request));
    }

    @ApiOperation(value = "导出表格")
    @RequestMapping(value = "/ExportExcel", method = RequestMethod.POST)
    public ResultMap ExportExcel(@RequestBody Map<String, Object> params) {
        return ResultMap.ok().put("outmap", fileService.ExportExcel(params));
    }
}
