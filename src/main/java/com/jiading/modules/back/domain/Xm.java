package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 项目表
 * @TableName t_xm
 */
@TableName(value ="t_xm")
@Data
public class Xm implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 封面地址
     */
    private String dz;

    /**
     * 经度
     */
    private String jd;

    /**
     * 纬度
     */
    private String wd;

    /**
     * 项目内容id 大字段id
     */
    private Integer xmnrid;

    /**
     * 项目名称 
     */
    private String xmmc;

    /**
     * 揭榜交付要求 
     */
    private String xmyq;

    /**
     * 完成时限
     */
    private String wcsx;

    /**
     * 揭榜金额 （万元）
     */
    private BigDecimal jbje;

    /**
     * 来源 
     */
    private String xmly;

    /**
     * 项目类型 (一级)
     */
    private String xmlx;

    /**
     * 状态 0未揭榜 1已揭榜 
     */
    private String jbzt;

    /**
     * 备注
     */
    private String bz;

    /**
     * 业主单位
     */
    private String yzdw;

    /**
     * 联系人
     */
    private String lxr;

    /**
     * 联系方式
     */
    private String lxfs;

    /**
     * 发榜人
     */
    private Integer fbrid;

    /**
     * 状态 0审核不通过 1未审核 2审核通过 
     */
    private String shzt;

    /**
     * 审核人id
     */
    private Integer shrid;

    /**
     * 驳回理由
     */
    private String bhly;

    /**
     * 项目地点
     */
    private String xmdd;

    /**
     * 是否提交 0未提交 1已提交
     */
    private String sftj;

    /**
     * 是否下架 0未下架 1已下架
     */
    private String sfxj;

    /**
     * 结案状态  0未结案  1对接成功结案  2对接失败结案
     */
    private String jazt;

    /**
     * 结案备注
     */
    private String jabz;

    /**
     * 揭榜人数限制
     */
    private Integer jbrs;

    /**
     * 难度等级设置  1:1星、2:2星、3:3星、4:4星、5:5星、6:超5星
     */
    private String nddj;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 审核时间
     */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime shsj;

    /**
     * 编码
     */
    private String bm;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String sczk;

    @TableField(exist = false)
    private List<Fjb> fjList;

    @TableField(exist = false)
    private List<User> jbrList;

    @TableField(exist = false)
    private List<Xmlx> XmlxList;
    /**
     * 开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime kssj;

    /**
     * 截止时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime jzsj;

    /**
     * 添加时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime tjsj;

    /**
     * 项目内容
     */
    @TableField(exist = false)
    private String xmnr;
    
}