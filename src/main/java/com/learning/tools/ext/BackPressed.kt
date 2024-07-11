package com.learning.tools.ext

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

/**
 * 注册OnBackPressedCallback()方法来处理返回手势
 * Create by Qing at 2024/7/11 11:18
 */
fun AppCompatActivity.onBackPressed(isEnabled: Boolean, callback: () -> Unit) {
    onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(isEnabled) {
        override fun handleOnBackPressed() {
            callback()
        }
    })
}