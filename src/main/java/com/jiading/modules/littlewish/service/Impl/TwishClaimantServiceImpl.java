package com.jiading.modules.littlewish.service.Impl;



import com.jiading.modules.littlewish.domain.Wish;
import com.jiading.modules.littlewish.mapper.TwishClaimantMapper;
import com.jiading.modules.littlewish.domain.wishClaimant;
import com.jiading.modules.littlewish.service.TwishClaimantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwishClaimantServiceImpl implements TwishClaimantService {
    @Autowired
    private TwishClaimantMapper TwishClaimantMapper;

    @Override
    public List<wishClaimant> getmywishClaimant(Integer pid) {
        return TwishClaimantMapper.getmywishClaimant(pid);
    }

    @Override
    public List<wishClaimant> myfulfilllist(Integer pid) {
        return TwishClaimantMapper.myfulfilllist(pid);
    }
}
