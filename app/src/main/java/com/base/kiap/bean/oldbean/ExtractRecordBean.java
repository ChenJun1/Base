package com.base.kiap.bean.oldbean;

/**
 * @Author: June
 * @CreateDate: 12/29/20 2:20 PM
 * @Description: java类作用描述
 */
public class ExtractRecordBean {

    /**
     * money : 1000
     * createTime : 2020-12-21 14:21:43
     * txMode : USDT
     * status : 0
     */

    private double money;
    private String createTime;
    private String txMode;
    private int status;
    private int failureStatus;
    private String remark;

    public int getFailureStatus() {
        return failureStatus;
    }

    public void setFailureStatus(int failureStatus) {
        this.failureStatus = failureStatus;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTxMode() {
        return txMode;
    }

    public void setTxMode(String txMode) {
        this.txMode = txMode;
    }

    public int getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
