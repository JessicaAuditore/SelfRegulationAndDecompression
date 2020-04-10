package com.limitofsoul.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.limitofsoul.R;
import com.limitofsoul.stress.chat.model.Model;
import com.limitofsoul.stress.chat.model.bean.UserInfo;
import com.limitofsoul.utils.JsonUtil;
import com.limitofsoul.utils.NetUtils;
import com.limitofsoul.utils.WebAccessTools;

public class RegisterActivity extends Activity {

    private EditText name;
    private EditText password;
    private EditText sex;
    private EditText age;
    private EditText question;
    private EditText answer;
    private WebAccessTools web;
    private Button bt_login_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name= (EditText) findViewById(R.id.question);
        password= (EditText) findViewById(R.id.answer);
        sex= (EditText) findViewById(R.id.sex);
        age= (EditText) findViewById(R.id.age);
        question= (EditText) findViewById(R.id.question);
        answer= (EditText) findViewById(R.id.answer);
        bt_login_regist=(Button)findViewById(R.id.register);
        web = new WebAccessTools(RegisterActivity.this);

        bt_login_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regist();
            }
        });
    }

    //注册
    private void regist() {
        //获取输入的用户名和密码
        final String name1 = name.getText().toString();
        final String password1 = password.getText().toString();
        final String sex1 = sex.getText().toString();
        final String question1 = question.getText().toString();
        final String answer1 = answer.getText().toString();
        final String age1 = age.getText().toString();

        //校验输入的用户名和密码
        if (TextUtils.isEmpty(name1) || TextUtils.isEmpty(password1)
                || TextUtils.isEmpty(age1)|| TextUtils.isEmpty(sex1)
                || TextUtils.isEmpty(question1)|| TextUtils.isEmpty(answer1)) {
            Toast.makeText(RegisterActivity.this, "输入的用户名、密码、年龄、性别、密保问题、答案不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        //去服务器注册账号
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //注册
                String url = NetUtils.SERVER_URL_PREFIX + "/user/addOrEdit";
                String webContent = null;
                try {
                    webContent = web.postWebContent(url, new UserInfo(name1, password1, sex1, age1, question1, answer1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final String s = (String) JsonUtil.jsonToObject(webContent, String.class);

                if (s.equals("1")) {
                    try {
                        //环信服务器注册账号
                        EMClient.getInstance().createAccount(name1, "123");

                        //在主线程上更新页面显示
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }
                        });
                    } catch (final HyphenateException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "环信注册失败" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}
