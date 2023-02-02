package com.jiading.modules.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiading.common.util.FenYe;
import com.jiading.common.util.ResultMap;
import com.jiading.modules.back.domain.Xm;
import com.jiading.modules.back.domain.XmXmlx;
import com.jiading.modules.back.domain.Xmlx;
import com.jiading.modules.back.mapper.XmXmlxMapper;
import com.jiading.modules.back.service.XmlxService;
import com.jiading.modules.back.mapper.XmlxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 */
@Service
public class XmlxServiceImpl extends ServiceImpl<XmlxMapper, Xmlx> implements XmlxService {
    @Autowired
    private XmlxMapper xmlxMapper;

    @Autowired
    private XmXmlxMapper xmXmlxMapper;

    @Override
    public Map<String, Object> getXmlxList(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);
            params.put("parentid", "0");
            List<Map<String, Object>> list = xmlxMapper.getXmlxList(params);
            this.fun(list);
            int count = xmlxMapper.getXmlxListCount(params);

            // 返回
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("count", 0);
            outmap.put("list", Collections.emptyList());
            outmap.put("curpage", 1);
            outmap.put("pagesize", 10);
        }
        return outmap;
    }

    @Override
    public Map<String, Object> updateXmlx(Map<String, Object> prarms) {
        Map<String, Object> outmap = new HashMap();
        try {
            int i = xmlxMapper.updateXmlx(prarms);
            // 返回
            outmap.put("code", i);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("code", 0);
        }
        return outmap;
    }

    @Override
    public Map<String, Object> addXmlx(Map<String, Object> prarms) {
        Map<String, Object> outmap = new HashMap();
        try {
            int i = xmlxMapper.addXmlx(prarms);
            // 返回
            outmap.put("code", i);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("code", 0);
        }
        return outmap;
    }

    @Override
    public Map<String, Object> getRylbList(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            int pageindex = FenYe.pageindex(params.get("curpage"), params.get("pagesize"));
            int pagesize = FenYe.pagesize(params.get("pagesize"));
            params.put("pagesize", pagesize);
            params.put("pageindex", pageindex);
            params.put("parentid", "0");
            List<Map<String, Object>> list = xmlxMapper.getRylbList(params);
            int count = xmlxMapper.getRylbListCount(params);

            // 返回
            outmap.put("count", count);
            outmap.put("list", list);
            outmap.put("curpage", FenYe.curpage(params.get("curpage")));
            outmap.put("pagesize", pagesize);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("count", 0);
            outmap.put("list", Collections.emptyList());
            outmap.put("curpage", 1);
            outmap.put("pagesize", 10);
        }
        return outmap;
    }

    @Override
    public Map<String, Object> updateRylb(Map<String, Object> prarms) {
        Map<String, Object> outmap = new HashMap();
        try {
            int i = xmlxMapper.updateRylb(prarms);
            // 返回
            outmap.put("code", i);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("code", 0);
        }
        return outmap;
    }

    @Override
    public Map<String, Object> addRylb(Map<String, Object> prarms) {
        Map<String, Object> outmap = new HashMap();
        try {
            int i = xmlxMapper.addRylb(prarms);
            // 返回
            outmap.put("code", i);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("code", 0);
        }
        return outmap;

    }


    //获取列表children方法
    private List<Map<String, Object>> fun(List<Map<String, Object>> list) {
        for (Map m : list) {
            Map outmap = new HashMap();
            if (m != null) {
                if (m.get("pid") != null) {
                    outmap.put("parentid", m.get("pid"));
                    List<Map<String, Object>> childrenList = xmlxMapper.getParentidList(outmap);
                    if (childrenList.size() != 0) {
                        m.put("children", fun(childrenList));
                    }
                }
            }
        }
        return list;
    }

    //获取我要揭榜-项目类型选择器
    public Map<String, Object> getAllXmlx(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            List<Map<String, Object>> list = xmlxMapper.getAllXmlx(params);
            // 返回
            outmap.put("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outmap;
    }

    public Map<String, Object> getXmlxTree(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            List<Map<String, Object>> list = xmlxMapper.getAllXmlx(params);
            List<Map<String, Object>> treeList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                List<Map<String, Object>> childList = new ArrayList<>();
                if ("0".equals(list.get(i).get("parentid").toString())) {
//                    System.out.println(list.get(i).get("parentid").toString()+list.get(i).get("lxmc").toString());
                    childList = getXmlxChildren(list.get(i), list);
                    if (childList.size() != 0) {
                        list.get(i).put("children", childList);
                    }
                    treeList.add(list.get(i));
                }
            }
            // 返回
            outmap.put("list", treeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outmap;
    }

    public List getXmlxChildren(Map<String, Object> parent, List<Map<String, Object>> list) {
        List<Map<String, Object>> childList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<Map<String, Object>> childList2 = new ArrayList<>();
            if (parent.get("pid").toString().equals(list.get(i).get("parentid").toString())) {
                childList2 = getXmlxChildren(list.get(i), list);
                if (childList2.size() != 0) {
                    list.get(i).put("children", childList2);
                }
                childList.add(list.get(i));
            }
        }
        return childList;
    }

    @Override
    public Map<String, Object> delJbxmbyYhid(Map<String, Object> prarms) {
        Map<String, Object> outmap = new HashMap();
        try {
            int i = xmlxMapper.delJbxmbyYhid(prarms);
            int j = xmlxMapper.getjbcountbyxmid(prarms);
            if (j == 0) {
                xmlxMapper.updateXmifjb(prarms);
            }
            outmap.put("code", i);
        } catch (Exception e) {
            e.printStackTrace();
            outmap.put("code", 0);
        }
        return outmap;
    }

    @Override
    public ResultMap importXmlx(Map<String, Object> prarms) {
        List<Xm> list = xmlxMapper.getXmListNewImport();
        List<Xmlx> xmlxList = xmlxMapper.selectList(null);

        for (Xm xm : list) {
            XmXmlx xmXmlx2 = new XmXmlx();
            xmXmlx2.setXmid(xm.getPid());
            xmXmlx2.setXmlxid(12);
            xmXmlxMapper.insert(xmXmlx2);//一级类型

            for (Xmlx xmlx : xmlxList) {
                if (xmlx.getLxmc().equals(xm.getBhly())) {
                    XmXmlx xmXmlx = new XmXmlx();
                    xmXmlx.setXmid(xm.getPid());
                    xmXmlx.setXmlxid(xmlx.getPid());
                    xmXmlxMapper.insert(xmXmlx);
                }
            }
        }
        return ResultMap.ok();
    }
}




