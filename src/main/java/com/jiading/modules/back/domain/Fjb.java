package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 项目附件表
 *
 * @TableName t_fjb
 */
@TableName(value = "t_fjb")
@Data
public class Fjb implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 所属项目id
     */
    private Integer ssxmid;

    /**
     * 附件名称
     */
    private String fjmc;

    /**
     * 附件地址
     */
    private String fjdz;

    /**
     * 附件大小（单位B）
     */
    private Integer fjdx;

    /**
     * 添加时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime tjsj;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime xgsj;

    /**
     * 是否删除 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String sczk;

    /**
     * 附件类型
     */
    private String fjlx;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}