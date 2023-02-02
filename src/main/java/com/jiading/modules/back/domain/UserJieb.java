package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户接榜表
 *
 * @TableName t_user_jieb
 */
@TableName(value = "t_user_jieb")
@Data
@NoArgsConstructor
public class UserJieb implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 用户id
     */
    private int yhid;

    /**
     * 项目id
     */
    private int xmid;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String sczk;

    /**
     * 揭榜日期
     */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime jbrq;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public UserJieb(Integer yhid, Integer xmid) {
        this.yhid = yhid;
        this.xmid = xmid;
    }
}