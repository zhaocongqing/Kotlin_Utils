package com.learning.tools

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * 网络工具类
 * Create by Qing at 2024/7/23 13:54
 */
object NetworkUtils {

    /**
     * 网络是否可用
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    /**
     * 获取网络类型
     * @param context 上下文
     */
    fun getNetworkType(context: Context): String {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return "No Internet Connection"

        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        capabilities?.let {
            when {
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return "WiFi"
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return "Cellular"
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return "Ethernet"
                it.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> return "Bluetooth"
                else -> return "Unknown"
            }
        }
        return "No Internet Connection"
    }
}