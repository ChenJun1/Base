package com.mobielwa.diki.utlis;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.mobielwa.diki.R;
import com.mobielwa.diki.activity.WebActivity;
import com.mobielwa.diki.bean.event.TaskStartdownPic;
import com.mobielwa.diki.config.AppConfig;
import com.mobielwa.diki.config.Constants;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.widget.WhiteProgressDialog;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Author: June
 * @CreateDate: 2020/11/4 11:41 AM
 * @Description: java类作用描述
 */
public class ShareUtils {


    /**
     * 分享facebook 文本
     */
    public static void shareCommonFacebook(String content) {
        Intent intent, intent1;
        PackageManager packageManager = AppConfig.INSTANCE.getApplication().getPackageManager();
        intent = packageManager.getLaunchIntentForPackage(Constants.FacebookPackageName);
        intent1 = packageManager.getLaunchIntentForPackage(Constants.FacebookLitePackageName);
        if (intent == null && intent1 == null) {
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            // 打开url
            Uri content_url = Uri.parse(Constants.FacebookDownloadUrl);
            intent.setData(content_url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
            EventBus.getDefault().post(new TaskStartdownPic());
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        } else {
            String tempPacakgeName = "";
            if (intent1 != null)
                tempPacakgeName = Constants.FacebookLitePackageName;
            if (intent != null)
                tempPacakgeName = Constants.FacebookPackageName;
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setPackage(tempPacakgeName);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
            intent.putExtra(Intent.EXTRA_TEXT, content);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
            EventBus.getDefault().post(new TaskStartdownPic());
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        }
    }

    /**
     * 分享whatsapp 文本
     */
    public static void shareWhat(String content) {
        Intent intent;
        PackageManager packageManager = AppConfig.INSTANCE.getApplication().getPackageManager();
        intent = packageManager.getLaunchIntentForPackage(Constants.WhatsAppPackageName);
        if (intent == null) {
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            // 打开url
            Uri content_url = Uri.parse(Constants.WhatsAppDownloadUrl);
            intent.setData(content_url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        } else {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setPackage(Constants.WhatsAppPackageName);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
            intent.putExtra(Intent.EXTRA_TEXT, content);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        }
    }


    /**
     * 分享ins 文本
     */
    public static void shareInsTxt(String content) {
        Intent intent;
        String packageName = Constants.InstagramPackageName;
        String appAddr = Constants.InstagramDownloadUrl;
        PackageManager packageManager = AppConfig.INSTANCE.getApplication().getPackageManager();
        intent = packageManager.getLaunchIntentForPackage(packageName);
        if (intent == null) {
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            //打开url
            Uri content_url = Uri.parse(appAddr);
            if (content_url != null)
                intent.setData(content_url);
            EventBus.getDefault().post(new TaskStartdownPic());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        } else {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setPackage(Constants.InstagramPackageName);
            intent.putExtra(Intent.EXTRA_TITLE, content);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            EventBus.getDefault().post(new TaskStartdownPic());
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        }
    }

    /**
     * 分享Messenger 图文  分享内容 会自动识别内容链接 显示图文
     */
    public static void shareMessengerAndPic(String content) {
        Intent intent;
        String packageName = Constants.MessengerPackageName;
        String appAddr = Constants.MessengerDownloadUrl;
        PackageManager packageManager = AppConfig.INSTANCE.getApplication().getPackageManager();
        intent = packageManager.getLaunchIntentForPackage(packageName);
        if (intent == null) {
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            //打开url
            Uri content_url = Uri.parse(appAddr);
            if (content_url != null)
                intent.setData(content_url);
            EventBus.getDefault().post(new TaskStartdownPic());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        } else {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setPackage(Constants.MessengerPackageName);
            intent.putExtra(Intent.EXTRA_TEXT, content);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            EventBus.getDefault().post(new TaskStartdownPic());
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        }

}


    /**
     * 分享telegram 文本
     */
    public static void shareTelegram(String content) {
        Intent intent;
        PackageManager packageManager = AppConfig.INSTANCE.getApplication().getPackageManager();
        intent = packageManager.getLaunchIntentForPackage(Constants.TelegramPackageName);
        if (intent == null) {
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            // 打开url
            Uri content_url = Uri.parse(Constants.TelegramDownloadUrl);
            intent.setData(content_url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        } else {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setPackage(Constants.TelegramPackageName);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
            intent.putExtra(Intent.EXTRA_TEXT, content);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        }
    }

    /**
     * 分享tweet
     *
     * @param titleStr
     * @param
     */
    public static void shareTweet(String titleStr) {
        Intent intent;
        try {
            intent = new TweetComposer.Builder(AppConfig.INSTANCE.getApplication())
                    .text(titleStr)
                    .createIntent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            AppConfig.INSTANCE.getApplication().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 加群弹窗
     *
     * @param context
     * @param links
     */
    public static void addGroup(Context context, final String links) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_group, null);
        Dialog dialog = new Dialog(context, R.style.style_bg_transparent_dialog);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        Rect rect = new Rect();
        View view1 = window.getDecorView();
        view1.getWindowVisibleDisplayFrame(rect);
        windowparams.height = (int) (context.getResources().getDisplayMetrics().heightPixels*0.2f);
        windowparams.width = (int) (context.getResources().getDisplayMetrics().widthPixels);
        window.setWindowAnimations(R.style.AnimTheme);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(
                (android.view.WindowManager.LayoutParams) windowparams);
        dialog.show();
        view.findViewById(R.id.WhatsApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                CommUtils.ToWeb(links, context);
            }
        });
        view.findViewById(R.id.WhatsApp1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                CommUtils.ToWeb(links, context);
            }
        });view.findViewById(R.id.WhatsApp2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                CommUtils.ToWeb(links, context);
            }
        });
        view.findViewById(R.id.telegramLLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                CommUtils.ToWeb(links, context);
            }
        });
    }


    /**
     * 分享弹窗 单文按
     *
     * @param context
     * @param content
     */
    public static void shareContent(Context context, final String content) {
        View view = View.inflate(context, R.layout.dialog_share_article, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(
                (int) (context.getResources().getDisplayMetrics().widthPixels * 0.9f),
                (int) (context.getResources().getDisplayMetrics().heightPixels * 0.3f)
        ));
        final Dialog dialog = DialogManager.newTransDialog(context, view, true);
        Window mWindow = dialog.getWindow();
        mWindow.setGravity(Gravity.BOTTOM);
        dialog.show();
        view.findViewById(R.id.FacebookLLayout).setOnClickListener(v -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            shareCommonFacebook(content);
        });

        view.findViewById(R.id.WhatsApp).setOnClickListener(v -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            shareWhat(content);
        });

        view.findViewById(R.id.twitter).setOnClickListener(v -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            shareTweet(content);
        });

        view.findViewById(R.id.telegramLLayout).setOnClickListener(v -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            shareTelegram(content);
        });

        view.findViewById(R.id.ll_messenger).setOnClickListener(v -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            shareInsTxt(content);
        });

    }

}
