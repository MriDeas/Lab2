package com.dan.learn.lab2.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookService extends Service {

    private final List<Book> bookList = new ArrayList<>();

    @Override
    public IBinder onBind(Intent intent) {
        return new RealBookBinder();
    }

    private class RealBookBinder extends BookBinder {

        @Override
        public void addBook(Book book) {
            Log.d("BookService","打印book对象 ----------- ：" + book);
            bookList.add(book);
        }

        @Override
        public List<Book> getBooks() {
            return bookList;
        }

        @Override
        public void clearBooks() {
            if (bookList != null) {
                bookList.clear();
            }
        }
    }
}