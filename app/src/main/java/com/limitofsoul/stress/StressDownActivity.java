package com.limitofsoul.stress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.limitofsoul.R;
import com.limitofsoul.common.BeginActivity;
import com.limitofsoul.stress.chat.controller.activity.MainActivity;
import com.limitofsoul.stress.music.activity.StressMusicActivity;

public class StressDownActivity extends Activity {

    TextView music;
    TextView talk;
    ImageView returnn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_stressdown);
        music = (TextView) findViewById(R.id.music);
        talk = (TextView) findViewById(R.id.talk);
        returnn = (ImageView) findViewById(R.id.returnn);

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StressDownActivity.this, StressMusicActivity.class));
            }
        });
        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StressDownActivity.this, MainActivity.class));
            }
        });
        returnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StressDownActivity.this, BeginActivity.class));
            }
        });
    }
}
