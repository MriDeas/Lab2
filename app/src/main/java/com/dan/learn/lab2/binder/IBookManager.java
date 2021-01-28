package com.dan.learn.lab2.binder;

import android.os.IInterface;

import java.util.List;

public interface IBookManager extends IInterface {

    void addBook(Book book);

    List<Book> getBooks();

    void clearBooks();
}
