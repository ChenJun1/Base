package com.base.kiap.bean.oldbean;

/**
 * @Author: June
 * @CreateDate: 1/5/21 5:39 PM
 * @Description: java类作用描述
 */
public class UsdtOrderBean {

    /**
     * imgUrl : https://idearn.oss-ap-south-1.aliyuncs.com/4274767fd98b4f04a20f0260ec1666e7.png
     * taskCount : 0
     * money : 2
     * prizeName : 2卢比
     * probability : 80
     * id : 1
     */

    private String imgUrl;
    private String usdtMoney;
    public String userId;
    public int vipId;
    public String whatsapp;
    public String usdtNote;

    public String getWhatsapp() {
        return whatsapp;
    }

    public String getUsdtNote() {
        return usdtNote;
    }

    public void setUsdtNote(String usdtNote) {
        this.usdtNote = usdtNote;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUsdtMoney() {
        return usdtMoney;
    }

    public void setUsdtMoney(String usdtMoney) {
        this.usdtMoney = usdtMoney;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }
}
