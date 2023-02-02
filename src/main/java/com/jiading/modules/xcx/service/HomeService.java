package com.jiading.modules.xcx.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.*;
import com.jiading.modules.back.mapper.*;
import com.jiading.modules.xcx.dao.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HomeService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private HomeMapper homeMapper;

    @Autowired
    private XmlxMapper xmlxMapper;

    @Autowired
    private ZxMapper zxMapper;

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private XmMapper xmMapper;

    @Autowired
    private UserJiebMapper userJiebMapper;

    @Autowired
    private ConfigMapper configMapper;


    public ResultMap getShowData(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();

        //入住专家数量
        int rzzjCount = userMapper.getListCountByMap(params);
        //揭榜金额
        int jbjeCount = homeMapper.getJbjeCount(params);
        //榜单数
        params.put("jbzt", "");
        int fbCount = homeMapper.getBdCount(params);
        //揭榜数
        params.put("jbzt", "1");
        int jbCount = homeMapper.getBdCount(params);

        QueryWrapper<Xmlx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lxdj", 1);
        //类型
        List<Xmlx> xmlxes = xmlxMapper.selectList(queryWrapper);


        resultMap.put("rzzjCount", rzzjCount);
        resultMap.put("jbjeCount", jbjeCount);
        resultMap.put("jbCount", jbCount);
        resultMap.put("fbCount", fbCount);
        resultMap.put("xmlxList", xmlxes);


        return resultMap;
    }


    public ResultMap getXmlxList(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();


        QueryWrapper<Xmlx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lxdj", 1);
        //类型
        List<Xmlx> xmlxes = xmlxMapper.selectList(queryWrapper);


        resultMap.put("xmlxList", xmlxes);

        return resultMap;
    }

    public ResultMap getLatestZx(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();


        QueryWrapper<Zx> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("tjsj");
        queryWrapper.last("limit 1");
        //类型

        Zx zx = zxMapper.selectOne(queryWrapper);
//        System.out.println("zx = " + zx);
        resultMap.put("zxInfo", zx);

        return resultMap;
    }

    public ResultMap getHeadBannerList(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();


        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zszt", "1");
        queryWrapper.last("limit 3");
        queryWrapper.orderByDesc("tjsj");
        //类型

        List<Banner> headBannerList = bannerMapper.selectList(queryWrapper);
//        System.out.println("zx = " + zx);
        resultMap.put("headBannerList", headBannerList);

        return resultMap;
    }

    private static final Object lockObj = new Object();


    public ResultMap jiebang(Integer yhid, Integer xmid) {
        ResultMap resultMap = new ResultMap();

        User user = userMapper.selectById(yhid);
        if (user == null) {
            return ResultMap.error("该账户不存在");
        }
        if ("1".equals(user.getJyzk())) {
            return ResultMap.error("该账户已被禁用");
        }

        //判断时间
        LocalDateTime now = LocalDateTime.now();
        Xm xm = xmMapper.selectById(xmid);
        LocalDateTime kssj = xm.getKssj();
        LocalDateTime jzsj = xm.getJzsj();

        if (jzsj != null && now.isAfter(jzsj)) {
            return ResultMap.error("已过揭榜截止时间");
        }

        if (kssj != null && now.isBefore(kssj)) {
            return ResultMap.error("未到揭榜开始时间");
        }

        if ("1".equals(xm.getSfxj())) {
            return ResultMap.error("该榜单已下架");
        }
        if (!"1".equals(xm.getSftj())) {
            return ResultMap.error("该榜单未提交");
        }
        if (!"0".equals(xm.getJazt())) {
            return ResultMap.error("该榜单已结案");
        }

        //判断用户是否被禁用
//        User user = userMapper.getOneByPidAndSczkAndJyzk(yhid, "0", "1");
//        if (user != null) {
//            return ResultMap.error("该账户已被禁用");
//        }

        UserJieb userJieb = new UserJieb(yhid, xmid);
        QueryWrapper<UserJieb> userJiebQueryWrapper = new QueryWrapper<>();
        userJiebQueryWrapper.eq("yhid", yhid);
        userJiebQueryWrapper.eq("xmid", xmid);
        List<UserJieb> userJiebList0 = userJiebMapper.selectList(userJiebQueryWrapper);
        if (!ObjectUtils.isEmpty(userJiebList0)) {
            return ResultMap.error("您已揭榜过此榜单");
        }

//        UserJieb userJieb = new UserJieb();
//        userJieb.setXmid(xmid);
//        QueryWrapper<UserJieb> userJiebQueryWrapper = new QueryWrapper<>();
////        userJiebQueryWrapper.eq("yhid", yhid);
//        userJiebQueryWrapper.eq("xmid", xmid);
////        List<UserJieb> userJiebList = userJiebMapper.selectList(userJiebQueryWrapper);

//        for (UserJieb jieb : userJiebList) {
//            if (jieb.getYhid() == yhid) {
//                return ResultMap.error("您已揭榜过此榜单");
//            }
//        }

        /* 一个人只能揭榜5次 */
        int count = userJiebMapper.getJieBangCountByYhid(yhid);

        if (count >= 5) {
            return ResultMap.error("一个人只允许同时揭5个榜单");
        }

        Integer jbrs = xmMapper.getJbrsByPidAndSczk(xmid);
        jbrs = jbrs == null ? 0 : jbrs;
//        System.out.println("jbrs = " + jbrs);

        /* 项目限制数量 */
        synchronized (lockObj) {
            QueryWrapper<UserJieb> userJiebQueryWrapper0 = new QueryWrapper<>();
            userJiebQueryWrapper0.eq("xmid", xmid);
            //项目当前有几人揭榜
            List<UserJieb> userJiebList = userJiebMapper.selectList(userJiebQueryWrapper0);
            int size = userJiebList == null ? 0 : userJiebList.size();
//            System.out.println("size = " + size);
            if (jbrs <= size) {
                return ResultMap.error("此榜单已达到最大揭榜人数限制");
            } else {
                int insert = userJiebMapper.insert(userJieb);
                if (insert != 1) {
                    return ResultMap.error("揭榜失败");
                }
            }
        }

        //更新项目揭榜状态
        if ("0".equals(xm.getJbzt())) {
            homeMapper.updateXmJbztTo1(xmid);
        }

        return resultMap;
    }

    public ResultMap bdxz() {
        ResultMap resultMap = new ResultMap();
        QueryWrapper<Config> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("limit 1");
        List<Config> configs = configMapper.selectList(queryWrapper);
//        System.out.println("configs = " + configs);
        if (!ObjectUtils.isEmpty(configs)) {
            resultMap.put("data", configs.get(0));
        } else {
            resultMap.put("data", new Config());
        }
        return resultMap;
    }

    public ResultMap editbdxz(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();
        Object pid = params.get("pid");
        Object bdxz = params.get("bdxz");
        if (ObjectUtils.isEmpty(pid)) {
            return ResultMap.error("pid不能为空");
        }
        if (ObjectUtils.isEmpty(bdxz)) {
            return ResultMap.error("榜单须知内容不能为空");
        }
        Config config = new Config();
        config.setPid(Integer.valueOf(pid.toString()));
        config.setBdxz(bdxz.toString());
        int update = configMapper.updateById(config);
        if (update != 1) {
            return ResultMap.error("保存失败");
        }
//        System.out.println("configs = " + configs);

        return resultMap;
    }

    public static final int maxSize = 100;

    public ResultMap jieBangUserRank(BdRank bdRank) {
        int type = bdRank.getType();
        int limit = bdRank.getSize() == null ? maxSize : bdRank.getSize();

//        List<User> users = userMapper.selectAllList();
//        List<BdRank> list = new ArrayList<>(users.size());
        ResultMap resultMap = new ResultMap();
        List<BdRank> listByType;
        switch (type) {
            case 1:
                //年
                listByType = homeMapper.getJBRankUserCountByYear();
                break;
            case 2:
                //季度
                listByType = homeMapper.getJBRankUserCountByQuarter();
                break;
            case 3:
                //月
                listByType = homeMapper.getJBRankUserCountByMonth();
                break;
            default:
                return ResultMap.error("类型参数非法");
        }
        //获取用户id列表
        List<Integer> yhidList = listByType.stream().map(BdRank::getYhid).collect(Collectors.toList());

        //获取用户信息列表
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("sczk", "0").in(!ObjectUtils.isEmpty(yhidList), "pid", yhidList).orderByAsc("pid");
        List<User> users = userMapper.selectList(userQueryWrapper);

        //按照数量降序
        List<BdRank> sortedList = listByType.stream()
                .sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder())).limit(limit).collect(Collectors.toList());


        //填充用户信息
        for (BdRank rank : sortedList) {
            for (User user : users) {
                if (rank.getYhid() == user.getPid()) {
                    rank.setUser(user);
                }
            }
        }


        resultMap.put("data", sortedList);
        return resultMap;
    }


    public ResultMap jieBangUserJbjeRank(BdRank bdRank) {
        int type = bdRank.getType();
        int limit = bdRank.getSize() == null ? maxSize : bdRank.getSize();

//        List<User> users = userMapper.selectAllList();
//        List<BdRank> list = new ArrayList<>(users.size());
        ResultMap resultMap = new ResultMap();
        List<BdRank> listByType;
        switch (type) {
            case 1:
                //年
                listByType = homeMapper.getJBRankUserJbjeCountByYear();
                break;
            case 2:
                //季度
                listByType = homeMapper.getJBRankUserJbjeCountByQuarter();
                break;
            case 3:
                //月
                listByType = homeMapper.getJBRankUserJbjeCountByMonth();
                break;
            default:
                return ResultMap.error("类型参数非法");
        }
        //获取用户id列表
        List<Integer> yhidList = listByType.stream().map(BdRank::getYhid).collect(Collectors.toList());

        //获取用户信息列表
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("sczk", "0").in(!ObjectUtils.isEmpty(yhidList), "pid", yhidList).orderByAsc("pid");
        List<User> users = userMapper.selectList(userQueryWrapper);

        //按照数量降序
        List<BdRank> sortedList = listByType.stream()
                .sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder())).limit(limit).collect(Collectors.toList());


        //填充用户信息
        for (BdRank rank : sortedList) {
            for (User user : users) {
                if (rank.getYhid() == user.getPid()) {
                    rank.setUser(user);
                }
            }
        }


        resultMap.put("data", sortedList);
        return resultMap;
    }

    public ResultMap jieBangSchoolRank(BdRank bdRank) {
        int type = bdRank.getType();
        int limit = bdRank.getSize() == null ? maxSize : bdRank.getSize();

        ResultMap resultMap = new ResultMap();
        List<BdRank> listByType;
        switch (type) {
            case 1:
                //年
                listByType = homeMapper.getJBRankUserCountByYearWithSchool();
                break;
            case 2:
                //季度
                listByType = homeMapper.getJBRankUserJbjeCountByQuarterWithSchool();
                break;
            case 3:
                //月
                listByType = homeMapper.getJBRankUserJbjeCountByMonthWithSchool();
                break;
            default:
                return ResultMap.error("类型参数非法");
        }
        //获取用户id列表
        List<Integer> yhidList = listByType.stream().map(BdRank::getYhid).collect(Collectors.toList());

        //获取用户信息列表
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("sczk", "0").in(!ObjectUtils.isEmpty(yhidList), "pid", yhidList).orderByAsc("pid");
//        List<User> users = userMapper.selectList(userQueryWrapper);

        List<User> users2 = userMapper.selectListWithSchool(yhidList);

        //按照数量降序
        List<BdRank> sortedList = listByType.stream()
                .sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder())).collect(Collectors.toList());


        //填充用户信息
        for (BdRank rank : sortedList) {
            for (User user : users2) {
                if (rank.getYhid() == user.getPid()) {
                    rank.setUser(user);
                    String schoolName = ObjectUtils.isEmpty(user.getName())?"":user.getName();
                    rank.setSchoolName(schoolName);
                }
            }
        }
