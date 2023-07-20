package com.redpine.core.extensions

import android.util.Patterns
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.textfield.TextInputEditText
import com.redpine.core.R
import java.util.Locale

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

fun View.onClickToPopBackStack() {
    this.setOnClickListener {
        findNavController().popBackStack()
    }
}

fun ImageView.loadImage(urls: String) {
    Glide.with(this)
        .load(urls)
        .error(R.drawable.ic_no_photo)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(android.R.drawable.ic_menu_camera)
        .centerCrop()
        .into(this)
}

fun SearchView.setSubmitTextListener(block: (query: String) -> Unit) {

    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String): Boolean {
            if (query.isNotEmpty()) {
                val capitalizedQuery = query.trim().replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                }
                block(capitalizedQuery)
            }
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            return false
        }
    })
}