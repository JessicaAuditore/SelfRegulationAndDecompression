package com.limitofsoul.stress.chat.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.limitofsoul.R;
import com.limitofsoul.stress.chat.model.Model;
import com.limitofsoul.stress.chat.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.limitofsoul.utils.JsonUtil;
import com.limitofsoul.utils.NetUtils;
import com.limitofsoul.utils.WebAccessTools;

// 添加联系人页面
public class AddContactActivity extends Activity {

    private TextView tv_add_find;
    private EditText et_add_name;
    private RelativeLayout rl_add;
    private TextView tv_add_name;
    private Button bt_add_add;
    private WebAccessTools web;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        initView();
        initListener();
    }

    private void initView() {
        tv_add_find = (TextView) findViewById(R.id.tv_add_find);
        et_add_name = (EditText) findViewById(R.id.et_add_name);
        rl_add = (RelativeLayout) findViewById(R.id.rl_add);
        tv_add_name = (TextView) findViewById(R.id.tv_add_name);
        bt_add_add = (Button) findViewById(R.id.bt_add_add);
        web = new WebAccessTools(AddContactActivity.this);
    }

    private void initListener() {
        // 查找按钮的点击事件处理
        tv_add_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find();
            }
        });

        // 添加按钮的点击事件处理
        bt_add_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    // 查找按钮的处理
    private void find() {
        final String name = et_add_name.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(AddContactActivity.this, "输入的用户名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        // 去服务器判断当前用户是否存在
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // 去服务器判断当前查找的用户是否存在
                String url = NetUtils.SERVER_URL_PREFIX + "/user/find/" + name;
                String webContent = web.getWebContent(url);
                userInfo = (UserInfo) JsonUtil.jsonToObject(webContent, UserInfo.class);

                if (userInfo.getName() != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rl_add.setVisibility(View.VISIBLE);
                            tv_add_name.setText(userInfo.getName());
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rl_add.setVisibility(View.INVISIBLE);
                        }
                    });
                }

            }
        });
    }

    // 添加按钮处理
    private void add() {

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // 去环信服务器添加好友
                try {
                    EMClient.getInstance().contactManager().addContact(userInfo.getName(), "添加好友");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddContactActivity.this, "发送添加好友邀请成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddContactActivity.this, "发送添加好友邀请失败" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
