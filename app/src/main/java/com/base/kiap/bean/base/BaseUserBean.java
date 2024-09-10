package com.base.kiap.bean.base;

/**
 * @Author: June
 * @CreateDate: 12/28/20 1:55 PM
 * @Description: java类作用描述
 */
public class BaseUserBean {

    private String uid;
    private String phone;
    private String mail;
    private int rsBalance;
    private String invitationCode;
    private String telegram;
    private String token;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getRsBalance() {
        return rsBalance;
    }

    public void setRsBalance(int rsBalance) {
        this.rsBalance = rsBalance;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
