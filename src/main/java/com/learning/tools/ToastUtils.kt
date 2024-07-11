package com.learning.tools

import android.app.Activity
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

/**
 * Create by zcq at 2024/6/25 16:58
 */
object ToastUtils {

    private var mSnackBar: Snackbar? = null

    /**
     * 显示Toast
     * @param activity 上下文
     * @param msg 消息
     * @param isLong 是否显示较长时间
     */
    @JvmOverloads
    fun showToast(activity: Activity, msg: String, isLong: Boolean = false) {
        if (msg.isEmpty()) {
            return
        }
        if (mSnackBar != null) {
            mSnackBar!!.dismiss()
        }
        mSnackBar = Snackbar.make(
            activity,
            activity.window.decorView,
            msg,
            if(isLong) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT
        )
        val snackBarView = mSnackBar!!.view
        snackBarView.elevation = 0f
        snackBarView.setPadding(0, 0, 0, 0)
        // 设置SnackBar背景颜色
        snackBarView.setBackgroundResource(R.drawable.tools_toast_bg)
        //设置SnackBar文字大小
        val tvSnackBarText  = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        tvSnackBarText.textSize = 14f
        val layoutParams = snackBarView.layoutParams
        layoutParams.width = FrameLayout.LayoutParams.WRAP_CONTENT
        layoutParams.height = FrameLayout.LayoutParams.WRAP_CONTENT
        // 重新设置属性参数
        val cl = FrameLayout.LayoutParams(layoutParams.width, layoutParams.height)
        cl.leftMargin = 100
        cl.rightMargin = 100
        // 设置显示位置在上居中
        cl.gravity = Gravity.CENTER
        snackBarView.layoutParams = cl
        mSnackBar!!.animationMode = Snackbar.ANIMATION_MODE_FADE
        mSnackBar!!.show()
    }

    @JvmOverloads
    fun showToast(activity: Activity, resId: Int, isLong: Boolean = false) {
        showToast(activity, activity.getString(resId), isLong)
    }
}