package com.sign.pushdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

/**
 * Created by cys on 2018/8/28 0028.
 */
public class MEIZUPushReceiver extends MzPushMessageReceiver {
    private static final String TAG = "MzPushMessageReceiver";

    @Override
    @Deprecated
    public void onRegister(Context context, String s) {
        DebugLogger.i(TAG, "onRegister pushID " + s);
        print(context, "receive pushID " + s);
    }

    @Override
    public void onMessage(Context context, String s) {
        DebugLogger.i(TAG, "onMessage " + s);
        //print(context,context.getPackageName() + " receive message " + s);
    }

    @Override
    public void onMessage(Context context, Intent intent) {
        Log.i(TAG, "flyme3 onMessage ");
        String content = intent.getExtras().toString();
        print(context,"flyme3 onMessage " + content);
    }

    @Override
    public void onMessage(Context context, String message, String platformExtra) {
        Log.i(TAG, "onMessage " + message +" platformExtra "+platformExtra);
        //print(context,context.getPackageName() + " receive message " + s);
    }

    @Override
    @Deprecated
    public void onUnRegister(Context context, boolean b) {
        DebugLogger.i(TAG, "onUnRegister " + b);
        print(context,context.getPackageName() + " onUnRegister " + b);
    }

    @Override
    public void onPushStatus(Context context,PushSwitchStatus pushSwitchStatus) {
    }

    @Override
    public void onRegisterStatus(Context context,RegisterStatus registerStatus) {
        DebugLogger.i(TAG, "onRegisterStatus " + registerStatus+ " "+context.getPackageName());
        //print(this," onRegisterStatus " + registerStatus);
    }

    @Override
    public void onUnRegisterStatus(Context context,UnRegisterStatus unRegisterStatus) {
        DebugLogger.i(TAG,"onUnRegisterStatus "+unRegisterStatus+" "+context.getPackageName());
    }

    @Override
    public void onSubTagsStatus(Context context,SubTagsStatus subTagsStatus) {
        DebugLogger.i(TAG, "onSubTagsStatus " + subTagsStatus+" "+context.getPackageName());
    }

    @Override
    public void onSubAliasStatus(Context context,SubAliasStatus subAliasStatus) {
        DebugLogger.i(TAG, "onSubAliasStatus " + subAliasStatus+" "+context.getPackageName());
    }

    @Override
    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        pushNotificationBuilder.setmLargIcon(R.drawable.flyme_status_ic_notification);
        pushNotificationBuilder.setmStatusbarIcon(R.drawable.mz_push_notification_small_icon);
    }

    @Override
    public void onNotificationArrived(Context context, MzPushMessage mzPushMessage) {
        DebugLogger.i(TAG, "onNotificationArrived title " + mzPushMessage.getTitle() + "content "
                + mzPushMessage.getContent() + " selfDefineContentString " + mzPushMessage.getSelfDefineContentString()+" notifyId "+mzPushMessage.getNotifyId());
    }

    @Override
    public void onNotificationClicked(Context context, MzPushMessage mzPushMessage) {
        DebugLogger.i(TAG, "onNotificationClicked title "+ mzPushMessage.getTitle() + "content "
                + mzPushMessage.getContent() + " selfDefineContentString " + mzPushMessage.getSelfDefineContentString()+" notifyId "+mzPushMessage.getNotifyId());
    }

    @Override
    public void onNotificationDeleted(Context context, MzPushMessage mzPushMessage) {
        DebugLogger.i(TAG, "onNotificationDeleted title " + mzPushMessage.getTitle() + "content "
                + mzPushMessage.getContent() + " selfDefineContentString " + mzPushMessage.getSelfDefineContentString()+" notifyId "+mzPushMessage.getNotifyId());
    }

    @Override
    public void onNotifyMessageArrived(Context context, String message) {
        DebugLogger.i(TAG, "onNotifyMessageArrived messsage " + message);
    }

    private void print(final Context context, final String info){
        new Handler(context.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, info, Toast.LENGTH_LONG).show();
            }
        });
    }

}

