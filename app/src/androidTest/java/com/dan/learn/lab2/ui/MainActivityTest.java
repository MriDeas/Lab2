package com.dan.learn.lab2.ui;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.activity.CustomWidgetsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by: dan
 * Created time: 2020/7/3
 * Description:
 * Modify time:
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(CustomWidgetsActivity.class);

    @Test
    public void runTest() {
        onView(withText("TEXTVIEW")).perform(click());
        onView(withId(R.id.bt_load)).perform(click());
        onView(withText("小控件")).perform(click());
    }
}
