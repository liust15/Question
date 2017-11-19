package com.example.liust.listvieweditview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListViewAdapter listViewAdapter;
    List<ListviewBean> list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.main);
        list = getList();
        listViewAdapter = new ListViewAdapter(this, R.layout.listview, list);
        listView.setAdapter(listViewAdapter);
    }

    public List<ListviewBean> getList() {
        List<ListviewBean> list = new ArrayList<>();
        ListviewBean listviewBean = new ListviewBean("1", "1", "1");
        list.add(listviewBean);
        return list;
    }


}


class ListViewAdapter extends BaseAdapter {
    private int resourceid;
    private Context context;
    List<ListviewBean> data;

    public ListViewAdapter(Context context, int textViewResourceid, List<ListviewBean> data) {
        this.context = context;
        resourceid = textViewResourceid;
        this.data = data;
    }

    public final class Packages {
        public EditText list_a;
        public EditText list_b;
        public EditText list_c;

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
    public View getView(int position, View view, ViewGroup parent) {
        ListviewBean listviewBean = (ListviewBean) getItem(position);
        Packages packages = null;
        if (null == view) {
            packages = new Packages();
            //获得组件，实例化组件
            view = LayoutInflater.from(context).inflate(resourceid, parent, false);
            packages.list_a = (EditText) view.findViewById(R.id.list_a);
            packages.list_b = (EditText) view.findViewById(R.id.list_b);
            packages.list_c = (EditText) view.findViewById(R.id.list_c);
            view.setTag(packages);
        } else {
            packages = (Packages) view.getTag();
        }
        isEmpty(listviewBean.getA(), packages.list_a);
        isEmpty(listviewBean.getB(), packages.list_b);
        isEmpty(listviewBean.getC(), packages.list_c);
        packages.list_b.setOnFocusChangeListener(new OncFocusChangeListener());
        return view;
    }

    public void isEmpty(String string, EditText editText) {
        if (!TextUtils.isEmpty(string)) {
            editText.setText(string);
        } else {
            editText.setText("");
        }
    }

    //绑定焦点事件
    class OncFocusChangeListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                Toast.makeText(context, "失去焦点", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "获得焦点", Toast.LENGTH_SHORT).show();
            }
        }
    }
}