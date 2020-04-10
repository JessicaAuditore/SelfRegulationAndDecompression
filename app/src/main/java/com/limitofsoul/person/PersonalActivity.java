package com.limitofsoul.person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.limitofsoul.R;
import com.limitofsoul.common.BeginActivity;
import com.limitofsoul.common.LoginActivity;
import com.limitofsoul.stress.chat.model.Model;
import com.limitofsoul.stress.chat.model.bean.UserInfo;
import com.limitofsoul.utils.JsonUtil;
import com.limitofsoul.utils.NetUtils;
import com.limitofsoul.utils.WebAccessTools;

public class PersonalActivity extends Activity {

    public static UserInfo userInfo;
    private TextView information;

    private EditText age;
    private EditText sex;
    private EditText question;
    private EditText answer;

    private Button save;
    private Button exit;
    private Button password_modify;

    private WebAccessTools web;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_personal);
        initView();

        final String username = EMClient.getInstance().getCurrentUser();
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                String url = NetUtils.SERVER_URL_PREFIX + "/user/find/" + username;
                String webContent = web.getWebContent(url);
                userInfo = (UserInfo) JsonUtil.jsonToObject(webContent, UserInfo.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        age.setText(userInfo.getAge());
                        sex.setText(userInfo.getSex());
                        question.setText(userInfo.getQuestion());
                        answer.setText(userInfo.getAnswer());
                    }
                });
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInfo.setName(userInfo.getName());
                userInfo.setPassword(userInfo.getPassword());
                userInfo.setAge(age.getText().toString());
                userInfo.setSex(sex.getText().toString());
                userInfo.setQuestion(question.getText().toString());
                userInfo.setAnswer(answer.getText().toString());

                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = NetUtils.SERVER_URL_PREFIX + "/user/addOrEdit";
                        String webContent = null;
                        try {
                            webContent = web.postWebContent(url, userInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        final String s = (String) JsonUtil.jsonToObject(webContent, String.class);

                        if (s.equals("1")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(PersonalActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(PersonalActivity.this, BeginActivity.class));
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(PersonalActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });


        password_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalActivity.this, ModifyActivity.class));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        //登录环信服务器退出登录
                        EMClient.getInstance().logout(false, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                // 关闭DBHelper
                                Model.getInstance().getDbManager().close();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 更新ui显示
                                        Toast.makeText(PersonalActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                                        // 回到登录页面
                                        startActivity(new Intent(PersonalActivity.this, LoginActivity.class));
                                        finish();
                                    }
                                });

                            }

                            @Override
                            public void onError(int i, final String s) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(PersonalActivity.this, "退出失败" + s, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                });
            }
        });
    }

    private void initView() {
        information = (TextView) findViewById(R.id.information);
        age = (EditText) findViewById(R.id.age);
        sex = (EditText) findViewById(R.id.sex);
        question = (EditText) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);

        password_modify = (Button) findViewById(R.id.password_modify);
        save = (Button) findViewById(R.id.save);
        exit = (Button) findViewById(R.id.exit);
        web = new WebAccessTools(PersonalActivity.this);
    }
}
