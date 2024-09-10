package com.base.kiap.bean.oldbean;

/**
 * @Author: June
 * @CreateDate: 3/16/21 10:57 AM
 * @Description: java类作用描述
 */
public class FinancialOrderBean {
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 理财产品ID
     */
    private int productId;
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
     * 购买的金额
     */
    private int money;
    /**
     * 购买时间
     */
    private String createTime;
    /**
     * 到期日期
     */
    private String termDate;
    /**
     * 已经获得利息
     */
    private int getMoney;
    /**
     * 状态:0生效中,1已到期,2已提现
     */
    private int stat;

    private int TotalRevenue;

    public int getTotalRevenue() {
        return TotalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        TotalRevenue = totalRevenue;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate;
    }

    public int getGetMoney() {
        return getMoney;
    }

    public void setGetMoney(int getMoney) {
        this.getMoney = getMoney;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}
