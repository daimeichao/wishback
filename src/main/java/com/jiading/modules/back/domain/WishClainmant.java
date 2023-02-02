package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value ="t_wish_claimant")
@Data
@NoArgsConstructor
public class WishClainmant implements Serializable {

    /**
     *   表ID
     * */
    private  int pid;
    /**
     *  心愿表id
     * */
    private  int wishid;
    /**
     *    认领人姓名
     * */
    private  int claimant;
    /**
     *    认领人id
     * */
    private  int claimantid;
    /**
     *      实现时间
     * */
    private String realizeTime;
    /**
     *   快递单号
     * */
    private String  expressage;
    /**
     *   认领审核状态 0：待审核 1：审核通过 2：审核不通过
     * */
//    @TableLogic(value = "0")
    private String claimantAuditState;
    /**
     *   认领审核备注
     * */
    private String  claimantAuditRemark;
    /**
     *   认领审核人id
     * */
    private  int  claimantAuditid;
    /**
     *   操作人id
     * */
    private  int operatorid;
    /**
     *    添加时间
     * */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime addTime;
//    private Date addTime;
    /**
     * 修改时间
     * */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 删除状况 0未删除 1已删除
     *  @TableLogic 参数，未删除的值默认为0
     */
    @TableLogic(value = "0")
    private String del;


}
