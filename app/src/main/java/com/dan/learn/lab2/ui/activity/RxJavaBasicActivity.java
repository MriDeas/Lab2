package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseActivity;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxJavaBasicActivity extends BaseActivity {

    private static final String TAG = RxJavaBasicActivity.class.getCanonicalName() + " ----------- ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("RxJava 基础");

        binding();
//        flow();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_rx_java_basic;
    }

    Disposable disposable;

    private void binding() {

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                emitter.onNext("hello");
                emitter.onNext("world");
                emitter.onNext("你好");
                emitter.onNext("世界");
                emitter.onComplete();
            }
        });

        Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                Log.d(TAG, "onSubscribe  isDisposed: " + d.isDisposed());
            }

            @Override
            public void onNext(String o) {
                Log.d(TAG, "onNext: " + o);
                disposable.dispose(); //停止观察指定的流
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete isDisposed:" + disposable.isDisposed());
            }
        };

//        observable.subscribe(observer);

        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1234567");
            }
        });
        Disposable subscribe = stringObservable.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {

                return Integer.parseInt(s);
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("RxJava ---------- ","doOnNext -> accept :" + integer);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("RxJava ---------- ","subscribe -> accept :" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("RxJava ---------- ","Consumer -> accept throwable : " + throwable);
            }
        });

    }

    public void flow() {
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {

            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {

            }
        }, BackpressureStrategy.BUFFER);

        flowable.subscribe();
    }

    public void single() {

    }

    public void complete() {

    }

    public void maybe() {

    }
}
