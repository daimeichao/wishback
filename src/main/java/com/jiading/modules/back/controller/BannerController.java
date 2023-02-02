package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Banner;
import com.jiading.modules.back.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * banner表 前端控制器
 * </p>
 *
 * @author author
 * @since 2022-10-24
 */
@RestController
@CrossOrigin
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/allList")
    public ResultMap getAllList() {
        List<Banner> list = bannerService.list();
        ResultMap resultMap = new ResultMap();
        resultMap.put("data", list);
        return resultMap;
    }

    @PostMapping("/pageList")
    public ResultMap getList(@RequestBody Banner banner) {
        return bannerService.pageList(banner);
    }

    @PostMapping("/list")
    public ResultMap getList(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap = bannerService.getListByMap(params);
        return ResultMap.ok().put("outmap", outmap);
    }
//
    @GetMapping("/get")
    public ResultMap getBannerById(@RequestParam("pid") Integer pid) {

        return bannerService.getBannerById(pid);
    }
//
//
    @GetMapping("/delete")
    public ResultMap deleteBanner(@RequestParam("pid") Integer pid) {
        if (bannerService.removeById(pid)) {
            return new ResultMap();
        } else {
            return ResultMap.error(999, "删除失败");
        }
    }
//
    @PostMapping("/update")
    public ResultMap updateBanner(@RequestBody Banner banner) {
        return bannerService.updateBanner(banner);
    }
//
    @PostMapping("/add")
    public ResultMap addBanner(@RequestBody Banner banner) {
       return bannerService.addBanner(banner);
    }

}
