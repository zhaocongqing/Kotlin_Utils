package com.learning.tools

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

/**
 * 剪贴板工具类
 * Create by Qing at 2024/8/20 10:53
 */
object ClipboardUtils {

    /**
     * 复制文本到剪贴板
     */
    @JvmStatic
    fun copyTextToClipboard(context: Context, text: String) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.setPrimaryClip(ClipData.newPlainText(null, text))
    }

    /**
     * 从剪贴板获取文本
     */
    @JvmStatic
    fun getTextFromClipboard(context: Context): String {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = clipboardManager.primaryClip
        return if (clipData != null && clipData.itemCount > 0) {
            clipData.getItemAt(0).text.toString()
        } else {
            ""
        }
    }
}