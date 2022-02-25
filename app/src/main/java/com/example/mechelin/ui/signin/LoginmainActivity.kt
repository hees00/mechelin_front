package com.example.mechelin.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.databinding.ActivityLoginBinding
import com.example.mechelin.databinding.ActivityLoginMainBinding
import com.example.mechelin.ui.savestore.WritingActivity
import com.example.mechelin.ui.signup.SignUpTermActivity
import com.example.mechelin.ui.signup.SignupActivity

class LoginmainActivity:AppCompatActivity() {
    lateinit var binding: ActivityLoginMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginMainLoginBtnActivate.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.loginMainSignupBtnDeactivate.setOnClickListener{
            val intent = Intent(this, SignUpTermActivity::class.java)
            startActivity(intent)
        }


    }

}