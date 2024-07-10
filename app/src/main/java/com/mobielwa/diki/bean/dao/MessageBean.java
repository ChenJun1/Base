package com.mobielwa.diki.bean.dao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author: June
 * @CreateDate: 2/25/21 4:29 PM
 * @Description: java类作用描述
 */
@Entity
public class MessageBean implements Parcelable {

    @Id(autoincrement = true)
    private Long pid;

    @Index(unique = true)
    private Integer id;

    private String title;

    private String content;

    private String createTime;

    private int isRead=0;//1 已读 0未读

    @Generated(hash = 1537310349)
    public MessageBean(Long pid, Integer id, String title, String content,
            String createTime, int isRead) {
        this.pid = pid;
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.isRead = isRead;
    }

    @Generated(hash = 1588632019)
    public MessageBean() {
    }

    public Long getPid() {
        return this.pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getIsRead() {
        return this.isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.pid);
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.createTime);
        dest.writeInt(this.isRead);
    }

    protected MessageBean(Parcel in) {
        this.pid = (Long) in.readValue(Long.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.content = in.readString();
        this.createTime = in.readString();
        this.isRead = in.readInt();
    }

    public static final Parcelable.Creator<MessageBean> CREATOR = new Parcelable.Creator<MessageBean>() {
        @Override
        public MessageBean createFromParcel(Parcel source) {
            return new MessageBean(source);
        }

        @Override
        public MessageBean[] newArray(int size) {
            return new MessageBean[size];
        }
    };
}
