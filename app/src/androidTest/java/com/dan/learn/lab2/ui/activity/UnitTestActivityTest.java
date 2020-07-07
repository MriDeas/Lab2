package com.dan.learn.lab2.ui.activity;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.entity.Item;
import com.dan.learn.lab2.idle.SampleIdlingResource;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.setFailureHandler;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by: dan
 * Created time: 2020/7/6
 * Description:
 * Modify time:
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class UnitTestActivityTest {

    @Rule
    public ActivityTestRule<UnitTestActivity> activityRule = new ActivityTestRule<>(UnitTestActivity.class);
    private SampleIdlingResource resource;

    @Before
    public void testBefore() {
        setFailureHandler(new ErrorDealHandler(activityRule.getActivity()));
        activityRule.getActivity();
        resource = new SampleIdlingResource(activityRule.getActivity());
    }

    @After
    public void testAfter() {
        IdlingRegistry.getInstance().unregister(resource);
    }

    @Test
    public void testText() {
        closeKeyBoard();
        viewMatch();

        checkExist();
        listChildAction();
        asynchronous();

//        mapListOperation();
//        listOperation();
        asynchronous();
    }

    private void closeKeyBoard() {
        onView(allOf(withText("煎蛋减淡蒹葭"), hasSibling(withText("2")))).perform(ViewActions.closeSoftKeyboard());
    }

    private void viewMatch() {
        onView(withId(R.id.tv_test_1)).check(matches(withText("煎蛋减淡蒹葭")));
        onView(withId(R.id.et_input_view)).perform(click()).perform(replaceText("简单爱"));
        onView(withId(R.id.et_input_view)).check(matches(withText(containsString("简单"))));
        onView(allOf(withText("煎蛋减淡蒹葭"), withId(R.id.bt_action))).check(matches(isDisplayed()));
    }

    private void checkExist() {
        onView(allOf(withText("煎蛋简单"), withId(R.id.bt_action_2))).check(doesNotExist());
    }

    private void listChildAction() {
        onData(instanceOf(Item.class)).atPosition(66).perform(scrollTo()).perform(click());
        onData(instanceOf(Item.class)).atPosition(70).onChildView(withId(R.id.cb_box)).perform(scrollTo()).perform(click());
    }

    private void mapListOperation() {
        onData(allOf(is(instanceOf(Map.class)), hasEntry(equalTo("STR"), is("item: 9")))).perform(scrollTo()).perform(click());
        onData(allOf(is(instanceOf(Map.class)), hasEntry(equalTo("STR"), is("item: 15")))).perform(scrollTo()).perform(click());
        onData(allOf(is(instanceOf(Map.class)), hasEntry(equalTo("STR"), is("item: 20")))).perform(scrollTo()).perform(click());
    }

    private void listOperation() {
        onData(allOf(is(instanceOf(String.class)), equalTo("Item :2"))).perform(scrollTo()).perform(click());
        onData(allOf(is(instanceOf(String.class)), equalTo("Item :14"))).perform(scrollTo()).perform(click());
        onData(allOf(is(instanceOf(String.class)), equalTo("Item :90"))).perform(scrollTo()).perform(click());
    }

    private void asynchronous() {
        boolean contains = IdlingRegistry.getInstance().getResources().contains(resource);
        onView(withId(R.id.bt_action)).perform(click());
        if(!contains){
            IdlingRegistry.getInstance().register(resource);
        }
        onView(withId(R.id.tv_test_1)).check(matches(withText("张三的歌")));
    }

    private static class ErrorDealHandler implements FailureHandler {

        private final FailureHandler mHandler;

        private ErrorDealHandler(Context context) {
            this.mHandler = new DefaultFailureHandler(context);
        }

        @Override
        public void handle(Throwable error, Matcher<View> viewMatcher) {
            System.out.println("打印下错误处理日志 ------- " + error);
            mHandler.handle(error, viewMatcher);
        }
    }

}
