/*
 * Copyright 2024 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.composebugrepro

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import java.util.Collections

@OptIn(ExperimentalTestApi::class)
@RunWith(Parameterized::class)
class ExampleInstrumentedTest {


    @JvmField
    @Parameter(0)
    var iteration: Int? = null

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Int> {
            return Collections.nCopies(1000, 0)
        }
    }

    @get:Rule
    val composeRule = AndroidComposeTestRule<TestRule, ComponentActivity>(
        activityRule = TestRule { base, _ -> base },
        Dispatchers.IO,
        activityProvider = {
            error(
                "createEmptyComposeRule() does not provide an Activity to set Compose content in." +
                        " Launch and use the Activity yourself, or use createAndroidComposeRule()."
            )
        }
    )

    @Test
    fun testLoadingCompleted() {
        launchActivity()

        while(composeRule.onAllNodes(hasText("Content loaded")).fetchSemanticsNodes().isEmpty()) {
           // do nothing.
        }
    }

    private fun launchActivity() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(context, MainActivity::class.java)
        ActivityScenario.launch<MainActivity>(intent)
    }
}
