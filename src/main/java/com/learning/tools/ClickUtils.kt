package com.learning.tools

/**
 * 防抖动点击工具类
 * Create by Qing at 2024/8/20 10:57
 */
object ClickUtils {

    private var lastClickTime = 0L

    /**
     * 是否是快速点击
     */
    @JvmStatic
    fun isFastClick(long: Long? = null): Boolean {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < (long ?: 500)) {
            return true
        }
        lastClickTime = currentTime
        return false
    }
}