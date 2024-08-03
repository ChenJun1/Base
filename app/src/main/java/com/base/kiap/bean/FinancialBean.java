package com.base.kiap.bean;

/**
 * @Author: June
 * @CreateDate: 3/16/21 10:57 AM
 * @Description: java类作用描述
 */
public class FinancialBean {
    private int id;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 日利率（千分比，需要转化百分比显示）
     */
    private double dayRate;
    /**
     * 期限（天）
     */
    private int term;
    /**
     * 最低限额
     */
    private int minMoney;
    /**
     * 最高限额
     */
    private int maxMoney;
    /**
     * 状态1有效，2无效
     */
    private int stat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getDayRate() {
        return dayRate;
    }

    public void setDayRate(double dayRate) {
        this.dayRate = dayRate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public int getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(int maxMoney) {
        this.maxMoney = maxMoney;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}
