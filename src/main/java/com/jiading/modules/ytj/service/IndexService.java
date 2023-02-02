package com.jiading.modules.ytj.service;

import com.jiading.modules.ytj.dao.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {

    @Autowired
    private IndexDao indexDao;

    public Map getCompleteWishList() {
        Map<String, Object> outmap = new HashMap();
        try {
            List<Map<String, Object>> list= indexDao.getCompleteWishList();
            list.forEach(item->{
                item.put("url",indexDao.getCompleteWishFileList(item));
            });
            outmap.put("list", list);
            outmap.put("state","true");
        }catch (Exception e){
            e.printStackTrace();
        }
        return outmap;
    }

    public Map getClaimWishList() {
        Map<String, Object> outmap = new HashMap();
        try {
            outmap.put("list", indexDao.getClaimWishList());
            outmap.put("state","true");
        }catch (Exception e){
            e.printStackTrace();
        }
        return outmap;
    }

    public Map addWish(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            indexDao.addWish(params);
            outmap.put("state","true");
        }catch (Exception e){
            e.printStackTrace();
        }
        return outmap;
    }

    public Map getWishList(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            outmap.put("list", indexDao.getWishList(params));
            outmap.put("state","true");
        }catch (Exception e){
            e.printStackTrace();
        }
        return outmap;
    }

    @Transactional(rollbackFor =  Exception.class)
    public Map addWishClaimant(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            Integer count=indexDao.confirmWished(params);
            if (count ==1){
                indexDao.updateWished(params);
                indexDao.addWishClaimant(params);
                outmap.put("state","true");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return outmap;
    }

    public Map getDeatail(Map<String, Object> params) {
        Map<String, Object> outmap = new HashMap();
        try {
            Integer type = (Integer) params.get("type");
            Map<String, Object> data = indexDao.getWishDeatail(params);
            if (type != 1){
                Map<String, Object> Wishclaimant = indexDao.getWishclaimant(params);
                data.put("claimant",Wishclaimant.get("claimant"));
                params.put("twcpid",Wishclaimant.get("pid"));
                data.put("url",indexDao.getCompleteWishFileList(params));
            }
            outmap.put("data",data);
            outmap.put("state","true");
        }catch (Exception e){
            e.printStackTrace();
        }
        return outmap;
    }
}
