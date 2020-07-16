package com.dan.learn.lab2.ui;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.dan.learn.lab2.ui.activity.MainActivity;
import com.dan.learn.lab2.R;
import com.dan.learn.lab2.entity.FuncEntity;
import com.dan.learn.lab2.entity.FuncGroupEntity;
import com.dan.learn.lab2.helper.ExpandableListViewIdlingResource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

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
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void runTest() {
        IdlingResource idlingResource = new ExpandableListViewIdlingResource(rule.getActivity().findViewById(R.id.elv_expand_list));
        IdlingRegistry.getInstance().register(idlingResource);
        onData(instanceOf(FuncGroupEntity.class)).inAdapterView(withId(R.id.elv_expand_list)).atPosition(1).perform(click());
        onData(instanceOf(FuncEntity.class)).inAdapterView(withId(R.id.elv_expand_list)).onChildView(allOf(withId(R.id.tv_name), withText("自定义组件"))).atPosition(0).perform(click());
    }
}
