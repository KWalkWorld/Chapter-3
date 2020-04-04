package com.example.chapter3.homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    private int length = 20;

    @Override
    public int getCount() {
        return length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        //public ImageView imageView;
        public TextView tvTitle,tvTime,tvContent;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(), R.layout.layout_listitem,null);
            holder = new ViewHolder();
            //holder.imageView = (ImageView)convertView.findViewById(R.id.iv);
            holder.tvContent = (TextView)convertView.findViewById(R.id.tv_content);
            holder.tvTime = (TextView)convertView.findViewById(R.id.tv_time);
            holder.tvTitle = (TextView)convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvTitle.setText("friend" + position);
        //holder.tvTime.setText("2020-03-04");
        holder.tvContent.setText("contents" + position);
        return convertView;
    }
}

