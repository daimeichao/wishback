package com.jiading.modules.back.domain;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 志愿者申请表
* @TableName t_zyz
*/
public class TZyz {

    /**
    * 表ID
    */
    @ApiModelProperty("表ID")
    private Integer pid;
    /**
    * 志愿者id
    */
    @ApiModelProperty("志愿者id")
    private Integer zyzid;
    /**
    * 志愿者姓名
    */
    @ApiModelProperty("志愿者姓名")
    @Length(max= 200,message="编码长度不能超过200")
    private String zyzname;
    /**
    * 申请时间
    */
    @ApiModelProperty("申请时间")
    private Date sqTime;
    /**
    * 申请原因
    */
    @ApiModelProperty("申请原因")
    @Length(max= 200,message="编码长度不能超过200")
    private String reason;
    /**
    * 志愿者审核状态 0：待审核 1：审核通过 2：审核不通过
    */
    @ApiModelProperty("志愿者审核状态 0：待审核 1：审核通过 2：审核不通过")
    @Length(max= 2,message="编码长度不能超过2")
    private String zyzAuditState;
    /**
    * 审核备注
    */
    @ApiModelProperty("审核备注")
    @Length(max= 200,message="编码长度不能超过200")
    private String zyzAuditRemark;
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
    * 志愿者id
    */
    private void setZyzid(Integer zyzid){
    this.zyzid = zyzid;
    }

    /**
    * 志愿者姓名
    */
    private void setZyzname(String zyzname){
    this.zyzname = zyzname;
    }

    /**
    * 申请时间
    */
    private void setSqTime(Date sqTime){
    this.sqTime = sqTime;
    }

    /**
    * 申请原因
    */
    private void setReason(String reason){
    this.reason = reason;
    }

    /**
    * 志愿者审核状态 0：待审核 1：审核通过 2：审核不通过
    */
    private void setZyzAuditState(String zyzAuditState){
    this.zyzAuditState = zyzAuditState;
    }

    /**
    * 审核备注
    */
    private void setZyzAuditRemark(String zyzAuditRemark){
    this.zyzAuditRemark = zyzAuditRemark;
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
    * 志愿者id
    */
    private Integer getZyzid(){
    return this.zyzid;
    }

    /**
    * 志愿者姓名
    */
    private String getZyzname(){
    return this.zyzname;
    }

    /**
    * 申请时间
    */
    private Date getSqTime(){
    return this.sqTime;
    }

    /**
    * 申请原因
    */
    private String getReason(){
    return this.reason;
    }

    /**
    * 志愿者审核状态 0：待审核 1：审核通过 2：审核不通过
    */
    private String getZyzAuditState(){
    return this.zyzAuditState;
    }

    /**
    * 审核备注
    */
    private String getZyzAuditRemark(){
    return this.zyzAuditRemark;
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
