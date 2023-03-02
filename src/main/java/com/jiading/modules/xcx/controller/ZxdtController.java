package com.jiading.modules.xcx.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.xcx.service.JbrfcService;
import com.jiading.modules.xcx.service.ZxdtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@SuppressWarnings("all")
@RestController
@RequestMapping("/xcx/zxdt/")
@CrossOrigin
public class ZxdtController {


	@Autowired
	private ZxdtService zxdtService;

	
	@PostMapping("getZxdtList")
	public ResultMap getZxdtList(@RequestBody Map<String,Object> params) {
		return zxdtService.getZxdtList(params);
	}
	

	


}
