package com.limitofsoul.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.limitofsoul.R;
import com.limitofsoul.stress.chat.model.Model;
import com.limitofsoul.stress.chat.model.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.limitofsoul.utils.JsonUtil;
import com.limitofsoul.utils.NetUtils;
import com.limitofsoul.utils.WebAccessTools;

// 登录页面
public class LoginActivity extends Activity {

    private EditText et_login_name;
    private EditText et_login_pwd;
    private Button bt_login_regist;
    private Button bt_login_login;
    private TextView forget;

    private WebAccessTools web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }

    private void initView() {
        et_login_name = (EditText) findViewById(R.id.question);
        et_login_pwd = (EditText) findViewById(R.id.answer);
        bt_login_regist = (Button) findViewById(R.id.register);
        bt_login_login = (Button) findViewById(R.id.login);
        forget = (TextView) findViewById(R.id.forget);
        web = new WebAccessTools(LoginActivity.this);
    }

    private void initListener() {
        bt_login_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        bt_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, VerifyActivity.class));
            }
        });
    }

    // 登录
    private void login() {
        //获取输入的用户名和密码
        final String loginName = et_login_name.getText().toString();
        final String loginPwd = et_login_pwd.getText().toString();

        //校验输入的用户名和密码
        if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPwd)) {
            Toast.makeText(LoginActivity.this, "输入的用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        //登录逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //登录
                String url = NetUtils.SERVER_URL_PREFIX + "/user/login";
                String webContent = null;
                try {
                    webContent = web.postWebContent(url, new UserInfo(loginName, loginPwd));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final String s = (String) JsonUtil.jsonToObject(webContent, String.class);

                if (s.equals("1")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (s.equals("2")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (s.equals("3")) {
                    // 去环信服务器登录
                    EMClient.getInstance().login(loginName, "123", new EMCallBack() {
                        // 登录成功后的处理
                        @Override
                        public void onSuccess() {
                            //对模型层数据的处理
                            Model.getInstance().loginSuccess(new UserInfo(loginName));

                            //保存用户账号信息到本地数据库
                            Model.getInstance().getUserAccountDao().addAccount(new UserInfo(loginName));

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // 提示登录成功
                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(LoginActivity.this, BeginActivity.class));
                                    finish();
                                }
                            });
                        }

                        // 登录失败的处理
                        @Override
                        public void onError(int i, final String s) {
                            // 提示登录失败
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "环信服务器登录失败" + s, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        // 登录过程中的处理
                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });
                }
            }
        });
    }
}
