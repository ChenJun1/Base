package com.base.kiap.config;

import com.base.kiap.bean.base.BaseUserBean;
import com.base.kiap.bean.base.BaseUserInfoBean;
import com.base.kiap.bean.oldbean.ConfigBean;
import com.base.kiap.bean.oldbean.UserBean;

import static com.base.kiap.utlis.SPUtils.*;

/**
 * @Author: June
 * @CreateDate: 12/28/20 2:23 PM
 * @Description: java类作用描述
 */
public class UserHelp {
    public static boolean isLogin() {
        String id = (String) get(SpCode.USERID, "");
        return id.isEmpty();
    }

    public static String getUserId() {
        return (String) get(SpCode.USERID, "");
    }

    public static String getUserName() {
        return (String) get(SpCode.USERNAME, "");
    }

    public static long getBalance() {
        return (long) get(SpCode.USERNLANCE, 0L);
    }

    public static String getPhone() {
        return (String) get(SpCode.USERPHONE, "");
    }

    public static int getLevel() {
        return (int) get(SpCode.LEVEL, 0);
    }
    public static String getToken() {
        return (String) get(SpCode.TOKEN, "");
    }

    public static String getMail() {
        return (String) get(SpCode.MAIL, "");
    }
    public static long getRsBalance() {
        return (long) get(SpCode.RSBALANCE, 0L);
    }

    public static String getTelegram() {
        return (String) get(SpCode.TELEGRAM, "");
    }

    public static String getInviteCode() {
        return (String) get(SpCode.INVITECODE, "");
    }

    public static int getCountryCode(){
        return (int) get(SpCode.COUNTRYCODE, 0);
    }

    public static int getCredit(){
        return (int) get(SpCode.CREDIT, 0);
    }

    public static String getPayPassword() {
        return (String) get(SpCode.PAYPASSWORD, "");
    }

    public static int getSurplusTask() {
        return (int) get(SpCode.SURPLUSTASK, 0);
    }

    public static String getTaskIncome() {
        return (String)get(SpCode.MEBTASKINCOME, "0");
    }

    public static void setBalance(long balance) {
        put(SpCode.USERNLANCE, balance);
    }

    public static void setPhone(String phone) {
        put(SpCode.USERPHONE, phone);
    }

    public static void setPayPassword(String payPassword) {
        put(SpCode.PAYPASSWORD, payPassword);
    }

    public static void setSurplusTask(int surplusTask) {
        put(SpCode.SURPLUSTASK, surplusTask);
    }

    public static boolean isNovice() {
        return (boolean) get(SpCode.NOVICE, false);
    }

    public static int getSTAT() {
        return (int) get(SpCode.STAT, 1);
    }


    /**
     * 更新用户信息
     */
    public static void updateUser(UserBean bean) {
        put(SpCode.USERID, bean.getId()+"");
        put(SpCode.INVITATIONOK, bean.getInvitationOk());
        put(SpCode.USERNAME, bean.getUsername());
        put(SpCode.USERNLANCE, bean.getBalance());
        put(SpCode.USERPHONE, bean.getPhone());
        put(SpCode.LEVEL, bean.getLevel());
        put(SpCode.INVITECODE, bean.getInviteCode());
        put(SpCode.PAYPASSWORD, bean.getPayPassword());
        put(SpCode.QRCODE, bean.getQrCode());
        put(SpCode.TODAYCOUNT, bean.getTodayCount());
        put(SpCode.FRIENDCOUNT, bean.getFriendCount());
        put(SpCode.FRIENDMONEY, bean.getFriendMoney());
        put(SpCode.STAT, bean.getStat());
        put(SpCode.COUNTRYCODE, bean.getCountryCode());
    }

    /**
     * 更新用户信息
     */
    public static void updateBaseUser(BaseUserBean bean) {
        put(SpCode.UID, bean.uid);
        put(SpCode.USERPHONE, bean.phone);
        put(SpCode.MAIL, bean.mail);
        put(SpCode.RSBALANCE, bean.rsBalance);
        put(SpCode.INVITECODE, bean.invitationCode);
        put(SpCode.TELEGRAM, bean.telegram);
    }

    /**
     * 更新用户信息
     */
    public static void updateBaseUser(BaseUserInfoBean bean) {
        put(SpCode.UID, bean.uid);
        put(SpCode.USERID, bean.userId + "");
        put(SpCode.USERPHONE, bean.phone);
        put(SpCode.MAIL, bean.email);
        put(SpCode.RSBALANCE, bean.balance);
        put(SpCode.TELEGRAM, bean.telegram);
    }

    public static void updateToken(String token){
        put(SpCode.TOKEN, token);
    }

    /**
     * 更新系统配置信息
     */
    public static void updateConfig(ConfigBean bean) {
        put(SpCode.DOMAINURL, bean.getDomainUrl());

    }
}
