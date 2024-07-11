package com.learning.tools

/**
 * Create by Qing at 2024/7/9 11:03
 */
object RomUtils {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    private val ROM_HUAWEI = arrayOf("huawei")
    private val ROM_VIVO = arrayOf("vivo")
    private val ROM_XIAOMI = arrayOf("xiaomi")
    private val ROM_OPPO = arrayOf("oppo")
    private val ROM_LEECO = arrayOf("leeco", "letv")
    private val ROM_360 = arrayOf("360", "qiku")
    private val ROM_ZTE = arrayOf("zte")
    private val ROM_ONEPLUS = arrayOf("oneplus")
    private val ROM_NUBIA = arrayOf("nubia")
    private val ROM_COOLPAD = arrayOf("coolpad", "yulong")
    private val ROM_LG = arrayOf("lg", "lge")
    private val ROM_GOOGLE = arrayOf("google")
    private val ROM_SAMSUNG = arrayOf("samsung")
    private val ROM_MEIZU = arrayOf("meizu")
    private val ROM_LENOVO = arrayOf("lenovo")
    private val ROM_SMARTISAN = arrayOf("smartisan", "deltainno")
    private val ROM_HTC = arrayOf("htc")
    private val ROM_SONY = arrayOf("sony")
    private val ROM_GIONEE = arrayOf("gionee", "amigo")
    private val ROM_MOTOROLA = arrayOf("motorola")

    /**
     * 是否华为
     */
    fun isHuawei(): Boolean {
        return isRightRom(ROM_HUAWEI)
    }

    /**
     * 是否Vivo
     */
    fun isVivo(): Boolean {
        return isRightRom(ROM_VIVO)
    }

    /**
     * 是否小米
     */
    fun isXiaomi(): Boolean {
        return isRightRom(ROM_XIAOMI)
    }

    /**
     * 是否Oppo
     */
    fun isOppo(): Boolean {
        return isRightRom(ROM_OPPO)
    }

    /**
     * 是否魅族
     */
    fun isMeizu(): Boolean {
        return isRightRom(ROM_MEIZU)
    }

    /**
     * 是否三星
     */
    fun isSamsung(): Boolean {
        return isRightRom(ROM_SAMSUNG)
    }

    /**
     * 是否乐视
     */
    fun isLeeco(): Boolean {
        return isRightRom(ROM_LEECO)
    }

    /**
     * 是否联想
     */
    fun isLenovo(): Boolean {
        return isRightRom(ROM_LENOVO)
    }

    /**
     * 是否中兴
     */
    fun isZte(): Boolean {
        return isRightRom(ROM_ZTE)
    }

    /**
     * 是否酷派
     */
    fun isCoolpad(): Boolean {
        return isRightRom(ROM_COOLPAD)
    }

    /**
     * 是否LG
     */
    fun isLg(): Boolean {
        return isRightRom(ROM_LG)
    }

    /**
     * 是否HTC
     */
    fun isHtc(): Boolean {
        return isRightRom(ROM_HTC)
    }

    /**
     * 是否索尼
     */
    fun isSony(): Boolean {
        return isRightRom(ROM_SONY)
    }

    /**
     * 是否努比亚
     */
    fun isNubia(): Boolean {
        return isRightRom(ROM_NUBIA)
    }

    /**
     * 是否一加
     */
    fun isOneplus(): Boolean {
        return isRightRom(ROM_ONEPLUS)
    }

    /**
     * 是否360
     */
    fun is360(): Boolean {
        return isRightRom(ROM_360)
    }

    /**
     * 是否Google
     */
    fun isGoogle(): Boolean {
        return isRightRom(ROM_GOOGLE)
    }

    /**
     * 是否摩托罗拉
     */
    fun isMotorola(): Boolean {
        return isRightRom(ROM_MOTOROLA)
    }

    /**
     * 是否锤子
     */
    fun isSmartisan(): Boolean {
        return isRightRom(ROM_SMARTISAN)
    }

    /**
     * 是否金立
     */
    fun isGionee(): Boolean {
        return isRightRom(ROM_GIONEE)
    }

    private fun isRightRom(vararg names: Array<String>): Boolean {
        val brand = getBrand()
        val manufacturer = getManufacturer()
        for (name in names) {
            for (keyword in name) {
                if (brand.contains(keyword,ignoreCase = true)
                    || manufacturer.contains(keyword,ignoreCase = true)) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 获取系统品牌
     */
    private fun getBrand(): String {
        return android.os.Build.BRAND
    }

    /**
     * 获取系统制造商
     */
    private fun getManufacturer(): String {
        return android.os.Build.MANUFACTURER
    }
}