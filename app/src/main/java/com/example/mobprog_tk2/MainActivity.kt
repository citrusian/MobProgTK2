package com.example.mobprog_tk2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mobprog_tk2.ui.login.LoginFragment
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyPairGenerator
import java.security.KeyStore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MobProgTK2)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Move logout listener here to reduce clutter at GalleryFragment
        val logoutButton = findViewById<Button>(R.id.LogoutButton)
        logoutButton.setOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Redirect to LoginFragment
        val targetFragment = LoginFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, targetFragment)
            .commit()
    }
}