package com.limitofsoul.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.limitofsoul.R;
import com.limitofsoul.person.PersonalActivity;
import com.limitofsoul.regulation.DisciplineActivity;
import com.limitofsoul.stress.StressDownActivity;

public class BeginActivity extends Activity {

    TextView stress_down;
    TextView discipline;
    TextView personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_begin);
        stress_down = (TextView) findViewById(R.id.stress_down);
        discipline = (TextView) findViewById(R.id.discipline);
        personal = (TextView) findViewById(R.id.personnal);
        class stress_downListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeginActivity.this, StressDownActivity.class));
            }
        }

        class disciplineListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeginActivity.this, DisciplineActivity.class));
            }
        }

        class personalListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeginActivity.this, PersonalActivity.class));
            }
        }

        stress_down.setOnClickListener(new stress_downListener());
        personal.setOnClickListener(new personalListener());
        discipline.setOnClickListener(new disciplineListener());
    }
}
