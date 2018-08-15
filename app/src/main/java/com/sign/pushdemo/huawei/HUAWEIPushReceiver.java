package com.sign.pushdemo.huawei;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;

/**
 * Created by cys on 2018/7/18 0018.
 */
public class HUAWEIPushReceiver extends PushReceiver {
    @Override
    public void onToken(Context context, String token, Bundle extras) {
        Log.d("test", "*******HUAWEIPushReceiver*************token=" + token + "extras=" + extras);
    }

    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
        Log.d("test", "*******HUAWEIPushReceiver*************msg=" + msg + "bundle=" + bundle);
        return false;
    }

    public void onEvent(Context context, Event event, Bundle extras) {
        Log.d("test", "*******HUAWEIPushReceiver*************event=" + event + "extras=" + extras);
    }

    @Override
    public void onPushState(Context context, boolean pushState) {
        Log.d("test", "*******HUAWEIPushReceiver*************pushState=" + pushState);
    }
}