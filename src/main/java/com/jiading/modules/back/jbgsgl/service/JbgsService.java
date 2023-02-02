package com.jiading.modules.back.jbgsgl.service;

import com.jiading.common.util.FenYe;
import com.jiading.modules.back.domain.*;
import com.jiading.modules.back.jbgsgl.dao.JbgsDao;
import com.jiading.modules.back.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JbgsService {

    @Resource
    private JbgsDao jbgsDao;

    @Autowired
    private UserJiebMapper userJiebMapper;

    @Autowired
    private XmlxMapper xmlxMapper;

    @Autowired
    private XmMapper xmMapper;

    @Autowired
    private DzdMapper dzdMapper;

    @Autowired
    private FjbMapper fjbMapper;


    public Map getXmlist(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

            // 取值
            List<Map> list = jbgsDao.getXmlist(params);
            int count = jbgsDao.getXmlistCount(params);
            int pagecount = FenYe.pagecount(count, pagesize);

            // 返回
            outmap.put("pagecount", pagecount);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 12);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }

    public Map updXmInfo(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            jbgsDao.editXm(params);
            outmap.put("result", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", "err");
        }
        return outmap;
    }

    public Map addXm(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            //type 0 存草稿  1 提交 2 重新发榜
            if("2".equals(params.get("sftj").toString())){
                Map<String, Object> params2 = new HashMap<>();
                params2.put("pid",params.get("cfpid"));
                Map<String, Object> xm = xmMapper.getXmInfo(params2);
                xm.put("bm","JBGS"+("0000000000"+params.get("cfpid").toString()).substring(("0000000000"+params.get("cfpid").toString()).length()-10,("0000000000"+params.get("cfpid").toString()).length()));
                xm.put("sftj","0");
                xm.put("sfxj","0");
                xm.put("shzt","1");
                xm.put("jazt","0");
                int k = jbgsDao.addXm(xm);
                List<Map<String,Object>> xmlxList = xmlxMapper.getXmlxByXmid(params2);
                String xmlxidstr = "";
                for(int i = 0;i < xmlxList.size();i++){
                    xmlxidstr += xmlxList.get(i).get("xmlxid").toString();
                }
                String[] xmlxids = xmlxidstr.split(",");
                for(int i = 0;i < xmlxids.length;i++){
                    params2.put("xmlxid",xmlxids[i]);
                    jbgsDao.addXmXmlx(params2);
                }
                List<Map<String,Object>> fjList = fjbMapper.getFjByXmid(params2);
                String filenamestr = "";
                String fileurlstr = "";
                for(int i = 0;i < fjList.size();i++){
                    filenamestr += fjList.get(i).get("fjmc").toString();
                    fileurlstr += fjList.get(i).get("fjdz").toString();
                }
                String[] filenames = filenamestr.split(",");
                String[] fileurls = fileurlstr.split(",");
                Fjb fjb = new Fjb();
                fjb.setSsxmid(Integer.parseInt(xm.get("pid").toString()));
                for(int i = 0;i < filenames.length;i++){
                    fjb.setPid(null);
                    fjb.setFjmc(filenames[i]);
                    fjb.setFjdz(fileurls[i]);
                    fjbMapper.insert(fjb);
                }
            }else{
                Integer oldoid = Integer.parseInt(params.get("pid").toString());
                Dzd dzd = new Dzd(params.get("xmnr").toString());
                dzdMapper.insert(dzd);
                params.put("xmnrid", dzd.getPid());
                String[] xmlxids = params.get("xmlx").toString().split(",");
                params.put("xmlx", xmlxids[0]);
                int k = jbgsDao.addXm(params);
                Map<String,Object> params3 = new HashMap<>();
                params3.put("pid",params.get("pid"));
                params3.put("bm","JBGS"+("0000000000"+params.get("pid").toString()).substring(("0000000000"+params.get("pid").toString()).length()-10,("0000000000"+params.get("pid").toString()).length()));
                jbgsDao.editXm(params3);
                Map<String, Object> params2 = new HashMap<>();
                params2.put("xmid", params.get("pid"));
                Fjb fjb = new Fjb();
                fjb.setSsxmid(Integer.parseInt(params.get("pid").toString()));
                String[] filenames = params.get("filename").toString().split(",");
                String[] fileurls = params.get("fileurl").toString().split(",");
                for (int i = 0; i < filenames.length; i++) {
                    fjb.setPid(null);
                    fjb.setFjmc(filenames[i]);
                    fjb.setFjdz(fileurls[i]);
                    fjbMapper.insert(fjb);
                }
                for (int i = 0; i < xmlxids.length; i++) {
                    params2.put("xmlxid", xmlxids[i]);
                    jbgsDao.addXmXmlx(params2);
                }
                if (!ObjectUtils.isEmpty(oldoid) ){
                    jbgsDao.deleteXM(oldoid);
                }
            }

            outmap.put("result", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", "err");
        }
        return outmap;
    }

    public Map editXm(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            Dzd dzd = new Dzd();
            dzd.setPid(Integer.parseInt(params.get("xmnrid").toString()));
            dzd.setVt(params.get("xmnr").toString());
            dzdMapper.updateById(dzd);
            xmlxMapper.delXmlxByXmid(params);
            String[] xmlxids = params.get("xmlx").toString().split(",");
            params.put("xmlx", xmlxids[0]);
            int k = jbgsDao.editXm(params);
            Map<String, Object> params2 = new HashMap<>();
            params2.put("xmid", params.get("pid"));
            fjbMapper.delFjByXmid(params);
            Fjb fjb = new Fjb();
            fjb.setSsxmid(Integer.parseInt(params.get("pid").toString()));
            String[] filenames = params.get("filename").toString().split(",");
            String[] fileurls = params.get("fileurl").toString().split(",");
            for (int i = 0; i < filenames.length; i++) {
                fjb.setPid(null);
                fjb.setFjmc(filenames[i]);
                fjb.setFjdz(fileurls[i]);
                fjbMapper.insert(fjb);
            }
            for (int i = 0; i < xmlxids.length; i++) {
                params2.put("xmlxid", xmlxids[i]);
                jbgsDao.addXmXmlx(params2);
            }
            outmap.put("result", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", "err");
        }
        return outmap;
    }

    public Map getJbrlist(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

            // 取值
            List<Map> list = jbgsDao.getJbrlist(params);
            int count = jbgsDao.getJbrlistCount(params);
            int pagecount = FenYe.pagecount(count, pagesize);

            // 返回
            outmap.put("pagecount", pagecount);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 12);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }

    public Map getXmlxlist(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

            // 取值
            List<Map> list = jbgsDao.getXmlxlist(params);
            int count = jbgsDao.getXmlxlistCount(params);
            int pagecount = FenYe.pagecount(count, pagesize);

            // 返回
            outmap.put("pagecount", pagecount);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 12);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }

    public Map updXmlxInfo(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            jbgsDao.editXmlx(params);
            outmap.put("result", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", "err");
        }
        return outmap;
    }

    public Map addXmlx(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            jbgsDao.addXmlx(params);
            outmap.put("result", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("result", "err");
        }
        return outmap;
    }

    public Map getXmlistWithJbrInfo(Map<String, Object> params) {
//        System.out.println("params = " + params);
        Map outmap = new HashMap();
        try {
            String yhid = ObjectUtils.isEmpty(params.get("yhid")) ? "" : params.get("yhid").toString();
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

            //只查询查询审核通过的
            params.put("shzt", "2");
            params.put("sftj", "1");
            params.put("sfxj", "0");
            // 取值
            List<Map> list = jbgsDao.getXmlistOnlyDate(params);
            int count = jbgsDao.getXmlistCount(params);
            int pagecount = FenYe.pagecount(count, pagesize);


            List<String> xmidList = new ArrayList<>(pagesize);

            for (Map map : list) {
                map.put("size", 3);
                List<User> userInfoByXmid = userJiebMapper.getUserInfoByXmid(map);
                map.put("jbrList", userInfoByXmid);
                int jbrCount = userJiebMapper.getUserCountByXmid(map);
                map.put("jbrCount", jbrCount);

                //找到此项目的二级类型
//               List<Xmlx> subXmlxList =  xmlxMapper.getSubXmlxByXmid(map);
                List<Xmlx> subXmlxList = xmlxMapper.getAllXmlxByXmid(map);
                map.put("subXmlxList", subXmlxList);

                xmidList.add(map.get("pid").toString());

                map.put("hasJieBang", false);


                if ("1".equals(String.valueOf(map.get("sfxj")))) {
                    map.put("hasJieBang", true);
                }
                if (!"1".equals(String.valueOf(map.get("sftj")))) {
                    map.put("hasJieBang", true);
                }
                if (!"0".equals(String.valueOf(map.get("jazt")))) {
                    map.put("hasJieBang", true);
                }
            }

//            System.out.println("xmidList:" + xmidList);

            params.put("ids", xmidList);
//            /* 判断人数是否超出最大限制 */
//            List<Map<String, Integer>> ifJieBangWithXmid = userJiebMapper.getIfJieBangWithXmid(params);
//            for (Map map : list) {
//                for (Map<String, Integer> m : ifJieBangWithXmid) {
//                    if (map.get("pid").toString().equals(m.get("xmid").toString()) && Integer.parseInt(String.valueOf(map.get("jbrs"))) <= m.get("count")) {
//                        map.put("hasJieBang", true);
//                    }
//                }
//            }


            if (!"".equals(yhid)) {
                //该用户是否揭榜过
                List<UserJieb> ifJieBangWithXmidAndYhid = userJiebMapper.getIfJieBangWithXmidAndYhid(params);

                for (Map map : list) {
                    if (ifJieBangWithXmidAndYhid == null) {
//                        map.put("hasJieBang", false);
                    } else {
                        for (UserJieb userJieb : ifJieBangWithXmidAndYhid) {
                            if (map.get("pid").toString().equals(String.valueOf(userJieb.getXmid())) && yhid.equals(String.valueOf(userJieb.getYhid()))) {
                                map.put("hasJieBang", true);
                            }
                        }
                    }

                }
            }
            // 返回
            outmap.put("pagecount", pagecount);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 12);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }


    public Map getJbrXmlistInfo(Map<String, Object> params) {
//        System.out.println("params = " + params);
        Map outmap = new HashMap();
        try {
            String yhid = ObjectUtils.isEmpty(params.get("yhid")) ? "" : params.get("yhid").toString();
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

            //只查询查询审核通过的
            params.put("shzt", "2");
            // 取值
            List<Map> list = jbgsDao.getXmlistbyyhid(params);
            int count = jbgsDao.getXmlistbyyhidCount(params);
            int pagecount = FenYe.pagecount(count, pagesize);


            List<String> xmidList = new ArrayList<>(pagesize);

            for (Map map : list) {
                map.put("size", 3);
                List<User> userInfoByXmid = userJiebMapper.getUserInfoByXmid(map);
                map.put("jbrList", userInfoByXmid);
                int jbrCount = userJiebMapper.getUserCountByXmid(map);
                map.put("jbrCount", jbrCount);

                //找到此项目的二级类型
//               List<Xmlx> subXmlxList =  xmlxMapper.getSubXmlxByXmid(map);
                List<Xmlx> subXmlxList = xmlxMapper.getAllXmlxByXmid(map);
                map.put("subXmlxList", subXmlxList);

                xmidList.add(map.get("pid").toString());

                map.put("hasJieBang", false);
            }

//            System.out.println("xmidList:" + xmidList);

            if (!"".equals(yhid)) {
                params.put("ids", xmidList);
                //该用户是否揭榜过
                List<UserJieb> ifJieBangWithXmidAndYhid = userJiebMapper.getIfJieBangWithXmidAndYhid(params);

                for (Map map : list) {
                    if (ifJieBangWithXmidAndYhid == null) {
                        map.put("hasJieBang", false);
                    } else {
                        for (UserJieb userJieb : ifJieBangWithXmidAndYhid) {
                            if (map.get("pid").toString().equals(String.valueOf(userJieb.getXmid())) && yhid.equals(String.valueOf(userJieb.getYhid()))) {
                                map.put("hasJieBang", true);
                            }
                        }
                    }

                }
            }
            // 返回
            outmap.put("pagecount", pagecount);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 12);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }

    public Map getFbrXmlistInfo(Map<String, Object> params) {
//        System.out.println("params = " + params);
        Map outmap = new HashMap();
        try {
            String yhid = ObjectUtils.isEmpty(params.get("yhid")) ? "" : params.get("yhid").toString();
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

//            //只查询查询审核通过的
//            params.put("shzt", "2");
            // 取值
            List<Map> list = jbgsDao.getFbXmlistByuid(params);
            int count = jbgsDao.getFbXmlistByuidCount(params);
            if(list != null && list.size() > 0){
                for(int i = 0;i < list.size();i++){
                    Map<String,Object> params2 = new HashMap<>();
                    params2.put("xmid",list.get(i).get("pid"));
                    System.out.println(params2.toString());
                    List<Map> jbrList = jbgsDao.getJbrlist(params2);
                    if(jbrList != null && jbrList.size() > 0){
                        list.get(i).put("jbrList",jbrList);
                        list.get(i).put("jbrListCount",jbrList.size());
                    }else{
                        list.get(i).put("jbrList",null);
                        list.get(i).put("jbrListCount",0);
                    }
                }
            }
            int pagecount = FenYe.pagecount(count, pagesize);


            List<String> xmidList = new ArrayList<>(pagesize);

            for (Map map : list) {
                map.put("size", 3);
                List<User> userInfoByXmid = userJiebMapper.getUserInfoByXmid(map);
                map.put("jbrList", userInfoByXmid);
                int jbrCount = userJiebMapper.getUserCountByXmid(map);
                map.put("jbrCount", jbrCount);

                //找到此项目的二级类型
//               List<Xmlx> subXmlxList =  xmlxMapper.getSubXmlxByXmid(map);
                List<Xmlx> subXmlxList = xmlxMapper.getAllXmlxByXmid(map);
                map.put("subXmlxList", subXmlxList);

                xmidList.add(map.get("pid").toString());

                map.put("hasJieBang", false);
            }

//            System.out.println("xmidList:" + xmidList);

            if (!"".equals(yhid)) {
                params.put("ids", xmidList);
                //该用户是否揭榜过
                List<UserJieb> ifJieBangWithXmidAndYhid = userJiebMapper.getIfJieBangWithXmidAndYhid(params);

                for (Map map : list) {
                    if (ifJieBangWithXmidAndYhid == null) {
                        map.put("hasJieBang", false);
                    } else {
                        for (UserJieb userJieb : ifJieBangWithXmidAndYhid) {
                            if (map.get("pid").toString().equals(String.valueOf(userJieb.getXmid())) && yhid.equals(String.valueOf(userJieb.getYhid()))) {
                                map.put("hasJieBang", true);
                            }
                        }
                    }

                }
            }
            // 返回
            outmap.put("pagecount", pagecount);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 12);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }

    public Map getJbrListByXmid(Map<String, Object> params) {
//        System.out.println("params = " + params);
        Map outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);

