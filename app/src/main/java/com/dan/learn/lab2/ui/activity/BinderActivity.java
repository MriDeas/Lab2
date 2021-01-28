package com.dan.learn.lab2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.binder.Book;
import com.dan.learn.lab2.binder.BookManagerProxy;
import com.dan.learn.lab2.binder.IBookManager;

import java.util.List;
import java.util.Random;

public class BinderActivity extends AppCompatActivity {

    private LocalServiceConn localServiceConn = new LocalServiceConn();
    private BookManagerProxy managerProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        bind();
    }

    private void bind() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.cmaker.adapt.binder.server",
                "com.dan.learn.lab2.binder.BookService"));
        bindService(intent, localServiceConn, BIND_AUTO_CREATE);
    }

    public void addBook(View view) {
        if (managerProxy != null) {
            Book book = new Book();
            book.setId(new Random().nextInt());
            book.setName("Binder学习指南");
            book.setAuthor("小龙");
            book.setPrice(100);
            managerProxy.addBook(book);
        }
    }

    public void getAllBooks(View view) {
        if (managerProxy != null) {
            List<Book> books = managerProxy.getBooks();
            System.out.println("打印books:" + books);
        }
    }

    public void clearBooks(View view) {
        if (managerProxy != null) {
            managerProxy.clearBooks();
            getAllBooks(view);
        }
    }


    private class LocalServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("打印连接 onServiceConnected  线程：" + Thread.currentThread().getName());
            managerProxy = new BookManagerProxy(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("打印断开连接 onServiceDisconnected");
            managerProxy = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (localServiceConn != null) {
            localServiceConn = null;
        }
    }
}