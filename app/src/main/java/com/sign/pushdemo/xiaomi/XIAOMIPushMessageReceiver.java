package com.sign.pushdemo.xiaomi;

import android.content.Context;
import android.util.Log;

import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;


/**
 * Created by cys on 2018/7/19 0019.
 */
public class XIAOMIPushMessageReceiver extends PushMessageReceiver {


    @Override
    public void onRequirePermissions(Context context, String[] strings) {
        Log.d("test", "**************XIAOMIPushMessageReceiver**************onRequirePermissions" + strings);

    }

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        Log.d("test", "**************XIAOMIPushMessageReceiver**************onReceivePassThroughMessage" + message);
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        Log.d("test", "**************XIAOMIPushMessageReceiver**************onNotificationMessageClicked" + message);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        Log.d("test", "**************XIAOMIPushMessageReceiver**************onNotificationMessageArrived" + message);
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        Log.d("test", "**************XIAOMIPushMessageReceiver**************onCommandResult" + message);
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        Log.d("test", "**************XIAOMIPushMessageReceiver**************onReceiveRegisterResult" + message);
    }
}