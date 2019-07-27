package com.ciompa.cleverlance.ui


import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.ciompa.cleverlance.R
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    lateinit var mDevice: UiDevice
    lateinit var launcherPackage: String
    lateinit var context: Context

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mDevice = UiDevice.getInstance(getInstrumentation())
        launcherPackage = mDevice.getLauncherPackageName()
        assertThat<Any>(launcherPackage, notNullValue())

        val instrumentation = getInstrumentation()
        context = instrumentation.getTargetContext()
    }

    @Test
    fun download_OK() {

        click(R.id.usernameEditText)
        typeText(R.id.usernameEditText, "ciompa")

        click(R.id.passwordEditText)
        typeText(R.id.passwordEditText, "frantisek")

        click(R.id.downloadButton)

        wait2()
        isDisplayed(R.id.downloadedViewImage)

        mDevice.openQuickSettings()

    }


    private fun click(viewId: Int) {
        onView(withId(viewId)).perform(androidx.test.espresso.action.ViewActions.click())
    }

    private fun typeText(viewId: Int, text: String) {
        onView(withId(viewId)).perform(androidx.test.espresso.action.ViewActions.replaceText(text))
    }

    private fun isDisplayed(viewId: Int) {
        onView(withId(viewId)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun wait2() {
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), WAIT_TIMEOUT)
    }

    companion object {
        const val WAIT_TIMEOUT = 10_000.toLong()
    }
}
