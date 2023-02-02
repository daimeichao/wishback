package com.jiading.modules.xcx.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.xcx.service.HomeService;
import com.jiading.modules.xcx.service.JbrfcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@SuppressWarnings("all")
@RestController
@RequestMapping("/xcx/jbrfc/")
@CrossOrigin
public class JbrfcController {
	

//	// 客户新增
//	@RequestMapping("ifexistuser")
//	@ResponseBody
//	public Map ifExistClient(HttpServletRequest request, HttpServletResponse response, @RequestParam Map paramsMap,
//                             HttpSession session) {
//		Map outmap = clientService.ifExistClient(paramsMap);
//		return outmap;
//	}

	@Autowired
	private JbrfcService jbrfcService;

	
	@PostMapping("getJbrList")
	public ResultMap getJbrList(@RequestBody Map<String,Object> params) {
		return jbrfcService.getJbrList(params);
	}
	

	


}
