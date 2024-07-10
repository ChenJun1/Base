package com.mobielwa.diki.bean;

/**
 * @Author: June
 * @CreateDate: 12/28/20 5:56 PM
 * @Description: java类作用描述
 */
public class MemberIndexBean {

    private String todayProfit;
    private String totalProfit;
    private String depositProfit;
    private String username;
    private int id;
    private int number;
    private boolean pm;

    public String getTodayProfit() {
        return todayProfit;
    }

    public void setTodayProfit(String todayProfit) {
        this.todayProfit = todayProfit;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getDepositProfit() {
        return depositProfit;
    }

    public void setDepositProfit(String depositProfit) {
        this.depositProfit = depositProfit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isPm() {
        return pm;
    }

    public void setPm(boolean pm) {
        this.pm = pm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
