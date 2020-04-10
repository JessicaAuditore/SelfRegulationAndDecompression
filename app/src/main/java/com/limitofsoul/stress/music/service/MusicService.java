package com.limitofsoul.stress.music.service;


import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service {
    MediaPlayer mediaPlayer=null;
    @Nullable
    @Override

    public int onStartCommand(Intent intent, int flag, int startId){
        try{
            String theme=intent.getStringExtra("theme");
            //从assets目录下获取歌曲
            AssetFileDescriptor afd=getAssets().openFd(theme);
            //实例化MediaPlayer
            mediaPlayer=new MediaPlayer();
            //设置数据源
            mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            //准备播放音乐
            mediaPlayer.prepare();
            //开始播放音乐
            mediaPlayer.start();
        }catch (IllegalArgumentException e){
            Log.e("ERROR",e.toString());
        }catch (IllegalStateException e){
            Log.e("ERROR",e.toString());
        }catch (IOException e){
            Log.e("ERROR",e.toString());
        }
        return super.onStartCommand(intent,flag,startId);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
