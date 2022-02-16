package com.example.mechelin.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.databinding.ActivitySignupBinding
import com.example.mechelin.databinding.ActivitySignupTermBinding
import com.example.mechelin.databinding.ActivityWritingBinding

class SignUpTermActivity: AppCompatActivity() {
    lateinit var binding: ActivitySignupTermBinding
    var checkterm1:Boolean =false
    var checkterm2:Boolean =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupTermBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.signupTermCheckAllDeactivate.setOnClickListener {
            if (checkterm1 && checkterm2){
                checkterm1=false
                checkterm2=false
            }
            else{
                checkterm1=true
                checkterm2=true
            }
            setbutton()
        }

        binding.signupTermCheckAllActivate.setOnClickListener {
            checkterm1=false
            checkterm2=false
            setbutton()
        }

        binding.signupTermCheck1Deactivate.setOnClickListener {
            checkterm1= !checkterm1
            setbutton()
        }
        binding.signupTermCheck1Activate.setOnClickListener {
            checkterm1= !checkterm1
            setbutton()
        }
        binding.signupTermCheck2Deactivate.setOnClickListener {
            checkterm2= !checkterm2
            setbutton()
        }
        binding.signupTermCheck2Activate.setOnClickListener {
            checkterm2= !checkterm2
            setbutton()
        }

        binding.signupTermAgreeBtnDeactivate.setOnClickListener{
            AlertDialog.Builder(this)
                .setMessage("이용약관 및 개인정보처리방침 동의 후 회원가입을 하실 수 있습니다")
                .create()
                .show()
        }
        binding.signupTermAgreeBtnActivate.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    fun setbutton(){
        if (checkterm1 && checkterm2){
            binding.signupTermCheckAllActivate.visibility=View.VISIBLE
            binding.signupTermAgreeBtnActivate.visibility=View.VISIBLE
            binding.signupTermAgreeBtnDeactivate.visibility=View.GONE
        }
        else{
            binding.signupTermCheckAllActivate.visibility=View.GONE
            binding.signupTermCheckAllDeactivate.visibility=View.VISIBLE
            binding.signupTermAgreeBtnActivate.visibility=View.GONE
            binding.signupTermAgreeBtnDeactivate.visibility=View.VISIBLE
        }
        if(checkterm1){
            binding.signupTermCheck1Activate.visibility=View.VISIBLE
        }
        else{
            binding.signupTermCheck1Activate.visibility=View.GONE
        }

        if(checkterm2){
            binding.signupTermCheck2Activate.visibility=View.VISIBLE
        }
        else{
            binding.signupTermCheck2Activate.visibility=View.GONE
        }
    }
}