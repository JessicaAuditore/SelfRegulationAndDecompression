package com.limitofsoul.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.limitofsoul.R;
import com.limitofsoul.stress.chat.controller.activity.MainActivity;
import com.limitofsoul.stress.chat.model.Model;
import com.limitofsoul.stress.chat.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;

// 欢迎页面
public class SplashActivity extends Activity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 如果当前activity已经退出，不处理handler中的消息
            if (isFinishing()) {
                return;
            }
            toMainOrLogin();
        }
    };

    // 判断进入主页面还是登录页面
    private void toMainOrLogin() {
        //调用线程池
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // 判断当前账号是否已经登录过
                if (EMClient.getInstance().isLoggedInBefore()) {
                    //获取到当前登录用户的信息
                    UserInfo account = Model.getInstance().getUserAccountDao().getAccountByHxId(EMClient.getInstance().getCurrentUser());

                    if (account == null) {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    } else {
                        // 登录成功
                        Model.getInstance().loginSuccess(account);
                        startActivity(new Intent(SplashActivity.this, BeginActivity.class));
                    }
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //延迟两秒
        handler.sendMessageDelayed(Message.obtain(), 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁消息
        handler.removeCallbacksAndMessages(null);
    }
}
