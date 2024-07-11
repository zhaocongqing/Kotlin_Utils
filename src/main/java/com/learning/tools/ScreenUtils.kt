package com.learning.tools

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.Window

/**
 * 屏幕工具类
 * Create by Qing at 2024/7/9 10:57
 */
object ScreenUtils {

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**
     * 获取ContentView宽度
     */
    fun getContentViewWidth(activity: Activity): Int {
        val contentView = activity.findViewById<View>(Window.ID_ANDROID_CONTENT)
        return contentView.width
    }

    /**
     * 获取ContentView高度
     */
    fun getContentViewHeight(activity: Activity): Int {
        val contentView = activity.findViewById<View>(Window.ID_ANDROID_CONTENT)
        return contentView.height
    }

    /**
     * dp转px
     */
    fun dp2px(context: Context, dp: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    /**
     * px转dp
     */
    fun px2dp(context: Context, px: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }
}