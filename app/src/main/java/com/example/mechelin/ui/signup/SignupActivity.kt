package com.example.mechelin.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : AppCompatActivity(),PhoneConfirmView {
    lateinit var binding: ActivitySignupBinding
    var checkphone : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupRequestNumTv.setOnClickListener {
            phonecofirm()
        }
        binding.signupCheckNumTv.setOnClickListener {
            checkconfirm(binding.signupInputVar.text.toString())
        }

        binding.signupBtnDeactivate.setOnClickListener {

            if(binding.signupInputPassword.text.toString() != binding.signupInputPasswordCheck.text.toString()){
                binding.signupPasswordNotsame.visibility= View.VISIBLE
            }else {
                if (checkphone) {
                    val nickName = binding.signupInputNickname.getText().toString()
                    val phoneNumber = makephoneNumber()
                    val password = binding.signupInputPassword.getText().toString()
                    val email = binding.signupInputEmail.getText().toString()

                    var retrofit = Retrofit.Builder()
                        .baseUrl("https://dev.mechelin.shop")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    var signupService: SignupService = retrofit.create(SignupService::class.java)
                    var signup: SignupResult? = null
                    var signupData: SignupData = SignupData(nickName, email, password, phoneNumber)

                    signupService.requestSignup(signupData)
                        .enqueue(object : Callback<SignupResult> {
                            override fun onResponse(
                                call: Call<SignupResult>,
                                response: Response<SignupResult>
                            ) {
                                signup = response.body()
                                Log.d("Signup", signup.toString())
                                Log.d("Signup", "msg: " + signup?.message)
                                Log.d("Signup", "code: " + signup?.code)
                                Log.d("Signup", "jwt: " + signup?.result?.jwt)
                                Log.d("Signup", "userIdx: " + signup?.result?.userIdx)

                                var jwtToken = signup?.result?.jwt
                                var userIdx = signup?.result?.userIdx

                                Toast.makeText(
                                    getApplicationContext(),
                                    "회원가입이 완료되었습니다",
                                    Toast.LENGTH_LONG
                                )
                                    .show()

                                val sharedUser = getSharedPreferences("User", 0)
                                val editor = sharedUser.edit()

                                editor.putString("jwtToken", jwtToken)
                                editor.putInt("userIdx", userIdx!!)
                                editor.apply()
                            }

                            override fun onFailure(call: Call<SignupResult>, t: Throwable) {
                                Log.d("Signup", "msg: " + signup?.message)
                                Log.d("Signup", "code: " + signup?.code)
                                Toast.makeText(
                                    getApplicationContext(),
                                    "회원가입이 실패했습니다",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        })
                }
                else{
                    AlertDialog.Builder(this)
                        .setMessage("본인 인증을 진행해주세요")
                        .create()
                        .show()
                }
            }
        }
    }
    fun makephoneNumber():String{
        val p1 = binding.signupInputPhone1.text.toString()
        val p2 = binding.signupInputPhone2.text.toString()
        val p3 = binding.signupInputPhone3.text.toString()
        Log.d("MAKEPHONE",p1+p2+p3)
        return p1+p2+p3
    }

    //인증번호 보내는 메소드
    fun phonecofirm(){
        PhoneConfirmService(this).requestconfirm(makephoneNumber())
    }

    //인증번호 확인
    fun checkconfirm(num:String){
        val phoneNum = makephoneNumber()
        Log.d("CHECKCONFIRM",phoneNum)
        PhoneConfirmService(this).checkNum(phoneNum,num)
    }

    override fun onconfirmrequestSuccess(response: PhoneResult) {
        Log.d("SEND_MESSEAGE", response.toString())
        when (response.code) {
            1000 -> {
                Log.d("sendmessage", response.result.toString())
                timerstart()
            }
            2072, 2073 -> {
                AlertDialog.Builder(this)
                    .setMessage(response.message)
                    .create()
                    .show()
            }
        }
    }

    override fun onconfirmrequestFailure(message: String) {
        Log.e("REQUESTNUM/API-ERROR", message)
    }

    override fun onCheckNumSuccess(response: ConfrimNumResult) {
        Log.d("CHECKNUM", response.toString())
        when (response.code) {
            1000 -> {
                Log.d("checknumsuccess", response.result.toString())
                checkphone=true
                binding.signupInfoCheckphneTv.visibility=View.VISIBLE
                binding.signupInfoCheckphneTv.text = response.message
            }
            2070, 2071,2073,2074 -> {
                binding.signupInfoCheckphneTv.visibility=View.VISIBLE
                binding.signupInfoCheckphneTv.text = response.message
            }
        }
    }

    override fun onCheckNumFailure(message: String) {
        Log.e("CHECKNUM/API-ERROR", message)
    }


    //타이머
    private fun timerstart() {
        var time = 18000
        val timerTask = kotlin.concurrent.timer(period = 10) {	// timer() 호출
            time--	// period=10, 0.01초마다 time를 1씩 증가
            val totalsec = time / 100	// time/100, 나눗셈의 몫 (초 부분)
            val min = totalsec / 60	// time%100, 나눗셈의 나머지 (밀리초 부분)
            val sec= totalsec%60

            // UI조작을 위한 메서드
            runOnUiThread {
                binding.signupTimerTv.visibility=View.VISIBLE
                binding.signupTimerTv.text = "$min"+":"+"$sec"	// TextView 세팅
            }
        }
    }
}
