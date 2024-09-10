package com.base.kiap.bean.oldbean;

/**
 * @Author: June
 * @CreateDate: 12/28/20 1:55 PM
 * @Description: java类作用描述
 */
public class UserBean {

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

    private int id;
    private String phone="";
    private String username="";
    private String payPassword="";
    private long balance=0;
    private int level=1;
    private String inviteCode="";
    private int stat=0;
    private String createTime="";
    private int parentId=0;
    private String qrCode = "";
    private String todayCount = "";
    private String friendCount = "";
    private String friendMoney = "";
    private String token="";
    private int vip=0;

    private String taskCommissionsToday;
    private String taskCommissionsAll;
    private String commissionsToday;
    private String commissionsTotal;
    private int countryCode;
    private String telegram;

    private String balanceStr;
    private String balanceInrStr;
    private String qcCode;


    /**
     * 有效邀请
     */
    private int invitationOk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getBalanceStr() {
        return balanceStr;
    }

    public void setBalanceStr(String balanceStr) {
        this.balanceStr = balanceStr;
    }

    public String getBalanceInrStr() {
        return balanceInrStr;
    }

    public void setBalanceInrStr(String balanceInrStr) {
        this.balanceInrStr = balanceInrStr;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(String todayCount) {
        this.todayCount = todayCount;
    }

    public String getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(String friendCount) {
        this.friendCount = friendCount;
    }

    public String getFriendMoney() {
        return friendMoney;
    }

    public void setFriendMoney(String friendMoney) {
        this.friendMoney = friendMoney;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getTaskCommissionsToday() {
        return taskCommissionsToday;
    }

    public void setTaskCommissionsToday(String taskCommissionsToday) {
        this.taskCommissionsToday = taskCommissionsToday;
    }

    public String getTaskCommissionsAll() {
        return taskCommissionsAll;
    }

    public void setTaskCommissionsAll(String taskCommissionsAll) {
        this.taskCommissionsAll = taskCommissionsAll;
    }

    public String getCommissionsToday() {
        return commissionsToday;
    }

    public void setCommissionsToday(String commissionsToday) {
        this.commissionsToday = commissionsToday;
    }

    public String getCommissionsTotal() {
        return commissionsTotal;
    }

    public void setCommissionsTotal(String commissionsTotal) {
        this.commissionsTotal = commissionsTotal;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public int getInvitationOk() {
        return invitationOk;
    }

    public String getQcCode() {
        return qcCode;
    }

    public void setQcCode(String qcCode) {
        this.qcCode = qcCode;
    }

    public void setInvitationOk(int invitationOk) {
        this.invitationOk = invitationOk;
    }
}
