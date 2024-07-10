package com.mobielwa.diki.bean;

/**
 * @Author: June
 * @CreateDate: 12/28/20 5:56 PM
 * @Description: java类作用描述
 */
public class MemberBean {

    /**
     * id : 1
     * title : LV1
     * upAmount : 0
     * taskCount : 10
     * validTime : 365
     * amount : 52
     * profit : 520
     */

    private int id;
    private String username;
    private int accountCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccountCount() {
        return accountCount;
    }

    public void setAccountCount(int accountCount) {
        this.accountCount = accountCount;
    }
}
