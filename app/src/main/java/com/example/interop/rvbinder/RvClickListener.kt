package com.example.interop.rvbinder

import android.view.View

interface RvClickListener {
    fun click(
        view: View,
        item: Any?,
        position: Int,
        adapter: GlobalAdapter<Any>
    )
}