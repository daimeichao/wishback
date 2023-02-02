package com.jiading.common.pojo;

import java.util.Date;

/**
 *
 * @Descript TODO (用户消费实体)
 * @author Administrator
 * @date 2019年5月24日
 *
 */
public class UserConsumeDetailRecord {
    private int id;

    private int userId;

    private String userName;

    private String userTel;

    private String consumeTitle;

    private Date consumeDate;

    private Double consumeAmount;

    @Override
    public String toString() {
        return "UserConsumeDetailRecord [id=" + id + ", userId=" + userId + ", userName=" + userName + ", userTel="
                + userTel + ", consumeTitle=" + consumeTitle + ", consumeDate=" + consumeDate + ", consumeAmount="
                + consumeAmount + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getConsumeTitle() {
        return consumeTitle;
    }

    public void setConsumeTitle(String consumeTitle) {
        this.consumeTitle = consumeTitle;
    }

    public Date getConsumeDate() {
        return consumeDate;
    }

    public void setConsumeDate(Date consumeDate) {
        this.consumeDate = consumeDate;
    }

    public Double getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Double consumeAmount) {
        this.consumeAmount = consumeAmount;
    }
}
