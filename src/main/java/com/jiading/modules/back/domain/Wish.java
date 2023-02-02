package com.jiading.modules.back.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@TableName(value ="t_wish")
@Data
@NoArgsConstructor
public class Wish implements Serializable {
    /**
     *   表ID
    * */
    @TableId(type = IdType.AUTO)
    private int pid;
    /**
     *  许愿人名称
     * */
     private String  wishusername;
    /**
     *  许愿人id
     * */
    private  int wishuserid;
    /**
     *   许愿地点
     * */
     private  String adder;
    /**
     *许愿时间
     * */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime  wishTime;
//     private Date wishTime;
    /**
     * 心愿审核状态 0：待审核 1：审核通过 2：审核不通过
     */
      private  String  wishAuditState;
    /**
     * 心愿审核备注
     * */
     private  String wishAuditRemark;
    /**
     * 心愿审核人id
     * */
     private  int wishAuditid;
    /**
     * 心愿状态 0：待认领 1：已认领 2：已完成
     */
    private  String wishState;
    /**
     *  操作人id
     * */
    private  int    operatorid;
    /**
     *    添加时间
     * */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime  addTime;
//    private Date addTime;
    /**
     * 修改时间
     * */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime updateTime;
//    private Date   updateTime;
    /**
     * 删除状况 0未删除 1已删除
     */
    @TableLogic(value = "0")
    private String del;
    /**
     *  排序
     * */
    private  int    sort;

}


