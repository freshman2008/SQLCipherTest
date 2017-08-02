package com.example.gd001.sqlciphertest;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import net.sqlcipher.Cursor;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private DBAdapter adapter;
    private ListView listView;
    private List<Person> personList = new ArrayList<Person>();
    private Button insertBtn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        insertBtn = (Button) findViewById(R.id.btn_insert);
        listView = (ListView)findViewById(R.id.person_list);
        dbHelper = new DBHelper(this);
        personList = queryData();
        adapter = new DBAdapter(getApplication(), personList);
        listView.setAdapter(adapter);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //实例化一个ContentValues， ContentValues是以键值对的形式，键是数据库的列名，值是要插入的值
                ContentValues values = new ContentValues();
                values.put("name", "Edward");
                values.put("age", 30);
                values.put("sex", "mail");

                //调用insert插入数据库
                dbHelper.insert(values);
                updateUI();
            }
        });
    }

    public void updateUI() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //重新刷新适配器
                adapter.refresh(queryData());
            }
        });
    }

    //查询数据库，将每一行的数据封装成一个person 对象，然后将对象添加到List中
    private List<Person> queryData() {
        personList.clear();

        //调用query()获取Cursor
        Cursor c = dbHelper.query();
        while (c.moveToNext()){
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            int age = c.getInt(c.getColumnIndex("age"));
            String sex = c.getString(c.getColumnIndex("sex"));

            //用一个Person对象来封装查询出来的数据
            Person p = new Person();
            p.set_id(_id);
            p.setName(name);
            p.setAge(age);
            p.setSex(sex);

            personList.add(p);
        }
        return personList;
    }
}

