package com.jiading.modules.xcx.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.BdRank;
import com.jiading.modules.back.service.XmService;
import com.jiading.modules.xcx.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@SuppressWarnings("all")
@RestController
@RequestMapping("/xcx/home/")
@CrossOrigin
public class HomeController {
	

//	// 客户新增
//	@RequestMapping("ifexistuser")
//	@ResponseBody
//	public Map ifExistClient(HttpServletRequest request, HttpServletResponse response, @RequestParam Map paramsMap,
//                             HttpSession session) {
//		Map outmap = clientService.ifExistClient(paramsMap);
//		return outmap;
//	}

	@Autowired
	private HomeService homeService;

	@Autowired
	private XmService xmService;

	
	@PostMapping("getShowData")
	public ResultMap getShowData(@RequestBody Map<String,Object> params) {
		return homeService.getShowData(params);
	}


	@PostMapping("getXmlxList")
	public ResultMap getXmlxList(@RequestBody Map<String,Object> params) {
		return homeService.getXmlxList(params);
	}

	@PostMapping("getLatestZx")
	public ResultMap getLatestZx(@RequestBody Map<String,Object> params) {
		return homeService.getLatestZx(params);
	}

	@PostMapping("getFiveZx")
	public ResultMap getFiveZx(@RequestBody Map<String,Object> params) {
		return homeService.getFiveZx(params);
	}


	@PostMapping("getHeadBannerList")
	public ResultMap getHeadBannerList(@RequestBody Map<String,Object> params) {
		return homeService.getHeadBannerList(params);
	}


	@GetMapping("getXmInfoByIdWithShzt2")
	public ResultMap getXmInfoByIdWithShzt2(@RequestParam("pid") Integer pid,@RequestParam(value = "yhid",required = false) Integer yhid) {
		return xmService.getXmInfoByIdWithShzt2(pid,yhid);
	}

	@GetMapping("getXmInfoByIdWithShztall")
	public ResultMap getXmInfoByIdWithShztall(@RequestParam("pid") Integer pid,@RequestParam(value = "yhid",required = false) Integer yhid) {
		return xmService.getXmInfoByIdWithShztall(pid,yhid);
	}



   /*揭榜 */
   @GetMapping("jiebang")
   public ResultMap jiebang(@RequestParam("yhid") Integer yhid ,@RequestParam("xmid") Integer xmid ) {
	   return homeService.jiebang(yhid,xmid);
   }


	/*揭榜 */
	@GetMapping("bdxz")
	public ResultMap bdxz() {
		return homeService.bdxz();
	}

	@PostMapping("editbdxz")
	public ResultMap editbdxz(@RequestBody Map<String,Object> params) {
		return homeService.editbdxz(params);
	}


	/*揭榜数量用户排行 */
	@GetMapping("jieBangUserRank")
	public ResultMap jieBangUserRank(BdRank bdRank) {
		return homeService.jieBangUserRank(bdRank);
	}

	/*揭榜金额用户排行 */
	@GetMapping("jieBangUserJbjeRank")
	public ResultMap jieBangUserJbjeRank(BdRank bdRank) {
		return homeService.jieBangUserJbjeRank(bdRank);
	}

	/*发榜数量用户排行 */
	@GetMapping("faBangRank")
	public ResultMap faBangRank(BdRank bdRank) {
		return homeService.faBangRank(bdRank);
	}

	/*发榜金额用户排行 */
	@GetMapping("faBangUserJbjeRank")
	public ResultMap faBangUserJbjeRank(BdRank bdRank) {
		return homeService.faBangUserJbjeRank(bdRank);
	}

	/*揭榜数量用户排行 */
	@GetMapping("jieBangSchoolRank")
	public ResultMap jieBangSchoolRank(BdRank bdRank) {
		return homeService.jieBangSchoolRank(bdRank);
	}

	/*揭榜金额用户排行 */
	@GetMapping("jieBangSchoolJbjeRank")
	public ResultMap jieBangSchoolJbjeRank(BdRank bdRank) {
		return homeService.jieBangSchoolJbjeRank(bdRank);
	}

	/*发榜单位数量用户排行 */
	@GetMapping("faBangDWRank")
	public ResultMap faBangDWRank(BdRank bdRank) {
		return homeService.faBangDWRank(bdRank);
	}

	/*发榜单位金额用户排行 */
	@GetMapping("faBangDWJbjeRank")
	public ResultMap faBangDWJbjeRank(BdRank bdRank) {
		return homeService.faBangDWJbjeRank(bdRank);
	}


	/*修改项目结案状态 */
	@PostMapping("editXMCase")
	public ResultMap editCase(@RequestBody Map<String,Object> params) {
		return homeService.editCase(params);
	}

	/*修改项目下架状态 */
	@PostMapping("editXMshelves")
	public ResultMap editXMshelves(@RequestBody Map<String,Object> params) {
		return homeService.editXMshelves(params);
	}
}
