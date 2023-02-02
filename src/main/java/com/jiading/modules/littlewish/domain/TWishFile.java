package com.jiading.modules.littlewish.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@TableName(value = "t_wish_file")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TWishFile {
    @TableId(type = IdType.AUTO)
    private Integer pid;


    /**
     * 实现表id
     */
    private Integer wishid;

    /**
     * 名称
     */
    private String name;


    /**
     * 地址
     */
    private String url;

    /**
     * 是否删除
     */
    @TableLogic(value = "0")
    private String del;

    /**
     * 类型
     */
    private String type;

}
