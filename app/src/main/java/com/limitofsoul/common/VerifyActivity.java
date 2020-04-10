package com.limitofsoul.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.limitofsoul.R;
import com.limitofsoul.stress.chat.model.Model;
import com.limitofsoul.stress.chat.model.bean.UserInfo;
import com.limitofsoul.utils.JsonUtil;
import com.limitofsoul.utils.NetUtils;
import com.limitofsoul.utils.WebAccessTools;

public class VerifyActivity extends Activity {

    private EditText question;
    private EditText answer;
    private Button verify;
    private EditText name;
    private WebAccessTools web;
    private UserInfo userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_verify);
        question = (EditText) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);
        verify = (Button) findViewById(R.id.verify);
        name = (EditText) findViewById(R.id.name);
        web = new WebAccessTools(VerifyActivity.this);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = NetUtils.SERVER_URL_PREFIX + "/user/find/" + name.getText().toString();
                        String webContent = web.getWebContent(url);
                        userInfo = (UserInfo) JsonUtil.jsonToObject(webContent, UserInfo.class);

                        if (userInfo.getQuestion() == null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VerifyActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else if (userInfo.getQuestion().equals(question.getText().toString()) && userInfo.getAnswer().equals(answer.getText().toString())) {
                            EMClient.getInstance().login(userInfo.getName(), "123", new EMCallBack() {
                                @Override
                                public void onSuccess() {
                                    Model.getInstance().loginSuccess(new UserInfo(userInfo.getName()));
                                    Model.getInstance().getUserAccountDao().addAccount(new UserInfo(userInfo.getName()));
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(VerifyActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(VerifyActivity.this, BeginActivity.class));
                                            finish();
                                        }
                                    });
                                }

                                @Override
                                public void onError(int i, final String s) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(VerifyActivity.this, "环信服务器登录失败" + s, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onProgress(int i, String s) {

                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VerifyActivity.this, "密保问题或答案错误", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });


    }
}
