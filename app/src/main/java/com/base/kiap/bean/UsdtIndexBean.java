package com.base.kiap.bean;

/**
 * @Author: June
 * @CreateDate: 12/28/20 5:56 PM
 * @Description: java类作用描述
 */
public class UsdtIndexBean {

    /**
     * id : 1
     * title : LV1
     * upAmount : 0
     * taskCount : 10
     * validTime : 365
     * amount : 52
     * profit : 520
     */

    private int todayAmount;
    private int todayCount;

    public int getTodayAmount() {
        return todayAmount;
    }

    public void setTodayAmount(int todayAmount) {
        this.todayAmount = todayAmount;
    }

    public int getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(int todayCount) {
        this.todayCount = todayCount;
    }
}
