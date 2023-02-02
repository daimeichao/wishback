package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * 用户表
 *
 * @TableName t_user
 */
@TableName(value = "t_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表ID
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 微信昵称
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

    /**
     * openid
     */
    private String openid;

    /**
     * 用户类型 1：小程序用户 2：系统用户
     */
    private String type;

    /**
     * 添加时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime addTime;

    /**
     * 禁用状况 0启用 1禁用
     */
    private String jyzk;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String del;




    /**
     * 临时验证码存储
     */
    private String yzm;

    @Override
    public String toString() {
        return "User{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", portrait='" + portrait + '\'' +
                ", phone='" + phone + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", openid='" + openid + '\'' +
                ", type='" + type + '\'' +
                ", addTime=" + addTime +
                ", jyzk='" + jyzk + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
