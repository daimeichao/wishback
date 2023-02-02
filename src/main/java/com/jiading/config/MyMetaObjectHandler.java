package com.jiading.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("tjsj", LocalDateTime.now(), metaObject);
        this.setFieldValByName("xgsj", LocalDateTime.now(), metaObject);
        this.setFieldValByName("sczk", "0", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("xgsj", LocalDateTime.now(), metaObject);
    }
}
