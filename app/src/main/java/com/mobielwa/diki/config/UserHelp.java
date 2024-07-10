package com.mobielwa.diki.config;

import com.mobielwa.diki.bean.ConfigBean;
import com.mobielwa.diki.bean.MemberBean;
import com.mobielwa.diki.bean.UserBean;
import com.mobielwa.diki.utlis.NLog;
import com.mobielwa.diki.utlis.SPUtils;
import com.mobielwa.diki.utlis.StringFormatUtils;

import java.util.List;

import static com.mobielwa.diki.utlis.SPUtils.*;

/**
 * @Author: June
 * @CreateDate: 12/28/20 2:23 PM
 * @Description: java类作用描述
 */
public class UserHelp {
    public static boolean isLogin() {
        int id = (int) get(SpCode.USERID, 0);
        return id > 0;
    }

    public static int getUserId() {
        return (int) get(SpCode.USERID, 0);
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
        put(SpCode.USERID, bean.getId());
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
