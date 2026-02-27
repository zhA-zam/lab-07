package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Test 1: Check whether the activity correctly switched
    @Test
    public void testActivitySwitch() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city to switch activity
        onView(withText("Edmonton")).perform(click());

        // Verify that ShowActivity is displayed
        onView(withId(R.id.textView_cityName)).check(matches(isDisplayed()));
        onView(withId(R.id.button_back)).check(matches(isDisplayed()));
    }

    // Test 2: Test whether the city name is consistent
    @Test
    public void testCityNameConsistency() {

        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Vancouver"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city list
        onView(withText("Vancouver")).perform(click());

        // Verify that the TextView in ShowActivity displays the correct city name
        onView(withId(R.id.textView_cityName)).check(matches(withText("Vancouver")));
    }

    // Test 3: Test the "back" button
    @Test
    public void testBackButton() {
        String testCity = "Calgary";

        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText(testCity));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city list
        onView(withText(testCity)).perform(click());

        // Verify that ShowActivity is displayed
        onView(withId(R.id.textView_cityName)).check(matches(withText(testCity)));

        // Click the back button
        onView(withId(R.id.button_back)).perform(click());

        // Verify we switched back to MainActivity
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
        onView(withId(R.id.button_clear)).check(matches(isDisplayed()));
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }

}