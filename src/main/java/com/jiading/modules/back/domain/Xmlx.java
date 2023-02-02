package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_xmlx
 */
@TableName(value ="t_xmlx")
@Data
public class Xmlx implements Serializable {
    /**
     * 项目类型id
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 项目类型名称
     */
    private String lxmc;

    /**
     * 添加时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime tjsj;

    /**
     * 删除状况 0未删除  1已删除
     */
    @TableLogic(value = "0")
    private String sczk;

    private Integer parentid;

    private Integer lxdj;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}