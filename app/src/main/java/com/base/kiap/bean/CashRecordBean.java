package com.base.kiap.bean;

/**
 * @Author: June
 * @CreateDate: 12/29/20 3:03 PM
 * @Description: java类作用描述
 */
public class CashRecordBean {

    /**
     * money : 1000
     * createTime : 2020-12-29 14:21:33
     * tag : 1
     * title : 大转盘
     * tagName : 任务
     */

    private String money;
    private String createTime;
    private int tag;
    private String title;
    private String tagName;
    private int isflag;//1是收入 2是支出

    public int getIsflag() {
        return isflag;
    }

    public void setIsflag(int isflag) {
        this.isflag = isflag;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
