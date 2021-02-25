package com.example.jni;

public class MediaRecorder {
    static {
        System.loadLibrary("media_jni");
        native_init();
    }

    public void add() {

    }

    public String appendParam(String p) {
        return "hello" + p;
    }

    private static native final void native_init();

    public native void start() throws IllegalStateException;
}
