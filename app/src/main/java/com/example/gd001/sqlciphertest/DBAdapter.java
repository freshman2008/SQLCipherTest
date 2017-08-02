package com.example.gd001.sqlciphertest;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gd001 on 17-8-1.
 */

public class DBAdapter extends BaseAdapter {
    private List<Person> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public DBAdapter(Context context, List<Person> list){
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    //刷新适配器
    public void refresh(List<Person> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Person p = list.get(position);
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder.name = (TextView)convertView.findViewById(R.id.nametext);
            holder.age = (TextView)convertView.findViewById(R.id.agetext);
            holder.sex = (TextView)convertView.findViewById(R.id.sextext);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(p.getName());
        holder.age.setText(p.getAge()+"");
        holder.sex.setText(p.getSex());

        return convertView;
    }


    public class ViewHolder {
        public TextView name;
        public TextView age;
        public TextView sex;
    }
}
