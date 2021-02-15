package com.example.oblig;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuizTest {

    @Rule
    public IntentsTestRule<quizActivity> testrule = new IntentsTestRule<>(quizActivity.class);

    @Test
    public void scoreCorrect() {
        ImgItem item = testrule.getActivity().item;
        int score = testrule.getActivity().score;

        onView(withId(R.id.inputQuiz)).perform(replaceText(item.getNavn()));
        onView(withId(R.id.btn_sendSvar)).perform(click());

        assertEquals(testrule.getActivity().score, score+1);
    }

    @Test
    public void scoreFalse() {
        int score = testrule.getActivity().score;

        onView(withId(R.id.inputQuiz)).perform(replaceText("false"));
        onView(withId(R.id.btn_sendSvar)).perform(click());
        assertEquals(testrule.getActivity().score, score);
    }
}