//        System.out.println("sortedList = " + sortedList);
        //根据院校分组
        List<BdRank> collect = sortedList.stream().collect(Collectors.groupingBy(BdRank::getSchoolName))
                .entrySet().stream()
                .filter(entry-> !entry.getKey().equals(""))
                .map(entry -> {
                    String key = entry.getKey();//学校名称
                    List<BdRank> value = entry.getValue();//该学校名称的 rank列表
                    int sum = value.stream().mapToInt(BdRank::getCount).sum();
                    return new BdRank().setSchoolName(key).setCount(sum);
                }).sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder()))
                .limit(limit).collect(Collectors.toList());


        resultMap.put("data", collect);
        return resultMap;
    }


    public ResultMap jieBangSchoolJbjeRank(BdRank bdRank) {
        int type = bdRank.getType();
        int limit = bdRank.getSize() == null ? maxSize : bdRank.getSize();

//        List<User> users = userMapper.selectAllList();
//        List<BdRank> list = new ArrayList<>(users.size());
        ResultMap resultMap = new ResultMap();
        List<BdRank> listByType;
        switch (type) {
            case 1:
                //年
                listByType = homeMapper.getJBRankUserJbjeCountByYear();
                break;
            case 2:
                //季度
                listByType = homeMapper.getJBRankUserJbjeCountByQuarter();
                break;
            case 3:
                //月
                listByType = homeMapper.getJBRankUserJbjeCountByMonth();
                break;
            default:
                return ResultMap.error("类型参数非法");
        }
        //获取用户id列表
        List<Integer> yhidList = listByType.stream().map(BdRank::getYhid).collect(Collectors.toList());

        List<User> users2 = userMapper.selectListWithSchool(yhidList);

        //按照数量降序
        List<BdRank> sortedList = listByType.stream()
                .sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder())).collect(Collectors.toList());


        //填充用户信息
        for (BdRank rank : sortedList) {
            for (User user : users2) {
                if (rank.getYhid() == user.getPid()) {
                    rank.setUser(user);
                    String schoolName = ObjectUtils.isEmpty(user.getName())?"":user.getName();
                    rank.setSchoolName(schoolName);
                }
            }
        }
