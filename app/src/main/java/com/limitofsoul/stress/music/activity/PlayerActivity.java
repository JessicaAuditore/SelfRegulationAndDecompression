package com.limitofsoul.stress.music.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.limitofsoul.R;

public class PlayerActivity extends Activity {

    private ImageView back;
    private ImageView picture;
    private ImageView stop;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.star_cloud);
        back = (ImageView) findViewById(R.id.back);
        picture = (ImageView) findViewById(R.id.imageView2);
        stop = (ImageView) findViewById(R.id.stop);
        textView=(TextView)findViewById(R.id.textView3);
        String theme = getIntent().getStringExtra("theme");
        switch (theme) {
            case "star_cloud":
                picture.setImageResource(R.mipmap.star_cloud);
                textView.setText("星云");
                break;
            case "rain":
                picture.setImageResource(R.mipmap.rain);
                textView.setText("雨水");
                break;
            case "happy":
                picture.setImageResource(R.mipmap.happy);
                textView.setText("快乐");
                break;
            case "reading":
                picture.setImageResource(R.mipmap.reading);
                textView.setText("阅读");
                break;
            case "bird":
                picture.setImageResource(R.mipmap.bird);
                textView.setText("鸟鸣");
                break;
            case "sea_water":
                picture.setImageResource(R.mipmap.sea_water);
                textView.setText("浪潮");
                break;
            case "fire":
                picture.setImageResource(R.mipmap.fire);
                textView.setText("篝火");
                break;
            case "sleep":
                picture.setImageResource(R.mipmap.sleep);
                textView.setText("入眠");
                break;
            case "study":
                picture.setImageResource(R.mipmap.study);
                textView.setText("学习");
                break;
            case "leaves":
                picture.setImageResource(R.mipmap.leaves);
                textView.setText("叶落");
                break;
            case "star_sky":
                picture.setImageResource(R.mipmap.star_sky);
                textView.setText("星空");
                break;
            case "fall":
                picture.setImageResource(R.mipmap.fall);
                textView.setText("瀑布");
                break;
            case "mountain":
                picture.setImageResource(R.mipmap.mountain);
                textView.setText("山风");
                break;
            case "firework":
                picture.setImageResource(R.mipmap.firework);
                textView.setText("烟花");
                break;
            default:
                break;
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlayerActivity.this, StressMusicActivity.class));
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StressMusicActivity.play_flag){
                    Intent intent = new Intent();
                    intent.setAction("com.limitofsoul.stress.music.service.MusicService");
                    intent.setPackage(getPackageName());
                    stopService(intent);
                    StressMusicActivity.play_flag = false;
                }else {
                    Intent intent = new Intent();
                    intent.setAction("com.limitofsoul.stress.music.service.MusicService");
                    intent.setPackage(getPackageName());
                    intent.putExtra("theme", StressMusicActivity.theme + ".mp3");
                    startService(intent);
                    StressMusicActivity.play_flag = true;
                }

            }
        });
    }
}
