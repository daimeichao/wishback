package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 消息表
 *
 * @TableName t_msg
 */
@TableName(value = "t_msg")
@Data
public class Msg {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 发送人id
     */
    private Integer fsrid;

    /**
     * 接收人id
     */
    private Integer jsrid;
    /**
     * 标题
     */
    private String bt;

    /**
     * 内容
     */
    private String nr;

    /**
     * 类型
     */
    private String lx;

    /**
     * 状态 0正常 1撤回
     */
    private String zt;

    /**
     * 添加时间
     */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime tjsj;

    /**
     * 发送时间
     */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime fssj;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String sczk;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    private Integer xmid;

    @TableField(exist = false)
    private List<User> jsrList;

    @TableField(exist = false)
    private List<Integer> jsridList;

    @TableField(exist = false)
    private Xm xm;

    @TableField(exist = false)
    private String jsridListStr;
}
