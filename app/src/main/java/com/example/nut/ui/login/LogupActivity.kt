package com.example.nut.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nut.R

class LogupActivity : AppCompatActivity() {
    lateinit var mEdAccount: EditText
    lateinit var mEtAuthCode: EditText
    lateinit var mEtPsw: EditText
    lateinit var mEtPswCheck: EditText
    lateinit var mBtLogup: Button
    lateinit var mTextLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logup)

        mEdAccount = findViewById(R.id.logup_account)
        mEtAuthCode = findViewById(R.id.logup_auth_code)
        mEtPsw = findViewById(R.id.logup_psw)
        mEtPswCheck = findViewById(R.id.logup_check)
        mBtLogup = findViewById(R.id.logup_button)
        mTextLogin = findViewById(R.id.logup_login)

        initView()
    }

    private fun initView() {
        mTextLogin.isClickable = true
        mTextLogin.setOnClickListener { v ->
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        mBtLogup.setOnClickListener { v ->
            if (postAccountPsw()) {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun postAccountPsw(): Boolean {
        return true
    }
}