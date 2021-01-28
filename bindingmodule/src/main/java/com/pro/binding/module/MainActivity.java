package com.pro.binding.module;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pro.binding.module.databinding.ActivityMainBinding;
import com.pro.binding.module.databinding.DialogBindingDemoBinding;
import com.pro.binding.module.databinding.IncludeLayoutBinding;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        IncludeLayoutBinding includeV = mainBinding.includeV;

        includeV.btIncludeBt1.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "include", Toast.LENGTH_LONG).show();
        });
        mainBinding.tvText1.setText("你好，ViewBinding");
        mainBinding.btButton1.setOnClickListener(v -> {
//            showDialog();
            showDialog2();
        });
    }

    private void showDialog() {
        Dialog dialog = new Dialog(context);
        DialogBindingDemoBinding binding = DialogBindingDemoBinding.inflate(getLayoutInflater());
        dialog.setContentView(binding.getRoot());
        binding.tvTitle.setText("ViewBinding Dialog测试");
        binding.tvContent.setText("就是这些内容");
        binding.btConfirm.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private void showDialog2() {
        DialogDemoFragment dialogFragment = DialogDemoFragment.newInstance();
        dialogFragment.show(getSupportFragmentManager(), "heyDialogFragment");
    }
}