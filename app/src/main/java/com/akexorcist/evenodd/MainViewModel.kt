package com.akexorcist.evenodd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _showEvenResult = MutableLiveData<SingleEvent<Unit>>()
    val showEvenResult: LiveData<SingleEvent<Unit>> = _showEvenResult

    private val _showOddResult = MutableLiveData<SingleEvent<Unit>>()
    val showOddResult: LiveData<SingleEvent<Unit>> = _showOddResult

    private val _hideNumberResult = MutableLiveData<SingleEvent<Unit>>()
    val hideNumberResult: LiveData<SingleEvent<Unit>> = _hideNumberResult

    fun onTextEntered(text: String?) {
        text?.toIntOrNull()?.let { number ->
            showNumberResult(number)
        } ?: run {
            _hideNumberResult.value = SingleEvent(Unit)
        }
    }

    private fun showNumberResult(number: Int) {
        if (number % 2 == 0) {
            _showEvenResult.value = SingleEvent(Unit)
        } else {
            _showOddResult.value = SingleEvent(Unit)
        }
    }
}
