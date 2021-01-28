package com.example.cmaker.adapt.binder.server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dan.learn.lab2.binder.BookBinder;
import com.dan.learn.lab2.binder.BookService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, BookService.class);
        intent.setAction(BookBinder.DESCRIPTOR_BOOK_BINDER);
        startService(intent);
    }
}