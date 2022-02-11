package com.example.mechelin.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.databinding.ActivitySplashBinding
import com.example.mechelin.ui.main.MainActivity
import com.example.mechelin.ui.main.SearchActivity
import com.example.mechelin.ui.signin.LoginActivity
import com.example.mechelin.ui.signin.LoginResult
import com.example.mechelin.ui.signin.LoginmainActivity
import com.example.mechelin.ui.signup.SignupActivity

class SplashActivity:AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginmainActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}