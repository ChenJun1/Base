package com.mobielwa.diki.bean;

/**
 * @Author: June
 * @CreateDate: 12/29/20 2:20 PM
 * @Description: java类作用描述
 */
public class RechargeRecordBean {

    /**
     * money : 1000
     * createTime : 2020-12-21 14:21:43
     * txMode : USDT
     * status : 0
     */

    private String createTime;
    private int moneyOk;
    private String title;
    private int status;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getMoneyOk() {
        return moneyOk;
    }

    public void setMoneyOk(int moneyOk) {
        this.moneyOk = moneyOk;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
