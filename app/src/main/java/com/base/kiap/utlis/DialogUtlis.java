package com.base.kiap.utlis;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.bean.DialogBean;
import com.base.kiap.listen.onItemClickListen;

import androidx.appcompat.app.AlertDialog;

/**
 * @Author: June
 * @CreateDate: 1/7/21 10:27 AM
 * @Description: java类作用描述
 */
public class DialogUtlis {

    /**
     * 通用
     */
    public static void showTaskDialog(Activity activity, DialogBean bean, onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_01, null);
        Button commit= dialogView.findViewById(R.id.bt_commit);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        commit.setText(bean.btntext);

        commit.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            if (onClick!=null) {
                onClick.onItemClick(v);
            }
        });
        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> mAlertDialog.dismiss());
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = mDialogWindow.getAttributes();
        attributes.width = CommUtils.dp2px(activity, 250f);
        attributes.height = CommUtils.dp2px(activity, 280f);
        mDialogWindow.setAttributes(attributes);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

    /**
     * 通用
     */
    public static void showTaskDialogExtract2(Activity activity, DialogBean bean, onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_08, null);
        Button commit= dialogView.findViewById(R.id.bt_commit);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        commit.setText(bean.btntext);

        commit.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            if (onClick!=null) {
                onClick.onItemClick(v);
            }
        });
        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> mAlertDialog.dismiss());
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = mDialogWindow.getAttributes();
        attributes.width = CommUtils.dp2px(activity, 250f);
        attributes.height = CommUtils.dp2px(activity, 330f);
        mDialogWindow.setAttributes(attributes);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

    /**
     * 失败订单
     */
    public static void showTaskDialogTask4(Activity activity, DialogBean bean, onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_01, null);
        Button commit= dialogView.findViewById(R.id.bt_commit);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        commit.setText(bean.btntext);

        commit.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            if (onClick!=null) {
                onClick.onItemClick(v);
            }
        });
        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> mAlertDialog.dismiss());
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = mDialogWindow.getAttributes();
        attributes.width = CommUtils.dp2px(activity, 250f);
        attributes.height = CommUtils.dp2px(activity, 280f);
        mDialogWindow.setAttributes(attributes);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

    /**
     * 人
     */
    public static void showTaskDialogP(Activity activity,DialogBean bean ,onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_03, null);
        Button commit= dialogView.findViewById(R.id.bt_commit);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        commit.setText(bean.btntext);

        commit.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            onClick.onItemClick(v);
        });
        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> {
            onClick.onClose();
            mAlertDialog.dismiss();
        });
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = mDialogWindow.getAttributes();
        attributes.width = CommUtils.dp2px(activity, 250f);
        attributes.height = CommUtils.dp2px(activity, 420f);
        mDialogWindow.setAttributes(attributes);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

    /**
     * 提现申请
     */
    public static void showTaskDialogPExtract(Activity activity,DialogBean bean ,onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_010, null);
        Button commit= dialogView.findViewById(R.id.bt_commit);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        commit.setText(bean.btntext);

        commit.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            onClick.onItemClick(v);
        });
        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> {
            onClick.onClose();
            mAlertDialog.dismiss();
        });
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = mDialogWindow.getAttributes();
        attributes.width = CommUtils.dp2px(activity, 250f);
        attributes.height = CommUtils.dp2px(activity, 460f);
        mDialogWindow.setAttributes(attributes);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

    /**
     * 大转盘 弹窗
     */
    public static void showTaskDialogPAddMoney(Activity activity,DialogBean bean ,onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_04, null);
        Button commit= dialogView.findViewById(R.id.bt_commit);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        commit.setText(bean.btntext);

        commit.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            onClick.onItemClick(v);
        });
        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> {
            onClick.onClose();
            mAlertDialog.dismiss();
        });
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = mDialogWindow.getAttributes();
        attributes.width = CommUtils.dp2px(activity, 250f);
        attributes.height = CommUtils.dp2px(activity, 420f);
        mDialogWindow.setAttributes(attributes);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

    /**
     * 首页新人弹窗
     */
    public static void showTaskDialogPNewUser(Activity activity,DialogBean bean ,onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_05, null);
        Button commit= dialogView.findViewById(R.id.bt_commit);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView title2= dialogView.findViewById(R.id.tv_title_lve);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        commit.setText(bean.btntext);
        title2.setText(bean.title2);

        commit.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            onClick.onItemClick(v);
        });
        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> {
            onClick.onClose();
            mAlertDialog.dismiss();
        });
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = mDialogWindow.getAttributes();
        attributes.width = CommUtils.dp2px(activity, 250f);
        attributes.height = CommUtils.dp2px(activity, 420f);
        mDialogWindow.setAttributes(attributes);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

    /**
     * 余额升级升级提示
     */
    public static void showTaskDialogPUp_1(Activity activity,DialogBean bean ,onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_06, null);
        Button commit= dialogView.findViewById(R.id.bt_commit);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        commit.setText(bean.btntext);

        commit.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            onClick.onItemClick(v);
        });
        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> {
            onClick.onClose();
            mAlertDialog.dismiss();
        });
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = mDialogWindow.getAttributes();
        attributes.width = CommUtils.dp2px(activity, 320f);
        attributes.height = CommUtils.dp2px(activity, 400f);
        mDialogWindow.setAttributes(attributes);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }


    /**
     * 二维码弹窗
     */
    public static void showShareDialog(Activity activity) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        WindowManager.LayoutParams layoutParams = mAlertDialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.5f; // 调整这个值改变背景变暗程度，范围 0.0f 到 1.0f
        mAlertDialog.getWindow().setAttributes(layoutParams);
        mAlertDialog.show();

        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_09, null);
        mAlertDialog.setContentView(dialogView);
    }

    /**
     * 提交任务前
     */
    public static void showTaskDialogTis(Activity activity, DialogBean bean, onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_11, null);
        Button commit= dialogView.findViewById(R.id.bt_commit);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        commit.setText(bean.btntext);

        commit.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            if (onClick!=null) {
                onClick.onItemClick(v);
            }
        });
        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> mAlertDialog.dismiss());
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = mDialogWindow.getAttributes();
        attributes.width = CommUtils.dp2px(activity, 250f);
        attributes.height = CommUtils.dp2px(activity, 300f);
        mDialogWindow.setAttributes(attributes);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

    /**
     * 内容自适应高度
     */
    public static void showTaskDialogTis12(Activity activity, DialogBean bean, onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_12, null);
        Button commit= dialogView.findViewById(R.id.bt_commit);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        commit.setText(bean.btntext);

        commit.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            if (onClick!=null) {
                onClick.onItemClick(v);
            }
        });
        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> mAlertDialog.dismiss());
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

    /**
     * 内容自适应高度 两个按钮
     */
    public static void showTaskDialogTis13(Activity activity, DialogBean bean, onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_13, null);
        Button yes= dialogView.findViewById(R.id.bt_yes);
        Button no= dialogView.findViewById(R.id.bt_no);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);

        yes.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            if (onClick!=null) {
                onClick.onItemClick(v);
            }
        });
        no.setOnClickListener(v -> {
            mAlertDialog.dismiss();
        });

        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> mAlertDialog.dismiss());
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

    /**
     * 内容自适应高度 两个按钮
     */
    public static void showTaskDialogTis14(Activity activity, DialogBean bean, onItemClickListen onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_common_14, null);
        Button yes= dialogView.findViewById(R.id.bt_yes);
        Button no= dialogView.findViewById(R.id.bt_no);
        TextView title= dialogView.findViewById(R.id.tv_title);
        TextView content= dialogView.findViewById(R.id.tv_content);
        title.setText(bean.title);
        content.setText(bean.content);
        yes.setText(bean.btntext);
        no.setText(bean.btntext2);

        yes.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            if (onClick!=null) {
                onClick.onItemClick(v);
            }
        });
        no.setOnClickListener(v -> {
            mAlertDialog.dismiss();
            if (onClick!=null) {
                onClick.onClose();
            }
        });

        dialogView.findViewById(R.id.iv_close).setOnClickListener(v -> mAlertDialog.dismiss());
        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);
    }

}
