package com.dan.learn.lab2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.fragment.TestFPageFragment;

public class FragmentPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_page);

//        findViewById(R.id.fl_fragment_container_1);
//        findViewById(R.id.fl_fragment_container_2);
//        findViewById(R.id.fl_fragment_container_3);
        TestFPageFragment f1 = TestFPageFragment.newInstance("F1");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_fragment_container_1, f1)
                .add(R.id.fl_fragment_container_2, TestFPageFragment.newInstance("F2"))
                .add(R.id.fl_fragment_container_3, TestFPageFragment.newInstance("F3"))
                .addToBackStack("hey");
        findViewById(R.id.fab_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction detach = fragmentTransaction.detach(f1);
                int commit = detach.commit();
            }
        });
        fragmentTransaction.commit();
    }
}