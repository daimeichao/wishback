package com.jiading.common.pojo;

import java.util.Date;

/**
 *
 * @Descript TODO (办件登记实体)
 * @author Administrator
 * @date 2019年5月24日
 *
 */
public class BjdjInfo {
    private String XH;

    private String BJID;

    private String SQBM;

    private String SPBM;

    private String SQSX;

    private String TSTAMP;

    private String SQR;

    private String LXDH;

    private String SJR;

    private String SLBM;

    private String BLQK;

    private String BJRQ;

    private String LQQK;

    private String BZ;

    public String getSPBM() {
        return SPBM;
    }

    public String getBJID() {
        return BJID;
    }

    public String getXH() {
        return XH;
    }

    public String getSQBM() {
        return SQBM;
    }

    public String getSQSX() {
        return SQSX;
    }

    public String getTSTAMP() {
        return TSTAMP;
    }

    public String getSQR() {
        return SQR;
    }

    public String getLXDH() {
        return LXDH;
    }

    public String getSJR() {
        return SJR;
    }

    public String getSLBM() {
        return SLBM;
    }

    public String getBLQK() {
        return BLQK;
    }

    public String getBJRQ() {
        return BJRQ;
    }

    public String getLQQK() {
        return LQQK;
    }

    public String getBZ() {
        return BZ;
    }

    @Override
    public String toString() {
        return "BjdjInfo [XH=" + XH + ", SQBM=" + SQBM + ", SQSX=" + SQSX + ", TSTAMP="
                + TSTAMP + ", SQR=" + SQR + ", LXDH=" + LXDH + ", SJR="
                + SJR + ", SLBM=" + SLBM + ", BLQK=" + BLQK + ", BJRQ=" + BJRQ + ", LQQK=" + LQQK + ", BZ=" + BZ + "]";
    }

    public void setXH(String XH) {
        this.XH = XH;
    }

    public void setSQBM(String SQBM) {
        this.SQBM = SQBM;
    }

    public void setSQSX(String SQSX) {
        this.SQSX = SQSX;
    }

    public void setTSTAMP(String TSTAMP) {
        this.TSTAMP = TSTAMP;
    }

    public void setSQR(String SQR) {
        this.SQR = SQR;
    }

    public void setLXDH(String LXDH) {
        this.LXDH = LXDH;
    }

    public void setSJR(String SJR) {
        this.SJR = SJR;
    }

    public void setSLBM(String SLBM) {
        this.SLBM = SLBM;
    }

    public void setBLQK(String BLQK) {
        this.BLQK = BLQK;
    }

    public void setBJRQ(String BJRQ) {
        this.BJRQ = BJRQ;
    }

    public void setLQQK(String LQQK) {
        this.LQQK = LQQK;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    public void setBJID(String BJID) {
        this.BJID = BJID;
    }

    public void setSPBM(String SPBM) {
        this.SPBM = SPBM;
    }
}
