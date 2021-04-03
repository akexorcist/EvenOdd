package com.akexorcist.evenodd

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.akexorcist.evenodd.screen.MainScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUiTest {
    @Rule
    @JvmField
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun shouldEvenResultWithIndigoTextColorWhenTextIsEvenNumber() {
        onScreen<MainScreen> {
            editTextInput {
                hasEmptyText()
                typeText("12")
            }
            textViewPrefixResult.isDisplayed()
            textViewResult {
                isDisplayed()
                hasText("even")
                hasTextColor(R.color.indigo_400)
            }
        }
    }

    @Test
    fun shouldOddResultWithIndigoTextColorWhenTextIsOddNumber() {
        onScreen<MainScreen> {
            editTextInput {
                hasEmptyText()
                typeText("31")
            }
            textViewPrefixResult.isDisplayed()
            textViewResult {
                isDisplayed()
                hasText("odd")
                hasTextColor(R.color.pink_400)
            }
        }
    }

    @Test
    fun shouldType5DigitNumbersMaximum() {
        onScreen<MainScreen> {
            editTextInput {
                typeText("12345678")
                hasText("12345")
            }
        }
    }

    @Test
    fun shouldShowHintAndHideResultWhenAllNumbersRemoved() {
        onScreen<MainScreen> {
            editTextInput {
                typeText("1234")
                hasText("1234")
                clearText()
                hasEmptyText()
            }
            textViewPrefixResult.isNotDisplayed()
            textViewResult.isNotDisplayed()
        }
    }
}
