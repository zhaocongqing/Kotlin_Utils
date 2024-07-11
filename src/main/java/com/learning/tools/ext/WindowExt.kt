package com.learning.tools.ext

import android.graphics.Color
import android.view.Window
import androidx.core.view.WindowCompat

/**
 * 适配 edge-to-edge
 * Create by Qing at 2024/7/9 15:58
 */
fun Window.adaptEdge2Edge() {
    //沉浸式导航栏和状态栏
    WindowCompat.setDecorFitsSystemWindows(this, false)
    this.statusBarColor = Color.TRANSPARENT
    this.navigationBarColor = Color.TRANSPARENT
}

/**
 * 设置状态栏字体颜色
 */
var Window.isLightStatusBar: Boolean
    get() {
        return WindowCompat.getInsetsController(this, this.decorView).isAppearanceLightStatusBars
    }
    set(value) {
        WindowCompat.getInsetsController(this, this.decorView).isAppearanceLightStatusBars = value
    }

/**
 * 设置状态栏背景颜色
 */
var Window.statusBarBgColor: Int
    get() {
        return this.statusBarColor
    }
    set(value) {
        this.statusBarColor = value
    }