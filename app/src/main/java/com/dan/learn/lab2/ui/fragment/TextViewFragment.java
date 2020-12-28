package com.dan.learn.lab2.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.utils.NetImageGetter;
import com.dan.learn.lab2.utils.SizeUtil;
import com.dan.learn.lab2.widget.TextViewExtendView;

import butterknife.BindView;
import butterknife.OnClick;

public class TextViewFragment extends BaseFragment {


    @BindView(R.id.tv_text_1)
    TextView tv_text_1;
    @BindView(R.id.tv_text_extend)
    TextViewExtendView tv_text_extend;
    @BindView(R.id.bt_get_matrix)
    TextView bt_get_matrix;
    @BindView(R.id.tv_text_2)
    TextView tv_text_2;
    @BindView(R.id.tv_text_3)
    TextView tv_text_3;
    @BindView(R.id.et_editor)
    EditText et_editor;

    private Activity mContext;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    private TextViewFragment(String title) {
        super(title);
    }

    public static TextViewFragment newInstance(String title) {
        return new TextViewFragment(title);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_text_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void test1() {
        String str1 = "TextView 加载图片测试\n <img src=" + R.drawable.ic_launcher_background +
                "></img><font color='red'>小眯眼<font>";
        Spanned spanned = Html.fromHtml(str1, source -> {
            Drawable drawable = null;
            if (TextUtils.isDigitsOnly(source)) {
                int id = Integer.parseInt(source);
                drawable = getResources().getDrawable(id);
                if (drawable != null) {
                    float size = SizeUtil.dp2px(18);
                    drawable.setBounds(0, 0, (int) size, (int) size);
                }
            }
            return drawable;
        }, (opening, tag, output, xmlReader) -> {

        });
        tv_text_1.setText(spanned);
    }

    private void test2() {
        String imageUrl = "https://dss1.bdstatic.com/6OF1bjeh1BF3odCf/it/u=3026049548,2736935058&fm=85&app=92&f=JPEG?w=121&h=75&s=15B87D7F08700082698CF0F503008060";
        String imageUrl2 = "https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1722506617,2322243230&fm=26&gp=0.jpg";

        String str2 = "<img src='" + imageUrl + "'></img> <font color='red'>小米眼👁:</font> 回复" +
                " <img src=" + imageUrl2 + "></img><font color='blue'>达米彦 :</font> Robolectric支持单元测试范围从Activity的跳转、" +
                "Activity展示View（包括菜单）和Fragment到View的点击触摸以及事件响应，同时Robolectric也能测试Toast和Dialog。" +
                "对于需要网络请求数据的测试，Robolectric可以模拟网络请求的response。对于一些Robolectric不能测试的对象，" +
                "比如ConcurrentTask，可以通过自定义Shadow的方式现实测试。下面将着重介绍Robolectric的常见用法。 " +
                "Robolectric 2.4模拟网络请求 由于商业App的多数Activity界面数据都是通过网络请求获取，因为网络请求是大多数App首要处理的模块，" +
                "测试依赖网络数据的Activity时，可以在@Before标记的函数中准备网络数据，进行网络请求的模拟";

        tv_text_extend.setImageGetter(new NetImageGetter().buildSize(SizeUtil.dp2px(18),
                SizeUtil.dp2px(18)).setContext(mContext));
        tv_text_extend.bindText(str2);

        String trim = et_editor.getText().toString().trim();
        boolean hasFocus = et_editor.hasFocus();
        System.out.println("打印输出 ------ " + trim + " hasFocus:" + hasFocus);
        et_editor.setSelection(0,10);

    }

    private void test3() {

        tv_text_2.setText("dDeEfFgGqPpYyIiLlQq");
        tv_text_3.setText("dDeEfFgGqPpYyIiLlQq");

//        TextPaint paint = tv_text_1.getPaint();
//        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
//        float fontSpacing = paint.getFontSpacing();
//
//        String str = "ascent:" + fontMetrics.ascent + " descent:"
//                + fontMetrics.descent + " top:" + fontMetrics.top
//                + " bottom:" + fontMetrics.bottom + " leading:" + fontMetrics.leading;
//
//        Rect bound = new Rect();
//        paint.getTextBounds(word,0,word.length(),bound);
//        String str2 = " l:" + bound.left + " t:" + bound.top + " r:" + bound.right + " b:" + bound.bottom + " fontSpacing:" + fontSpacing;

        String textInfo1 = getTextInfo(tv_text_2);
        String textInfo2 = getTextInfo(tv_text_3);

        tv_text_1.setText(textInfo1 + "\n\n" + textInfo2);
    }

    private String getTextInfo(TextView textView){
        String word = "你好";
        TextPaint paint = textView.getPaint();
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float fontSpacing = paint.getFontSpacing();

        String str = "ascent:" + fontMetrics.ascent + " descent:"
                + fontMetrics.descent + " top:" + fontMetrics.top
                + " bottom:" + fontMetrics.bottom + " leading:" + fontMetrics.leading;

        Rect bound = new Rect();
        paint.getTextBounds(word,0,word.length(),bound);
        String str2 = " l:" + bound.left + " t:" + bound.top + " r:" + bound.right + " b:" + bound.bottom + " fontSpacing:" + fontSpacing;
        return str + str2;
    }

    @OnClick({R.id.bt_load, R.id.bt_get_matrix})
    public void load(View v) {
//        test1();
        switch (v.getId()) {
            case R.id.bt_get_matrix:
                test3();
                break;
            case R.id.bt_load:
                test2();
                break;
        }

    }
}
