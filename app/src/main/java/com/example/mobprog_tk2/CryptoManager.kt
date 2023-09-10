package com.example.mobprog_tk2

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import com.example.mobprog_tk2.Constants.keystoreAlias
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.OutputStream
import java.security.KeyStore
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class CryptoManager {
    // Use simpler method eg:SHA-256, because i cant get BLOCK_MODE_CBC & AES to work
    // it means the connection no encrypted only hashed
    companion object{
        fun hashPassword(password: String): ByteArray {
            val md = MessageDigest.getInstance("SHA-256")
            return md.digest(password.toByteArray())
        }

        fun validatePassword(inputPassword: String, storedHash: ByteArray): Boolean {
            val inputHash = hashPassword(inputPassword)
            return inputHash.contentEquals(storedHash)
        }
    }
}