package com.akexorcist.evenodd

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun `Should send show even event when text is positive even number`() {
        // Given
        val text = "6"

        // When
        viewModel.onTextEntered(text)

        // Then
        assertEquals(viewModel.showEvenResult.getOrAwaitValue().consume(), Unit)
    }

    @Test
    fun `Should send show even event when text is negative even number`() {
        // Given
        val text = "-6"

        // When
        viewModel.onTextEntered(text)

        // Then
        assertEquals(viewModel.showEvenResult.getOrAwaitValue().consume(), Unit)
    }

    @Test
    fun `Should send show odd event when text is positive odd number`() {
        // Given
        val text = "3"

        // When
        viewModel.onTextEntered(text)

        // Then
        assertEquals(viewModel.showOddResult.getOrAwaitValue().consume(), Unit)
    }

    @Test
    fun `Should send show odd event when text is negative odd number`() {
        // Given
        val text = "3"

        // When
        viewModel.onTextEntered(text)

        // Then
        assertEquals(viewModel.showOddResult.getOrAwaitValue().consume(), Unit)
    }

    @Test
    fun `Should send hide number result event when text is empty`() {
        // Given
        val text = ""

        // When
        viewModel.onTextEntered(text)

        // Then
        assertEquals(viewModel.hideNumberResult.getOrAwaitValue().consume(), Unit)
    }

    @Test
    fun `Should send hide number result event when text is null`() {
        // Given
        val text: String? = null

        // When
        viewModel.onTextEntered(text)

        // Then
        assertEquals(viewModel.hideNumberResult.getOrAwaitValue().consume(), Unit)
    }

    @Test
    fun `Should send hide number result event when text is not all digits`() {
        // Given
        val text = "1234ABC"

        // When
        viewModel.onTextEntered(text)

        // Then
        assertEquals(viewModel.hideNumberResult.getOrAwaitValue().consume(), Unit)
    }
}
