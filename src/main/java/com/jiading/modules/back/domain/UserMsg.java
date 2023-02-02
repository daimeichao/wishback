package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户消息表
 * @TableName t_user_msg
 */
@TableName(value ="t_user_msg")
@Data
@NoArgsConstructor
public class UserMsg implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 接收用户id
     */
    private Integer jsrid;

    /**
     * 消息id
     */
    private Integer xxid;

    /**
     * 回复状态 0未回复 1已回
     */
    private String hfzt;

    /**
     * 阅读状态 0未读 1已读
     */
    private String ydzt;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String sczk;

    /**
     * 阅读时间
     */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime ydsj;

    /**
     * 回复时间
     */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime hfsj;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public UserMsg(Integer jsrid, Integer xxid) {
        this.jsrid = jsrid;
        this.xxid = xxid;
    }
}