package com.redpine.core.extensions

import android.util.Patterns
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText

fun String.emailValidation() =
    Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.passwordValidation(): Boolean {
    val passwordPattern = "(?=.*[a-z])(?=.*\\d)"
    val reg = Regex(passwordPattern)

    return reg.find(this) != null && this.length >= 8
}

fun TextInputEditText.onTextChanged(action: (text: String) -> Unit) =
    this.doOnTextChanged { text, _, _, _ ->
        action(text.toString())
    }

fun View.onClickToPopBackStack(){
    this.setOnClickListener{
        findNavController().popBackStack()
    }
}