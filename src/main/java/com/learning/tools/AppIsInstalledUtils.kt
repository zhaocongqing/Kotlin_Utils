package com.learning.tools

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

/**
 * 判断应用是否安装工具类
 * Create by Qing at 2024/8/19 13:19
 */
object AppIsInstalledUtils {

    const val WECHAT = "com.tencent.mm"
    const val FACEBOOK = "com.facebook.katana"
    const val INSTAGRAM = "com.instagram.android"
    const val WHATSAPP = "com.whatsapp"

    fun appIsInstall(context: Context, packageName: String): Boolean {
        if (packageName.isEmpty()) {
            return false
        }
        return queryPackageInstall(context, packageName)
    }


    /**
     * 通过签名判断是否安装
     *
     * PackageManager.GET_SIGNATURES 签名
     * PackageManager.GET_SIGNING_CERTIFICATES 签名证书
     */
    private fun queryPackageInstall(context: Context, packageName: String): Boolean {
        if (packageName.isEmpty()) {
            return false
        }
        var isInstall = false
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                context.packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            } else {
                context.packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            }
            isInstall = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return isInstall
    }
}