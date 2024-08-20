package com.learning.tools

/**
 * Create by Qing at 2024/8/20 11:22
 */
object StringUtils {

    /**
     * 判断字符串是否是数字含有小数点
     */
    fun isNumeric(s: String): Boolean {
        return s.matches("^[0-9]+(.[0-9]+)?$".toRegex())
    }

    /**
     * 判断字符串是否是数字
     */
    fun isNumber(s: String): Boolean {
        return s.matches("^[0-9]+$".toRegex())
    }

    /**
     * 判断字符串是否是纯汉字
     */
    fun isChinese(s: String): Boolean {
        return s.matches("^[\\u4e00-\\u9fa5]+$".toRegex())
    }

    /**
     * 判断字符串是否纯字母
     */
    fun isLetter(s: String): Boolean {
        return s.matches("^[a-zA-Z]+$".toRegex())
    }
}