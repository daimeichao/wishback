package com.jiading.modules.littlewish.mapper;

import com.jiading.modules.littlewish.domain.Wish;
import com.jiading.modules.littlewish.domain.wishClaimant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TwishClaimantMapper {
    List<wishClaimant> getmywishClaimant(Integer pid);

    List<wishClaimant> myfulfilllist(Integer pid);
}
