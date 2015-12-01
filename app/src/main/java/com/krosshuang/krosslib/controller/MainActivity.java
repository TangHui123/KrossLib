package com.krosshuang.krosslib.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.krosshuang.krosslib.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static final String LOG_TAG = "MainActivity";

    private ListView mListView = null;
    private DemoListAdapter mAdapter = null;
    private String[][] mData = new String[][]{
            {"Bezier Curve", "BezierActivity"},
            {"Screenshot Marker", "ScreenshotActivity"},
            {"Test All API", "TestAllApiActivity"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new DemoListAdapter();

        mListView = (ListView)findViewById(R.id.lv_list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClassName("com.krosshuang.krosslib", "com.krosshuang.krosslib.controller." + mData[position][1]);
        startActivity(intent);
    }

    class DemoListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mData.length;
        }

        @Override
        public Object getItem(int position) {
            return mData[position][0];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = MainActivity.this.getLayoutInflater().inflate(R.layout.item_demo, null);
                holder.demoName = (TextView)convertView.findViewById(R.id.tv_demo_name);
                convertView.setTag(holder);
            } else {
                holder = (Holder)convertView.getTag();
            }

            holder.demoName.setText(mData[position][0]);

            return convertView;
        }
    }

    class Holder {
        public TextView demoName;
    }
}