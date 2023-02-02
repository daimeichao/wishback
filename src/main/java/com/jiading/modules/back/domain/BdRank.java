package com.jiading.modules.back.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BdRank {
    private int type;
    private User user;
    private int count;
    private int yhid;
    private int tabType;
    private String schoolName = "";
    private String dw = "";
    private Integer size;

}
