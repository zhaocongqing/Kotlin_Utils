package com.learning.tools.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * 跳转Activity
 * @param bundle 传递的参数
 * @param requestCode 请求码
 * @param block intent的配置
 * Create by Qing at 2024/6/26 09:12
 */
inline fun <reified T : Activity> Activity.startActivity(
    bundle: Bundle? = null,
    requestCode: Int = -1,
    block: (intent: Intent) -> Unit) {
    val intent = Intent(this, T::class.java)
    bundle?.let { intent.putExtras(bundle) }
    block.invoke(intent)
    startActivityForResult(intent, requestCode)
}