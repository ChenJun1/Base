package com.base.kiap.bean;

/**
 * @Author: June
 * @CreateDate: 12/28/20 1:55 PM
 * @Description: java类作用描述
 */
public class IndexBean {

    /**
     * id : 2
     * phone : 1999999999
     * username : null
     * payPassword : null
     * balance : null
     * level : 1
     * inviteCode : 2127011
     * stat : 1
     * createTime : 2020-12-28T05:53:59.014+0000
     * parentId : 0
     */

    private String kucoinPrice;
    private String wazirPrice;
    private String binancePrice;
    private int usdtPrice;
    private String balance;
    private String balance1;


    public String getKucoinPrice() {
        return kucoinPrice;
    }

    public void setKucoinPrice(String kucoinPrice) {
        this.kucoinPrice = kucoinPrice;
    }

    public String getWazirPrice() {
        return wazirPrice;
    }

    public void setWazirPrice(String wazirPrice) {
        this.wazirPrice = wazirPrice;
    }

    public String getBinancePrice() {
        return binancePrice;
    }

    public void setBinancePrice(String binancePrice) {
        this.binancePrice = binancePrice;
    }

    public int getUsdtPrice() {
        return usdtPrice;
    }

    public void setUsdtPrice(int usdtPrice) {
        this.usdtPrice = usdtPrice;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalance1() {
        return balance1;
    }

    public void setBalance1(String balance1) {
        this.balance1 = balance1;
    }
}
