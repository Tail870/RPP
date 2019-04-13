package com.example.lab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<String> data;

    MyAdapter(Context context, List<String> data) {
        ctx = context;
        this.data = data;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View view = convertView;
        if (view == null)
            view = lInflater.inflate(R.layout.mylist, parent, false);
        if (position % 2 == 0)
            view.setBackgroundResource(R.color.list2Color);
        else
            view.setBackgroundResource(R.color.list1Color);
        ((TextView) view.findViewById(R.id.number)).setText(data.get(position));
        Picasso.get().load("https://pp.userapi.com/c824200/v824200784/1e7b3/e0gAgUqzqZg.jpg").into(((ImageView) view.findViewById(R.id.icon_draw)));
        return view;
    }
}