package com.jiading.modules.back.mapper;

import com.jiading.modules.back.domain.Xm;
import com.jiading.modules.back.domain.Xmlx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.jiading.modules.back.domain.Xmlx
 */
@Mapper
public interface XmlxMapper extends BaseMapper<Xmlx> {

    List<Xmlx> getSubXmlxByXmid(Map map);

    List<Xmlx> getAllXmlxByXmid(Map map);

    List<Map<String,Object>> getXmlxList(Map<String, Object> map);

    Integer getXmlxListCount(Map<String, Object> map);

    List<Map<String, Object>> getParentidList(Map outmap);

    int updateXmlx(Map<String, Object> prarms);

    int addXmlx(Map<String, Object> prarms);

    List<Map<String, Object>> getAllXmlx(Map outmap);

    List<Map<String,Object>> getRylbList(Map<String, Object> map);

    Integer getRylbListCount(Map<String, Object> map);

    int updateRylb(Map<String, Object> prarms);

    int addRylb(Map<String, Object> prarms);

    int delJbxmbyYhid(Map<String, Object> prarms);

    int getjbcountbyxmid(Map<String, Object> prarms);

    int updateXmifjb(Map<String, Object> prarms);

    int delXmlxByXmid(Map<String, Object> prarms);

    List<Map<String,Object>> getXmlxByXmid(Map<String, Object> map);

    List<Xm> getXmListNewImport();

}