//        System.out.println("sortedList = " + sortedList);
        //根据院校分组
        List<BdRank> collect = sortedList.stream().collect(Collectors.groupingBy(BdRank::getSchoolName))
                .entrySet().stream()
                .filter(entry-> !entry.getKey().equals(""))
                .map(entry -> {
                    String key = entry.getKey();//学校名称
                    List<BdRank> value = entry.getValue();//该学校名称的 rank列表
                    int sum = value.stream().mapToInt(BdRank::getCount).sum();
                    return new BdRank().setSchoolName(key).setCount(sum);
                }).sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder()))
                .limit(limit).collect(Collectors.toList());


        resultMap.put("data", collect);
        return resultMap;
    }

    public ResultMap faBangRank(BdRank bdRank) {
        int type = bdRank.getType();
        int limit = bdRank.getSize() == null ? maxSize : bdRank.getSize();

//        List<User> users = userMapper.selectAllList();
//        List<BdRank> list = new ArrayList<>(users.size());
        ResultMap resultMap = new ResultMap();
        List<BdRank> listByType;
        switch (type) {
            case 1:
                //年
                listByType = homeMapper.getFBRankUserCountByYear();
                break;
            case 2:
                //季度
                listByType = homeMapper.getFBRankUserCountByQuarter();
                break;
            case 3:
                //月
                listByType = homeMapper.getFBRankUserCountByMonth();
                break;
            default:
                return ResultMap.error("类型参数非法");
        }
        //获取用户id列表
        List<Integer> yhidList = listByType.stream().map(BdRank::getYhid).collect(Collectors.toList());

        //获取用户信息列表
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("sczk", "0").in(!ObjectUtils.isEmpty(yhidList), "pid", yhidList).orderByAsc("pid");
        List<User> users = userMapper.selectList(userQueryWrapper);

        //按照数量降序
        List<BdRank> sortedList = listByType.stream()
                .sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder())).limit(limit).collect(Collectors.toList());


        //填充用户信息
        for (BdRank rank : sortedList) {
            for (User user : users) {
                if (rank.getYhid() == user.getPid()) {
                    rank.setUser(user);
                }
            }
        }


        resultMap.put("data", sortedList);
        return resultMap;
    }

    public ResultMap faBangUserJbjeRank(BdRank bdRank) {
        int type = bdRank.getType();
        int limit = bdRank.getSize() == null ? maxSize : bdRank.getSize();

//        List<User> users = userMapper.selectAllList();
//        List<BdRank> list = new ArrayList<>(users.size());
        ResultMap resultMap = new ResultMap();
        List<BdRank> listByType;
        switch (type) {
            case 1:
                //年
                listByType = homeMapper.getFBRankUserJbjeCountByYear();
                break;
            case 2:
                //季度
                listByType = homeMapper.getFBRankUserJbjeCountByQuarter();
                break;
            case 3:
                //月
                listByType = homeMapper.getFBRankUserJbjeCountByMonth();
                break;
            default:
                return ResultMap.error("类型参数非法");
        }
        //获取用户id列表
        List<Integer> yhidList = listByType.stream().map(BdRank::getYhid).collect(Collectors.toList());

        //获取用户信息列表
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("sczk", "0").in(!ObjectUtils.isEmpty(yhidList), "pid", yhidList).orderByAsc("pid");
        List<User> users = userMapper.selectList(userQueryWrapper);

        //按照数量降序
        List<BdRank> sortedList = listByType.stream()
                .sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder()))
                .limit(limit).collect(Collectors.toList());


        //填充用户信息
        for (BdRank rank : sortedList) {
            for (User user : users) {
                if (rank.getYhid() == user.getPid()) {
                    rank.setUser(user);
                }
            }
        }


        resultMap.put("data", sortedList);
        return resultMap;
    }

    public ResultMap faBangDWRank(BdRank bdRank) {
        int type = bdRank.getType();
        int limit = bdRank.getSize() == null ? maxSize : bdRank.getSize();

//        List<User> users = userMapper.selectAllList();
//        List<BdRank> list = new ArrayList<>(users.size());
        ResultMap resultMap = new ResultMap();
        List<BdRank> listByType;
        switch (type) {
            case 1:
                //年
                listByType = homeMapper.getFBRankUserCountByYear2(limit);
                break;
            case 2:
                //季度
                listByType = homeMapper.getFBRankUserCountByQuarter2(limit);
                break;
            case 3:
                //月
                listByType = homeMapper.getFBRankUserCountByMonth2(limit);
                break;
            default:
                return ResultMap.error("类型参数非法");
        }
//        //获取用户id列表
//        List<Integer> yhidList = listByType.stream().map(BdRank::getYhid).collect(Collectors.toList());
//
//        //获取用户信息列表
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("sczk", "0").in(!ObjectUtils.isEmpty(yhidList), "pid", yhidList).orderByAsc("pid");
//        List<User> users = userMapper.selectList(userQueryWrapper);
//
//
////        List<User> users2 = userMapper.selectListWithSchool(yhidList);
//
//        //按照数量降序
//        List<BdRank> sortedList = listByType.stream()
//                .sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder())).collect(Collectors.toList());
//
//
//        //填充用户信息
//        for (BdRank rank : sortedList) {
//            for (User user : users) {
//                if (rank.getYhid() == user.getPid()) {
//                    rank.setUser(user);
//                    String schoolName = ObjectUtils.isEmpty(user.getDw())?"":user.getDw();
//                    rank.setDw(schoolName);
//                }
//            }
//        }
////        System.out.println("sortedList = " + sortedList);
//        //根据院校分组
//        List<BdRank> collect = sortedList.stream().collect(Collectors.groupingBy(BdRank::getDw))
//                .entrySet().stream()
//                .filter(entry-> !entry.getKey().equals(""))
//                .map(entry -> {
//                    String key = entry.getKey();//单位名称
//                    List<BdRank> value = entry.getValue();//该单位名称的 rank列表
//                    int sum = value.stream().mapToInt(BdRank::getCount).sum();
//                    return new BdRank().setDw(key).setCount(sum);
//                }).sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder()))
//                .limit(limit)
//                .collect(Collectors.toList());


        resultMap.put("data", listByType);
        return resultMap;
    }

    public ResultMap faBangDWJbjeRank(BdRank bdRank) {
        int type = bdRank.getType();
        int limit = bdRank.getSize() == null ? maxSize : bdRank.getSize();

//        List<User> users = userMapper.selectAllList();
//        List<BdRank> list = new ArrayList<>(users.size());
        ResultMap resultMap = new ResultMap();
        List<BdRank> listByType;
        switch (type) {
            case 1:
                //年
                listByType = homeMapper.getFBRankUserJbjeCountByYear2(limit);
                break;
            case 2:
                //季度
                listByType = homeMapper.getFBRankUserJbjeCountByQuarter2(limit);
                break;
            case 3:
                //月
                listByType = homeMapper.getFBRankUserJbjeCountByMonth2(limit);
                break;
            default:
                return ResultMap.error("类型参数非法");
        }
//        //获取用户id列表
//        List<Integer> yhidList = listByType.stream().map(BdRank::getYhid).collect(Collectors.toList());
//
//        //获取用户信息列表
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("sczk", "0").in(!ObjectUtils.isEmpty(yhidList), "pid", yhidList).orderByAsc("pid");
//        List<User> users = userMapper.selectList(userQueryWrapper);
//
//        //按照数量降序
//        List<BdRank> sortedList = listByType.stream()
//                .sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder())).collect(Collectors.toList());
//
//
//        //填充用户信息
//        for (BdRank rank : sortedList) {
//            for (User user : users) {
//                if (rank.getYhid() == user.getPid()) {
//                    rank.setUser(user);
//                    String schoolName = ObjectUtils.isEmpty(user.getDw())?"":user.getDw();
//                    rank.setDw(schoolName);
//                }
//            }
//        }
////        System.out.println("sortedList = " + sortedList);
//        //根据院校分组
//        List<BdRank> collect = sortedList.stream().collect(Collectors.groupingBy(BdRank::getDw))
//                .entrySet().stream()
//                .filter(entry-> !entry.getKey().equals(""))
//                .map(entry -> {
//                    String key = entry.getKey();//单位名称
//                    List<BdRank> value = entry.getValue();//该单位名称的 rank列表
//                    int sum = value.stream().mapToInt(BdRank::getCount).sum();
//                    return new BdRank().setDw(key).setCount(sum);
//                }).sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder()))
//                .limit(limit)
//                .collect(Collectors.toList());


        resultMap.put("data", listByType);
        return resultMap;
    }


