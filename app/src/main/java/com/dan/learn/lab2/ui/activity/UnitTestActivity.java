package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.adapter.SampleAdapter;
import com.dan.learn.lab2.entity.Item;
import com.dan.learn.lab2.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class UnitTestActivity extends BaseActivity {

    @VisibleForTesting
    public static final String STR = "STR";
    @VisibleForTesting
    public static final String LEN = "LEN";

    @BindView(R.id.list_view)
    ListView list_view;
    @BindView(R.id.tv_test_1)
    TextView tv_test_1;
    @BindView(R.id.rv_recycler_view)
    RecyclerView rv_recycler_view;

    private List<Map<String, String>> data = new ArrayList<>();
    private List<String> data2 = new ArrayList<>();
    private List<Item> data3 = new ArrayList<>();

    private boolean mIsReset;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_unit_test;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("单元测试");
        for (int i = 0; i < 100; i++) {
            data.add(makeItem(i));
            data2.add("Item :" + i);
            data3.add(new Item("item:" + i, i));
        }

        list_view.setAdapter(new SampleAdapter(this, data, data2, data3));
        list_view.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(UnitTestActivity.this, "点击了：" + position, Toast.LENGTH_SHORT).show());
    }


    public void clickButton(View v) {
        Toast.makeText(this, "tushy", Toast.LENGTH_SHORT).show();
    }

    public void clickButton2(View v) {
        if (!mIsReset) {

            new Thread() {
                @Override
                public void run() {
                    super.run();

                    try {
                        Thread.sleep(2000);
                        runOnUiThread(() -> tv_test_1.setText("张三的歌"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mIsReset = true;
                }
            }.start();

        }
    }

    public Map<String, String> makeItem(int forRow) {
        Map<String, String> dataRow = new HashMap<>();
        dataRow.put(STR, "item: " + forRow);
        dataRow.put(LEN, (dataRow.get(STR)).length() + "");
        return dataRow;
    }


}
