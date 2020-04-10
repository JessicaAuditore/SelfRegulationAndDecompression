package com.limitofsoul.regulation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.limitofsoul.R;

public class DisciplineActivity extends Activity {

    private TextView b1;
    private TextView t1;
    private TextView m1;
    private TextView begin_last;
    static String time = "25";
    static String name = "待办是您要专注的事";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.discipline);
        b1 = (TextView) findViewById(R.id.b1);
        t1 = (TextView) findViewById(R.id.t1);
        m1 = (TextView) findViewById(R.id.m1);
        begin_last = (TextView) findViewById(R.id.begin_last);
        t1.setText(name);
        m1.setText(time);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisciplineActivity.this, LastModifyActivity.class));
            }
        });

        begin_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisciplineActivity.this, TimeActivity.class));
            }
        });
    }
}
