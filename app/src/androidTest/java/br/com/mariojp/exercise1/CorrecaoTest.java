package br.com.mariojp.exercise1;

import android.content.Context;
import android.content.pm.ActivityInfo;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnitRunner;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.action.ViewActions.*;


import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class CorrecaoTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("br.com.mariojp.exercise1", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void textoInicial() {
        onView(withId(R.id.editNome))
                .check(matches(withText("")));
        onView(withId(R.id.labelMensagem))
                .check(matches(withText("Alô, Mundo!")));
    }

    @Test
    public void mudaTexto() {
        onView(withId(R.id.editNome))
                .perform(typeText("1234"));

        onView(withId(R.id.editNome)).perform(ViewActions.closeSoftKeyboard());


        onView(withId(R.id.btnCumprimentar))
                .perform(click());

        onView(withId(R.id.labelMensagem))
                .check(matches(withText("Alô, 1234!")));
    }

    @Test
    public void mantemLabelECaixaDeTextoAposRotacao() {
        onView(withId(R.id.editNome))
                .perform(typeText("1234"));

        onView(withId(R.id.editNome)).perform(ViewActions.closeSoftKeyboard());


        onView(withId(R.id.btnCumprimentar))
                .perform(click());

        onView(withId(R.id.editNome))
                .perform(typeText("56"));

        onView(withId(R.id.editNome)).perform(ViewActions.closeSoftKeyboard());


        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.labelMensagem))
                .check(matches(withText("Alô, 1234!")));
        onView(withId(R.id.editNome))
                .check(matches(withText("123456")));
    }
}
