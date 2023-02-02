package com.jiading.modules.xcx.dao;


import com.jiading.modules.back.domain.BdRank;
import com.jiading.modules.back.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface HomeMapper {

    @Select("select IFNULL(sum(jbje),0) count  from t_xm where sczk ='0'  and shzt = '2' and sftj='1' ")
    int getJbjeCount(Map<String, Object> params);

    int getBdCount(Map<String, Object> params);

    @Update("update t_xm set jbzt = '1' where jbzt = '0' and sczk ='0' and pid = #{xmid}")
    void updateXmJbztTo1(Integer xmid);

    List<BdRank> getJBRankUserCountByYear();

    List<BdRank> getJBRankUserCountByQuarter();

    List<BdRank> getJBRankUserCountByMonth();

    List<BdRank> getFBRankUserCountByYear();

    List<BdRank> getFBRankUserCountByQuarter();

    List<BdRank> getFBRankUserCountByMonth();

    List<BdRank> getJBRankUserJbjeCountByYear();

    List<BdRank> getJBRankUserJbjeCountByQuarter();

    List<BdRank> getJBRankUserJbjeCountByMonth();

    List<BdRank> getFBRankUserJbjeCountByYear();

    List<BdRank> getFBRankUserJbjeCountByQuarter();

    List<BdRank> getFBRankUserJbjeCountByMonth();

    void editCase(Map<String, Object> params);

    void editXMshelves(Map<String, Object> params);

    List<BdRank> getJBRankUserCountByYearWithSchool();

    List<BdRank> getJBRankUserJbjeCountByQuarterWithSchool();

    List<BdRank> getJBRankUserJbjeCountByMonthWithSchool();

    List<BdRank> getFBRankUserCountByYear2(int limit);

    List<BdRank> getFBRankUserCountByQuarter2(int limit);

    List<BdRank> getFBRankUserCountByMonth2(int limit);

    List<BdRank> getFBRankUserJbjeCountByQuarter2(int limit);

    List<BdRank> getFBRankUserJbjeCountByYear2(int limit);

    List<BdRank> getFBRankUserJbjeCountByMonth2(int limit);

//    List<User> getJBRankUserByYear();
//
//    List<User> getJBRankUsertByQuarter();
//
//    List<User> getJBRankUserByMonth();

}




