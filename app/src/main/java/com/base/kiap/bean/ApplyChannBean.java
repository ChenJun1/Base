package com.base.kiap.bean;

/**
 * @Author: June
 * @CreateDate: 1/14/21 1:34 PM
 * @Description: java类作用描述
 */
public class ApplyChannBean {
    private int id;
    private String customName;
    private String payName;
    private String fieldStr;
    private int logoView;
    private String currency;
    private String currencyCode;
    private int helps;
    private int channel;

    public int getLogoView() {
        return logoView;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setLogoView(int logoView) {
        this.logoView = logoView;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getFieldStr() {
        return fieldStr;
    }

    public int getHelps() {
        return helps;
    }

    public void setHelps(int helps) {
        this.helps = helps;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public void setFieldStr(String fieldStr) {
        this.fieldStr = fieldStr;
    }
}
