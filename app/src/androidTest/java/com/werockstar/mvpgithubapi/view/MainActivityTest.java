package com.werockstar.mvpgithubapi.view;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.werockstar.mvpgithubapi.ElapsedTimeIdlingTime;
import com.werockstar.mvpgithubapi.R;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activity =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void put_github_werockstar_should_show_Kotchaphan_Muangsan() {
        onView(withId(R.id.edtUsername))
                .perform(typeText("werockstar"), closeSoftKeyboard());
        onView(withId(R.id.btnLoad))
                .perform(click());

        Espresso.registerIdlingResources(new ElapsedTimeIdlingTime(5));

        onView(withId(R.id.tvFullName))
                .check(matches(withText("Kotchaphan Muangsan")));

        Espresso.unregisterIdlingResources();
    }

}