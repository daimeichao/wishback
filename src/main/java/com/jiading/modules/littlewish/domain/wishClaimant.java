package com.jiading.modules.littlewish.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@TableName(value = "t_wish_claimant")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class wishClaimant {

    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 许愿人id
     */
    private Integer wishid;

    /**
     * 认领人id
     */
    private Integer claimantid;

    /**
     * 认领人姓名
     */
    private String claimant;

    /**
     * 实现时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime realizeTime;

    /**
     * 认领审核状态 0：待审核 1：审核通过 2：审核不通过
     */
    @TableLogic(value = "0")
    private String claimantAuditState;

    /**
     * 快递单号
     */
    private String expressage;

    /**
     * 认领审核备注
     */
    private String claimantAuditRemark;

    /**
     * 认领审核人id
     */
    private Integer claimantAuditid;

    /**
     * 操作人id
     */
    private Integer operatorid;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String del;

    /**
     * 添加时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;


    @TableField(exist = false)
    private String wishContent;

}