//            //只查询查询审核通过的
//            params.put("shzt", "2");
            // 取值
            List<User> list = userJiebMapper.getUserInfoByXmid(params);
            int count = userJiebMapper.getUserCountByXmid(params);
            int pagecount = FenYe.pagecount(count, pagesize);
            // 返回
            outmap.put("pagecount", pagecount);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("search", params);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("curpage", 1);
            outmap.put("pagesize", 10);
            outmap.put("count", 0);
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("search", params);
        }
        return outmap;
    }

    public Map getCountByCity(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {

            List<Map> list = jbgsDao.getCountByCity(params);
            List<Map> list1 = jbgsDao.getCountByCity1(params);
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                for (int j = 0; j < list1.size(); j++) {
                    Map<String, Object> map1 = list1.get(j);
                    if (map.get("yhloc") != null && map1.get("yhloc") != null && map1.get("yhloc").toString().equals(map.get("yhloc").toString())) {
                        map.put("fbsl", map1.get("fbsl"));

                    }
                }
            }

            for (int k = 0; k < list.size(); k++) {
                Map<String, Object> map = list.get(k);
                if (map.get("fbsl") == null) {
                    map.put("fbsl", 0);
                }
            }


            outmap.put("list", list);
            outmap.put("result", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("result", "err");
        }
        return outmap;
    }

    public Map getlzCountByCity(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {

            List<Map> list = jbgsDao.getCountByCity2(params);


            outmap.put("list", list);
            outmap.put("result", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("result", "err");
        }
        return outmap;
    }

    public Map getTjhzCount(Map<String, Object> params) {
        Map outmap = new HashMap();
        try {
            int a = jbgsDao.getXmCount(params);
            List<Map> list = jbgsDao.getTjhzCount(params);
            Map<String, Object> map = list.get(0);
            map.put("sydw", a);

            List<Map> list1 = jbgsDao.getLbCount(params);


            list.addAll(list1);

            outmap.put("list", list);
            outmap.put("result", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("list", new ArrayList<Map<String, Object>>());
            outmap.put("result", "err");
        }
        return outmap;
    }
}
