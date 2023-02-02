package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * <p>
 * banner表
 * </p>
 *
 * @author author
 * @since 2022-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_banner")
public class Banner implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表ID
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 内容
     */
    private String content;

    /**
     * 封面地址
     */
    private String url;

    /**
     * 链接地址
     */
    private String link;

    /**
     * 类型   0 文本内容 1链接
     */
    private String type;

    @TableField(exist = false)
    private String typeName;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String del;

    /**
     * 添加时间
     */
//    @TableField(fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime addTime;

    @TableField(exist = false)
    private String vt;

    @TableField(exist = false)
    private Integer pagesize;

    @TableField(exist = false)
    private Integer curpage;
}
