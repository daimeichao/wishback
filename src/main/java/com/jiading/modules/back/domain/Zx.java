package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 资讯表
 *
 * @TableName t_zx
 */
@TableName(value = "t_zx")
@Data
public class Zx implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 咨询名称
     */
    private String zxmc;

    /**
     * 资讯内容
     */
    private Integer zxnrid;

    @TableField(exist = false)
    private String zxnr;

    /**
     * 图片地址
     */
    private String dz;

    /**
     * 新增时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime tjsj;

    /**
     * 发布人ID
     */
    private Integer fbrid;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String sczk;

    /**
     * 来源
     */
    private String ly;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
