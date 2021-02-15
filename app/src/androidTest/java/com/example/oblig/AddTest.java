package com.example.oblig;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import static android.app.Instrumentation.ActivityResult;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import org.hamcrest.Matcher;
import static androidx.test.espresso.intent.Intents.intending;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddTest {

    @Rule
    public IntentsTestRule<addPictureActivity> testrule = new IntentsTestRule<>(addPictureActivity.class);

    @Test
    public void add() {
        int numItems = testrule.getActivity().numItems;

        Matcher<Intent> intents = allOf(hasAction(Intent.ACTION_PICK));

        ActivityResult aResult = testPick();
        intending(intents).respondWith(aResult);

        onView(withId(R.id.btn_velgBilde)).perform(click());
        intended(intents);

        onView(withId(R.id.EditText01)).perform(replaceText("Test"));
        onView(withId(R.id.btn_leggtil)).perform(click());

        assertEquals(testrule.getActivity().db.imgdeo().getAllitems().size(), numItems + 1);
    }

    private ActivityResult testPick() {
        Uri uri = Uri.parse("android.resource://com.example.oblig/drawable/" + R.drawable.testimg);
        Intent i = new Intent();
        i.setData(uri);
        return new ActivityResult(Activity.RESULT_OK, i);
    }
}