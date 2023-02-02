package com.jiading.modules.littlewish.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@TableName(value = "t_wish")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wish {
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 许愿人名称
     */
    private String wishusername;

    /**
     * 许愿人id
     */
    private Integer wishuserid;

    /**
     * 许愿内容
     */
    private String wishContent;

    /**
     * 许愿地点
     */
    private String adder;

    /**
     * 许愿时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime wishTime;

    /**
     * 心愿审核状态 0：待审核 1：审核通过 2：审核不通过
     */
    @TableLogic(value = "0")
    private String wishAuditState;

    /**
     * 心愿审核备注
     */
    private String wishAuditRemark;

    /**
     * 心愿审核人id
     */
    private Integer wishAuditid;

    /**
     * 心愿状态 0：待认领 1：已认领 2：已完成
     */
    @TableLogic(value = "0")
    private String wishState;

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

    /**
     * 排序
     */
    private Integer sort;

    @TableField(exist = false)
    private String claimant;

    @TableField(exist = false)
    private String expressage;

    @TableField(exist = false)
    private String claimantAuditRemark;

    @TableField(exist = false)
    private String url;
}
