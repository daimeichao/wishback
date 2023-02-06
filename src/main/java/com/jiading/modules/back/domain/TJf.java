package com.jiading.modules.back.domain;

import java.util.Date;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 积分表
* @TableName t_jf
*/
public class TJf {

    /**
    * 表ID
    */
    @ApiModelProperty("表ID")
    private Integer pid;
    /**
    * 用户id
    */
    @ApiModelProperty("用户id")
    private Integer userid;
    /**
    * 积分加减 0加 1减
    */
    @ApiModelProperty("积分加减 0加 1减")
    @Length(max= 2,message="编码长度不能超过2")
    private String change;
    /**
    * 积分变化原因
    */
    @ApiModelProperty("积分变化原因")
    @Length(max= 200,message="编码长度不能超过200")
    private String remark;
    /**
    * 积分数量
    */
    @ApiModelProperty("积分数量")
    private Integer score;
    /**
    * 积分审核状态 0：待审核 1：审核通过 2：审核不通过
    */
    @ApiModelProperty("积分审核状态 0：待审核 1：审核通过 2：审核不通过")
    @Length(max= 2,message="编码长度不能超过2")
    private String jfAuditState;
    /**
    * 审核备注
    */
    @ApiModelProperty("审核备注")
    @Length(max= 200,message="编码长度不能超过200")
    private String jfAuditRemark;
    /**
    * 审核人id
    */
    @ApiModelProperty("审核人id")
    private Integer zyzAuditid;
    /**
    * 操作人id
    */
    @ApiModelProperty("操作人id")
    private Integer operatorid;
    /**
    * 添加时间
    */
    @ApiModelProperty("添加时间")
    private Date addTime;
    /**
    * 修改时间
    */
    @ApiModelProperty("修改时间")
    private Date updateTime;
    /**
    * 是否删除 0未删除 1已删除
    */
    @ApiModelProperty("是否删除 0未删除 1已删除")
    @Length(max= 2,message="编码长度不能超过2")
    private String del;

    /**
    * 表ID
    */
    private void setPid(Integer pid){
    this.pid = pid;
    }

    /**
    * 用户id
    */
    private void setUserid(Integer userid){
    this.userid = userid;
    }

    /**
    * 积分加减 0加 1减
    */
    private void setChange(String change){
    this.change = change;
    }

    /**
    * 积分变化原因
    */
    private void setRemark(String remark){
    this.remark = remark;
    }

    /**
    * 积分数量
    */
    private void setScore(Integer score){
    this.score = score;
    }

    /**
    * 积分审核状态 0：待审核 1：审核通过 2：审核不通过
    */
    private void setJfAuditState(String jfAuditState){
    this.jfAuditState = jfAuditState;
    }

    /**
    * 审核备注
    */
    private void setJfAuditRemark(String jfAuditRemark){
    this.jfAuditRemark = jfAuditRemark;
    }

    /**
    * 审核人id
    */
    private void setZyzAuditid(Integer zyzAuditid){
    this.zyzAuditid = zyzAuditid;
    }

    /**
    * 操作人id
    */
    private void setOperatorid(Integer operatorid){
    this.operatorid = operatorid;
    }

    /**
    * 添加时间
    */
    private void setAddTime(Date addTime){
    this.addTime = addTime;
    }

    /**
    * 修改时间
    */
    private void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
    }

    /**
    * 是否删除 0未删除 1已删除
    */
    private void setDel(String del){
    this.del = del;
    }


    /**
    * 表ID
    */
    private Integer getPid(){
    return this.pid;
    }

    /**
    * 用户id
    */
    private Integer getUserid(){
    return this.userid;
    }

    /**
    * 积分加减 0加 1减
    */
    private String getChange(){
    return this.change;
    }

    /**
    * 积分变化原因
    */
    private String getRemark(){
    return this.remark;
    }

    /**
    * 积分数量
    */
    private Integer getScore(){
    return this.score;
    }

    /**
    * 积分审核状态 0：待审核 1：审核通过 2：审核不通过
    */
    private String getJfAuditState(){
    return this.jfAuditState;
    }

    /**
    * 审核备注
    */
    private String getJfAuditRemark(){
    return this.jfAuditRemark;
    }

    /**
    * 审核人id
    */
    private Integer getZyzAuditid(){
    return this.zyzAuditid;
    }

    /**
    * 操作人id
    */
    private Integer getOperatorid(){
    return this.operatorid;
    }

    /**
    * 添加时间
    */
    private Date getAddTime(){
    return this.addTime;
    }

    /**
    * 修改时间
    */
    private Date getUpdateTime(){
    return this.updateTime;
    }

    /**
    * 是否删除 0未删除 1已删除
    */
    private String getDel(){
    return this.del;
    }

}
