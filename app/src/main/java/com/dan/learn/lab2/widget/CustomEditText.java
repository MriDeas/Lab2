package com.dan.learn.lab2.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: dan
 * Created time: 2020/11/26
 * Description:
 * Modify time:
 * Demand:
 * 1. 当包含符号时，且
 */
public class CustomEditText extends androidx.appcompat.widget.AppCompatEditText implements View.OnFocusChangeListener {

    String symbolPattern = "[`~!@#$^&*()=|{}':;',\\\\[\\\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]";
    private Pattern mPattern;

    public CustomEditText(Context context) {
        this(context, null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    {
        setOnFocusChangeListener(this);
        setSelectAllOnFocus(true);
        mPattern = Pattern.compile(symbolPattern);
    }

    private void print(String msg) {
        System.out.println("打印EditText 消息：---------- " + msg);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        print("是否有焦点 : " + hasFocus);

        if (hasFocus) {
            Editable text = getText();
            String str = text == null ? "" : text.toString();
            if (!TextUtils.isEmpty(str)) {
                Matcher matcher = mPattern.matcher(str);
                boolean has = matcher.find();

                if (has) {
                    int start1 = matcher.start();
                    int endIndex = 0;
                    for (int i = str.length() - 1; i >= 0; i--) {
                        char c = str.charAt(i);
                        String cc = c + "";
                        if (mPattern.matcher(cc).find()) {
                            endIndex = i;
                            break;
                        }
                    }
                    print("选中区域 start:" + start1 + " end:" + endIndex);
                    setSelection(start1, endIndex + 1);
                    setCursorVisible(true);
                }
            }
        }
    }

}
