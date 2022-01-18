package com.angelawang.demo.classes

import android.view.View

class DebouncingOnClickListener(
    private val intervalMillis: Long,
    private val doClick: ((View) -> Unit)
) : View.OnClickListener {

    companion object {
        var enabled = true
        private val ENABLE_AGAIN =
            Runnable { enabled = true }
    }

    override fun onClick(v: View) {
        if (enabled) {
            enabled = false
            v.postDelayed(ENABLE_AGAIN, intervalMillis)
            doClick(v)
        }
    }
}