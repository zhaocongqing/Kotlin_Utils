package com.learning.tools

import java.net.URLEncoder
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * DES加解密工具类
 * Create by Qing at 2024/8/20 10:59
 */
object DESUtils {

    private val iv1 = byteArrayOf(18, 52, 86, 120, -112, -85, -51, -17)
    private const val encryptKey = "PdNcUYRf"

    @Throws(Exception::class)
    fun decrypt(decryptString: String, decryptKey: String): String {
        val iv = IvParameterSpec(iv1)
        val key = SecretKeySpec(decryptKey.toByteArray(charset("UTF-8")), "DES")
        val cipher = Cipher.getInstance("DES/CBC/PKCS5Padding")
        cipher.init(2, key, iv)
        return String(cipher.doFinal(EncodeUtils.base64Decode(decryptString)))
    }

    fun encrypt(encryptString: String, encryptKey: String): String {
        return try {
            val iv = IvParameterSpec(iv1)
            val dks = DESKeySpec(encryptKey.toByteArray())
            val keyFactory = SecretKeyFactory.getInstance("DES")
            val key = keyFactory.generateSecret(dks)
            val cipher = Cipher.getInstance("DES/CBC/PKCS5Padding")
            cipher.init(1, key, iv)
            String(EncodeUtils.base64Encode(cipher.doFinal(encryptString.toByteArray(charset("UTF-8")))))
        } catch (var7: Exception) {
            encryptString
        }
    }

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val result = encrypt("4629", encryptKey)
        println(result)
        println(URLEncoder.encode(result))
        val org = decrypt(result, encryptKey)
        println(org)
    }
}