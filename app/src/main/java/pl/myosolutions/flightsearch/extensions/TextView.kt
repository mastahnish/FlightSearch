package pl.myosolutions.flightsearch.extensions

import android.widget.TextView

fun TextView.text(): String = this.text.toString().trim()