package com.limitofsoul.stress.music.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.limitofsoul.R;
import com.limitofsoul.stress.StressDownActivity;
import com.limitofsoul.stress.music.adapter.ListViewAdapter;


import java.util.ArrayList;
import java.util.HashMap;

public class StressMusicActivity extends Activity {

    Intent intent = new Intent();
    Intent intent1 = new Intent();
    ArrayList<HashMap<String, Object>> arrayList1;
    ArrayList<HashMap<String, Object>> arrayList2;
    int pos = -1;
    static boolean play_flag = false;
    static String theme;
    ListViewAdapter listViewAdapter1;
    ListViewAdapter listViewAdapter2;
    ImageView returnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stress_music);

        returnn = (ImageView) findViewById(R.id.returnn);
        ListView listView1 = (ListView) findViewById(R.id.listView1);
        ListView listView2 = (ListView) findViewById(R.id.listView2);
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        listViewAdapter1 = new ListViewAdapter(this, getData1());
        listViewAdapter2 = new ListViewAdapter(this, getData2());
        listView1.setAdapter(listViewAdapter1);
        listView2.setAdapter(listViewAdapter2);
        returnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StressMusicActivity.this, StressDownActivity.class));
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (play_flag) {
                    if (pos != position) {
                        stopPlayer(pos);
                        pos = position;
                        startPlayer(pos, 1);
                    }
                } else {
                    pos = position;
                    startPlayer(pos, 1);
                }
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (play_flag) {
                    if (pos != position) {
                        stopPlayer(pos);
                        pos = position;
                        startPlayer(pos, 2);
                    }
                } else {
                    pos = position;
                    startPlayer(pos, 2);
                }
            }
        });
    }

    private void startPlayer(int position, int flag) {
        play_flag = true;
        intent.setAction("com.limitofsoul.stress.music.service.MusicService");
        intent.setPackage(getPackageName());
        if (flag == 1) {
            switch (position) {
                case 0:
                    theme = "star_cloud";
                    break;
                case 1:
                    theme = "rain";
                    break;
                case 2:
                    theme = "happy";
                    break;
                case 3:
                    theme = "reading";
                    break;
                case 4:
                    theme = "bird";
                    break;
                case 5:
                    theme = "sea_water";
                    break;
                case 6:
                    theme = "fire";
                    break;
                default:
                    break;
            }
        } else {
            switch (position) {
                case 0:
                    theme = "sleep";
                    break;
                case 1:
                    theme = "study";
                    break;
                case 2:
                    theme = "leaves";
                    break;
                case 3:
                    theme = "star_sky";
                    break;
                case 4:
                    theme = "fall";
                    break;
                case 5:
                    theme = "mountain";
                    break;
                case 6:
                    theme = "firework";
                    break;
                default:
                    break;
            }
        }
        intent1.setClass(StressMusicActivity.this, PlayerActivity.class);
        intent1.putExtra("theme", theme);
        intent.putExtra("theme", theme + ".mp3");
        startService(intent);

        startActivity(intent1);
    }

    private void stopPlayer(int position) {
        play_flag = false;
        intent.setAction("com.limitofsoul.stress.music.service.MusicService");
        intent.setPackage(getPackageName());
        stopService(intent);
    }

    private ArrayList<HashMap<String, Object>> getData1() {
        HashMap<String, Object> hashMap1 = new HashMap<>();
        HashMap<String, Object> hashMap2 = new HashMap<>();
        HashMap<String, Object> hashMap3 = new HashMap<>();
        HashMap<String, Object> hashMap4 = new HashMap<>();
        HashMap<String, Object> hashMap5 = new HashMap<>();
        HashMap<String, Object> hashMap6 = new HashMap<>();
        HashMap<String, Object> hashMap7 = new HashMap<>();

        hashMap1.put("picture", R.mipmap.star_cloud);
        hashMap1.put("theme", "星云");
        hashMap1.put("nature", "大自然的频率");
        arrayList1.add(hashMap1);
        hashMap2.put("picture", R.mipmap.rain);
        hashMap2.put("theme", "雨水");
        hashMap2.put("nature", "大自然的频率");
        hashMap3.put("picture", R.mipmap.happy);
        hashMap3.put("theme", "快乐");
        hashMap3.put("nature", "大自然的频率");
        hashMap4.put("picture", R.mipmap.reading);
        hashMap4.put("theme", "阅读");
        hashMap4.put("nature", "大自然的频率");
        hashMap5.put("picture", R.mipmap.bird);
        hashMap5.put("theme", "鸟鸣");
        hashMap5.put("nature", "大自然的频率");
        hashMap6.put("picture", R.mipmap.sea_water);
        hashMap6.put("theme", "浪潮");
        hashMap6.put("nature", "大自然的频率");
        hashMap7.put("picture", R.mipmap.fire);
        hashMap7.put("theme", "篝火");
        hashMap7.put("nature", "大自然的频率");

        arrayList1.add(hashMap2);
        arrayList1.add(hashMap3);
        arrayList1.add(hashMap4);
        arrayList1.add(hashMap5);
        arrayList1.add(hashMap6);
        arrayList1.add(hashMap7);
        return arrayList1;
    }

    private ArrayList<HashMap<String, Object>> getData2() {
        HashMap<String, Object> hashMap1 = new HashMap<>();
        HashMap<String, Object> hashMap2 = new HashMap<>();
        HashMap<String, Object> hashMap3 = new HashMap<>();
        HashMap<String, Object> hashMap4 = new HashMap<>();
        HashMap<String, Object> hashMap5 = new HashMap<>();
        HashMap<String, Object> hashMap6 = new HashMap<>();
        HashMap<String, Object> hashMap7 = new HashMap<>();

        hashMap1.put("picture", R.mipmap.sleep);
        hashMap1.put("theme", "入眠");
        hashMap1.put("nature", "大自然的频率");
        hashMap2.put("picture", R.mipmap.study);
        hashMap2.put("theme", "学习");
        hashMap2.put("nature", "大自然的频率");
        hashMap3.put("picture", R.mipmap.leaves);
        hashMap3.put("theme", "叶落");
        hashMap3.put("nature", "大自然的频率");
        hashMap4.put("picture", R.mipmap.star_sky);
        hashMap4.put("theme", "星空");
        hashMap4.put("nature", "大自然的频率");
        hashMap5.put("picture", R.mipmap.fall);
        hashMap5.put("theme", "瀑布");
        hashMap5.put("nature", "大自然的频率");
        hashMap6.put("picture", R.mipmap.mountain);
        hashMap6.put("theme", "山风");
        hashMap6.put("nature", "大自然的频率");
        hashMap7.put("picture", R.mipmap.firework);
        hashMap7.put("theme", "烟花");
        hashMap7.put("nature", "大自然的频率");
        arrayList2.add(hashMap1);
        arrayList2.add(hashMap2);
        arrayList2.add(hashMap3);
        arrayList2.add(hashMap4);
        arrayList2.add(hashMap5);
        arrayList2.add(hashMap6);
        arrayList2.add(hashMap7);
        return arrayList2;
    }
}

