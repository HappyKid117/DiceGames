package androidsamples.java.dicegames;
 
import static org.junit.Assert.assertEquals;
 
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.TextView;
 
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
 
import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;
 
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
 
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class WalletInstrumentedTest {
 
  @Rule
  public ActivityScenarioRule<WalletActivity> activityRule = new ActivityScenarioRule<>(WalletActivity.class);
 
  @Rule
  public ActivityTestRule<WalletActivity> mActivityTestRule = new ActivityTestRule<>(WalletActivity.class);
 
  String getText(final Matcher<View> matcher) {
    final String[] stringHolder = { null };
    onView(matcher).perform(new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return isAssignableFrom(TextView.class);
      }
 
      @Override
      public String getDescription() {
        return "getting text from a TextView";
      }
 
      @Override
      public void perform(UiController uiController, View view) {
        TextView tv = (TextView)view; //Save, because of check in getConstraints()
        stringHolder[0] = tv.getText().toString();
      }
    });
    return stringHolder[0];
  }

  @Test
  public void testPortraitDieRoll() {
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
  }

  public void testLandscapeDieRoll() {
    mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
  }
 
  @Test
  public void testRollValuePersistsOnRotation() {
    onView(withId(R.id.btn_die)).perform(click());
    mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    onView(withId(R.id.txt_total_rolls)).check(matches(withText("1")));
  }
 
 
  @Test
  public void testDieValuePersistsOnRotation() {
    String value = getText(withId(R.id.txt_coins));
    mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    assert(value.equals(getText(withId(R.id.txt_coins))));
  }
 
  // credit: Manthan Asher
  @Test
  public void testPreviousValuePersistOnRotation() {
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    String prev_val_before = getText(withId(R.id.txt_prev_roll));
    mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    onView(withId(R.id.txt_prev_roll)).check(matches(withText(prev_val_before)));
  }
 
  //credit: Manthan Asher
  @Test
  public void testDoubleSixesPersistOnRotation() {
    onView(withId(R.id.btn_die)).perform(click());
    String double_sixes_before = getText(withId(R.id.txt_double_sixes));
    mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    onView(withId(R.id.txt_double_sixes)).check(matches(withText(double_sixes_before)));
  }

}