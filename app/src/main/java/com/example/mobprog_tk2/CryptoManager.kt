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

    // Generate Public Key
    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val encryptCypher = Cipher.getInstance(TRANSFORMATION).apply {
        Log.d("DEBUG", "encryptCypher is processed")
        init(Cipher.ENCRYPT_MODE, getKey())
    }

    private fun getDecryptCipherForIv(iv: ByteArray): Cipher{
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    private fun getKey(): SecretKey{
        Log.d("DEBUG", "getKey is processed")
        val existingKey = keyStore.getEntry(keystoreAlias,null) as? KeyStore.SecretKeyEntry
        Log.d("DEBUG", "existingKey is processed")
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey{
        return KeyGenerator.getInstance(ALGORITM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    keystoreAlias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

//    Test to show encrypted data in output text
//    fun encrypt(bytes: ByteArray, outputStream: OutputStream): ByteArray{
//        val encryptedBytes = encryptCypher.doFinal(bytes)
//        outputStream.use {
//            it.write(encryptCypher.iv.size)
//            it.write(encryptCypher.iv)
//            it.write(encryptedBytes.size)
//            it.write(encryptedBytes)
//        }
//        return encryptedBytes
//    }

//    fun encrypt(bytes: ByteArray): ByteArray{
//        Log.d("DEBUG", "encrypt is processed")
//        val encryptedBytes = encryptCypher.doFinal(bytes)
//        Log.d("DEBUG", encryptedBytes.toString())
//        return encryptedBytes
//    }

    // modify to accept multiple instance
    fun encrypt(bytes: ByteArray): ByteArray {
        val encryptCypher = Cipher.getInstance(TRANSFORMATION).apply {
            Log.d("DEBUG", "encryptCypher is processed")
            init(Cipher.ENCRYPT_MODE, getKey())
        }

        val encryptedBytes = encryptCypher.doFinal(bytes)
        Log.d("EncryptedData", encryptedBytes.toString())
        return encryptedBytes
    }


//    Test to show decrypted data in output text
//    fun decrypt(inputStream: InputStream): ByteArray{
//        return inputStream.use {
//            val ivSize = it.read()
//            val iv = ByteArray(ivSize)
//            it.read(iv)
//
//            val encryptedByteSize = it.read()
//            val encryptedBytes = ByteArray(encryptedByteSize)
//            it.read(encryptedBytes)
//
//            getDecryptCipherForIv(iv).doFinal(encryptedBytes)
//
//        }
//    }

    // modify to accept multiple instance
    fun decrypt(encryptedData: ByteArray): ByteArray {
        val decryptCypher = Cipher.getInstance(TRANSFORMATION).apply {
            Log.d("DEBUG", "decryptCypher is processed")
            init(Cipher.DECRYPT_MODE, getKey())
        }

        Log.d("DecryptedData", encryptedData.toString())
        return decryptCypher.doFinal(encryptedData)
    }

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

        private const val ALGORITM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITM/$BLOCK_MODE/$PADDING"
    }

}