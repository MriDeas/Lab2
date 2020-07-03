package com.dan.learn.lab2.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.dan.learn.lab2.entity.Size;

import java.util.concurrent.ExecutionException;

/**
 * Created by: dan
 * Created time: 2020/7/3
 * Description:
 * Modify time:
 */
public class NetImageGetter implements Html.ImageGetter {

    private Size mSize;
    private Context mContext;

    @Override
    public Drawable getDrawable(String source) {
        Size size = mSize == null ? new Size(20, 20) : mSize;
        Context context = mContext;
        Drawable drawable = null;
        try {
            FutureTarget<Drawable> submit = Glide.with(context).load(source)
                    .apply(new RequestOptions().circleCrop()).submit();
            drawable = submit.get();
            if (drawable != null) {
                drawable.setBounds(0, 0, (int) size.getWidth(), (int) size.getHeight());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return drawable;
    }


    public NetImageGetter buildSize(float width, float height) {
        mSize = new Size(width, height);
        return this;
    }

    public NetImageGetter setContext(Context context) {
        mContext = context;
        return this;
    }


}
