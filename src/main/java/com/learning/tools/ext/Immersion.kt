package com.learning.tools.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import kotlin.math.ceil

/**
 * 状态栏工具类
 * Create by Qing at 2024/7/9 09:41
 */

/**
 * 透明色值
 */
private const val COLOR_TRANSPARENT = 0

/**
 * 设置状态栏颜色
 * @param color 颜色值
 */
fun Activity.statusBarColor(@ColorInt color: Int) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window?.statusBarColor = color
}

/**
 * 使用视图的背景色作为状态栏颜色
 * @param view 提取该View的背景颜色设置为状态栏颜色, 如果该View没有背景颜色则该函数调用无效
 * @param darkMode 是否显示暗色状态栏文字颜色
 */
@JvmOverloads
fun Activity.immersive(view: View, darkMode: Boolean? = null) {
    val background = view.background
    if (background is ColorDrawable) {
        immersive(background.color, darkMode)
    }
}

/**
 * 设置透明状态栏或者状态栏颜色, 此函数会导致状态栏覆盖界面,
 * 如果不希望被状态栏遮挡Toolbar请再调用[statusPadding]设置视图的paddingTop 或者 [statusMargin]设置视图的marginTop为状态栏高度
 * 如果不指定状态栏颜色则会应用透明状态栏(全屏属性), 会导致键盘遮挡输入框
 * @param color 状态栏颜色, 不指定则为透明状态栏
 * @param darkMode 是否显示暗色状态栏文字颜色
 */
@SuppressLint("ObsoleteSdkInt")
@JvmOverloads
fun Activity.immersive(@ColorInt color: Int = COLOR_TRANSPARENT, darkMode: Boolean? = null) {
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
            when (color) {
                COLOR_TRANSPARENT -> {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                    window.statusBarColor = COLOR_TRANSPARENT
                }
                else -> {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.statusBarColor = color
                }
            }
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            if (color != COLOR_TRANSPARENT) {
                setTranslucentView(window.decorView as ViewGroup, color)
            }
        }
    }
    if (darkMode != null) {
        darkMode(darkMode)
    }
}

/**
 * 退出沉浸式状态栏并恢复默认状态栏颜色
 * @param black 是否显示黑色状态栏白色文字(不恢复状态栏颜色)
 */
@JvmOverloads
@Suppress("DEPRECATION")
fun Activity.immersiveExit(black: Boolean = false) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                and View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        // 恢复默认状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (black) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            } else {
                val typedArray = obtainStyledAttributes(intArrayOf(android.R.attr.statusBarColor))
                window.statusBarColor = typedArray.getColor(0, 0)
                typedArray.recycle()
            }
        }
    }
}

/**
 * 获取颜色资源值来设置状态栏
 */
@JvmOverloads
fun Activity.immersiveRes(@ColorRes color: Int, darkMode: Boolean? = null) =
    immersive(ContextCompat.getColor(this, color), darkMode)

/**
 * 开关状态栏暗色模式, 并不会透明状态栏, 只是单纯的状态栏文字变暗色调.
 *
 * @param darkMode 状态栏文字是否为暗色
 */
@JvmOverloads
@Suppress("DEPRECATION")
fun Activity.darkMode(darkMode: Boolean = true) {
    var systemUiVisibility = window.decorView.systemUiVisibility
    systemUiVisibility = if (darkMode) {
        systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
    window.decorView.systemUiVisibility = systemUiVisibility
}

/**
 * 增加View的paddingTop, 增加高度为状态栏高度, 用于防止视图和状态栏重叠
 * 如果是RelativeLayout设置padding值会导致centerInParent等属性无法正常显示
 * @param remove 如果默认paddingTop大于状态栏高度则添加无效, 如果小于状态栏高度则无法删除
 */
@JvmOverloads
fun View.statusPadding(remove: Boolean = false) {
    if (this is RelativeLayout) {
        throw UnsupportedOperationException("Unsupported set statusPadding for RelativeLayout")
    }
    val statusBarHeight = getStatusBarHeight()
    val lp = layoutParams
    if (lp != null && lp.height > 0) {
        lp.height += statusBarHeight //增高
    }
    if (remove) {
        if (paddingTop < statusBarHeight) return
        setPadding(
            paddingLeft, paddingTop - statusBarHeight,
            paddingRight, paddingBottom
        )
    } else {
        if (paddingTop >= statusBarHeight) return
        setPadding(
            paddingLeft, paddingTop + statusBarHeight,
            paddingRight, paddingBottom
        )
    }
}

/**
 * 增加View的marginTop值, 增加高度为状态栏高度, 用于防止视图和状态栏重叠
 * @param remove 如果默认marginTop大于状态栏高度则添加无效, 如果小于状态栏高度则无法删除
 */
@JvmOverloads
fun View.statusMargin(remove: Boolean = false) {
    val statusBarHeight = getStatusBarHeight()
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    if (remove) {
        if (lp.topMargin < statusBarHeight) return
        lp.topMargin -= statusBarHeight
        layoutParams = lp
    } else {
        if (lp.topMargin >= statusBarHeight) return
        lp.topMargin += statusBarHeight
        layoutParams = lp
    }
}

/**
 * 创建假的透明栏
 */
private fun Context.setTranslucentView(container: ViewGroup, color: Int) {
    var simulateStatusBar: View? = container.findViewById(android.R.id.custom)
    if (simulateStatusBar == null && color != 0) {
        simulateStatusBar = View(container.context)
        simulateStatusBar.id = android.R.id.custom
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
        container.addView(simulateStatusBar, lp)
    }
    simulateStatusBar?.setBackgroundColor(color)
}

/**
 * 获取导航栏高度
 */
@SuppressLint("InternalInsetResource", "DiscouragedApi")
fun getNavigationBarHeight(context: Context): Int {
    val resources = context.resources
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        resources.getDimensionPixelSize(resourceId)
    } else {
        0
    }
}

/**
 * 获取状态栏高度
 */
@SuppressLint("InternalInsetResource", "DiscouragedApi", "ObsoleteSdkInt")
fun getStatusBarHeight(): Int {
    val resources = Resources.getSystem()
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        resources.getDimensionPixelSize(resourceId)
    } else {
        val defaultHeight: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            24
        } else {
            25
        }
        ceil((defaultHeight * resources.displayMetrics.density).toDouble()).toInt()
    }
}
