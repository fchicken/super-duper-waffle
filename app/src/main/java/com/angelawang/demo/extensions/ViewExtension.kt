package com.angelawang.demo.extensions

import android.view.View
import com.angelawang.demo.classes.DebouncingOnClickListener

fun View.setOnClickListener(intervalMillis: Long = 0, doClick: (View) -> Unit) =
    setOnClickListener(DebouncingOnClickListener(intervalMillis = intervalMillis, doClick = doClick))