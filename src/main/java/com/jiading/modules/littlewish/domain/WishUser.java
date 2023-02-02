package com.jiading.modules.littlewish.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value = "t_user")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WishUser implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 微信名称
     */
    private String nick;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;

    private String openid;

    /**
     * 用户类型 1：小程序用户 2：系统用户
     */
    private String type;

    /**
     * 禁用状况 0启用 1禁用
     */
    @TableLogic(value = "0")
    private String jyzk;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String del;

    /**
     * 添加时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

}
