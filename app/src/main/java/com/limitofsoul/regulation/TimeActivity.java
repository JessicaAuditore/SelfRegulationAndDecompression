package com.limitofsoul.regulation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.limitofsoul.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimeActivity extends Activity {

    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.time);
        time = (TextView) findViewById(R.id.time);
        time.setText(DisciplineActivity.time);


        //********************开始计时*******************


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int t = Integer.parseInt(time.getText().toString());
                        if (t == 0) {
                            Toast.makeText(TimeActivity.this, "计时结束", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TimeActivity.this, DisciplineActivity.class));
                            finish();
                        }
                        time.setText((t - 1) + "");
                    }
                });
            }
        }, 0, 60000);
    }
}
