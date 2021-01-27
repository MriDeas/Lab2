package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.fragment.TestFPageFragment;

public class FragmentPageActivity extends AppCompatActivity {

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        printActivityLifeCycle("onAttachedToWindow");
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        printActivityLifeCycle("onAttachFragment");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_page);
        printActivityLifeCycle("onCreate");
//        findViewById(R.id.fl_fragment_container_1);
//        findViewById(R.id.fl_fragment_container_2);
//        findViewById(R.id.fl_fragment_container_3);

        findViewById(R.id.fab_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitFragment();
            }
        });

    }

    private void commitFragment() {
        TestFPageFragment f1 = TestFPageFragment.newInstance("F1");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_fragment_container_1, f1)
                .add(R.id.fl_fragment_container_2, TestFPageFragment.newInstance("F2"))
                .add(R.id.fl_fragment_container_3, TestFPageFragment.newInstance("F3"))
                .addToBackStack("hey");
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        printActivityLifeCycle("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        printActivityLifeCycle("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        printActivityLifeCycle("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        printActivityLifeCycle("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        printActivityLifeCycle("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        printActivityLifeCycle("onRestart");
    }

    private void printActivityLifeCycle(String tag) {
        System.out.println("打印执行函数 ----------- Activity::" + tag);
    }
}