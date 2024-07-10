package com.mobielwa.diki.bean;

/**
 * @Author: June
 * @CreateDate: 12/28/20 5:19 PM
 * @Description: java类作用描述
 */
public class TaskOrderBean {


    /**
     * id : 2
     * taskId : 2
     * operType : 1
     * userId : 3
     * money : 52
     * createTime : 2020-12-30 12:00:45
     * submitTime : null
     * examineTime : null
     * title : 第一次做
     * thumbnail : https://idearn.oss-ap-south-1.aliyuncs.com/4274767fd98b4f04a20f0260ec1666e7.png
     * demoUrl : null
     * downUrl :
     * content :
     */

    private int id;
    private int taskId;
    private int operType;
    private int userId;
    private float money;
    private String createTime;
    private String submitTime;
    private String examineTime;
    private String title;
    private String thumbnail;
    private String demoUrl;
    private String downUrl;
    private String content;
    private String contentImg;
    private String packgeName;
    private boolean isStart=false;

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public String getPackgeName() {
        return packgeName;
    }

    public void setPackgeName(String packgeName) {
        this.packgeName = packgeName;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getOperType() {
        return operType;
    }

    public void setOperType(int operType) {
        this.operType = operType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(String examineTime) {
        this.examineTime = examineTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
