package com.jiading.modules.back.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 项目-项目类型关系表
 * @TableName t_xm_xmlx
 */
@TableName(value ="t_xm_xmlx")
@Data
public class XmXmlx implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 项目类型id
     */
    private Integer xmlxid;

    /**
     * 项目id
     */
    private Integer xmid;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String sczk;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}