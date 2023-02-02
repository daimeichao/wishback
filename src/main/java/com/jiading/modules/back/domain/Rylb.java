package com.jiading.modules.back.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 标签表
 * @TableName t_rylb
 */
@TableName(value ="t_rylb")
@Data
public class Rylb implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 标签名称
     */
    private String zwmc;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String sczk;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}