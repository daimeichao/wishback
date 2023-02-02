package com.jiading.modules.back.controller;

import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Wish;
import com.jiading.modules.back.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin
@RequestMapping("/wish")
//心愿列表
public class WishController {
    @Autowired
    private WishService wishService;

    @PostMapping("/list")
    public ResultMap getList(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap = wishService.getListByMap(params);
        return ResultMap.ok().put("outmap", outmap);
    }
    /**
     * 查询人员信息表详情
     * @param params
     * @return
     */
    @PostMapping("/getwish")
    public ResultMap getwish(@RequestBody Map<String, Object> params) {
        return ResultMap.ok(wishService.getWish(params));
    }
    @PostMapping("/getone")
    public ResultMap getListById(@RequestBody Map<String, Object> params) {
        return ResultMap.ok(wishService.getListById(params));
    }
    /**
     * 根据人员id删除人员信息
     * @param params
     * @return
     */
    @PostMapping("/delwish")
    public ResultMap deleteWishById(@RequestBody Map<String, Object> params){
        return ResultMap.ok(wishService.deleteWishById(params));
    }
    @PostMapping("/delwish1")
    public ResultMap deleteWishById1(@RequestBody Map<String, Object> params){
        return ResultMap.ok(wishService.deleteWishById1(params));
    }
    /**
     * 根据传入的列表id批量删除人员信息，并返回删除结果
     * @param params 列表delList
     * @return
     */


    /**
     * 根据人员id修改人员信息
     * @param params
     * @return
     */
    @PostMapping("/updatewish")
    public ResultMap updateWishById(@RequestBody Map<String, Object> params){
        System.out.println("---------");
        return ResultMap.ok(wishService.updateWishById(params));
    }
    @PostMapping("/updatewish1")
    public ResultMap updateWishById1(@RequestBody Map<String, Object> params){
        System.out.println("---------");
        return ResultMap.ok(wishService.updateWishById1(params));
    }
//    心愿列表的实现心愿
    @PostMapping("/updSX")
    public ResultMap updSX(@RequestBody Map<String, Object> params){
        System.out.println("---------");
        return ResultMap.ok(wishService.updSX(params));
    }
    @PostMapping("/updatesort")
    public ResultMap updatesort(@RequestBody Map<String, Object> params){
        return ResultMap.ok(wishService.updatesort(params));
    }

    /**
     * 添加人员信息数据（单条数据添加）
//     * @param params
     * @return
     */
    @PostMapping("/addwish")
    public ResultMap addWish(@RequestBody Map<String, Object> params) {
        System.out.println("--------- ");
        return ResultMap.ok(wishService.addWish(params));
    }
    /*心愿发布新增*/
    @PostMapping("/addwish1")
    public ResultMap addWish1(@RequestBody Map<String, Object> params) {

        return ResultMap.ok(wishService.addWish1(params));
    }
    @RequestMapping("/shenHe")
    public ResultMap getSHList(@RequestBody Map<String, Object> params) {
        Map<String, Object> outmap = wishService.getSHList(params);
        return ResultMap.ok().put("outmap", outmap);
    }

//心愿列表两表连接
@RequestMapping("/wishlist")
public ResultMap wishList(@RequestBody Map<String, Object> params) {
    Map<String, Object> outmap = wishService.wishList(params);
    return ResultMap.ok().put("outmap", outmap);
}
}


