package com.dan.learn.lab2.binder;

import java.util.List;

public interface IBookManager extends android.os.IInterface {

    void addBook(Book book);

    List<Book> getBooks();

    void clearBooks();
}
