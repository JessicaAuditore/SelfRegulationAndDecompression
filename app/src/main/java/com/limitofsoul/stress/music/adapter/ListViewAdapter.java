package com.limitofsoul.stress.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.limitofsoul.R;
import com.limitofsoul.stress.music.Bean;


import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter implements View.OnClickListener{
    private Context context;
    private ArrayList<HashMap<String, Object>> data;
    Bean bean=null;

    public ListViewAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //首次listView某行未加载数据时调用
        if(convertView==null){
            bean=new Bean();
            LayoutInflater layoutInflater= LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.listitem1,null);
            bean.picture=(ImageView)convertView.findViewById(R.id.picture);
            bean.theme=(TextView) convertView.findViewById(R.id.theme);
            bean.nature=(TextView)convertView.findViewById(R.id.nature);
            convertView.setTag(bean);
        }else{
            bean=(Bean)convertView.getTag();
        }
        bean.picture.setBackgroundResource((int)data.get(position).get("picture"));
        bean.theme.setText((data.get(position).get("theme")).toString());
        bean.nature.setText((data.get(position).get("nature")).toString());
        return convertView;
    }


    @Override
    public void onClick(View v) {

    }
}

