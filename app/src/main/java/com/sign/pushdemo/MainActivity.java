package com.sign.pushdemo;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
        EasyPermissions.requestPermissions(this, "", 100, perms);
        /**
         * 若为非华为机型，会提示安装华为移动服务，用户体验不好，此处未下载安装测试
         * 若为华为机型，华为移动服务有更新会提示更新，华为机型应用被杀死，仍会收到推送
         */
        if (SystemJudgeUtil.getSystem() == SystemJudgeUtil.SYS_EMUI) {
            Log.i("test", "**************************System HUAWEI********************************");
            //华为
            HMSAgent.init(this);
            HMSAgent.connect(this, new ConnectHandler() {
                @Override
                public void onConnect(int rst) {
                    Log.d("test", "HMSAgent connect end");
                }
            });
            getToken();
        }
    }


    /**
     * 获取token
     */
    private void getToken() {
        Log.d("test", "get token: begin");
        HMSAgent.Push.getToken(new GetTokenHandler() {
            @Override
            public void onResult(int rst) {
                Log.d("test", "get token: end" + rst);
            }
        });
    }
}
