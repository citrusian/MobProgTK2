package com.example.mobprog_tk2.data

import android.util.Base64
import android.util.Log
import com.example.mobprog_tk2.CryptoManager
import com.example.mobprog_tk2.data.model.LoggedInUser
import java.io.IOException
import java.security.KeyStore

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

class LoginDataSource {
    private val cryptoManager = CryptoManager() // Instantiate CryptoManager

    // Simulated hashed username and password from credential server
    private val storedUsernameHash = CryptoManager.hashPassword("pengguna")
    private val storedPasswordHash = CryptoManager.hashPassword("masuk")

    fun login(username: String, password: String): Result<LoggedInUser> {
        Log.d("DEBUG", "LoginDataSource is processed")
        // Simulate authentication
        return try {
            val inputUsernameHash = CryptoManager.hashPassword(username)
            val inputPasswordHash = CryptoManager.hashPassword(password)

            if (CryptoManager.validatePassword(username, storedUsernameHash) &&
                CryptoManager.validatePassword(password, storedPasswordHash)) {
                // Authentication successful
                val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), username)
                Result.Success(fakeUser)
            } else {
                // Authentication failed
                Result.Error(IOException("Authentication failed"))
            }
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

//    private fun validateCredentials(encryptedUsername: ByteArray, encryptedPassword: ByteArray): Boolean {
//        // Implement your logic to validate credentials, e.g., compare against stored data
//        // Decrypt stored encrypted credentials for comparison
//        val storedUsername = cryptoManager.decrypt(encryptedUsername)
//        val storedPassword = cryptoManager.decrypt(encryptedPassword)
//        Log.d("Decryption", "Decrypted Username: ${String(storedUsername)}")
//        Log.d("Decryption", "Decrypted Password: ${String(storedPassword)}")
//
//
//        return storedUsername.contentEquals("pengguna".encodeToByteArray()) &&
//                storedPassword.contentEquals("masuk".encodeToByteArray())
//    }

    fun logout() {
        // TODO: revoke authentication
    }
}

