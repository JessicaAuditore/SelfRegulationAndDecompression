package com.limitofsoul.stress.chat.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.limitofsoul.R;
import com.limitofsoul.common.BeginActivity;
import com.limitofsoul.common.LoginActivity;
import com.limitofsoul.stress.chat.model.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

// 设置页面
public class SettingFragment extends Fragment {

    private Button bt_setting_out;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_setting, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        bt_setting_out = (Button) view.findViewById(R.id.bt_setting_out);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        //在button上显示当前用户名称
        bt_setting_out.setText("返回主页");

        //退出登录的逻辑处理
        bt_setting_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BeginActivity.class));
            }
        });
    }
}
