package com.dan.learn.lab2.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;

public class BookManagerProxy implements IBookManager {
    //打印 onTransact 调用 -------------- 1 this:com.dan.learn.lab2.binder.BookService$RealBookBinder@bafa16c
//BookService: 打印book对象 ----------- ：com.dan.learn.lab2.binder.Book@27ed335
    //BookManagerProxy: 打印book对象 ----------- ：com.dan.learn.lab2.binder.Book@30994fd
    private final IBinder mRemote;
    static final int TRANSACTION_addBook = android.os.IBinder.FIRST_CALL_TRANSACTION;
    static final int TRANSACTION_clearBooks = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_getBooks = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);

    public static final String DESCRIPTOR_BOOK_BINDER = "descriptor_book_binder";

    public BookManagerProxy(IBinder remote) {
        this.mRemote = remote;

        System.out.println("打印remote对象仍然是远程Binder的代理对象 BinderProxy + "+ remote);
    }

    @Override
    public void addBook(Book book) {
        Log.d("BookManagerProxy","打印book对象 ----------- ：" + book);
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        _data.writeInterfaceToken(DESCRIPTOR_BOOK_BINDER);
        if (book != null) {
            _data.writeInt(1);
            book.writeToParcel(_data, 0);
        } else {
            _data.writeInt(0);
        }
        try {
            boolean status = mRemote.transact(TRANSACTION_addBook, _data, _reply, 0);
            _reply.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            _data.recycle();
            _reply.recycle();
        }
    }

    @Override
    public List<Book> getBooks() {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        List<Book> _result = null;

        try {
            _data.writeInterfaceToken(DESCRIPTOR_BOOK_BINDER);
            mRemote.transact(TRANSACTION_getBooks,_data,_reply,0);
            _reply.readException();
            _result = _reply.createTypedArrayList(Book.CREATOR);

        } catch (RemoteException e) {
            e.printStackTrace();
            _data.recycle();
            _reply.recycle();
        }

        return _result;
    }

    @Override
    public void clearBooks() {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        _data.writeInterfaceToken(DESCRIPTOR_BOOK_BINDER);
        try {
            mRemote.transact(TRANSACTION_clearBooks,_data,_reply,0);
            _reply.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}
