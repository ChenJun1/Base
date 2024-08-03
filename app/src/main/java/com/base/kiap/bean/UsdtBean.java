package com.base.kiap.bean;

/**
 * @Author: June
 * @CreateDate: 1/13/21 6:11 PM
 * @Description: java类作用描述
 */
public class UsdtBean {

    private String qrcode;
    private String usdtAddress;
    private double usdExchangerate;
    private int times;

    public String getQrcode() {
        return qrcode;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getUsdtAddress() {
        return usdtAddress;
    }

    public void setUsdtAddress(String usdtAddress) {
        this.usdtAddress = usdtAddress;
    }

    public double getUsdExchangerate() {
        return usdExchangerate;
    }

    public void setUsdExchangerate(double usdExchangerate) {
        this.usdExchangerate = usdExchangerate;
    }
}
