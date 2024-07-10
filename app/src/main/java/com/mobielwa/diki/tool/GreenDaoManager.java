package com.mobielwa.diki.tool;

import android.content.Context;

import com.mobielwa.diki.APP;
import com.mobielwa.diki.bean.dao.MessageBean;
import com.mobielwa.diki.bean.dao.MessageBeanDao;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 2/25/21 4:40 PM
 * @Description: java类作用描述
 */
public class GreenDaoManager {
    private Context mContext;
    private MessageBeanDao mMessageBeanDao;

    public GreenDaoManager (Context context) {
        this.mContext = context;
        // 获取DAO实例
        mMessageBeanDao = APP.getDaoSession().getMessageBeanDao();
    }

    // 查询所有的数据
    public List<MessageBean> queryGoods () {
        return mMessageBeanDao.loadAll();
    }

    // 修改指定商品的商品信息
    public void updateMessageInfo (MessageBean model) {
        mMessageBeanDao.update(model);
    }

    // 删除指定商品
    public void deleteMessageInfo (MessageBean model) {
        mMessageBeanDao.deleteByKey(model.getPid());
    }

    // 添加所有的数据到数据库
    public void insertGoods (MessageBean entities) {
        mMessageBeanDao.insertOrReplace(entities);
    }

    // 添加所有的数据到数据库
    public void insertGoods (List<MessageBean> entities) {
        for (MessageBean entity : entities) {
            mMessageBeanDao.insertOrReplace(entity);
        }
    }
}
