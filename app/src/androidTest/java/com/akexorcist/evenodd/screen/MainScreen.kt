package com.akexorcist.evenodd.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.akexorcist.evenodd.R

class MainScreen : Screen<MainScreen>() {
    val editTextInput = KEditText { withId(R.id.editTextInput) }
    val textViewPrefixResult = KTextView { withId(R.id.textViewPrefixResult) }
    val textViewResult = KTextView { withId(R.id.textViewResult) }
}
