package com.dan.learn.lab2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dan.learn.lab2.R;

public class ThreadActivity extends AppCompatActivity {

    private TextView textView;
    private Button bt_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        bt_event = findViewById(R.id.bt_event);
        new Thread(() -> {
            textView = findViewById(R.id.tv_text);
            textView.setText("Hello Thread Activity thread :");
            textView.setOnClickListener(v -> {
                update();
            });
        }).start();
        bt_event.setOnClickListener(v -> {
            textView.setText("button 子线程更新 ");
        });
    }

    private void update(){
        textView.setText("更新内容");
    }


}
