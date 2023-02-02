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
//实现心愿附件表
@TableName(value ="t_wish_file")
@Data
@NoArgsConstructor
public class WishFile implements Serializable {
    /**
     *   表ID
     * */
    @TableId(type = IdType.AUTO)
    private int pid;
    private int  wishid;

    private String name;
    private String  url;
    /**
     *    添加时间
     * */
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime addTime;
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
    //附件类型
    private String   type;

}
