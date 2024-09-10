package com.base.kiap.bean.oldbean;

/**
 * @Author: June
 * @CreateDate: 1/4/21 11:40 AM
 * @Description: java类作用描述
 */
public class ConfigBean {

    /**
     * activeCount : 32434
     * taskTitle : 热门任务
     * turntablePrice : 5
     * newbieExamine : 1
     * withdrawalLevel : 100,500,1000
     * rechargeLevel : null
     */

    private String customerService;
    private int appVersion;
    private String appDownAddr;
    private String updateContent;
    private String shareUrl;
    private String domainUrl;
    private String telegram;

    public String getCustomerService() {
        return customerService;
    }

    public void setCustomerService(String customerService) {
        this.customerService = customerService;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppDownAddr() {
        return appDownAddr;
    }

    public void setAppDownAddr(String appDownAddr) {
        this.appDownAddr = appDownAddr;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getDomainUrl() {
        return domainUrl;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }
}
