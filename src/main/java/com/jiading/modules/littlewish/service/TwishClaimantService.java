package com.jiading.modules.littlewish.service;


import com.jiading.modules.littlewish.domain.Wish;
import com.jiading.modules.littlewish.domain.wishClaimant;

import java.util.List;

public interface TwishClaimantService {
    List<wishClaimant> getmywishClaimant(Integer pid);

    List<wishClaimant> myfulfilllist(Integer pid);
}
