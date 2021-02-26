package com.dan.learn.lab2.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public abstract class BookBinder extends Binder implements IBookManager {

    static final int TRANSACTION_addBook = android.os.IBinder.FIRST_CALL_TRANSACTION;
    static final int TRANSACTION_clearBooks = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_getBooks = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);

    public static final String DESCRIPTOR_BOOK_BINDER = "descriptor_book_binder";

    @Override
    public void attachInterface(@Nullable IInterface owner, @Nullable String descriptor) {
        super.attachInterface(owner, descriptor);
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        System.out.println("打印 onTransact 调用 -------------- " + code + " this:" + this + " 线程：" + Thread.currentThread().getName());

        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR_BOOK_BINDER);
                return true;
            case TRANSACTION_addBook:
                data.enforceInterface(DESCRIPTOR_BOOK_BINDER);
                Book _book;
                if (data.readInt() != 0) {
                    _book = Book.CREATOR.createFromParcel(data);
                } else {
                    _book = null;
                }

                this.addBook(_book);
                reply.writeNoException();
                return true;
            case TRANSACTION_getBooks:
                data.enforceInterface(DESCRIPTOR_BOOK_BINDER);
                List<Book> books = this.getBooks();
                reply.writeNoException();
                reply.writeTypedList(books);
                return true;
            case TRANSACTION_clearBooks:
                data.enforceInterface(DESCRIPTOR_BOOK_BINDER);
                this.clearBooks();
                reply.writeNoException();
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }


}