//    public ResultMap faBangDWRank(BdRank bdRank) {
//        int type = bdRank.getType();
//        int limit = bdRank.getSize() == null ? maxSize : bdRank.getSize();
//
////        List<User> users = userMapper.selectAllList();
////        List<BdRank> list = new ArrayList<>(users.size());
//        ResultMap resultMap = new ResultMap();
//        List<BdRank> listByType;
//        switch (type) {
//            case 1:
//                //年
//                listByType = homeMapper.getFBRankUserCountByYear();
//                break;
//            case 2:
//                //季度
//                listByType = homeMapper.getFBRankUserCountByQuarter();
//                break;
//            case 3:
//                //月
//                listByType = homeMapper.getFBRankUserCountByMonth();
//                break;
//            default:
//                return ResultMap.error("类型参数非法");
//        }
//        //获取用户id列表
//        List<Integer> yhidList = listByType.stream().map(BdRank::getYhid).collect(Collectors.toList());
//
//        //获取用户信息列表
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("sczk", "0").in(!ObjectUtils.isEmpty(yhidList), "pid", yhidList).orderByAsc("pid");
//        List<User> users = userMapper.selectList(userQueryWrapper);
//
//
////        List<User> users2 = userMapper.selectListWithSchool(yhidList);
//
//        //按照数量降序
//        List<BdRank> sortedList = listByType.stream()
//                .sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder())).collect(Collectors.toList());
//
//
//        //填充用户信息
//        for (BdRank rank : sortedList) {
//            for (User user : users) {
//                if (rank.getYhid() == user.getPid()) {
//                    rank.setUser(user);
//                    String schoolName = ObjectUtils.isEmpty(user.getDw())?"":user.getDw();
//                    rank.setDw(schoolName);
//                }
//            }
//        }
////        System.out.println("sortedList = " + sortedList);
//        //根据院校分组
//        List<BdRank> collect = sortedList.stream().collect(Collectors.groupingBy(BdRank::getDw))
//                .entrySet().stream()
//                .filter(entry-> !entry.getKey().equals(""))
//                .map(entry -> {
//                    String key = entry.getKey();//单位名称
//                    List<BdRank> value = entry.getValue();//该单位名称的 rank列表
//                    int sum = value.stream().mapToInt(BdRank::getCount).sum();
//                    return new BdRank().setDw(key).setCount(sum);
//                }).sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder()))
//                .limit(limit)
//                .collect(Collectors.toList());
//
//
//        resultMap.put("data", collect);
//        return resultMap;
//    }
//
//    public ResultMap faBangDWJbjeRank(BdRank bdRank) {
//        int type = bdRank.getType();
//        int limit = bdRank.getSize() == null ? maxSize : bdRank.getSize();
//
////        List<User> users = userMapper.selectAllList();
////        List<BdRank> list = new ArrayList<>(users.size());
//        ResultMap resultMap = new ResultMap();
//        List<BdRank> listByType;
//        switch (type) {
//            case 1:
//                //年
//                listByType = homeMapper.getFBRankUserJbjeCountByYear();
//                break;
//            case 2:
//                //季度
//                listByType = homeMapper.getFBRankUserJbjeCountByQuarter();
//                break;
//            case 3:
//                //月
//                listByType = homeMapper.getFBRankUserJbjeCountByMonth();
//                break;
//            default:
//                return ResultMap.error("类型参数非法");
//        }
//        //获取用户id列表
//        List<Integer> yhidList = listByType.stream().map(BdRank::getYhid).collect(Collectors.toList());
//
//        //获取用户信息列表
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("sczk", "0").in(!ObjectUtils.isEmpty(yhidList), "pid", yhidList).orderByAsc("pid");
//        List<User> users = userMapper.selectList(userQueryWrapper);
//
//        //按照数量降序
//        List<BdRank> sortedList = listByType.stream()
//                .sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder())).collect(Collectors.toList());
//
//
//        //填充用户信息
//        for (BdRank rank : sortedList) {
//            for (User user : users) {
//                if (rank.getYhid() == user.getPid()) {
//                    rank.setUser(user);
//                    String schoolName = ObjectUtils.isEmpty(user.getDw())?"":user.getDw();
//                    rank.setDw(schoolName);
//                }
//            }
//        }
////        System.out.println("sortedList = " + sortedList);
//        //根据院校分组
//        List<BdRank> collect = sortedList.stream().collect(Collectors.groupingBy(BdRank::getDw))
//                .entrySet().stream()
//                .filter(entry-> !entry.getKey().equals(""))
//                .map(entry -> {
//                    String key = entry.getKey();//单位名称
//                    List<BdRank> value = entry.getValue();//该单位名称的 rank列表
//                    int sum = value.stream().mapToInt(BdRank::getCount).sum();
//                    return new BdRank().setDw(key).setCount(sum);
//                }).sorted(Comparator.comparing(BdRank::getCount, Comparator.reverseOrder()))
//                .limit(limit)
//                .collect(Collectors.toList());
//
//
//        resultMap.put("data", collect);
//        return resultMap;
//    }

    //获取小程序5条资讯
    public ResultMap getFiveZx(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();
        QueryWrapper<Zx> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("tjsj");
        queryWrapper.last("limit 5");
        //类型

        List<Zx> zx = zxMapper.selectList(queryWrapper);
//        System.out.println("zx = " + zx);
        resultMap.put("zxInfo", zx);

        return resultMap;
    }

    public ResultMap editCase(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();
        try {
            homeMapper.editCase(params);
            resultMap.put("status", "true");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public ResultMap editXMshelves(Map<String, Object> params) {
        ResultMap resultMap = new ResultMap();
        try {
            homeMapper.editXMshelves(params);
            resultMap.put("status", "true");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
