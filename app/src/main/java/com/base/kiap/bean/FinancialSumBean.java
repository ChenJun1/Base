package com.base.kiap.bean;

/**
 * @Author: June
 * @CreateDate: 3/16/21 3:48 PM
 * @Description: java类作用描述
 */
public class FinancialSumBean {
    private String assetsTotal;
    private String interestTotal;
    private String yesterdayInterest;

    public String getAssetsTotal() {
        return assetsTotal;
    }

    public void setAssetsTotal(String assetsTotal) {
        this.assetsTotal = assetsTotal;
    }

    public String getInterestTotal() {
        return interestTotal;
    }

    public void setInterestTotal(String interestTotal) {
        this.interestTotal = interestTotal;
    }

    public String getYesterdayInterest() {
        return yesterdayInterest;
    }

    public void setYesterdayInterest(String yesterdayInterest) {
        this.yesterdayInterest = yesterdayInterest;
    }
}
