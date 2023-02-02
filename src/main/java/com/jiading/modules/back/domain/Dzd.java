package com.jiading.modules.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 大字段表
 * @TableName t_dzd
 */
@TableName(value ="t_dzd")
@Data
@NoArgsConstructor
public class Dzd implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 大字段
     */
    private String vt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Dzd(String vt) {
        this.vt = vt;
    }
}