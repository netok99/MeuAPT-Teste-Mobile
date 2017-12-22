package com.meuapttestemobile;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.meuapttestemobile.presentation.Shot.ShotActivity;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import kotlin.jvm.JvmField;
import kotlin.jvm.Throws;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@RunWith(AndroidJUnit4.class)
public class ShotActivityTest  {

    private MockWebServer server;

    @Rule
    @JvmField
    public ActivityTestRule<ShotActivity> mActivityRule =
            new ActivityTestRule(ShotActivity.class, false, false);

    @Before
    @Throws(exceptionClasses = Exception.class)
    public void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
        setupServerUrl();
    }

    private void setupServerUrl() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        server.url("https://api.dribbble.com/v1/shots?access_token=84ec8334d80acb8d849f9d2f24038d25df170bcce1b3d0d998543cfb81bb7e8e&page=1");
    }

    @Test
    public void whenResultIsOkShouldDisplayListWithGists() {
        server.enqueue(new MockResponse().setResponseCode(200));
        mActivityRule.launchActivity(createIntent());
        Espresso.onView(ViewMatchers.withId(R.id.shotsList)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    private Intent createIntent() {
        return new Intent().putExtra("id", "teste");
    }

    @Test
    public void checkUserItemViewIsDisplayed() {
        server.enqueue(new MockResponse().setResponseCode(200));
        mActivityRule.launchActivity(createIntent());
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.txtTitle), ViewMatchers.withText("usa"))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @After
    @Throws(exceptionClasses = Exception.class)
    public void tearDown() throws IOException {
        server.shutdown();
    }
}
