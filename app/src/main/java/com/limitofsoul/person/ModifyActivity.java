package com.limitofsoul.person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.limitofsoul.R;
import com.limitofsoul.common.BeginActivity;
import com.limitofsoul.stress.chat.model.Model;
import com.limitofsoul.utils.JsonUtil;
import com.limitofsoul.utils.NetUtils;
import com.limitofsoul.utils.WebAccessTools;

public class ModifyActivity extends Activity {

    private Button yes;
    private EditText former;
    private EditText now;
    private WebAccessTools web;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_password_modify);
        yes = (Button) findViewById(R.id.yes);
        former = (EditText) findViewById(R.id.former);
        now = (EditText) findViewById(R.id.now);
        web = new WebAccessTools(ModifyActivity.this);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String former_pwd = former.getText().toString();
                String now_pwd = now.getText().toString();
                if (!former_pwd.equals(PersonalActivity.userInfo.getPassword())) {
                    Toast.makeText(ModifyActivity.this, "原密码错误", Toast.LENGTH_SHORT).show();
                } else {
                    PersonalActivity.userInfo.setPassword(now_pwd);
                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            String url = NetUtils.SERVER_URL_PREFIX + "/user/addOrEdit";
                            String webContent = null;
                            try {
                                webContent = web.postWebContent(url, PersonalActivity.userInfo);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            final String s = (String) JsonUtil.jsonToObject(webContent, String.class);

                            if (s.equals("1")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ModifyActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ModifyActivity.this, BeginActivity.class));
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ModifyActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });


                    startActivity(new Intent(ModifyActivity.this, BeginActivity.class));
                }
            }
        });

    }
}
