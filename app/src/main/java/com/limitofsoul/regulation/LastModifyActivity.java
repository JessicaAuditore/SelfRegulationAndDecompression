package com.limitofsoul.regulation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.limitofsoul.R;

public class LastModifyActivity extends Activity {

    EditText thing_name;
    EditText thing_time;
    Button thing_yes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.lastmodify);
        thing_name = (EditText) findViewById(R.id.thingname);
        thing_time = (EditText) findViewById(R.id.thingtime);
        thing_yes = (Button) findViewById(R.id.thingyes);
        thing_name.setText(DisciplineActivity.time);
        thing_time.setText(DisciplineActivity.name);


        thing_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisciplineActivity.time = thing_name.getText().toString();
                DisciplineActivity.name = thing_time.getText().toString();
                startActivity(new Intent(LastModifyActivity.this, DisciplineActivity.class));
            }
        });
    }
}
