package com.learning.tools

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.channels.FileChannel
import java.security.MessageDigest

/**
 * MD5加密工具类
 * Create by Qing at 2024/8/20 11:02
 */
object MD5 {

    private var hexDigits =
        charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

    fun getMD5(s: String): String? {
        return try {
            val md5 = MessageDigest.getInstance("MD5")
            val byteArray = s.toByteArray(charset("UTF-8"))
            val md5Bytes = md5.digest(byteArray)
            val hexValue = StringBuffer()
            for (i in md5Bytes.indices) {
                val value = md5Bytes[i].toInt() and 0xff
                if (value < 16) hexValue.append("0")
                hexValue.append(Integer.toHexString(value))
            }
            hexValue.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    @Throws(IOException::class)
    fun getFileMD5String(file: File): String {
        var inputStream: FileInputStream? = null
        try {
            val messageDigest = MessageDigest.getInstance("MD5")
            inputStream = FileInputStream(file)
            val ch = inputStream.channel
            val byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length())
            messageDigest.update(byteBuffer)
            return bufferToHex(messageDigest.digest())
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }
        return ""
    }

    @Throws(IOException::class)
    fun getFileMD5String(fileName: String): String {
        val f = File(fileName)
        return getFileMD5String(f)
    }

    private fun bufferToHex(bytes: ByteArray): String {
        return bufferToHex(bytes, 0, bytes.size)
    }

    private fun bufferToHex(bytes: ByteArray, m: Int, n: Int): String {
        val stringBuffer = StringBuffer(2 * n)
        val k = m + n
        for (l in m until k) {
            appendHexPair(bytes[l], stringBuffer)
        }
        return stringBuffer.toString()
    }

    private fun appendHexPair(bt: Byte, stringBuffer: StringBuffer) {
        val c0 = hexDigits[bt.toInt() and 0xf0 shr 4]
        val c1 = hexDigits[bt.toInt() and 0xf]
        stringBuffer.append(c0)
        stringBuffer.append(c1)
    }
}