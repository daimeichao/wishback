package com.jiading.modules.back.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户标签表
 *
 * @TableName t_user_bq
 */
@TableName(value = "t_user_bq")
@Data
@NoArgsConstructor
public class UserBq implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 用户id
     */
    private Integer yhid;

    /**
     * 标签id
     */
    private Integer bqid;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String sczk;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    public UserBq(Integer yhid, Integer bqid) {
        this.yhid = yhid;
        this.bqid = bqid;
    }
}