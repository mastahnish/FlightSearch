package pl.myosolutions.flightsearch.extensions

import android.text.InputFilter
import android.text.InputType
import android.widget.EditText
import android.widget.TextView

fun TextView.text(): String = this.text.toString().trim()

fun EditText.onlyUppercase() {
    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
    filters = arrayOf(InputFilter.AllCaps())
}