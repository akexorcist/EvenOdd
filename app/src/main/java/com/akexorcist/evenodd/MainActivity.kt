package com.akexorcist.evenodd

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.akexorcist.evenodd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.editTextInput.doOnTextChanged { text, _, _, _ ->
            updateShadowText(text?.toString())
            updateResult(text?.toString())
        }

        viewModel.showEvenResult.observe(this) { onShowEvenResult() }
        viewModel.showOddResult.observe(this) { onShowOddResult() }
        viewModel.hideNumberResult.observe(this) { onHideNumberResult() }

        Handler(Looper.getMainLooper()).postDelayed({
            showKeyboard(binding.editTextInput)
        }, 500L)
    }

    private fun updateShadowText(text: String?) {
        when {
            text.isNullOrEmpty() -> binding.textViewShadowInput.setText(R.string.number_hint)
            else -> binding.textViewShadowInput.text = text
        }
    }

    private fun updateResult(text: String?) {
        viewModel.onTextEntered(text)
    }

    private fun onShowEvenResult() {
        binding.textViewResult.isEnabled = false
        binding.textViewResult.text = getString(R.string.even)
        binding.textViewResult.visibility = View.VISIBLE
        binding.textViewPrefixResult.visibility = View.VISIBLE
    }

    private fun onShowOddResult() {
        binding.textViewResult.isEnabled = true
        binding.textViewResult.text = getString(R.string.odd)
        binding.textViewResult.visibility = View.VISIBLE
        binding.textViewPrefixResult.visibility = View.VISIBLE
    }

    private fun onHideNumberResult() {
        binding.textViewResult.text = ""
        binding.textViewResult.visibility = View.GONE
        binding.textViewPrefixResult.visibility = View.GONE
    }

    private fun showKeyboard(view: View) {
        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}
